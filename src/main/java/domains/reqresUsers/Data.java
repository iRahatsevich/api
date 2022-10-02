package domains.reqresUsers;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Data {

    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
