package ru.API.tests;

import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.Test;
import ru.API.helpers.Assert;
import ru.API.helpers.BaseRequests;
import ru.API.pojo.Addition;
import ru.API.pojo.Entity;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class PatchEntityTest extends BaseTest {
    @Test
    public void testPatchEntity() {
        Entity entityNew = Entity.builder().title("Update Title").verified(Boolean.TRUE).important_numbers(Arrays.asList(4, 5, 6)).addition(Addition.builder().additional_info("Update Info").additional_number(66).build()).build();

        given().spec(requestSpecification).body(entityNew).when().patch("/api/patch/" + entityId).then().statusCode(204);

        Entity entityNewCheck = given().spec(requestSpecification).when().get("/api/get/" + entityId).then().statusCode(200).extract().as(Entity.class, ObjectMapperType.GSON);

        Assert.softAssert(entityNew, entityNewCheck);
        BaseRequests.deleteEntityById(entityId);
    }
}
