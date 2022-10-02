package domains.dummyApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataCl {

    private int id;
    private String employee_name;
    private int employee_salary;
    private int employee_age;
    private String profile_image;
}
