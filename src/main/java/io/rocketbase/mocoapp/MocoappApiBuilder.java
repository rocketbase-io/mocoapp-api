package io.rocketbase.mocoapp;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter(AccessLevel.PACKAGE)
public class MocoappApiBuilder {

    public static final String MOCOAPP_BASE = "mocoapp.com";

    private String host = MOCOAPP_BASE;

    private String domain = null;
    private String apiToken = null;
    private String userAgent = null;
    private ThrottleProvider throttleProvider;

    private ApiTokenProvider apiTokenProvider;

    private DomainProvider domainProvider;

    public MocoappApiBuilder apiToken(String apiToken) {
        this.apiToken = apiToken;
        return this;
    }

    public MocoappApiBuilder userAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public MocoappApiBuilder domain(String domain) {
        this.domain = domain;
        return this;
    }

    public MocoappApiBuilder host(String host) {
        this.host = host;
        return this;
    }

    public MocoappApiBuilder throttleProvider(ThrottleProvider throttleProvider) {
        this.throttleProvider = throttleProvider;
        return this;
    }

    public MocoappApiBuilder apiTokenProvider(ApiTokenProvider apiTokenProvider) {
        this.apiTokenProvider = apiTokenProvider;
        return this;
    }

    public MocoappApiBuilder domainProvider(DomainProvider domainProvider) {
        this.domainProvider = domainProvider;
        return this;
    }

    public MocoappApi build() {
        RequestContext context = new RequestContext(this);
        return new MocoappApi(context);
    }

    boolean throttleProviderPresent() {
        return throttleProvider != null;
    }


    public interface ApiTokenProvider {

        String getApiToken();

    }

    public interface DomainProvider {

        String getDomain();

    }

    /**
     * manually handle the throttling of the api<br>
     * useful when you work with JToggle and this api together with the same api-key
     */
    public interface ThrottleProvider {

        /**
         * @return milliseconds in future when a next call is allowed
         */
        long getNextCallAllowed();

        /**
         * will get called from {@link RequestContext} immediately after api is called in order to keep lastCall somewhere outside
         */
        void apiCalled();

    }

}
