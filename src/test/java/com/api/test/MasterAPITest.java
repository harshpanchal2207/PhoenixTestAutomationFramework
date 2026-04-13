package com.api.test;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constants.Role;
import static com.api.utils.SpecUtil.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() throws IOException {
		
		given()
			.spec(requestspecwithauth(Role.FD))
			.contentType("")
		.when()
			.post("master")
		.then()
			.spec(responsespec())
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
			.spec(requestspec())
		.when()
			.post("master")
		.then()
			.spec(responsespec(401, ContentType.HTML));
	}	

}
