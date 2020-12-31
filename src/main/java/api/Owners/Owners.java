
package api.Owners;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Owners {

    @Expose
    private String address;

    @Expose
    private String city;

    @Expose
    private String id;

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private List<Pet> pets;

    @Expose
    private String telephone;


}


