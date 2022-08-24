package io.rocketbase.mocoapp.chain;

import io.rocketbase.mocoapp.RequestContext;
import io.rocketbase.mocoapp.model.PaginatedResult;
import io.rocketbase.mocoapp.model.Project;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

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
            getUriBuilder().appendPath("/" + id);
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
        public Fetch perPage(int perPage) {
            super.getUriBuilder()
                    .addParameter("perPage", perPage);
            return this;
        }

        @SneakyThrows
        public PaginatedResult<Project> get() {
            return getContext().executePaged(getUriBuilder(), HttpMethod.GET, null, TYPE_REFERENCE);
        }
    }

}
