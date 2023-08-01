package com.gorest.demo.crudtest;

import com.gorest.demo.model.UserPojo;
import com.gorest.demo.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCrudTest
{
    static String name = "PrimeUser" + TestUtils.getRandomValue();
    static String email = "PrimeUser" + TestUtils.getRandomValue() + "@gmail.com";
    static String updatedEmail = "Updated" + TestUtils.getRandomValue() + "@gmail.com";
    static String gender = "male";
    static String status = "active";
    static int userId;

    static  String token = "d88d02069e48aedd8e5032e17f21b929e78721f871e7289b0dc925c3571ee4f7";

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
    }

    @Test()
    public void verifyUserCreatedSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .headers("Content-Type", "application/json","Authorization", "Bearer " + token)
                .when()
                .body(userPojo)
                .post("/public/v2/users");
        response.prettyPrint();
        response.then().statusCode(201);

    }

    @Test
    public void verifyUserUpdateSuccessfully() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(updatedEmail);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .headers("Content-Type", "application/json","Authorization", "Bearer "+token)
                .when()
                .body(userPojo)
                .patch("/public/v2/users/" + userId);
        response.prettyPrint();
        response.then().statusCode(201);

        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        userId = jsonPath.getInt("id");
    }

    @Test
    public void zzVerifyUserDeleteSuccessfully() {

        Response response = given()
                .headers("Content-Type", "application/json","Authorization", "Bearer "+token)
                .pathParam("id", userId)
                .when()
                .delete("/public/v2/users/{id}");
        response.prettyPrint();
        response.then().statusCode(204);

    }
}
