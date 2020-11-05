package com.heroman.sample.infra.service.contact;

import com.heroman.sample.infra.service.RequestProcessor;
import com.heroman.sample.infra.service.contact.da.UpdateContactDa;
import com.heroman.sample.infra.service.contact.dto.CreateContactRequest;
import com.heroman.sample.infra.service.contact.dto.CreateContactResponse;
import com.heroman.sample.infra.service.contact.dto.SearchContactRequest;
import com.heroman.sample.infra.service.contact.dto.SearchContactResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final RequestProcessor requestProcessor;
    private final UpdateContactDa updateContactDa;
    private final TransactionTemplate transactionTemplate;
    private final String githubUrl;

    public ContactController(RequestProcessor requestProcessor,
                             UpdateContactDa updateContactDa,
                             TransactionTemplate transactionTemplate,
                             @Value("${github.api.repo-list.url}") String githubUrl) {
        this.requestProcessor = requestProcessor;
        this.updateContactDa = updateContactDa;
        this.transactionTemplate = transactionTemplate;
        this.githubUrl = githubUrl;
    }

    @PostMapping("/new")
    public CreateContactResponse createContact(@RequestBody CreateContactRequest request) {
        Mono<String> repos = WebClient.create()
                .get()
                .uri(String.format(githubUrl, request.getGithub()))
                .retrieve()
                .bodyToFlux(HashMap.class)
                .map(m -> (String) m.get("name"))
                .reduce((s, s2) -> String.join(",", s, s2));
        requestProcessor.execute(request);
        repos.subscribe(r -> transactionTemplate.execute(st -> updateContactDa.update(request.getUuid(), r)));
        return null;
    }

    @PostMapping("/search")
    public SearchContactResponse searchContact(@RequestBody SearchContactRequest request) {
        requestProcessor.execute(request);
        return request.getResponse();
    }


}
