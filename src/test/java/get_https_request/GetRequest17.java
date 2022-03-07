package get_https_request;

import Base_url.GMIBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest17 extends GMIBankBaseUrl {
    /*
   http://www.gmibank.com/api/tp-customers/114351 adresindeki müşteri bilgilerini doğrulayın

{
   "firstName": "Della",
   "lastName": "Heaney",
   "email": "ricardo.larkin@yahoo.com",
   "mobilePhoneNumber": "123-456-7893",
}
    */

    @Test
    public void test17(){

        //1) URL oluşturma
        spec03.pathParams("bir","tp-customers","iki","114351");

        //2) beklenen data oluştur
        Map<String,Object> expData=new HashMap<>();
        expData.put("firstName","Della");
        expData.put("lastName","Heaney");
        expData.put("email","ricardo.larkin@yahoo.com");
        expData.put("mobilePhoneNumber","123-456-7893");

        System.out.println(expData);

        //3) request respons kısmını oluştur
        Response response =given().spec(spec03)
                .header("Authorization", "Bearer " + generateToken())
                .when().get("/{bir}/{iki}");

        response.prettyPrint();

        Map<String,Object> actData=response.as(HashMap.class);
        System.out.println(actData);

        Assert.assertEquals(expData.get("firstName"),actData.get("firstName"));
        Assert.assertEquals(expData.get("lastName"),actData.get("lastName"));
        Assert.assertEquals(expData.get("email"),actData.get("email"));
        Assert.assertEquals(expData.get("mobilePhoneNumber"),actData.get("mobilePhoneNumber"));


    }
}
