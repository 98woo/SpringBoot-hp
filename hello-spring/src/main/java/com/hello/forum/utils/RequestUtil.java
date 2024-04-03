package com.hello.forum.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

public abstract class RequestUtil {

	private RequestUtil() {
		
	}
	
	public static HttpServletRequest getRequest() {
		
		// Spring 이 관리하는 Request 객체를 얻어온다.
		// 요청과 관련된 모든 정보를 스프링에게 돌려준다.
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		
		// Request 객체를 가져오기 위해서 ServletRequestAttributes 로 변경한다.
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		
		return sra.getRequest();
		
		/*
		 * 한줄 작성
		 * HttpServletRequest request = ((ServletRequestAttributes) RequestContestHolder.getRequestAttributes()).getRequest();
		 */
	}
}
