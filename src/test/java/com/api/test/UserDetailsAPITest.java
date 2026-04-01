package com.api.test;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class UserDetailsAPITest {
	
	@Test
	public void userDetailsAPITest() throws IOException{
		
		Header authToken = new Header("authorization", AuthTokenProvider.getAuthToken(Role.SUP));
		
		given()
				.baseUri(getProperty("BASE_URI"))
				.and()
				.header(authToken)
				.and()
				.accept(ContentType.JSON)
				.log().headers()
		.when()
				.get("userdetails")
		.then()
				.statusCode(200)
				.time(lessThan(2000L))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponseSchema/UserDetailsResponseSchema.json"))
				.and()
				.log().body();
		
	}

}
