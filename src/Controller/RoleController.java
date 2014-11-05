package Controller;
import javax.annotation.Resource;

import Model.*;
import Service.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String getAdd(@RequestParam("id")Integer userId,Model model){
		
		Role role = new Role();
		
		model.addAttribute("userId",userId);
		model.addAttribute("roleAttribute",role);
		
		return "addRole";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String postAdd(@RequestParam("id")Integer userId,@ModelAttribute("roleAttribute")Role role){
		
		roleService.add(userId, role);
		return "redirect:/record/list";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDelete(@RequestParam("id") Integer roleId) {
		
		roleService.delete(roleId);
		return "redirect:/record/list";
	}
   
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam("uid") Integer userId,@RequestParam("rid") Integer roleId, Model model) {
    	
    	Role role1 = roleService.get(roleId);

    	model.addAttribute("userId",userId);
    	model.addAttribute("roleAttribute",role1);

    	return "editChapter";
	}
 
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEdit(@RequestParam("id") Integer roleId,
    		@ModelAttribute("roleAttribute") Role role) {
		
		role.setRoleId(roleId);
		roleService.edit(role);

		return "redirect:/record/list";
	}
    
}

