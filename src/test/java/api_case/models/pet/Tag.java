package api_case.models.pet;

import lombok.Data;

//Pet modelinde bir attribute a ait sınıf

@Data
public class Tag {
    private long id;
    private String name;
}
