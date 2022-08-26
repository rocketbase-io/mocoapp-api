package io.rocketbase.mocoapp.chain;

import io.rocketbase.mocoapp.RequestContext;
import io.rocketbase.mocoapp.model.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ActivityChain {

    private final RequestContext context;

    public Activity get(long id) {
        return new Get(context).get(id);
    }

    protected static class Get extends ExecutableRequestChain {
        private static final ParameterizedTypeReference<Activity> TYPE_REFERENCE = new ParameterizedTypeReference<Activity>() {
        };

        public Get(RequestContext context) {
            super(context, "/activities");
        }

        @SneakyThrows
        public Activity get(long id) {
            return getContext().execute(getUriBuilder(), HttpMethod.GET, TYPE_REFERENCE);
        }
    }

    public Fetch fetch() {
        return new Fetch(context);
    }

    public static class Fetch extends ExecutableRequestChain {
        private static final ParameterizedTypeReference<List<Activity>> TYPE_REFERENCE = new ParameterizedTypeReference<List<Activity>>() {
        };

        public Fetch(RequestContext context) {
            super(context, "/activities");
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
        public Fetch perPage(int perPage) {
            super.getUriBuilder()
                    .addParameter("per_page", perPage);
            return this;
        }

        public Fetch from(LocalDate from) {
            if (from != null) {
                super.getUriBuilder()
                        .addParameter("from", from.toString());
            }
            return this;
        }

        public Fetch to(LocalDate to) {
            if (to != null) {
                super.getUriBuilder()
                        .addParameter("to", to.toString());
            }
            return this;
        }

        public Fetch userId(Long userId) {
            super.getUriBuilder()
                    .addParameter("user_id", userId);
            return this;
        }

        public Fetch projectId(Long projectId) {
            super.getUriBuilder()
                    .addParameter("project_id", projectId);
            return this;
        }

        public Fetch taskId(Long taskId) {
            super.getUriBuilder()
                    .addParameter("task_id", taskId);
            return this;
        }

        public Fetch companyId(Long companyId) {
            super.getUriBuilder()
                    .addParameter("company_id", companyId);
            return this;
        }

        public Fetch term(String term) {
            super.getUriBuilder()
                    .addParameter("term", term);
            return this;
        }

        @SneakyThrows
        public PaginatedResult<Activity> get() {
            return getContext().executePaged(getUriBuilder(), HttpMethod.GET, null, TYPE_REFERENCE);
        }
    }


    public Activity create(ActivityCreate create) {
        return new Create(context).create(create);
    }

    protected static class Create extends ExecutableRequestChain {
        private static final ParameterizedTypeReference<Activity> TYPE_REFERENCE = new ParameterizedTypeReference<Activity>() {
        };

        public Create(RequestContext context) {
            super(context, "/activities");
        }

        @SneakyThrows
        public Activity create(ActivityCreate create) {
            return getContext().execute(getUriBuilder(), HttpMethod.POST, create, TYPE_REFERENCE);
        }
    }

    public void delete(long id) {
        new Delete(context).delete(id);
    }

    protected static class Delete extends ExecutableRequestChain {
        private static final ParameterizedTypeReference<Void> TYPE_REFERENCE = new ParameterizedTypeReference<Void>() {
        };

        public Delete(RequestContext context) {
            super(context, "/activities");
        }

        @SneakyThrows
        public void delete(long id) {
            getContext().execute(getUriBuilder().appendPath("/" + id), HttpMethod.DELETE, TYPE_REFERENCE);
        }
    }

}
