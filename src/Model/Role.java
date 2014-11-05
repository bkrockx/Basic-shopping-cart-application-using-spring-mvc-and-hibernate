package Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="role")
public class Role implements Serializable{
	
	@Id
	@Column(name="roleId")
	@GeneratedValue
	private Integer roleId;
	
	@Column(name="roleName")
	private String roleName;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(
			name="UserRole",
			joinColumns= @JoinColumn(name="ROLE_ID"),
			inverseJoinColumns = @JoinColumn(name="USER_ID")
	)
	private User user;
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
