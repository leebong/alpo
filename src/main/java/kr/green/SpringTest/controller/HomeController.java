package kr.green.SpringTest.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.SpringTest.dao.Mapper;
import kr.green.SpringTest.dao.User;


@Controller
public class HomeController {
	@Autowired
	Mapper mapper;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeGet() {
				
		return "/WEB-INF/views/home.jsp";
	}
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String homePost(String id, String pw, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "redirect:/main";
	}
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String mainGet(String id, String pw,Model model) {
		model.addAttribute("id", id);
		return "/WEB-INF/views/main.jsp";
	}
	@RequestMapping(value = "/main", method = RequestMethod.POST)
	public String mainPost(HttpServletRequest request,Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		User user = mapper.getUser(id);
		if(user == null)//계정을 못찾은 경우
			return "redirect:/";
		//계정은 찾았지만 비번이 틀린 경우
		else if(user.getPw().compareTo(pw) != 0)
			return "redirect:/";
		return "/WEB-INF/views/main.jsp";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testPost(Model model, HttpServletRequest request) {
		
		mapper.setUser("1234", "1234", "1234@email");
		return "/WEB-INF/views/test.jsp";
	}
}




