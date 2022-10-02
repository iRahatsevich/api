package domains;

import java.util.ArrayList;
import java.util.List;

public class ListUsersResponse {

    private List<UserGoRest> data = new ArrayList<>();





    public List<UserGoRest> getUsers() {
        return data;
    }
    public void setUsers(List<UserGoRest> users) {
        this.data = users;
    }


}
