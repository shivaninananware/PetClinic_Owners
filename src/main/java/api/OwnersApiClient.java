package api;

import api.Owners.Owners;
import com.fedex.apicommon.owner.ApiClient;
import com.fedex.apicommon.owner.ApiRequest;
import com.fedex.apicommon.owner.ApiResponse;
import com.fedex.apicommon.owner.exception.InvalidResponseException;
import com.google.gson.GsonBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.Method;
import io.restassured.internal.mapping.GsonMapper;
import io.restassured.mapper.ObjectMapperType;

public class OwnersApiClient extends ApiClient {




    public OwnersApiClient (String baseUrl , String basePathOwners) {
            super(baseUrl, basePathOwners);


            ObjectMapperConfig config = new ObjectMapperConfig(ObjectMapperType.GSON)
                    .gsonObjectMapperFactory((type, s) -> new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create());
            setObjectMapper(new GsonMapper(config.gsonObjectMapperFactory()));
        }
        //GET
        public Owners[] getOwners() throws InvalidResponseException {
            ApiResponse<Owners[]> response = caller.executeRequest(getRequest(), Method.GET, Owners[].class);
            return  response.getContent();




        }
    //POST
    public Owners createOwner(Owners owner) throws InvalidResponseException {
        ApiRequest request = getRequest().withBody(owner).withHeader("Content-Type", "application/json");
        ApiResponse<Owners> response = caller.executeRequest(request, Method.POST, Owners.class);
        return  response.getContent();
    }
    //DELETE
   public ApiResponse<Owners[]> deleteOwner() throws InvalidResponseException{

            ApiResponse<Owners[]> response = caller.executeRequest(getRequest(),Method.DELETE,Owners[].class);
            return response;


    }


    }



