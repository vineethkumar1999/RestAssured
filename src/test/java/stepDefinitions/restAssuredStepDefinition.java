package stepDefinitions;

import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.util.Random;

public class restAssuredStepDefinition {

    RequestSpecification httpRequest;
    Response response;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        System.out.println("Perform all the pre-requisite setup here");
    }

    @AfterAll
    public static void deInit() {
        System.out.println("Perform all end operations here");
    }

    @Given("Navigate to BookStore API")
    public void bookStoreMain() {
        httpRequest = RestAssured.given();
    }

    @When("Run the Get books")
    public void runGetBooks() {
        response = httpRequest.request(Method.GET, "BookStore/v1/Books");
    }

    @Then("Validate book response")
    public void validateBookResponse() {
        Assert.assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());
        //to get status related info use getStatusCode, getStatusLine
        //to read the response headers use headers class, getHeaders or Header or Headers
        Headers allHeaders = response.getHeaders();
        for (Header h : allHeaders) {
            System.out.println(h);
        }
        String contentType = response.header("content-type");
        System.out.println(contentType);
        ResponseBody body = response.getBody();
        String sBody = body.asString();
        System.out.println(sBody);
        useQueryParameter();
    }

    public void useQueryParameter() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com/BookStore/v1";
        RequestSpecification httpRequest = RestAssured.given();
        Response res = httpRequest.queryParam("ISBN", "9781449325862").get("/Book");
        ResponseBody body = res.body();
        String rbdy = body.asString();
        System.out.println(rbdy);
        JsonPath jpath = new JsonPath(rbdy);
        //Storing publisher name in a string variable
        String title = jpath.getString("title");
        System.out.println("The book title is - " + title);
    }

    @When("post the create user")
    public void postRequest() {
        Random rand = new Random();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", "TestUser" + rand.nextInt(1000));
        jsonObject.put("password", "Pass@123");
        response = httpRequest.header("Content-Type", "application/json")
                .header("accept","application/json")
                .body(jsonObject.toJSONString())
                .log().all()
                .request(Method.POST, "Account/v1/User");
    }

    @Then("Validate user created")
    public void userValidate()
    {
        System.out.println(response.getStatusLine());
        ResponseBody responseBody = response.getBody();
        String res = responseBody.asString();
        System.out.println(res);
        Assert.assertTrue(res.contains("userID"));
        Assert.assertEquals(201, response.getStatusCode());
    }
    
}
