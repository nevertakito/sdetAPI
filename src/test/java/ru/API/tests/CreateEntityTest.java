package ru.API.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.API.helpers.Assert;
import ru.API.helpers.BaseRequests;
import ru.API.helpers.EntityPool;
import ru.API.pojo.Entity;

public class CreateEntityTest extends BaseTest{

    @Test
    public void testCreateEntity(){
        String id = BaseRequests.createEntity(EntityPool.getEntity(),requestSpecification);
        Entity entityNew = BaseRequests.getEntityById(id,requestSpecification);
        Assert.softAssert(EntityPool.getEntity(),entityNew);
        BaseRequests.deleteEntityById(id);
        EntityPool.deleteEntity();
    }
}
