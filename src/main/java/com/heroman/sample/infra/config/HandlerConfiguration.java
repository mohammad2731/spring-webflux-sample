package com.heroman.sample.infra.config;

import com.heroman.sample.core.RequestHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.heroman.sample",
    includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = RequestHandler.class)
)
public class HandlerConfiguration {
}
