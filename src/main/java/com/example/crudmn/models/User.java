package com.example.crudmn.models;

import java.util.*;
import java.util.stream.Collectors;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Indexed;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Document(collection = "users")
public class User implements UserDetails {
  @Id
  private String id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Date creationDate = new Date();

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  @NotBlank
  @Size(max = 120)
  private String password;

  @NotBlank
  @Size(min = 8)
  private String phonenumber;



  private Adress address;
  private List<Hobby> hobbies;

  private UserInfo userinfo ;

  public UserInfo getUserinfo() {
    return userinfo;
  }



  List<String> following = new ArrayList<>();

  List<String> follower = new ArrayList<>();

  public List<String> getFollowing() {
    return following;
  }

  public void setFollowing(List<String> following) {
    this.following = following;
  }

  public List<String> getFollower() {
    return follower;
  }

  public void setFollower(List<String> follower) {
    this.follower = follower;
  }

  public void setUserinfo(UserInfo userinfo) {
    this.userinfo = userinfo;
  }

  public Adress getAddress() {
    return address;
  }

  public void setAddress(Adress address) {
    this.address = address;
  }

  public List<Hobby> getHobbies() {
    return hobbies;
  }

  public void setHobbies(List<Hobby> hobbies) {
    this.hobbies = hobbies;
  }

  public String getPhonenumber() {
    return phonenumber;
  }



  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }

  @DBRef
  private Set<Role> roles = new HashSet<>();

  @DBRef
  private List<Account> accounts = new ArrayList<>();

  @DBRef(lazy = true)
  private List<Photos> photos = new ArrayList<>();

  public List<Photos> getPhotos() {
    return photos;
  }

  public void setPhotos(List<Photos> photos) {
    this.photos = photos;
  }

  private  Gender gender ;

  public Gender getGender() {
    return gender;
  }

  private boolean enabled;

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public void setAuthorities(Set<String> authorities) {
    this.authorities = authorities;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public List<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<Account> accounts) {
    this.accounts = accounts;
  }

  public User() {
  }

  public User(String username, String email, String password, String phonenumber) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.phonenumber = phonenumber;
  }

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{" +
            "id='" + id + '\'' +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", creationDate=" + creationDate +
            ", password='" + password + '\'' +
            ", phonenumber='" + phonenumber + '\'' +
            ", address=" + address +
            ", hobbies=" + hobbies +
            ", roles=" + roles +
            ", accounts=" + accounts +
            '}';
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  private Set<String>  authorities;

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
     return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}