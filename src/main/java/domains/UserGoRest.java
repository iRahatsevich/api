package domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
//will generate constractor that will initialize all class vars
public class UserGoRest {

    private String name;
    private String email;
    private String gender;
    private String status;



}


