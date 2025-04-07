package ru.API.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id) && Objects.equals(addition, entity.addition) && Objects.equals(important_numbers, entity.important_numbers) && Objects.equals(title, entity.title) && Objects.equals(verified, entity.verified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addition, important_numbers, title, verified);
    }
}
