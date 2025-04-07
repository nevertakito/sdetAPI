package ru.API.tests;

import com.google.gson.Gson;
import org.testng.annotations.Test;
import ru.API.helpers.Assert;
import ru.API.helpers.BaseRequests;
import ru.API.pojo.Entity;
import ru.API.pojo.EntityListResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class GetAllEntityTest extends BaseTest {
    private final List<String> entityIdList = new ArrayList<>();

    @Test
    public void testGetCorrectAllEntity() {

        entityIdList.add(entityId);
        entityIdList.addAll(BaseRequests.createEntities(List.of(entity, entity), requestSpecification));
        List<Entity> entityList = new ArrayList<>();
        entityList.add(BaseRequests.getEntityById(entityIdList.get(0), requestSpecification));
        entityList.add(BaseRequests.getEntityById(entityIdList.get(1), requestSpecification));
        entityList.add(BaseRequests.getEntityById(entityIdList.get(2), requestSpecification));

        String jsonResponse = given().spec(requestSpecification).when().get("/api/getAll").then().statusCode(200).extract().body().asString();

        EntityListResponse response = new Gson().fromJson(jsonResponse, EntityListResponse.class);
        List<Entity> responseList = new ArrayList<>(response.getEntity());

        Set<Entity> setEntity = responseList.stream()
                .flatMap(newResponse -> entityList.stream()
                        .filter(newResponse::equals)
                        .map(entity -> newResponse))
                .collect(Collectors.toCollection(HashSet::new));

        Assert.softAsserts(entityList, setEntity.stream().toList());

        BaseRequests.deleteEntities(entityIdList);
        entityIdList.clear();
        entityList.clear();
        responseList.clear();
    }
}
