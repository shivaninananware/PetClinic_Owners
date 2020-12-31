
package api.Owners;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Pet {
    @Expose
    private String birthDate;

    @Expose
    private String id;

    @Expose
    private String name;

    @Expose
    private Long owner;

    @Expose
    private Type type;

    @Expose
    private List<Visit> Visits;


}