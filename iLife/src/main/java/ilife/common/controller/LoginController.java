package ilife.common.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal) {
		String name = principal.getName(); // get logged in username

		model.addAttribute("username", name);
		model.addAttribute("message", "Spring Security login + database example");
		return "index";
	}

	
	
}
