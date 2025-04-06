package ru.API.helpers;

import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import ru.API.pojo.Entity;
import ru.API.tests.BaseTest;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BaseRequests extends BaseTest{
    public static void deleteEntityById(String entityId){
        given()
                .when()
                .delete("/api/delete/" + entityId)
                .then()
                .statusCode(204);
    }
    public static void deleteEntities(List<String> entityIdList){
        if(!entityIdList.isEmpty()){
            entityIdList
                    .forEach(entityId ->
                            given()
                                    .when()
                                    .delete("/api/delete/" + entityId)
                                    .then()
                                    .statusCode(204));
        }
    }
    public static String createEntity(Entity entity, RequestSpecification requestSpecification){
        return given()
                .spec(requestSpecification)
                .body(entity)
                .when()
                .post("/api/create")
                .then()
                .statusCode(200)
                .extract()
                .asString();
    }
    public static Entity getEntityById(String entityId, RequestSpecification requestSpecification){
        return given()
                .spec(requestSpecification)
                .when()
                .get("/api/get/"+entityId)
                .then()
                .statusCode(200)
                .extract().as(Entity.class, ObjectMapperType.GSON);
    }
}
