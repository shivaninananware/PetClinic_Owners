package api.Owners;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class Visit {


    @Expose
    private String id;

    @Expose
    private String description;

    @Expose
    private Date date;

    @Expose
    private int pet;


}
