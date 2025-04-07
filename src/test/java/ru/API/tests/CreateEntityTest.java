package ru.API.tests;

import jdk.jfr.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.API.helpers.Assert;
import ru.API.helpers.BaseRequests;
import ru.API.pojo.Entity;

public class CreateEntityTest extends BaseTest {
    @Test
    @Description("Проверка создания сущности")
    public void testCreateEntity() {
        Entity entityNew = BaseRequests.getEntityById(entityId, requestSpecification);
        Assert.softAssert(entity, entityNew);
    }

    @AfterMethod
    public void teardown(){
        BaseRequests.deleteEntityById(entityId);
    }
}
