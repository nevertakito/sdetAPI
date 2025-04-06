package ru.API.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.API.helpers.Assert;
import ru.API.helpers.BaseRequests;
import ru.API.helpers.EntityPool;
import ru.API.pojo.Entity;

public class GetEntityTest extends BaseTest {
    @Test
    public void testGetCorrectEntity(){
        Entity entityNew = BaseRequests.getEntityById(EntityPool.getEntity().getId(), requestSpecification);
        Assert.softAssert(EntityPool.getEntity(),entityNew);
        EntityPool.deleteEntity();
    }
}