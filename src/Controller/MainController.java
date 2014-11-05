package Controller;

import java.util.*;


import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Model.*;
import Dto.*;
import Service.*;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@RequestMapping("/record")
public class MainController {
	
	@Resource(name="userService")
	private UserService userService; 
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	@RequestMapping(value="/login")
	public String GetFront(Model model){
		return "login";
	}
	
	/*
	@RequestMapping(value="/admin")
	public String Front1(Model model){
		return "admin";
	}
	
	@RequestMapping(value="/Hello")
	public String Front2(Model model){
		return "Hello";
	}
	
	@RequestMapping(value="/error")
	public String Front3(Model model){
		return "error";
	}
	*/
	
	@RequestMapping(value="/authenticate",method = RequestMethod.POST)
    public String authenticate(@RequestParam("uname")String userName,@RequestParam("pass")String password,
    		HttpSession session,HttpServletRequest request,Model model){
    	
    	boolean success = userService.validate(userName,password);
    	User user = userService.getuserByName(userName);
    	Set<Role> roles = user.getRole();
    	String role = null;
    	
    	for (Role role1: roles) {   		
    		if(role1.getRoleName().equalsIgnoreCase("admin")){
    			role = "admin";
    		}    		
    	}
    	
    	if(success){
    		session = request.getSession();
    		session.setAttribute("users",userName);
    		session.setAttribute("role", roles);
    		return "Hello";
    	}
    	else{
    		return "error";
    	}
    
    }
	
	/*
	@RequestMapping(value="/admin")
	public String admin(HttpSession session,HttpServletRequest request,Model model){
		
		HttpSession session3 = request.getSession(false);
		Set<Role> roles = (Set<Role>) session3.getAttribute("role");
		boolean permit = false;
		
		for (Role role1: roles) {   		
    		if(role1.getRoleName().equalsIgnoreCase("admin")){
    			permit = true;
    		}    		
    	}
		
		if(permit){
			return "admin";
		}
		else{
			return "error";
		}
	}
	*/
	
	@RequestMapping(value="/Logout")
	public String Logout(HttpSession session,HttpServletRequest request,Model model){
		HttpSession session2 = request.getSession(false);
		session2.removeAttribute("users");
		session2.removeAttribute("role");
		if(session2!=null){
			session2.invalidate();
		}
		return "logout";
	}
    
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getRecords(Model model) {
    	
    	List<User> users = userService.getAll();
    	
    	List<UserDTO> userDTO = new ArrayList<UserDTO>();
    	
    	for (User user: users) {
    		UserDTO dto = new UserDTO();
    		
			dto.setUserId(user.getUserId());
			dto.setUserName(user.getUserName());
			dto.setPassword(user.getPassword());
			dto.setRole(roleService.getAll(user.getUserId()));
			
			userDTO.add(dto);
    	}
    	
    	model.addAttribute("users", userDTO);
		return "record";
	}
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
    	
    	model.addAttribute("userAttribute", new User());
    	
    	return "addUser";
	}
 
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAdd(@ModelAttribute("userAttribute") User user) {
		
    	userService.add(user);
		return "redirect:/record/list";
	}
    
 
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDelete(@RequestParam("id") Integer userId) {
    	
		userService.delete(userId);
		return "redirect:/record/list";
	}
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam("id") Integer userId, Model model) {
    	
    	User user1 = userService.get(userId);
    	model.addAttribute("userAttribute",user1);
    	
    	return "editUser";
	}
 
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEdit(@RequestParam("id") Integer userId, 
    						    @ModelAttribute("userAttribute") User user) {
		
		user.setUserId(userId);
		userService.edit(user);
		return "redirect:/record/list";
	}

}
