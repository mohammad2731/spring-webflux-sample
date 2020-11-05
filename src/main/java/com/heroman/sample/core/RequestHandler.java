package com.heroman.sample.core;

public interface RequestHandler<R extends Request> {

    void execute(R request);
}
