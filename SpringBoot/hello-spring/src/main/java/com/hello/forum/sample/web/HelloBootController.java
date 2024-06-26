package com.hello.forum.sample.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 *	Servlet : HelloBootServlet.java
 *		web.xml
 *			<servlet>
 *				<servlet-name>HelloBootServlet</servlet-name>
 *				<servlet-class>com.hello.forum.sample.web.HelloBootServlet</servlet-class>
 *			</servlet>
 *			<servlet-mapping>
 *				<servlet-name>HelloBootServlet</servlet-name>
 *				<url-pattern>hello</url-pattern>
 *			</servelt-mapping>
 *
 * 
 * 	HelloBootServlet.java
 * 		doGet(HttpServletRequest request, HttpServletResponse response) { ... }
 * 		doPost(HttpServletRequest request, HttpServletResponse response) { ... }
 * 
 * 	Browser > http://localhost:8080/project-name/hello
 * 
 *  스프링의 Controller 애너테이션이 위 코드의 역할을 해준다.
 */

// Spring이 인스턴스로 만들어주는 대상.
// 브라우저와 서버가 통신(데이터를 주고 받는)할 수 있는 End-point ==> Controller
@Controller		// <-- Servelt의 역할을 수행함.
public class HelloBootController {

	public HelloBootController() {
		// Spring이 호출한다!!! -> 생선된 객체를 Bean Container에 보관한다.
		System.out.println("HelloBootController() 호출됨.");
		System.out.println(this);
	}
	
	@GetMapping("/hello")	// @GetMapping Servelet의 doGet(); 메서드를 수행
							// "/hello" <-- servlet-mapping > url-pattern 값. url mapping을 해줘야 브라우저에서 찾아갈 수 있다. 
	public ResponseEntity<String> hello() {
		ResponseEntity<String> responseBody = new ResponseEntity<>("<h1>Hello Boot Controller</h1>", HttpStatus.OK);
		return responseBody;
	}
	
	// Mapping 주소를 중복해서 사용할 수 없다.
	@GetMapping("/hello-html")
	public ResponseEntity<String> helloHtml() {
		
		StringBuffer html = new StringBuffer();
		html.append(" <!DOCTYPE html> ");
		html.append(" <html> ");
		html.append(" 	<head> ");
		html.append(" 		<title>Hello Spring</title> ");
		html.append(" 	</head> ");
		html.append(" 	<body> ");
		html.append(" 		<div>Hello, Spring Controller</div> ");
		html.append(" 	</body> ");
		html.append(" </html> ");
		
		ResponseEntity<String> responseBody = new ResponseEntity<>(html.toString(), HttpStatus.OK);
		return responseBody;
	}
	
	/*
	 * 서블릿이라면
	 * RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/helloboot.jsp");
	 * rd.forward(request, response);
	 * 를 사용해야함.
	 */
	@GetMapping("/hello-jsp")
	public String helloJSP(Model model) {
		
		// request.setAttribute("myname", "Spring Boot Sample Application"); 과 완전히 같은 코드이다.) - Servlet
		// JSP 파일에서 myname 이라는 key 로 접근할 수 있다.
		// 이런 데이터를 여러 개 보낼 수 있다. 타입도 원하는 대로 보낼 수 있다.
		model.addAttribute("myname", "Spring Boot Sample Application");
		model.addAttribute("createDate", "2024-03-19");
		model.addAttribute("author", "ktds 23th");
		model.addAttribute("version", 1.0);
		
		// Spring (Boot) 1개의 Servlet 내장되어 있다.
		// 내장되어 있는 Servlet이 Controller를 호출한다.
		// 만약 , Controller가 반환시킨 데이터가 String 타입이라면
		// Servlet의 코드 (RequestDispatcher ~~~; rd.forward(request, response); 를 수행한다.
		// 파일의 이름이 반환되었을 경우
		// application.yml에 정의된 prefix, suffix 를 붙인다.
		// /WEB-INF/views/helloboot.jsp 이 경로를 찾아서 브라우저에게 보여준다.
		
		
		
		return "helloboot";
	}
	
	
	
//	@PostMapping
//	@DeleteMapping
}
