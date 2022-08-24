package io.rocketbase.mocoapp;

import io.rocketbase.mocoapp.chain.ProjectChain;

public class MocoappApi {

    private final RequestContext context;

    MocoappApi(RequestContext context) {
        this.context = context;
    }

    public ProjectChain project() {
        return new ProjectChain(context);
    }

}
