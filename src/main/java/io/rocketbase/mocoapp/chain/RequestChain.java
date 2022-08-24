package io.rocketbase.mocoapp.chain;

import io.rocketbase.mocoapp.RequestContext;

import java.util.Optional;

class RequestChain {

    private final RequestContext context;

    private final Optional<RequestChain> parent;

    private final String path;

    public RequestChain(RequestContext context, String path) {
        this.context = context;
        this.path = path;
        this.parent = Optional.empty();
    }

    public RequestChain(RequestChain parent, String path) {
        this.context = parent.getContext();
        this.parent = Optional.of(parent);
        this.path = path;
    }

    protected String resolvePath() {
        return ((parent.isPresent()) ? parent.get().resolvePath() : "") + path;
    }

    protected RequestContext getContext() {
        return context;
    }
}
