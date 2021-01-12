import api.Owners.Owners;
import api.OwnersApiClient;
import com.fedex.apicommon.owner.ApiResponse;
import com.fedex.apicommon.owner.exception.InvalidResponseException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;


public class OwnersApiTest {

    static String apiUrl;




    @BeforeAll
    static void getApiUrl() {
        apiUrl = System.getProperty("apiUrl");
    }


    //get all the owner details using GET method

    @Test
    public void accessOwners_Information_ShouldCheckUniqueId() throws InvalidResponseException {
        OwnersApiClient client = new OwnersApiClient(apiUrl, "/api/owners/");
        Owners[] owners = client.getOwners();


        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(owners[0].getCity()).as("the City is Sun Prairie:").isEqualTo("Sun Prairie");
        softly.assertThat(owners[1].getId()).as("the given ID is :  ").isEqualTo("3");
        softly.assertThat(owners[2].getId()).as("ID's should be unique").isNotSameAs(owners[3].getId());
        softly.assertAll();
        validateResponseTime();
    }


    //create a new owner using POST method

    @Test

    public void createOwner_checkId_ShouldReturnNewOwner() throws InvalidResponseException, IOException {

        Properties pro = new Properties();
        FileInputStream file = new FileInputStream("C:\\Users\\Shivani\\IdeaProjects\\PetClinic_Owners\\src\\main\\java\\api\\PostData.Properties");
        pro.load(file);

        OwnersApiClient client = new OwnersApiClient(apiUrl, "/api/owners/");

        Owners createdOwner = client.createOwner(Owners.builder().firstName(pro.getProperty("firstName"))
                .lastName(pro.getProperty("lastName")).address(pro.getProperty("address")).city(pro.getProperty("city"))
                .telephone(pro.getProperty("telephone")).build());




        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(createdOwner.getFirstName()).as("First name should be Harry").isEqualTo(pro.getProperty("firstName"));
        softly.assertThat(createdOwner.getId()).as("A unique ID should be populated").isNotBlank();
        softly.assertThat(createdOwner.getId()).as("Id is different than the existing one's").isGreaterThan("1");

        softly.assertAll();


        validateResponseTime();

    }


    //delete the owner created  by passing ID of it

    @Test

    public void deleteNewOwners_throughID_ShouldDisplayDeletedId() throws InvalidResponseException, IOException {
        //create a new owner
        Properties pro = new Properties();
        FileInputStream file = new FileInputStream("C:\\Users\\Shivani\\IdeaProjects\\PetClinic_Owners\\src\\main\\java\\api\\PostData.Properties");
        pro.load(file);

        OwnersApiClient client = new OwnersApiClient(apiUrl, "/api/owners/");

        Owners newOwner = client.createOwner(Owners.builder().firstName(pro.getProperty("firstName"))
                .lastName(pro.getProperty("lastName")).address(pro.getProperty("address")).city(pro.getProperty("city"))
                .telephone(pro.getProperty("telephone")).build());




        String ownerId = newOwner.getId(); //fetch the new owner ID

        //delete the created owner
        OwnersApiClient client1 = new OwnersApiClient(apiUrl, "/api/owners/" + ownerId);
        ApiResponse<Owners[]> deletedOwners = client1.deleteOwner();


        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(deletedOwners.getHttpStatusCode()).as("The Status code is :").isEqualTo(204);
        softly.assertAll();

        validateResponseTime();
    }


    //To calculate the response time taken by each HTTP method
    public void validateResponseTime() {
        Response resp = RestAssured.get(apiUrl);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(resp.getTime()).as("Response Time is less than 2 seconds").isLessThan(2000);
        System.out.println("The response Time is : " + resp.getTime());


    }
}
