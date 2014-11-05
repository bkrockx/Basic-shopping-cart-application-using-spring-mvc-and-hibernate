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

@Service("roleService")
@Transactional
public class RoleService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public List<Role> getAll(Integer userId){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User as u WHERE u.id="+userId);
		User user = (User)query.uniqueResult();
		return new ArrayList<Role>(user.getRole());
	}

	public List<Role>getAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Role");
		return query.list();
	}
	
	public Role get(Integer roleId){
		Session session = sessionFactory.getCurrentSession();
		Role role = (Role)session.get(Role.class,roleId);
		return role;
	}
	
	public void add(Integer userId,Role role){
		Session session = sessionFactory.getCurrentSession();
		session.save(role);
		
		User user1 = (User)session.get(User.class,userId);
		user1.getRole().add(role);
		
		session.save(user1);
	}
	
	public void delete(Integer roleId){
		Session session = sessionFactory.getCurrentSession();
		
		Role role = (Role)session.get(Role.class,roleId);
		session.delete(role);
	}
	
	public void edit(Role role){
		Session session = sessionFactory.getCurrentSession();
		Role role1 = (Role)session.get(Role.class,role.getRoleId());
		
		role1.setRoleName(role.getRoleName());
		session.save(role1);
	}
	
}

