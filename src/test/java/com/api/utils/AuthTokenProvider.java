package com.api.utils;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import com.api.constants.Role;
import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	private AuthTokenProvider() {
		//private constructor
	}
	
	public static String getAuthToken(Role role) throws IOException {
		
		UserCredentials userCredentials = null;
		
		if(role == Role.FD) {
			userCredentials = new UserCredentials("iamfd","password");
		}
		else if (role == Role.SUP) {
			userCredentials = new UserCredentials("iamsup","password");
		}
		else if (role == Role.ENG) {
			userCredentials = new UserCredentials("iameng","password");
			
		}
		else if (role == Role.QC) {
			userCredentials = new UserCredentials("iamqc","password");
		}
		
		
	String token = 	given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
			.contentType(ContentType.JSON)
			.body(userCredentials)
		.when()
			.post("login")
		.then()
			.log().body()
			.extract()
			.body()
			.jsonPath()
			.getString("data.token");
	
	return token;
	}

}
