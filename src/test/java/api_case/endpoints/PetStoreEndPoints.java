package api_case.endpoints;


//Pet Store requestlerinde kullanılacak endpointleri içerir.

public class PetStoreEndPoints {
    public static String ADD_PET = "/pet";
    public static String FIND_BY_STATUS = "/pet/findByStatus";
    public static String PET_BY_ID = "/pet/{petId}";
    public static String UPDATE_PET = "/pet/{petId}";
    public static String DELETE_PET = "/pet/{petId}";
}
