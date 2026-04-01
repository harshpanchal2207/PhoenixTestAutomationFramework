package com.api.test;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constants.Role;

import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() throws IOException {
		
		given()
			.baseUri(getProperty("BASE_URI"))
			.and()
			.header("Authorization",getAuthToken(Role.FD))
			.contentType("")
		.when()
			.post("master")
		.then()
			.log().all()
			.statusCode(200)
			.time(lessThan(2500L))
			.body("message", equalTo("Success"))
			.body("data", notNullValue())
			.body("data",hasKey("mst_oem"))
			.body("data",hasKey("mst_model"))
			.body("$",hasKey("message"))
			.body("data.mst_oem.size()", greaterThan(0))
			.body("data.mst_model.size()", greaterThan(0))
			.body("data.mst_oem.id", everyItem(notNullValue()))
			.body("data.mst_oem.name", everyItem(notNullValue()))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponseSchema/MasterAPIResponse.json"));
	}
	
	@Test
	public void invalidTokenMasterAPITest() throws IOException {
		given()
			.baseUri(getProperty("BASE_URI"))
			.and()
			.header("Authorization","")
			.contentType("")
		.when()
			.post("master")
		.then()
			.log().all()
			.statusCode(401);
	}	

}
