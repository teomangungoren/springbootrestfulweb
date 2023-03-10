package com.teomangungoren.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("teoman", "gungoren"));
	}
	
	@GetMapping(path = "/person",params = "version=1")
	public PersonV1 getFirstVersionOfPersonRequestParameter() {
		return new PersonV1("Beyza Gungoren");
	}
	
	@GetMapping(path="/person",params="version=2")
	public PersonV2 getSecondVersionOfPersonRequestParamater() {
		return new PersonV2(new Name("Beyza","Gungoren"));
	}
	
	@GetMapping(path = "/person",headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonRequestHeader(){
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path = "/person",headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonRequestHeader(){
		return new PersonV2(new Name("Beyza","Gungoren"));
	}
	
	@GetMapping(path="/person/accept",produces ="application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeader() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path="/person/accept",produces="application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeader() {
		return new PersonV2(new Name("Bob","Charlie"));
	}
}
