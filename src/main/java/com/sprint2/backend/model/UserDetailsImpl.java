package com.sprint2.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sprint2.backend.entity.AppAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
  private long idUser;
  private String username;
  @JsonIgnore
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(long idUser, String username, String password, Collection<? extends GrantedAuthority> authorities) {
    this.idUser = idUser;
    this.username = username;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(AppAccount user) {
    List<GrantedAuthority> grantList = new ArrayList<>();
    ;
    grantList.add(new SimpleGrantedAuthority(user.getAppRole().getRoleName()));
    return new UserDetailsImpl(user.getId(),
      user.getUsername(),
      user.getPassword(),
      grantList);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public long getIdUser() {
    return idUser;
  }

  public void setIdUser(long idUser) {
    this.idUser = idUser;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }

}
