package domains.dummyApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class DummyApi {

    private String status;
    private String message;
    private List<DataCl> data;

}
