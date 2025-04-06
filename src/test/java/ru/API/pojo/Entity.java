package ru.API.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Entity {
    String id;
    Addition addition;
    List<Integer> important_numbers;
    String title;
    Boolean verified;

    @Override
    public String toString() {
        return "Entity{" +
                "id='" + id + '\'' +
                ", addition=" + addition +
                ", important_numbers=" + important_numbers +
                ", title='" + title + '\'' +
                ", verified=" + verified +
                '}';
    }
}
