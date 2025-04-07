package ru.API.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import ru.API.helpers.BaseRequests;
import ru.API.helpers.ConfProperties;
import ru.API.helpers.EntityGenerator;
import ru.API.pojo.Entity;

public class BaseTest {
    protected RequestSpecification requestSpecification;
    protected Entity entity;
    protected String entityId;

    @BeforeMethod
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType(ContentType.JSON).setBaseUri(ConfProperties.getProperty("apiUrl")).setAccept(ContentType.JSON);
        requestSpecification = requestSpecBuilder.build();

        entity = EntityGenerator.generate();
        entityId = BaseRequests.createEntity(entity, requestSpecification);
    }
}
