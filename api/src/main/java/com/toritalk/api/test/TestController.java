package com.toritalk.api.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.common.utils.TestUtils;

@RestController
@RequestMapping("api/")
public class TestController {
	@GetMapping("test")
	public ResponseEntity<String> test() {
		TestUtils utils = new TestUtils();
		return new ResponseEntity<String>(utils.getMessage(), HttpStatus.OK);
	}
}
