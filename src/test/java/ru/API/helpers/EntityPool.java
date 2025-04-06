package ru.API.helpers;

import ru.API.pojo.Entity;

public class EntityPool {
    private static final ThreadLocal<Entity> entity = new ThreadLocal<>();
    public static Entity getEntity() {
        return entity.get();
    }
    public static void setEntity(Entity newEntity) {
        entity.set(newEntity);
    }
    public static void deleteEntity(){
        if(entity.get()!=null){
            BaseRequests.deleteEntityById(entity.get().getId());
            entity.remove();
        }
    }
    public static void removeEntity(){
        if(entity.get()!=null){
            entity.remove();
        }
    }
}
