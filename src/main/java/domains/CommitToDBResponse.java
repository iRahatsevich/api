package domains;

public class CommitToDBResponse {

    private int numberOfFoodSaved;
    private String message;


    public int getNumberOfFoodSaved() {
        return numberOfFoodSaved;
    }

    public void setNumberOfFoodSaved(int numberOfFoodSaved) {
        this.numberOfFoodSaved = numberOfFoodSaved;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
