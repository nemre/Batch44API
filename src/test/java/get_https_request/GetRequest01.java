package get_https_request;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {

    @Test
    public void test01(){

        String url="https://restful-booker.herokuapp.com/booking";

        Response response=given().when().get(url);
        response.prettyPrint();
        System.out.println("status   :"+response.statusCode());
        System.out.println("content type:  "+response.contentType());
        System.out.println(response.statusLine());
        System.out.println("süre:  "+response.time());

        //Assert.assertEquals(200,response.statusCode());
        //Assert.assertEquals("Assert.assertEquals(200,response.statusCode());",response.contentType());
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }
}
