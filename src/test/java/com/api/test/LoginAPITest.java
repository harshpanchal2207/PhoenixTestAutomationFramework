package com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static com.api.utils.SpecUtil.*;

import java.io.IOException;
import org.testng.annotations.Test;
import com.api.pojo.UserCredentials;


import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
	public void loginAPITest() throws IOException{
		//RestAssured code
		
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		
		given()
				.spec(requestspec(userCredentials))
		.when()
				.post("login")
		.then()
		 		.spec(responsespec())
				
		 		.body("message", equalTo("Success"))
		 		.and()
		 		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponseSchema/LoginResponseSchema.json"));
	}

}
