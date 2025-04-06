package ru.API.tests;

import org.apache.commons.lang3.ObjectUtils;
import org.testng.annotations.Test;
import ru.API.helpers.BaseRequests;
import ru.API.helpers.EntityPool;

import static io.restassured.RestAssured.given;

public class DeleteEntityTest extends BaseTest{


    @Test
    public void testDeleteEntity(){
        BaseRequests.deleteEntityById(EntityPool.getEntity().getId());
        given()
                .spec(requestSpecification)
                .when()
                .get("/api/get/" + EntityPool.getEntity().getId())
                .then()
                .equals(ObjectUtils.NULL);
    }
}
