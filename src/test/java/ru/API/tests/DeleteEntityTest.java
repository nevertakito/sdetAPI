package ru.API.tests;

import org.apache.commons.lang3.ObjectUtils;
import org.testng.annotations.Test;
import ru.API.helpers.BaseRequests;

import static io.restassured.RestAssured.given;

public class DeleteEntityTest extends BaseTest {

    @Test
    public void testDeleteEntity() {
        BaseRequests.deleteEntityById(entityId);
        given().spec(requestSpecification).when().get("/api/get/" + entityId).then().equals(ObjectUtils.NULL);
    }
}
