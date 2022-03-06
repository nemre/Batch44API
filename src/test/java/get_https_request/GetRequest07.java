package get_https_request;

import Base_url.RegresinBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest07 extends RegresinBaseUrl {

      /*
        https://reqres.in/api/users URL request olustur.
        body icerisindeki idsi 5 olan datayi
        1) Matcher CLASS ile
        2) JsonPath ile dogrulayin.
        */

    @Test
    public void test07(){

        spec01.pathParams("parametre1","api","parametre2","users");

        Response response=given().spec(spec01).when().get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        //Matchers Class

        response.then().assertThat().body("data[4].email", Matchers.equalTo("charles.morris@reqres.in")
                ,"data[4].first_name",Matchers.equalTo("Charles")
                ,"data[4].last_name",Matchers.equalTo("Morris"));

        //JsonPath

        JsonPath json =response.jsonPath();

        System.out.println(json.getList("data.email"));
        System.out.println(json.getString("data.first_name"));
        System.out.println(json.getString("data.last_name"));

        Assert.assertEquals("charles.morris@reqres.in",json.getString("data[4].email"));
        Assert.assertEquals("Charles",json.getString("data[4].first_name"));
        Assert.assertEquals("Morris",json.getString("data[4].last_name"));
    }


}
