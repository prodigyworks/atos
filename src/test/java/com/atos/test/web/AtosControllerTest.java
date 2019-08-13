package com.atos.test.web;

import com.atos.test.Application;
import com.atos.test.dto.CustomerDTO;
import com.jayway.restassured.http.ContentType;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.After;
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
@ActiveProfiles("local")
public class AtosControllerTest {
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

    @After
    public void tearDown() {
    }


    @Test
    public void test1() {
        given().
                mockMvc(mvc).
                when().
                get("/atos/customers").
                then().
                statusCode(HttpStatus.SC_OK).
                body("[1].firstname", Matchers.equalTo("David")).
                body("[1].surname", Matchers.equalTo("Smith"));
    }


    @Test
    public void test2() {
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
    public void test3() {
        given().
                mockMvc(mvc).
                body(new CustomerDTO(1, "New", "User")).
                contentType(ContentType.JSON).
                when().
                post("/atos/customers").
                then().
                statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void test4() {
        given().
                mockMvc(mvc).
                contentType(ContentType.JSON).
                when().
                delete("/atos/customers/1").
                then().
                statusCode(HttpStatus.SC_OK);

        /* Now row removed. index 1 is nor 0
            1,Kevin,Hilton
            2,David,Smith
            3,Alan,Jones
            4,Peter,Thomas
            5,Paul,Jennings
        */
        given().
                mockMvc(mvc).
                when().
                get("/atos/customers").
                then().
                statusCode(HttpStatus.SC_OK).
                body("[0].firstname", Matchers.equalTo("David")).
                body("[0].surname", Matchers.equalTo("Smith"));

    }

    @Test
    public void test5() {
        given().
                mockMvc(mvc).
                contentType(ContentType.JSON).
                when().
                delete("/atos/customers/343433").
                then().
                statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
