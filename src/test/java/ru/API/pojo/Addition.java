package ru.API.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
public class Addition {
    String additional_info;
    Integer additional_number;
    String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Addition addition = (Addition) o;
        return Objects.equals(additional_info, addition.additional_info) && Objects.equals(additional_number, addition.additional_number) && Objects.equals(id, addition.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(additional_info, additional_number, id);
    }
}
