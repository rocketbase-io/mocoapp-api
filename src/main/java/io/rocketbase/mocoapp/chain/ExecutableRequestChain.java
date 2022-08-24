package io.rocketbase.mocoapp.chain;

import io.rocketbase.mocoapp.RequestContext;
import io.rocketbase.mocoapp.RestUriBuilder;
import io.rocketbase.mocoapp.model.PaginatedResult;

class ExecutableRequestChain extends RequestChain {

     private final RestUriBuilder uriBuilder;

     public ExecutableRequestChain(RequestChain parent, String path) {
         super(parent, path);
         this.uriBuilder = getContext().getUriBuilder();
         this.uriBuilder.appendPath(resolvePath());
     }

     public ExecutableRequestChain(RequestContext context, String path) {
         super(context, path);
         this.uriBuilder = getContext().getUriBuilder();
         this.uriBuilder.appendPath(resolvePath());
     }

     protected RestUriBuilder getUriBuilder() {
         return this.uriBuilder;
     }

}
