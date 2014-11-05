package Service;

import java.util.*;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import Model.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;

import org.springframework.web.multipart.MultipartFile;

@Service("itemService")
@Transactional
public class ItemService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public List<Item> getAll(Integer categoryId){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Category as c WHERE c.id="+categoryId);
		Category category = (Category)query.uniqueResult();
		return new ArrayList<Item>(category.getItem());
	}
	
	public List<Item> getAllItem(Integer userId){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User as u WHERE u.id="+userId);
		User user = (User)query.uniqueResult();
		return new ArrayList<Item>(user.getItem());
	}

	public List<Item>getAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Item");
		return query.list();
	}
	
	public Item get(Integer itemId){
		Session session = sessionFactory.getCurrentSession();
		Item item = (Item)session.get(Item.class,itemId);
		return item;
	}
	
	public void add(Integer categoryId,Item item,MultipartFile file) throws IOException{
		
		Session session = sessionFactory.getCurrentSession();
        Item item1 = new Item();
        item1.setItemImage(file.getBytes());
        item1.setItemId(item.getItemId());
        item1.setItemName(item.getItemName());
        item1.setItemContent(item.getItemContent());
        item1.setItemPrice(item.getItemPrice());
 
		session.save(item1);
		
		Category category1 = (Category)session.get(Category.class,categoryId);
		category1.getItem().add(item1);
		
		session.save(category1);
	}
	
	// To save item  //
	public void saveItem(Item item){
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(item);
	}
	
	// To add item to user // 
	public void addItemToUser(Integer userId,Integer itemId){
		Session session = sessionFactory.getCurrentSession();
		Item item2 = (Item)session.get(Item.class,itemId);
		
		User user2 = (User)session.get(User.class,userId);
		user2.getItem().add(item2);
		
		session.save(user2);
	}
	
	public void removeAllItems(Integer userId){
		Session session = sessionFactory.getCurrentSession();
		User user3 = (User) session.get(User.class,userId);
		user3.getItem().clear();
	}
	
	public void delete(Integer itemId){
		Session session = sessionFactory.getCurrentSession();
		
		Item item = (Item)session.get(Item.class,itemId);
		session.delete(item);
	}
	
	public void edit(Item item,MultipartFile file) throws IOException{
		Session session = sessionFactory.getCurrentSession();
		Item item2 = (Item)session.get(Item.class,item.getItemId());
		
		item2.setItemName(item.getItemName());
		item2.setItemContent(item.getItemContent());
		//Item2.setItemImage(Item.getItemImage());
		item2.setItemPrice(item.getItemPrice());
		item2.setItemImage(file.getBytes());
		session.save(item2);
	}
	
}


