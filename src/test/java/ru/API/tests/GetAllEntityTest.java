package ru.API.tests;

import com.google.gson.Gson;
import jdk.jfr.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.API.helpers.Assert;
import ru.API.helpers.BaseRequests;
import ru.API.helpers.EntityGenerator;
import ru.API.pojo.Entity;
import ru.API.pojo.EntityListResponse;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class GetAllEntityTest extends BaseTest {
    private final List<String> entityIdList = new ArrayList<>();
    private final List<Entity> entityList = new ArrayList<>();

    @Test
    @Description("Проверка получения всех сущностей")
    public void testGetCorrectAllEntity() {
        entityIdList.add(entityId);
        entityIdList.addAll(BaseRequests.createEntities(List.of(EntityGenerator.generate(), EntityGenerator.generate()), requestSpecification));

        entityList.add(BaseRequests.getEntityById(entityIdList.get(0), requestSpecification));
        entityList.add(BaseRequests.getEntityById(entityIdList.get(1), requestSpecification));
        entityList.add(BaseRequests.getEntityById(entityIdList.get(2), requestSpecification));

        String jsonResponse = given().spec(requestSpecification).when().get("/api/getAll").then().statusCode(200).extract().body().asString();

        EntityListResponse response = new Gson().fromJson(jsonResponse, EntityListResponse.class);
        List<Entity> responseList = new ArrayList<>(response.getEntity());

        System.out.println(responseList);
        System.out.println(entityList);

        Assert.softAsserts(entityList, responseList);
        responseList.clear();
}

    @AfterMethod
    public void teardown() {
        BaseRequests.deleteEntities(entityIdList);
        entityIdList.clear();
        entityList.clear();
    }
}
