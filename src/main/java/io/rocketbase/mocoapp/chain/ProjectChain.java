package io.rocketbase.mocoapp.chain;

import io.rocketbase.mocoapp.RequestContext;
import io.rocketbase.mocoapp.model.PaginatedResult;
import io.rocketbase.mocoapp.model.Project;
import io.rocketbase.mocoapp.model.Task;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProjectChain {

    private final RequestContext context;

    public Project get(long id) {
        return new Get(context).get(id);
    }

    protected static class Get extends ExecutableRequestChain {
        private static final ParameterizedTypeReference<Project> TYPE_REFERENCE = new ParameterizedTypeReference<Project>() {
        };

        public Get(RequestContext context) {
            super(context, "/projects");
        }

        @SneakyThrows
        public Project get(long id) {
            return getContext().execute(getUriBuilder(), HttpMethod.GET, TYPE_REFERENCE);
        }
    }

    public List<Task> getTasks(long id) {
        return new GetTasks(context).get(id);
    }

    protected static class GetTasks extends ExecutableRequestChain {
        private static final ParameterizedTypeReference<List<Task>> TYPE_REFERENCE = new ParameterizedTypeReference<List<Task>>() {
        };

        public GetTasks(RequestContext context) {
            super(context, "/projects");
        }

        @SneakyThrows
        public List<Task> get(long id) {
            getUriBuilder().appendPath("/" + id).appendPath("/tasks");
            return getContext().execute(getUriBuilder(), HttpMethod.GET, TYPE_REFERENCE);
        }
    }

    public Fetch fetch() {
        return new Fetch(context);
    }

    public static class Fetch extends ExecutableRequestChain {
        private static final ParameterizedTypeReference<List<Project>> TYPE_REFERENCE = new ParameterizedTypeReference<List<Project>>() {
        };

        public Fetch(RequestContext context) {
            super(context, "/projects");
        }

        /**
         * Pages are starting with 1.
         */
        public Fetch page(int page) {
            super.getUriBuilder()
                    .addParameter("page", page);
            return this;
        }

        /**
         * Default page size is set to 100 but can be increased
         */
        public Fetch pageSize(int pageSize) {
            super.getUriBuilder()
                    .addParameter("page_size", pageSize);
            return this;
        }

        public Fetch includeArchived(Boolean includeArchived) {
            super.getUriBuilder()
                    .addParameter("include_archived", includeArchived);
            return this;
        }

        public Fetch retainer(Boolean retainer) {
            super.getUriBuilder()
                    .addParameter("retainer", retainer);
            return this;
        }

        public Fetch includeCompany(Boolean includeCompany) {
            super.getUriBuilder()
                    .addParameter("include_company", includeCompany);
            return this;
        }

        public Fetch leaderId(Long leaderId) {
            super.getUriBuilder()
                    .addParameter("leader_id", leaderId);
            return this;
        }

        public Fetch companyId(Long companyId) {
            super.getUriBuilder()
                    .addParameter("company_id", companyId);
            return this;
        }

        public Fetch createdFrom(LocalDate createdFrom) {
            if (createdFrom != null) {
                super.getUriBuilder()
                        .addParameter("created_from", createdFrom.toString());
            }
            return this;
        }

        public Fetch createdTo(LocalDate createdTo) {
            if (createdTo != null) {
                super.getUriBuilder()
                        .addParameter("created_to", createdTo.toString());
            }
            return this;
        }

        public Fetch tags(String... tags) {
            if (tags != null) {
                super.getUriBuilder()
                        .addParameter("tags", Arrays.stream(tags).collect(Collectors.joining(",")));
            }
            return this;
        }

        public Fetch identifier(String identifier) {
            super.getUriBuilder()
                    .addParameter("identifier", identifier);
            return this;
        }

        public Fetch updatedFrom(LocalDate updatedFrom) {
            if (updatedFrom != null) {
                super.getUriBuilder()
                        .addParameter("updated_from", updatedFrom.toString());
            }
            return this;
        }

        public Fetch updatedTo(LocalDate updatedTo) {
            if (updatedTo != null) {
                super.getUriBuilder()
                        .addParameter("updated_to", updatedTo.toString());
            }
            return this;
        }

        @SneakyThrows
        public PaginatedResult<Project> get() {
            return getContext().executePaged(getUriBuilder(), HttpMethod.GET, null, TYPE_REFERENCE);
        }
    }

}
