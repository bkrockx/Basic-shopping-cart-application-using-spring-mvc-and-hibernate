package Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import Dto.*;
import Model.*;
import Service.*;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Resource(name="categoryService")
	private CategoryService categoryService;
	
	@Resource(name="itemService")
	private ItemService itemService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="billableService")
	private BillableService billableService;
	
	//---------------------------- to list all the items(GET)-------------------------------------------//
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getRecords(Model model,HttpSession sess,HttpServletRequest request) {
    	
		sess = request.getSession(false);
		String  userName = (String)sess.getAttribute("users");
		
    	List<Item> items = itemService.getAll();
    	
    	List<ItemDTO> itemDTO = new ArrayList<ItemDTO>();
    	
    	for (Item item: items) {
    		ItemDTO dto = new ItemDTO();
    		
			dto.setItemId(item.getItemId());
			dto.setItemName(item.getItemName());
			dto.setItemPrice(item.getItemPrice());
			dto.setItemContent(item.getItemContent());
			dto.setItemImage(item.getItemImage());
			
			itemDTO.add(dto);
			
    	}
    	List<Category> categories = categoryService.getAll();
    	
    	List<CategoryDTO> categoryDTO = new ArrayList<CategoryDTO>();
    	
    	for (Category category: categories) {
    		CategoryDTO dto = new CategoryDTO();
    		
			dto.setCategoryId(category.getCategoryId());
			dto.setCategoryName(category.getCategoryName());
			
			dto.setItem(itemService.getAll(category.getCategoryId()));
			
			categoryDTO.add(dto);
    	}
   
    	Category category = new Category();
    	model.addAttribute("userName",userName);
    	model.addAttribute("items",itemDTO);
		model.addAttribute("categories", categoryDTO);
		model.addAttribute("categoryAttribute",category);
    	return "ItemRecord";
	}
	
	//---------------------------- to list all the items(POST)--------------------------------------------//
	@RequestMapping(value = "/list", method = RequestMethod.POST)
    public String postRecords(HttpSession session1,HttpServletRequest req,@ModelAttribute("categoryAttribute")Category cat,Model model) {
    	
		session1 = req.getSession(false);
		String userName = (String) session1.getAttribute("users");
		
		Category catg = categoryService.getCategoryByName(cat.getCategoryName());
    	List<Item> items = itemService.getAll(catg.getCategoryId());
    	
    	List<ItemDTO> itemDTO = new ArrayList<ItemDTO>();
    	
    	for (Item item: items) {
    		ItemDTO dto = new ItemDTO();
    		
			dto.setItemId(item.getItemId());
			dto.setItemName(item.getItemName());
			dto.setItemPrice(item.getItemPrice());
			dto.setItemContent(item.getItemContent());
			dto.setItemImage(item.getItemImage());
			
			itemDTO.add(dto);
			
    	}
    	List<Category> categories = categoryService.getAll();
    	
    	List<CategoryDTO> categoryDTO = new ArrayList<CategoryDTO>();
    	
    	for (Category category: categories) {
    		CategoryDTO dto = new CategoryDTO();
    		
			dto.setCategoryId(category.getCategoryId());
			dto.setCategoryName(category.getCategoryName());
			
			dto.setItem(itemService.getAll(category.getCategoryId()));
			
			categoryDTO.add(dto);
    	}
    	
    	model.addAttribute("userName",userName);
    	model.addAttribute("items",itemDTO);
		model.addAttribute("categories", categoryDTO);
		model.addAttribute("categoryAttribute",catg);
    	return "ItemRecord";
	}
	
	//---------------------------------- adding items to category(GET)--------------------------------//
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String getAdd(@RequestParam("id")Integer categoryId,Model model){
		
		Item item = new Item();
		
		model.addAttribute("categoryId",categoryId);
		model.addAttribute("itemAttribute",item);
		
		return "addItem";
	}
	
	//
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String postAdd(@RequestParam("file")MultipartFile file,@RequestParam("id")Integer categoryId,@ModelAttribute("itemAttribute")Item item) throws IOException{
		
		itemService.add(categoryId, item,file);
		return "redirect:/category/list";
	}
	
	//-------------------adding item to cart of user---------------------------//
	
	@RequestMapping(value="/addToUser")
	public String getAddToUser(@RequestParam("bid")String userName,@RequestParam("cid")Integer itemId,Model model){
		
		User user = userService.getuserByName(userName);
		Item item = itemService.get(itemId);
		
		itemService.addItemToUser(user.getUserId(),item.getItemId());
		
		List<Item> items = itemService.getAllItem(user.getUserId());
		
		int sum = 0;
		for(Item item1: items){
			System.out.println(item1.getItemName());
			System.out.println(item1.getItemPrice());
			sum = sum + item1.getItemPrice();
		}
		
		System.out.println(sum);
		System.out.println(user.getUserName());
		
		model.addAttribute("CurrentUser",user);
		model.addAttribute("CurrentItems",items);
		model.addAttribute("Total",sum);
		model.addAttribute("billableAttribute",new billable());
		
		return "CartRecord";
		
	}
	// -----------------------------------------------------------------------------------//
	
	// -------------------------- checkOut -----------------------------------------------//
	 @RequestMapping(value="/checkOut",method = RequestMethod.POST)
	    public String checkOut(@RequestParam("id")String userName,@RequestParam("rName")String rName,@RequestParam("bAddress")String bAddress,Model model){
		 
		 billable bill = new billable();
		 bill.setBillableName(rName);
		 bill.setBillableAddress(bAddress);
		
		 User user = userService.getuserByName(userName);
		 
		 bill.setUser(user);
		 
		 Set<Item> s = (Set)user.getItem();
		 Set<Item> items1 = new HashSet<Item>();
		 
		 for(Item i:s){	 
			 Item myItem = (Item)i;
			 //myItem.setUser(user);
			 //myItem.setBillable(bill);
			 itemService.saveItem(myItem);
			 items1.add(myItem);
		 }
		 
		 bill.setItem(items1);
		 
		 /*
		 Set<Item> s1 = (Set)bill.getItem();
		 
		 Iterator iter = s1.iterator();
		 while(iter.hasNext()){
			 int i = 1;
			 System.out.println(i);
			 Item item4 = (Item)iter.next();
			 System.out.println(item4.getItemName());
			 i++;
		 }
		 
		 */
		 
		 billableService.add(bill);
		 
		 List<Item> items = itemService.getAllItem(user.getUserId());
		 
		 model.addAttribute("orderedItems",items);
		 model.addAttribute("loggedUser",user);
		 model.addAttribute("rName",rName);
		 model.addAttribute("rAddress",bAddress);
		 
		 int total = 0;
		 for(Item item2: items){
			 System.out.println(item2.getItemName());
			 total = total + item2.getItemPrice();
			 System.out.println(total);
		 }
		 model.addAttribute("Total",total);
		 /*
		 List<Item> items1 = itemService.getAllItem(user.getUserId());
		 
		 Iterator<Item> iter = items1.iterator();
		 while (iter.hasNext()) {
		    iter.remove();
		 }
		 */
		 
		 itemService.removeAllItems(user.getUserId());
		 return "userCheckOut";
	    	
	 }
	// ------------------------------------------------------------------------------------- //
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDelete(@RequestParam("id") Integer itemId) {
		
		itemService.delete(itemId);
		return "redirect:/category/list";
	}
   
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam("bid") Integer categoryId,@RequestParam("cid") Integer itemId, Model model) {
    	
    	Item item2 = itemService.get(itemId);

    	model.addAttribute("categoryId",categoryId);
    	model.addAttribute("itemAttribute",item2);

    	return "editItem";
	}
 
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEdit(@RequestParam("file")MultipartFile file,@RequestParam("id") Integer itemId,
    		@ModelAttribute("itemAttribute") Item item) throws IOException {
		
		item.setItemId(itemId);
		itemService.edit(item,file);

		return "redirect:/category/list";
	}
 
}

