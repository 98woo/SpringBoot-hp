package com.hello.forum.exceptions;
public class PageNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3272607721385806716L;

	public PageNotFoundException() {
		super("페이지를 찾을 수 없습니다.");
	}
}
