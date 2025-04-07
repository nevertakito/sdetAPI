package ru.API.tests;

import jdk.jfr.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.API.helpers.Assert;
import ru.API.helpers.BaseRequests;
import ru.API.pojo.Entity;

public class GetEntityTest extends BaseTest {
    @Test
    @Description("Проверка получения сущности")
    public void testGetCorrectEntity() {
        Entity entityNew = BaseRequests.getEntityById(entityId, requestSpecification);
        Assert.softAssert(entity, entityNew);
    }

    @AfterMethod
    public void teardown(){
        BaseRequests.deleteEntityById(entityId);
    }
}