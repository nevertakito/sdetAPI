package ru.API.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Addition {
    String additional_info;
    Integer additional_number;
    String id;
}
