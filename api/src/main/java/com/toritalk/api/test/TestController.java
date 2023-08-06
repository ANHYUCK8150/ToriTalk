package com.toritalk.api.test;

import core.common.handler.BaseApiHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.common.utils.TestUtils;

@RestController
public class TestController extends BaseApiHandler {
	@GetMapping("test")
	public ResponseEntity<String> test() {
		TestUtils utils = new TestUtils();
		return new ResponseEntity<String>(utils.getMessage(), HttpStatus.OK);
	}
}
