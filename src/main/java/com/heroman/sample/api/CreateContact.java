package com.heroman.sample.api;

import com.heroman.sample.core.exception.InvalidNameException;
import com.heroman.sample.core.Request;
import com.heroman.sample.core.RequestHandler;
import com.heroman.sample.domain.Contact;
import com.heroman.sample.domain.EmailAddress;
import com.heroman.sample.domain.PhoneNumber;
import org.apache.commons.lang3.StringUtils;

public class CreateContact implements RequestHandler<CreateContact.CreateContactCommand> {
    private final CreateContactDa da;

    public CreateContact(CreateContactDa da) {
        this.da = da;
    }

    @Override
    public void execute(CreateContactCommand command) {
        if (StringUtils.isBlank(command.getName())) {
            throw new InvalidNameException();
        }
        da.persist(new Contact()
                .setName(command.getName())
                .setPhoneNumber(PhoneNumber.create(command.getPhoneNumber()))
                .setEmailAddress(EmailAddress.create(command.getEmail()))
                .setOrganization(command.getOrganization())
                .setGithub(command.getGithub())
        );
    }

    public interface CreateContactCommand extends Request {
        String getName();

        String getPhoneNumber();

        String getEmail();

        String getOrganization();

        String getGithub();
    }

    public interface CreateContactDa {
        Contact persist(Contact contact);
    }
}
