package com.hello.forum.member.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hello.forum.member.service.MemberService;
import com.hello.forum.member.vo.MemberVO;
import com.hello.forum.utils.AjaxResponse;
import com.hello.forum.utils.ValidationUtils;
import com.hello.forum.utils.Validator;
import com.hello.forum.utils.Validator.Type;

import jakarta.servlet.http.HttpSession;



@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/member/regist")
	public String viewRegistMemberPage(Model model) {
		
		System.out.println("member member member");
		return "member/memberregist";
	}
	
	// http://localhost:8080/member/regist/available?email=aaa@aaa.com
	@ResponseBody // 응답하는 데이터를 JSON으로 변환해 클라이언트에게 반환한다.
	@GetMapping("/member/regist/available")
	public Map<String, Object> checkAvailableEmail(@RequestParam String email) {
		
		// 사용 가능한 이메일이라면 true
		// 아니라면 false
		boolean isAvailableEmail = this.memberService.checkAvailableEmail(email);
		
		/*
		 * ResponseBody 어노테이션을 사용 했을 때 Map 을 반환시키면 key value 를 이용해서 아래와 같은 객체 리터럴을 반환한다. 
		 * Spring 이 바꿔준다.
		 * {
		 * 	"email": "aaa@aaa.com",
		 *  "availalbe": false
		 *  }
		 */
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("email", email);
		responseMap.put("available", isAvailableEmail);
		
		return responseMap;
	}
	
	@PostMapping("/member/regist")
	public String doRegistMember(MemberVO memberVO, Model model, BindingResult bindingResult) {
		
		boolean isNotEmptyEmail = ValidationUtils.notEmpty(memberVO.getEmail());
		boolean isNotEmptyName = ValidationUtils.notEmpty(memberVO.getName());
		boolean isNotEmptyPassword = ValidationUtils.notEmpty(memberVO.getPassword());
		boolean isNotEmptyConfirmPassword = ValidationUtils.notEmpty(memberVO.getConfirmPassword());
		
		if (!isNotEmptyEmail) {
			model.addAttribute("errorMessage", "이메일을 입력하세요.");
			model.addAttribute("memberVO", memberVO);
			
			return "member/memberregist";
		}
		
		if (!isNotEmptyName) {
			model.addAttribute("errorMessage", "이름을 입력하세요.");
			model.addAttribute("memberVO", memberVO);
			
			return "member/memberregist";
		}
		
		if (!isNotEmptyPassword) {
			model.addAttribute("errorMessgae", "비밀번호를 입력하세요.");
			model.addAttribute("memberVO", memberVO);
			
			return "member/memberregist";
		}
		
		if (!isNotEmptyConfirmPassword) {
			model.addAttribute("errorMessage", "비밀번호 확인을 입력하세요.");
			model.addAttribute("memberVO", memberVO);
			
			return "member/memberregist";
		}
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("memberVO", memberVO);
			return "member/memberregist";
			
		}
		
		boolean isSuccess = this.memberService.createNewMember(memberVO);
		
		if (isSuccess) {
			
			return "redirect:/member/login"; //login
		}
		
		model.addAttribute("memberVO", memberVO);
		return "redirect:/member/regist";
	}
	
//	@ResponseBody // JSON 으로 응답한다.
//	@GetMapping("/member/regist/available")
//	public Map<String, Object> checkAvailalbeEmail(@RequestParam String email) {
//		
//		boolean isAvailableEmail = memberService.checkAvailableEmail(email);
//		
//		Map<String, Object> responseMap = memberService.checkAvailableEmail(email);
//		// Map을 Return 하면 @ResponseBody 에 의해 JSON 으로 변환되어 응답된다.
//		return responseMap;
//	}
	
	@GetMapping("member/login")
	public String viewLoginPage() {
		return "member/memberlogin";
	}
	
	// ajax 를 반환 시킬 수 있는 MAP 을 사용한다.
	
	@ResponseBody
	@PostMapping("/member/login")
	public AjaxResponse doLogin(MemberVO memberVO, HttpSession session) {
		
		// Validation Check 파라미터 유효성 검사
		Validator<MemberVO> validator = new Validator<>(memberVO);
		//			  검사할 변수  검사 내용 
		validator.add("email", Type.NOT_EMPTY, "이메일을 입력해주세요.")
				 .add("email", Type.EMAIL, "이메일 형식이 아닙니다.")
				 .add("password", Type.NOT_EMPTY, "비밀번호를 입력해주세요")
				 .start();	// 유효성 검사를 시작한다. 
		
		if (validator.hasErrors()) {
			Map<String, List<String>> errors = validator.getErrors();	// 복잡한 형태의 제네릭을 반환한다.
			return new AjaxResponse().append("errors", errors);
		}
		
		
		
		try {
		// 로그인이 정상적으로 이루어졌다면 세션을 생성한다.
			MemberVO member = this.memberService.getMember(memberVO);
			
			System.out.println(session.getId());
			session.setAttribute("_LOGIN_USER_", member);
		} catch (IllegalArgumentException iae) {
			// 로그인에 실패했다면 화면으로 실패 사유를 보내준다.
			return new AjaxResponse().append("errorMessage", iae.getMessage());
		}
		
		
		
		return new AjaxResponse().append("next", "/board/list");
	}
}