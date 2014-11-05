package Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import Model.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public List<User> getAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User");
		return query.list();
	}
	
	public User get(Integer userId){
		Session session = sessionFactory.getCurrentSession();
		return (User)session.get(User.class,userId);
	}
	
	public User getuserByName(String userName){
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.like("userName",userName));
		
		Object result = criteria.uniqueResult();
		User user = (User) result;
		
		return user;
	}
	
	public boolean validate(String userName,String password){
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.like("userName",userName));
		
		boolean flag = false;
		Object result = criteria.uniqueResult();
		if(result!=null){
			User user = (User) result;
			if(user.getPassword().equalsIgnoreCase(password)){
				flag = true;
			}
		}
		
		if(flag==true){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void add(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}
	
	public void delete(Integer userId){
		Session session = sessionFactory.getCurrentSession();
		User user = (User)session.get(User.class,userId);
		session.delete(user);
	}

	public void edit(User user){
		Session session = sessionFactory.getCurrentSession();
		User user1 = (User)session.get(User.class,user.getUserId());
		
		user1.setUserName(user.getUserName());
		user1.setPassword(user.getPassword());
		
		session.save(user1);
	}
	
}

