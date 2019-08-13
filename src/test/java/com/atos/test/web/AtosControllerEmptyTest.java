package com.atos.test.web;

import com.atos.test.Application;
import com.atos.test.dto.CustomerDTO;
import com.jayway.restassured.http.ContentType;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@ContextConfiguration
@ActiveProfiles("test")
public class AtosControllerEmptyTest {
    @Value("${local.server.port}")
    int port;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        RestAssured.port = port;
    }

    @Test
    public void getAllCustomers() {
        given().
                mockMvc(mvc).
                when().
                get("/atos/customers").
                then().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void addCustomerOk() {
        given().
                mockMvc(mvc).
                body(new CustomerDTO(3434, "New", "User")).
                contentType(ContentType.JSON).
                when().
                post("/atos/customers").
                then().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void addCustomerAlsoOk() {
        given().
                mockMvc(mvc).
                body(new CustomerDTO(1, "New", "User")).
                contentType(ContentType.JSON).
                when().
                post("/atos/customers").
                then().
                statusCode(HttpStatus.SC_OK);
    }
}
