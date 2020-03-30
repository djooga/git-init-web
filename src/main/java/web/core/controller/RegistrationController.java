package web.core.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import web.core.domain.Role;
import web.core.domain.User;
import web.core.repos.UserRepo;

@Controller
public class RegistrationController {

	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/registration")
	private String registration() {
		return "registration";
	}
	
	@PostMapping("/registration")
	private String addUser(User user,Map<String, Object> model) {
		User userFromDb = userRepo.findByUsername(user.getUsername());
		if (userFromDb!=null) {
			model.put("message", "user exist");
			return "registration";
		}
		
		user.setActive(true);
		
		user.setRoles(Collections.singleton(Role.USER));
		userRepo.save(user);
		
		return "redirect:/login";
	}
}
