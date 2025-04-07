package ru.API.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class EntityListResponse {
    private List<Entity> entity;
}
