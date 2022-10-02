package utils;

public enum FoodDeliveryEndpoints {


    FOOD_CACHE_ENDPOINT("/food/cache/add"),
    LIST_FOOD_CACHE_ENDPOINT("/food/cache/list"),
    COMMIT_CACHE_TO_DB_ENDPOINT("/food/commit"),
    CLEAR_CACHE("/food/resetcache"),
    UPDATE_FOOD_ENDPOINT("/food/cache/update"),
    USER_REGISTRATION_ENDPOINT("/user/registration");

    private final String endpoint;

    FoodDeliveryEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }
    public String getEndpoint(){
        return endpoint;
    }

    }
