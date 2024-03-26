package com.hello.forum.utils;

import java.util.HashMap;
import java.util.Map;

public class AjaxResponse {

	// 모든 ajax return 은 Map 으로 한다.
	// Map 으로 하면 코드가 길어져 util 을 따로 만들어 관리한다. 
	private Map<String, Object> response;

	public AjaxResponse() {
		this.response = new HashMap<>();
	}

	public AjaxResponse append(String key, Object value) {
		this.response.put(key, value);
		return this;
	}

	public Map<String, Object> getData() {
		return this.response;
	}

}
