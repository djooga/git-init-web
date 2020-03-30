package web.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.core.domain.Message;
import web.core.repos.MessageRepo;

@Controller
public class MainController {

	@Autowired
	private MessageRepo messageRepo;

	@GetMapping("/")
	public String greeting(Map<String, Object> model) {
		return "greeting";
	}

	@GetMapping("/main")
	public String main(Map<String, Object> model) {
		Iterable<Message> messages = messageRepo.findAll();
		model.put("messages", messages);
		return "main";
	}

	@PostMapping("/main")
	public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
		Message newM = new Message(text, tag);
		messageRepo.save(newM);
		Iterable<Message> messages = messageRepo.findAll();
		model.put("messages", messages);
		return "main";
	}

	@PostMapping("/filter")
	public String filter(@RequestParam String filter, Map<String, Object> model) {
		Iterable<Message> messages;
		if (!filter.isEmpty() && filter != null) {
			messages = messageRepo.findByTag(filter);
		} else {
			messages = messageRepo.findAll();
		}
		model.put("messages", messages);
		return "main";
	}

}