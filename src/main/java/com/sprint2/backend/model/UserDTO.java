package com.sprint2.backend.model;

import java.util.List;

public class UserDTO {
  private String token;
  private String type = "Bearer";
  private long id;
  private String username;
  private List<String> role;

  public UserDTO() {
  }

  public UserDTO(String token, long id, String username, List<String> role) {
    this.token = token;
    this.id = id;
    this.username = username;
    this.role = role;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRole() {
    return role;
  }

  public void setRole(List<String> role) {
    this.role = role;
  }
}
