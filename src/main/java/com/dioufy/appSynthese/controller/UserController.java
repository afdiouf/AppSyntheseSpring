package com.dioufy.appSynthese.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dioufy.appSynthese.model.User;
import com.dioufy.appSynthese.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String home(Model model) {
		return this.findPaginated(1, "firstName", "asc", model);
	}

	@GetMapping("/showUserForm")
	public String showUserForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "newUserForm";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute(value = "user") User user) {
		this.userService.saveUser(user);
		return "redirect:/";
	}

	@GetMapping("/updateUser/{id}")
	public String updateUser(@PathVariable(value = "id") long id, Model model) {
		User user = this.userService.getUserById(id);
		model.addAttribute("user", user);
		return "updateUserForm";
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(value = "id") long id) {
		this.userService.deleteUserById(id);
		return "redirect:/";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam(value = "sortField") String sortField, 
			@RequestParam(value = "sortDir") String sortDir,
			Model model) {
		
		int pageSize = 8;
		
		Page<User> page = this.userService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<User> listUser = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listUser", listUser);
		
		return "index";
	}
	
}
