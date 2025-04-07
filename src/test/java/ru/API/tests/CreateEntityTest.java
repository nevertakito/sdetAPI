package ru.API.tests;

import org.testng.annotations.Test;
import ru.API.helpers.Assert;
import ru.API.helpers.BaseRequests;
import ru.API.pojo.Entity;

public class CreateEntityTest extends BaseTest {
    @Test
    public void testCreateEntity() {
        Entity entityNew = BaseRequests.getEntityById(entityId, requestSpecification);
        Assert.softAssert(entity, entityNew);
        BaseRequests.deleteEntityById(entityId);
    }
}
