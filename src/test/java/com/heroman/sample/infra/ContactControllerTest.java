package com.heroman.sample.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heroman.sample.infra.service.contact.dto.CreateContactRequest;
import com.heroman.sample.infra.service.contact.dto.CreateContactResponse;
import com.heroman.sample.infra.service.contact.dto.SearchContactRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.support.TransactionTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {

    @Autowired
    TransactionTemplate transactionTemplate;
    @Value("${server.port")
    private String port;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void clearStates() {
        transactionTemplate.execute(st -> jdbcTemplate.update("delete from contact"));
    }

    @Test
    void createContact() throws Exception {
        CreateContactRequest request = new CreateContactRequest()
                .setName("test")
                .setPhoneNumber("123456")
                .setEmail("user@test.com")
                .setOrganization("Organization")
                .setGithub("burrsutter");
        callCreateContact(request);

        CreateContactResponse result =
                jdbcTemplate.queryForObject("select * from contact", (resultSet, i) -> new CreateContactResponse()
                        .setName(resultSet.getString("name"))
                        .setPhoneNumber(resultSet.getString("phoneNumber"))
                        .setEmail(resultSet.getString("emailAddress"))
                        .setOrganization(resultSet.getString("organization"))
                        .setGithub(resultSet.getString("github"))
                );

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(request.getName());
        assertThat(result.getPhoneNumber()).isEqualTo(request.getPhoneNumber());
        assertThat(result.getEmail()).isEqualTo(request.getEmail());
        assertThat(result.getOrganization()).isEqualTo(request.getOrganization());
        assertThat(result.getGithub()).isEqualTo(request.getGithub());
    }

    private void callCreateContact(CreateContactRequest request) throws Exception {
        mockMvc.perform(post("/contact/new")
                .contentType(MediaType.valueOf("application/json"))
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void searchContacts() throws Exception {
        CreateContactRequest request = new CreateContactRequest()
                .setName("test")
                .setPhoneNumber("123456")
                .setEmail("user@test.com")
                .setOrganization("Organization")
                .setGithub("burrsutter");
        callCreateContact(request);
        SearchContactRequest searchRequest = new SearchContactRequest()
                .setName("test");
        mockMvc.perform(post("/contact/search")
                .contentType(MediaType.valueOf("application/json"))
                .content(objectMapper.writeValueAsString(searchRequest)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{\"date\":[{\"name\":\"test\",\"phoneNumber\":\"123456\"," +
                                "\"emailAddress\":\"user@test.com\",\"organization\":\"Organization\"," +
                                "\"github\":\"burrsutter\"}]}"));
    }
}
