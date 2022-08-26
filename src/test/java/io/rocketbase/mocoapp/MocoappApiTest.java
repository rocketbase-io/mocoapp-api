package io.rocketbase.mocoapp;

import io.rocketbase.mocoapp.model.Activity;
import io.rocketbase.mocoapp.model.PaginatedResult;
import io.rocketbase.mocoapp.model.Project;
import io.rocketbase.mocoapp.model.Task;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

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

        List<Task> tasks = mocoappApi.project().getTasks(944939494);

        assertThat(projects, notNullValue());
        assertThat(project, notNullValue());
        assertThat(tasks, notNullValue());
    }


    @SneakyThrows
    @Test
    public void activites() {
        MocoappApi mocoappApi = getApi();

        PaginatedResult<Activity> activities = mocoappApi.activity().fetch().perPage(2).page(1).from(LocalDate.of(2022, 7, 1)).to(LocalDate.now()).get();

        assertThat(activities, notNullValue());
    }

}
