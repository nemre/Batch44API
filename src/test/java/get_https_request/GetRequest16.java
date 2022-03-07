package get_https_request;

import Base_url.jsonplaceholderUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest16 extends jsonplaceholderUrl {

     /*
   https://jsonplaceholder.typicode.com/todos/7

   {
   "userId": 1,
   "id": 7,
   "title": "illo expedita consequatur quia in",
   "completed": false
}
    */

    @Test
    public void test16(){

        //1) URL oluşturma
        spec04.pathParams("bir","todos","iki","7");

        //2) beklenen data oluştur
        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("id",7);
        expectedData.put("title","illo expedita consequatur quia in");
        expectedData.put("completed",false);
        System.out.println(expectedData);

        //3) request respons kısmını oluştur
        Response response = given().spec(spec04).when().get("/{bir}/{iki}");
        ///{bir}/{iki} => todos/7 ekledik
        response.prettyPrint();

        //datayı javadan json a donuşturme işlemine De-Serialization
        //datayı json dan java ya donuşturme işlemine Serialization

        Map<String,Object> actualData=response.as(HashMap.class);//De-Serialization
        System.out.println("acual data :"+actualData);

        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("id"),actualData.get("id"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));
    }
}
