package ru.API.tests;

import com.google.gson.Gson;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.API.helpers.Assert;
import ru.API.helpers.BaseRequests;
import ru.API.pojo.EntityListResponse;
import ru.API.pojo.Entity;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllEntityTest extends BaseTest{
    protected List<Entity> entityList;
    protected List<String> entityIdList = new ArrayList<>();

    @Test
    public void testGetCorrectAllEntity(){
        BaseRequests.deleteEntityById(entityId);

        entityIdList = BaseRequests.createEntities(List.of(entity,entity,entity),requestSpecification);

        entityList = Arrays.asList(
                BaseRequests.getEntityById(entityIdList.get(0),requestSpecification),
                BaseRequests.getEntityById(entityIdList.get(1),requestSpecification),
                BaseRequests.getEntityById(entityIdList.get(2),requestSpecification)
        );

        String jsonResponse = given()
                .spec(requestSpecification)
                .when()
                .get("/api/getAll")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        EntityListResponse response = new Gson().fromJson(jsonResponse, EntityListResponse.class);
        List<Entity> responseList = response.getEntity();
        System.out.println(entityList);
        System.out.println();
        System.out.println(responseList);
        Assert.softAsserts(entityList,responseList);
        BaseRequests.deleteEntities(entityIdList);
    }
}
