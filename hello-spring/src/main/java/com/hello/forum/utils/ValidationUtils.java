package com.hello.forum.utils;

/**
 * Spring Validator 를 사용하지 않고
 * 파라미터 유효성 검사를 하기 위한 유틸리티이다.
 * -> Utility 클래스는 추상 클래스로 만들어야 한다.
 */
public abstract class ValidationUtils {

	// 익명클래스를 생성하지 못 하도록 생성자 생성.
	private ValidationUtils() {}
	
	// 비어 있으면 true 비어 있지 않으면 false
	public final static boolean notEmpty(String value) {
		return ! StringUtils.isEmpty(value);
	}
	
	// email 포맷이면 true 아니면 false
	public final static boolean email(String value) {
		return StringUtils.isEmailFormat(value);
	}
	
	
}
