package ru.API.helpers;

import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import ru.API.pojo.Entity;
import ru.API.tests.BaseTest;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class BaseRequests extends BaseTest{
    /**
     * Метод удаляет сущность по id
     * @param entityId - id сущности
     */
    @Step("Удаление сущности по id")
    public static void deleteEntityById(String entityId){
        given()
                .when()
                .delete("/api/delete/" + entityId)
                .then()
                .statusCode(204);
    }

    /**
     * Метод удаляет все сущности из списка с id
     * @param entityIdList - список id сущностей
     */
    @Step("Удаление всех сущностей по списку с id")
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

    /**
     * Метод сохраняет сущности в сервисе по списку сущностей
     * @param entityList - список сущностей
     * @param requestSpecification - спецификатор
     * @return - список id, под которыми сохранились сущности
     */
    @Step("Сохранение списка сущностей в сервисе")
    public static List<String> createEntities(List<Entity> entityList, RequestSpecification requestSpecification){
        return entityList.stream()
                .map(entity ->
                        given()
                                .spec(requestSpecification)
                                .body(entity)
                                .when()
                                .post("/api/create")
                                .then()
                                .statusCode(200)
                                .extract()
                                .body()
                                .asString()
                )
                .collect(Collectors.toList());
    }

    /**
     * Метод создает сущность в сервисе
     * @param entity - сущность
     * @param requestSpecification - спецификатор
     * @return - id, под которым сохранилась сущность
     */
    @Step("Сохранение сущности в сервисе")
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

    /**
     * Метод возвращает сущность по id
     * @param entityId - id сущности
     * @param requestSpecification - спецификатор
     * @return - сущность по его id
     */
    @Step("Получение сущности по id")
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
