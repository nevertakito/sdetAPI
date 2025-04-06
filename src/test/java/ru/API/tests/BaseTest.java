package ru.API.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.API.helpers.BaseRequests;
import ru.API.helpers.ConfProperties;
import ru.API.pojo.Addition;
import ru.API.pojo.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseTest {
    protected RequestSpecification requestSpecification;
    protected Entity entity;
    protected String entityId;

    @BeforeClass
    public void setup() {

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setContentType(ContentType.JSON)
                .setBaseUri(ConfProperties.getProperty("apiUrl"))
                .setAccept(ContentType.JSON);
        requestSpecification = requestSpecBuilder.build();

        entity = Entity.builder()
                .title("Test Title")
                .verified(Boolean.TRUE)
                .important_numbers(Arrays.asList(1,2,3))
                .addition(Addition.builder()
                        .additional_info("Test Info")
                        .additional_number(99)
                        .build())
                .build();
        entityId = BaseRequests.createEntity(entity,requestSpecification);
    }
}
