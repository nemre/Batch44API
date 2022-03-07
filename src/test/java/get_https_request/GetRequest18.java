package get_https_request;

import Base_url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRequest18 extends GMIBankBaseUrl {

    /*
http://www.gmibank.com/api/tp-customers/43703

"firstName": "Alda",
"lastName": "Monahan",
"middleInitial": "Nichelle Hermann Kohler",
"email": "com.github.javafaker.Name@7c011174@gmail.com",
"mobilePhoneNumber": "909-162-8114",
"city": "St Louis",
"ssn": "108-53-6655"

1) MATCHERS CLASS
2) JSON PATH
3) De-Serialization
 */

    @Test
    public void test18(){

        // 1.
        spec03.pathParams("one","tp-customers","two","43703");

        // 2.
        Map<String,Object> expdata =new HashMap<>();
        expdata.put("firstName","Alda");
        expdata.put("lastName","Monahan");
        expdata.put("middleInitial","Nichelle Hermann Kohler");
        expdata.put("email","com.github.javafaker.Name@7c011174@gmail.com");
        expdata.put("mobilePhoneNumber","909-162-8114");
        expdata.put("city","St Louis");
        expdata.put("ssn","108-53-6655");

        System.out.println(expdata);

        // 3.
        Response response =given().spec(spec03)
                .header("Authorization", "Bearer " + generateToken())
                .when().get("/{one}/{two}");
        response.prettyPrint();

        // 4. Doğrulama

        //1-Matcher class
        response.then().assertThat().body("firstName", equalTo("Alda"),
                "lastName", equalTo("Monahan"),
                "middleInitial", equalTo("Nichelle Hermann Kohler"),
                "email",equalTo("com.github.javafaker.Name@7c011174@gmail.com"),
                "mobilePhoneNumber",    equalTo("909-162-8114"),
                "city", equalTo("St Louis"),
                "ssn", equalTo("108-53-6655"));

        //2-Jsonpath
        JsonPath json = response.jsonPath();
        Assert.assertEquals("Alda",json.getString("firstName"));
        Assert.assertEquals("Monahan",json.getString("lastName"));
        Assert.assertEquals("Nichelle Hermann Kohler",json.getString("middleInitial"));
        Assert.assertEquals("com.github.javafaker.Name@7c011174@gmail.com",json.getString("email"));
        Assert.assertEquals("909-162-8114",json.getString("mobilePhoneNumber"));
        Assert.assertEquals("St Louis",json.getString("city"));
        Assert.assertEquals("108-53-6655",json.getString("ssn"));

        //3-De-Serialization
        Map<String,Object> actualData=response.as(HashMap.class);
        System.out.println("Actual Data :"+actualData);

        Assert.assertEquals(expdata.get("firstName"),actualData.get("firstName"));
        Assert.assertEquals(expdata.get("lastName"),actualData.get("lastName"));
        Assert.assertEquals(expdata.get("middleInitial"),actualData.get("middleInitial"));
        Assert.assertEquals(expdata.get("email"),actualData.get("email"));
        Assert.assertEquals(expdata.get("mobilePhoneNumber"),actualData.get("mobilePhoneNumber"));
        Assert.assertEquals(expdata.get("city"),actualData.get("city"));
        Assert.assertEquals(expdata.get("ssn"),actualData.get("ssn"));



    }
}
