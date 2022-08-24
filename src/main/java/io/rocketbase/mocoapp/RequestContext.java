package io.rocketbase.mocoapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.rocketbase.mocoapp.model.PaginatedResult;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class RequestContext {

    private static long lastCall;
    private final MocoappApiBuilder apiBuilder;
    private HttpClient httpClient;
    private ClientHttpRequestFactory requestFactory;
    private RestTemplate restTemplate;
    private long throttlePeriod = 1010;

    RequestContext(MocoappApiBuilder apiBuilder) {
        this.apiBuilder = apiBuilder;

        this.httpClient = HttpClientBuilder.create().build();
        this.requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        restTemplate = new RestTemplate(requestFactory);
        restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter(getObjectMapper())));
    }


    protected ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        return mapper;
    }

    public RestUriBuilder getUriBuilder() {
        return new RestUriBuilder().protocol("https")
                .host((apiBuilder.getDomainProvider() != null ? apiBuilder.getDomainProvider().getDomain() : apiBuilder.getDomain()) + "." + apiBuilder.getHost())
                .path("/api/v1");
    }

    public synchronized <E> E execute(RestUriBuilder uriBuilder, HttpMethod method, ParameterizedTypeReference<E> entityClass) {
        return execute(uriBuilder, method, null, entityClass);
    }

    public synchronized <E> E execute(RestUriBuilder uriBuilder, HttpMethod method, E body, ParameterizedTypeReference<E> entityClass) {
        return executeWithResponse(uriBuilder, method, body, entityClass).getBody();
    }

    protected synchronized <E> ResponseEntity<E> executeWithResponse(RestUriBuilder uriBuilder, HttpMethod method, E body, ParameterizedTypeReference<E> entityClass) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Token token=" + (apiBuilder.getThrottleProvider() != null ? apiBuilder.getApiTokenProvider().getApiToken() : apiBuilder.getApiToken()));

        checkThrottlePeriod();

        HttpEntity<E> httpEntity = new HttpEntity<E>(body, headers);
        ResponseEntity<E> response = restTemplate.exchange(uriBuilder.build(), method, httpEntity, entityClass);
        lastCall = System.currentTimeMillis();
        if (apiBuilder.throttleProviderPresent()) {
            apiBuilder.getThrottleProvider().apiCalled();
        }

        return response;
    }

    public synchronized <E> PaginatedResult<E> executePaged(RestUriBuilder uriBuilder, HttpMethod method, List<E> body, ParameterizedTypeReference<List<E>> entityClass) {
        ResponseEntity<List<E>> response = executeWithResponse(uriBuilder, method, body, entityClass);

        PaginatedResult<E> result = new PaginatedResult<>();
        String page = response.getHeaders().getFirst("X-Page");
        String perPage = response.getHeaders().getFirst("X-Per-Page");
        String total = response.getHeaders().getFirst("X-Total");
        if (page != null && perPage != null && total != null && page.matches("[0-9]+") && perPage.matches("[0-9]+") && total.matches("[0-9]+")) {
            result.setPage(Integer.parseInt(page));
            result.setPerPage(Integer.parseInt(perPage));
            result.setTotal(Long.parseLong(total));
            result.setContent(response.getBody());
        } else {
            return null;
        }
        return result;
    }

    protected synchronized void checkThrottlePeriod() {
        if (apiBuilder.throttleProviderPresent()) {
            waitFuturePassed(apiBuilder.getThrottleProvider().getNextCallAllowed());
        } else {
            waitFuturePassed(lastCall + throttlePeriod);
        }
    }

    protected synchronized void waitFuturePassed(long future) {
        while (future > System.currentTimeMillis()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // ignore, except to propagate
                Thread.currentThread().interrupt();
            }
        }
    }

}
