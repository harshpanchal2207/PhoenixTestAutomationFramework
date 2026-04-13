package com.api.test;

import static io.restassured.RestAssured.given;
import static com.api.utils.SpecUtil.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constants.Role;


import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	
	@Test
	public void userDetailsAPITest() throws IOException{
		
		given()
				.spec(requestspecwithauth(Role.SUP))
		.when()
				.get("userdetails")
		.then()
				.spec(responsespec())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponseSchema/UserDetailsResponseSchema.json"))
				.and()
				.log().body();
		
	}

}
