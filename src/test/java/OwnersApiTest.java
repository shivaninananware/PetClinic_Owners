import api.Owners.Owners;
import api.OwnersApiClient;
import api.common.exception.InvalidResponseException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class OwnersApiTest {

    static String apiUrl;

    @BeforeAll
    static void getApiUrl() {
        apiUrl = System.getProperty("apiUrl");
    }

    @Test
    public void getOwners_Information() throws InvalidResponseException {
        OwnersApiClient client = new OwnersApiClient(apiUrl);
        Owners[] owners = client.getOwners();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(owners[0].getFirstName()).isEqualTo("Betty");
        softly.assertThat(owners[1].getId()).isEqualTo("3");
        softly.assertThat(owners[2].getId()).isNotSameAs(owners[3].getId());
        softly.assertAll();
    }


    @Test
    public void createNewOwner_addMultipleDetails() throws InvalidResponseException {
        OwnersApiClient client = new OwnersApiClient(apiUrl);
            Owners createdOwner = client.createOwner(Owners.builder().
                    firstName("Emma").lastName("Watson")
                    .address("Ontario , Salisbury Park Road").city("Ontario")
                    .telephone("86747358")
                    .build());
            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(createdOwner.getFirstName()).isEqualTo("Emma");
            softly.assertThat(createdOwner.getId()).isNotBlank();
            softly.assertThat(createdOwner.getId()).isGreaterThan("1");
            softly.assertAll();

    }

    /*@Test
    @Disabled
    public void deleteOwners_throughID() throws InvalidResponseException {
        OwnersApiClient client = new OwnersApiClient(apiUrl);
        Owners deletedOwners = client.deleteOwner().getCity("Ontario");

        SoftAssertions softly = new SoftAssertions();

        softly.assertAll();
    }*/
}
