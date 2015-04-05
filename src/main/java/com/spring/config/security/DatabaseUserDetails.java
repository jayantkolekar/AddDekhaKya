package com.spring.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.Entities.RoleMstr;
import com.project.Entities.UserMstr;

public class DatabaseUserDetails extends UserMstr implements UserDetails {
	private static final long serialVersionUID = 1L;
    
    public DatabaseUserDetails(UserMstr user) {
    	if(user != null) {
			this.setUserID(user.getUserID());
			this.setEmailID(user.getEmailID());
			this.setUserPassword(user.getUserPassword());
			//this.setCreatedDate(user.getCreatedDate());
			//this.setModifiedDate(user.getModifiedDate());
			this.setRoles(user.getRoles());
			this.setLogin("true");
			this.setEnabled(true);
		}
    }
 
    public UserMstr getUser() {
        return this;
    }

    public String getPassword() {
        return super.getUserPassword();
    }

    public String getUsername() {
        return super.getEmailID();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }
    
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    public boolean isEnabled() {
        return true;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {  
    	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
 		Set<RoleMstr> roles = this.getRoles();
 		if(roles != null)
 		{
 			for (RoleMstr role : roles) {
 				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
 				authorities.add(authority);
 			}
 		}
		return authorities;
    }

  }

