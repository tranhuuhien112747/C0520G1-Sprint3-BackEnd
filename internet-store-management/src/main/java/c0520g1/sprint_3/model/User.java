package c0520g1.sprint_3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

/**
 * class question
 * <p>
 * Version 1.0
 * <p>
 * Date: 26/12/2020
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 26/12/2020        Tùng TS          class user
 */

@Entity
public class User {
    public User() {
    }

    public interface checkCreate {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @NotEmpty(message = "Vui lòng nhập tên đăng nhập", groups = checkCreate.class)
    @Pattern(regexp = "^[a-z0-9]{3,30}$", message = "Tên đăng nhập không hợp lệ", groups = checkCreate.class)
    private String username;
    @NotEmpty(message = "Vui lòng nhập họ tên", groups = checkCreate.class)
    @Size(max = 30, message = "Họ tên không hợp lệ", groups = checkCreate.class)
    private String fullName;
    @NotEmpty(message = "Vui lòng nhập mật khẩu", groups = checkCreate.class)
    @Pattern(regexp = "^[a-z0-9]{6,30}$", message = "Mật khẩu không hợp lệ", groups = checkCreate.class)
    private String password;
    @NotEmpty(message = "Vui lòng nhập giới tính", groups = checkCreate.class)
    private Boolean gender;
    private String image;
    @Pattern(regexp = "^[a-z][a-z0-9_\\.]{3,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$", message = "Địa chỉ không hợp lệ", groups = checkCreate.class)
    private String email;
    private String money;
    private String timeRemaining;

    @NotEmpty(message = "Vui lòng nhập ngày sinh", groups = checkCreate.class)
    private String birthday;

    private Boolean status;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @ManyToOne
    @JoinColumn(name = "idRole")
    @JsonIgnoreProperties("userCollection")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private Collection<Bill> billCollection;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private Collection<Computer> computerCollection;

    public Collection<Computer> getComputerCollection() {
        return computerCollection;
    }

    public void setComputerCollection(Collection<Computer> computerCollection) {
        this.computerCollection = computerCollection;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(String timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public Role getRole() {
        return role;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public Collection<Bill> getBillCollection() {
        return billCollection;
    }

    public void setBillCollection(Collection<Bill> billCollection) {
        this.billCollection = billCollection;
    }
}
