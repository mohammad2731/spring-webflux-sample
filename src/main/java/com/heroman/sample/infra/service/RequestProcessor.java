package com.heroman.sample.infra.service;

import com.heroman.sample.core.Request;
import com.heroman.sample.core.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import javax.naming.OperationNotSupportedException;
import java.util.stream.Stream;

@Component
public class RequestProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(RequestProcessor.class);

    private final ApplicationContext context;
    private final TransactionTemplate transactionTemplate;

    public RequestProcessor(ApplicationContext context,
                            TransactionTemplate transactionTemplate) {
        this.context = context;
        this.transactionTemplate = transactionTemplate;
    }

    public void execute(Request request) {
        internalExecute(request);
    }

    private void internalExecute(Request request){
        transactionTemplate.execute(st -> {
            try {
                String handlerName = extractHandlerName(request);
                context.getBean(handlerName, RequestHandler.class).execute(request);
            }catch (Exception e){
                LOG.warn(e.getMessage(),e);
                st.setRollbackOnly();
            }
            return null;
        });

    }

    private String extractHandlerName(Request request) throws Exception {
        String[] type = context.getBeanNamesForType(
                ResolvableType.forClassWithGenerics(RequestHandler.class, extractRequestType(request)));
        return type[0];
    }

    private Class extractRequestType(Request request) throws Exception {
        return Stream.of(request.getClass().getInterfaces())
                .filter(c->c.isAssignableFrom(request.getClass()))
                .findFirst()
                .orElseThrow(OperationNotSupportedException::new);

    }
}
