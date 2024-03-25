package com.hello.forum.beans;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configurable
@EnableWebMvc	// (yml mvc 설정을 나중에 가져옴) MVC와 관련된 여러 가지 기능들이 활성화 된다.
				// MVC와 관련된 설정들은 이 파일에 작성해야 한다.
				// 그 중 하나가 파라미터 유효성 검사이다.
public class WebConfig implements WebMvcConfigurer{
	
}
