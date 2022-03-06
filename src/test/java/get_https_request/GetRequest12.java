package get_https_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.Authentication;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends Authentication {

    //Authenticatio Class'ın içerisindeki generatToken() metodu kullanılacak

    String endPoint = "http://www.gmibank.com/api/tp-customers";

    @Test
    public void test12(){

        Response response = given()
                .header("Authorization", "Bearer " + genarateToken())
                .when()
                .get(endPoint)
                .then()
                .extract()
                .response();

        response.prettyPeek();

        response.then().assertThat().contentType(ContentType.JSON).statusCode(200);
    }
}
//Token sürekli değiştiği için çalışmayabilir