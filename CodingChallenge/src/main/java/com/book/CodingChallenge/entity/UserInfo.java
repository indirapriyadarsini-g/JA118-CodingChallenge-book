package com.book.CodingChallenge.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;




import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserInfo implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
//	@Column(unique = true)
	private String username;
	
	private String password;
	
	
	private String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private boolean isEnabled = true;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		/*  convert your role into authority of type GrantedAuthority  */
		List<GrantedAuthority> list = new ArrayList<>();
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority(role.toString());
		list.add(sga);
		return list;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", isEnabled=" + isEnabled + "]";
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public UserInfo(int id, String username, String password, String role, boolean isEnabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.isEnabled = isEnabled;
	}

	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserInfo(String username, String password) {
		super();
		this.username = username;
		this.password = password;
//		this.role = RoleType.USER;
//		this.isEnabled = true;
	}
}
