import io.cucumber.messages.internal.com.google.common.io.Resources;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;


public class generatorApiTest {
    Response response;
    JSONObject json;

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://generator.swagger.io/api";
    }

    @Test
    public void callingGetSpringServers() {
        response = RestAssured.given()
                .get("/gen/servers/spring")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(response.getBody().jsonPath().get("sortParamsByRequiredFlag.opt"), "sortParamsByRequiredFlag");
    }

    @Test
    public void Deneme() throws IOException {
        getJsonObject();
        json.put("options.additionalProp1", "örnek");
        json.put("options.additionalProp2", "örnek");
        json.put("options.additionalProp3", "örnek");
        json.put("authorizationValue.value", "örnek");
        json.put("authorizationValue.type", "örnek");
        json.put("authorizationValue.keyName", "örnek");
        json.put("securityDefinition.type", "örnek");
        json.put("securityDefinition.description", "örnek");
        addMethod(json,"/gen/clients/ada",400);
    }

    public void getJsonObject() throws IOException {
        URL file = Resources.getResource("createLibrary.json");
        String myJson = Resources.toString(file, Charset.defaultCharset());
        json = new JSONObject(myJson);
    }
    public void addMethod(Object json,String link,int status) {
        response = RestAssured.given()
                .contentType("application/json")
                .body(json.toString())
                .when()
                .post(link)
                .then()
                .statusCode(status)
                .extract()
                .response();
    }
}


