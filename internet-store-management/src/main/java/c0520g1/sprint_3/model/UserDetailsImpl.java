package c0520g1.sprint_3.model;//package vn.codegym.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
///**
// * UserDetailsImpl
// *
// * Version 1.0
// *
// * Date: 24/11/2020
// *
// * Copyright
// *
// * Author: Le Toan
// */
//public class UserDetailsImpl implements UserDetails {
//    private long idUser;
//    private String username;
//    @JsonIgnore
//    private String password;
//    private String fullName;
//    private String department;
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public UserDetailsImpl(long idUser, String username, String password, String fullName, String department, Collection<? extends GrantedAuthority> authorities) {
//        this.idUser = idUser;
//        this.username = username;
//        this.password = password;
//        this.fullName = fullName;
//        this.department = department;
//        this.authorities = authorities;
//    }
//
//    public static UserDetailsImpl build(User user) {
//        List<GrantedAuthority> grantList = new ArrayList<>();;
//        grantList.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
//        return new UserDetailsImpl(user.getIdUser(),
//                                    user.getUserName(),
//                                    user.getPassword(),
//                                    user.getFullName(),
//                                    user.getDepartment(),
//                                    grantList);
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public long getIdUser() {
//        return idUser;
//    }
//
//    public void setIdUser(long idUser) {
//        this.idUser = idUser;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public String getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }
//
//    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
//        this.authorities = authorities;
//    }
//}
