package com.gorest.demo.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostExtractionTest
{
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    //Extract the title
    @Test
    public void test001() {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of title is : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the total number of record
    @Test
    public void test002() {
        List<String> title = response.extract().path("title");
        int noOfRecords = title.size();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of title is : " + noOfRecords);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the body of 15th record
    @Test
    public void test003() {
        String body = response.extract().path("[15].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of 15th record is : " + body);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the user_id of all the records
    @Test
    public void test004() {
        List<String> userId = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The user_id of all the records are : " + userId);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the title of all the records
    @Test
    public void test005() {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The itle of all the records are : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the title of all records whose user_id = 39655
    @Test
    public void test006() {
        List<String> title = response.extract().path("findAll{it.user_id = 4123649}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all records whose user_id = 4123649 are : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the body of all records whose id = 39654
    @Test
    public void test007() {
        List<String> body = response.extract().path("findAll{it.id = '57448'}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of body of all records whose id = 57448 are : " + body);
        System.out.println("------------------End of Test---------------------------");
    }
}
