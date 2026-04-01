package com.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
	public void loginAPITest() throws IOException{
		//RestAssured code
		
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		
		given()
				.baseUri(getProperty("BASE_URI"))
				.and()
				.contentType(ContentType.JSON)
				.and()
				.accept(ContentType.JSON)
				.body(userCredentials)
				.log().uri()
				.log().headers()
				.log().body()
		.when()
				.post("login")
		.then()
		 		.statusCode(200)
		 		//.time(lessThan(2500L))
		 		.body("message", equalTo("Success"))
		 		.and()
		 		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponseSchema/LoginResponseSchema.json"))
		 		.and()
		 		.log().body();
	}

}
