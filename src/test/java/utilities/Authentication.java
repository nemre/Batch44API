package utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {

    public static void main(String[] args) {
         String guncelToken=genarateToken();
        System.out.println(guncelToken);
    }


//karşıdan token alma


    public static String genarateToken(){
        String username="Batch44Api";
        String password="Batch44+";

        Map<String,Object> map=new HashMap<>();
        map.put("username",username);
        map.put("password",password);

        String endPoint="http://www.gmibank.com/api/tp-customers";
        Response response=given().contentType(ContentType.JSON).body(map).when().post(endPoint);

        JsonPath token =response.jsonPath();

        return token.getString("id_token");

    }
}
