package demo.backend.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import demo.backend.model.User;

public class UserDetailsImpl implements UserDetails{

     private final User user;

     public UserDetailsImpl(User user){
          this.user = user;
     }

     @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
          String role = user.getRole().startsWith("ROLE_") ? user.getRole() : "ROLE_" + user.getRole();
          return List.of(new SimpleGrantedAuthority(role));
     }

     @Override
     public String getPassword() {
          return user.getPassword();
     }

     @Override
     public String getUsername() {
          return user.getUsername();
     }

     @Override
     public boolean isAccountNonExpired(){
          return true;
     }

     @Override
     public boolean isCredentialsNonExpired(){
          return true;
     }

     @Override
     public boolean isAccountNonLocked(){
          return true;
     }

     public User getUser(){
          return user;
     }

}
