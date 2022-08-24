package io.rocketbase.mocoapp;

import io.rocketbase.mocoapp.model.PaginatedResult;
import io.rocketbase.mocoapp.model.Project;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by marten on 19.01.17.
 */
public class MocoappApiTest {

    protected MocoappApi getApi() {
        String apiToken = "api_token";
        String domain = "api_token";
        try {
            apiToken = System.getenv("MOCO_API_TOKEN");
            domain = System.getenv("MOCO_DOMAIN");
        } catch (Exception e) {
            System.err.println("System Env not set use defaults");
        }

        return new MocoappApiBuilder()
                .apiToken(apiToken)
                .domain(domain)
                .userAgent("java-test")
                .build();
    }


    @SneakyThrows
    @Test
    public void project() {
        MocoappApi mocoappApi = getApi();


        PaginatedResult<Project> projects = mocoappApi.project().fetch().get();

        Project project = mocoappApi.project().get(944939494);

        assertThat(projects, notNullValue());
    }

}
