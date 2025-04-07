package ru.API.tests;

import io.restassured.mapper.ObjectMapperType;
import jdk.jfr.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.API.helpers.Assert;
import ru.API.helpers.BaseRequests;
import ru.API.helpers.EntityGenerator;
import ru.API.pojo.Addition;
import ru.API.pojo.Entity;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class PatchEntityTest extends BaseTest {
    @Test
    @Description("Проверка изменения сущности")
    public void testPatchEntity() {
        Entity entityNew = EntityGenerator.generate();

        given()
                .spec(requestSpecification)
                .body(entityNew)
                .when()
                .patch("/api/patch/" + entityId)
                .then()
                .statusCode(204);

        Entity entityNewCheck = given()
                .spec(requestSpecification)
                .when()
                .get("/api/get/" + entityId)
                .then()
                .statusCode(200)
                .extract().as(Entity.class, ObjectMapperType.GSON);

        Assert.softAssert(entityNew, entityNewCheck);
    }

    @AfterMethod
    public void teardown(){
        BaseRequests.deleteEntityById(entityId);
    }
}
