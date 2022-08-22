package api_case.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Grocery Item requestlerinin response bodylerini çevirdiğimiz ya da requestlerde kullanacağımız bodyleri modellediğimiz sınıf.

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItem {

    int id;
    String name;
    int price;
    int stock;

}
