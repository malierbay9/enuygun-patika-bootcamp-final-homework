package api_case.models.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

//Pet Store requestlerinin response bodylerini çevirdiğimiz ya da requestlerde kullanacağımız bodyleri modellediğimiz sınıf.

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private long id;
    private Category category;
    private String name;
    private ArrayList<String> photoUrls;
    private ArrayList<Tag> tags;
    private String status;
}
