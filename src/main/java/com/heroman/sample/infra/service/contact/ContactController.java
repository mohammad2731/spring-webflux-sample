package com.heroman.sample.infra.service.contact;

import com.heroman.sample.infra.service.RequestProcessor;
import com.heroman.sample.infra.service.contact.dto.CreateContactRequest;
import com.heroman.sample.infra.service.contact.dto.CreateContactResponse;
import com.heroman.sample.infra.service.contact.dto.SearchContactRequest;
import com.heroman.sample.infra.service.contact.dto.SearchContactResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final RequestProcessor requestProcessor;

    public ContactController(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @PostMapping("/new")
    public CreateContactResponse createContact(@RequestBody CreateContactRequest request) {
        requestProcessor.execute(request);
        return null;
    }

    @PostMapping("/search")
    public SearchContactResponse searchContact(@RequestBody SearchContactRequest request) {
        requestProcessor.execute(request);
        return request.getResponse();
    }


}
