package ru.API.helpers;

import org.testng.asserts.SoftAssert;
import ru.API.pojo.Entity;

import java.util.List;
import java.util.stream.IntStream;

public class Assert {
    public static void softAssert(Entity entityFirst, Entity entitySecond){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(entityFirst.getTitle(), entitySecond.getTitle());
        softAssert.assertEquals(entityFirst.getVerified(), entitySecond.getVerified());
        softAssert.assertEquals(entityFirst.getAddition().getAdditional_info(), entitySecond.getAddition().getAdditional_info());
        softAssert.assertEquals(entityFirst.getAddition().getAdditional_number(), entitySecond.getAddition().getAdditional_number());
        softAssert.assertEquals(entityFirst.getImportant_numbers(), entitySecond.getImportant_numbers());
        softAssert.assertAll();
    }

    public static void softAsserts(List<Entity> entitiesFirst, List<Entity> entitiesSecond){
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(
                entitiesFirst.size(),
                entitiesSecond.size()
        );
        IntStream.range(0, Math.min(entitiesFirst.size(), entitiesSecond.size()))
                .forEach(index -> {
                    Entity entity = entitiesFirst.get(index);
                    Entity response = entitiesSecond.get(index);

                    softAssert.assertEquals(
                            entity.getTitle(),
                            response.getTitle()
                    );

                    softAssert.assertEquals(
                            entity.getVerified(),
                            response.getVerified()
                    );

                    softAssert.assertEquals(
                            entity.getAddition().getAdditional_info(),
                            response.getAddition().getAdditional_info()
                    );

                    softAssert.assertEquals(
                            entity.getAddition().getAdditional_number(),
                            response.getAddition().getAdditional_number()
                    );

                    softAssert.assertEquals(
                            entity.getImportant_numbers(),
                            response.getImportant_numbers()
                    );
                });

        softAssert.assertAll();
    }
}
