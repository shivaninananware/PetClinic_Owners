
package api.Owners;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;



@Getter
@Builder
public class Type {

    @Expose
    private String id;

    @Expose
    private String name;


}
