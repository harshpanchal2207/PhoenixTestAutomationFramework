package com.api.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.api.utils.ConfigManager.*;

import java.io.IOException;

import com.api.constants.Role;


public class SpecUtil {
	// Static Methods

	public static RequestSpecification requestspec() throws IOException {
		// To take care of common request sections

		RequestSpecification request = new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.log(LogDetail.URI)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.build();
		
		return request;
	}
	
	public static RequestSpecification requestspec(Object payload) throws IOException {
		// To take care of common request sections

		RequestSpecification request = new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.setBody(payload)
				.log(LogDetail.URI)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.build();
		
		return request;
	}
	
	
	public static RequestSpecification requestspecwithauth(Role role) throws IOException {
		// To take care of common request sections

		RequestSpecification request = new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getAuthToken(role))
				.log(LogDetail.URI)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.build();
		
		return request;
	}
	
	public static ResponseSpecification responsespec() {
		ResponseSpecification response = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.log(LogDetail.ALL)
				.build();
		
		return response;		
	}
	
	public static ResponseSpecification responsespec(int statusCode, ContentType contentType) {
		ResponseSpecification response = new ResponseSpecBuilder()
				.expectContentType(contentType)
				.expectStatusCode(statusCode)
				.log(LogDetail.ALL)
				.build();
		
		return response;		
	}
}
