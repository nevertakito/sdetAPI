package ru.API.tests;

import jdk.jfr.Description;
import org.apache.commons.lang3.ObjectUtils;
import org.testng.annotations.Test;
import ru.API.helpers.BaseRequests;

import static io.restassured.RestAssured.given;

public class DeleteEntityTest extends BaseTest {

    @Test
    @Description("Проверка удаления сущности")
    public void testDeleteEntity() {
        BaseRequests.deleteEntityById(entityId);
        given()
                .spec(requestSpecification)
                .when()
                .get("/api/get/" + entityId)
                .then()
                .statusCode(500)
                .equals(ObjectUtils.NULL);
    }
}
