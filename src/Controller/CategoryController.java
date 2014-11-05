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
import java.sql.Blob;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Resource(name="categoryService")
	private CategoryService categoryService;
	
	@Resource(name="itemService")
	private ItemService itemService;
    
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getRecords(Model model) {
    	
    	List<Category> categories = categoryService.getAll();
    	
    	List<CategoryDTO> categoryDTO = new ArrayList<CategoryDTO>();
    	
    	for (Category category: categories) {
    		CategoryDTO dto = new CategoryDTO();
    		
			dto.setCategoryId(category.getCategoryId());
			dto.setCategoryName(category.getCategoryName());
			
			dto.setItem(itemService.getAll(category.getCategoryId()));
			
			categoryDTO.add(dto);
    	}
    	
    	model.addAttribute("categories", categoryDTO);
		return "AdminRecord";
	}
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
    	
    	model.addAttribute("categoryAttribute", new Category());
    	
    	return "addCategory";
	}
 
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAdd(@ModelAttribute("categoryAttribute") Category category) {
		
    	categoryService.add(category);
		return "redirect:/category/list";
	}
    
 
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDelete(@RequestParam("id") Integer categoryId) {
    	
		categoryService.delete(categoryId);
		return "redirect:/category/list";
	}
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam("id") Integer categoryId, Model model) {
    	
    	Category category1 = categoryService.get(categoryId);
    	model.addAttribute("categoryAttribute",category1);
    	
    	return "editCategory";
	}
 
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEdit(@RequestParam("id") Integer categoryId, 
    						    @ModelAttribute("categoryAttribute") Category category) {
		
		category.setCategoryId(categoryId);
		categoryService.edit(category);
		return "redirect:/category/list";
	}
    
}

