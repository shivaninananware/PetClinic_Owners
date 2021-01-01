import api.Owners.Owners;
import api.OwnersApiClient;
import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class OwnersApiTest {

    static String apiUrl;
    private String ownerId;

    @BeforeAll
    static void getApiUrl() {
        apiUrl = System.getProperty("apiUrl");
    }


    //to set sequence of all the GET , POST and DELETE methods
    @Test
    public void get_create_delete_operations()throws InvalidResponseException{
        getOwners_Information();
        createNewOwner_addMultipleDetails();
        deleteOwners_throughID();
    }


    //get all the owner details using GET method
    public void getOwners_Information() throws InvalidResponseException {
        OwnersApiClient client = new OwnersApiClient(apiUrl);
             Owners[] owners = client.getOwners();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(owners[0].getCity()).isEqualTo("Sun Prairie");
        softly.assertThat(owners[1].getId()).isEqualTo("3");
        softly.assertThat(owners[2].getId()).isNotSameAs(owners[3].getId());
        softly.assertAll();
    }


    //create a new owner using POST method
    public void createNewOwner_addMultipleDetails() throws InvalidResponseException {
        OwnersApiClient client = new OwnersApiClient(apiUrl);
            Owners createdOwner = client.createOwner(Owners.builder().
                    firstName("Harry").lastName("Potter")
                    .address("Hogwarts,Scotland").city("Glasgow")
                    .telephone("+567891523")
                    .build());
            ownerId= createdOwner.getId();

            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(createdOwner.getFirstName()).isEqualTo("Harry");
            softly.assertThat(createdOwner.getId()).isNotBlank();
            softly.assertThat(createdOwner.getId()).isGreaterThan("1");
            softly.assertAll();

    }


    //delete the owner created in POST method by passing ID of it
    public void deleteOwners_throughID() throws InvalidResponseException {
        OwnersApiClient client = new OwnersApiClient(apiUrl,ownerId);
        ApiResponse<Owners[]> deletedOwners = client.deleteOwner();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(deletedOwners.getHttpStatusCode()).isEqualTo(204);
        System.out.print("The Deleted Id is :"+ownerId);  //to check if the ID deleted is same as passed in Request URI
        softly.assertAll();
    }
}
