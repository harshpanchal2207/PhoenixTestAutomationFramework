package com.api.test;

import static com.api.utils.SpecUtil.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;

public class CountAPITest {

	@Test
	public void verifyCountAPIResponse() throws IOException {
		
		
		given()
			.spec(requestspecwithauth(Role.FD))
		.when()
			.get("/dashboard/count")
		.then()
			.spec(responsespec())
			.body("message", equalTo("Success"))
			.body("data", notNullValue())
			.body("data.size()", equalTo(3))
			.body("data.count", everyItem(greaterThanOrEqualTo(0)))
			.body("data.label", everyItem(not(blankOrNullString())))
			.body("data.key", containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"))
			.body(matchesJsonSchemaInClasspath("ResponseSchema/CountAPIResponseSchema.json"));
		
	}
	
	
	@Test
	public void countAPITest_MissingAuthToken() throws IOException {
		given()
			.spec(SpecUtil.requestspec())
		.when()
			.get("/dashboard/count")
	    .then()
			.spec(responsespec(401, ContentType.HTML));
	}
}
