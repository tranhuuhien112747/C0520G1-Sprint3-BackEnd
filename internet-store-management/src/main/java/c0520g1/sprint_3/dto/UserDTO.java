package c0520g1.sprint_3.dto;

import java.util.List;

public class UserDTO {
    private String token;
    private String type = "Bearer";
    private long id;
    private String username;
    private String fullName;
    private String money;
    private String timeRemaining;
    private List<String> role;

    public UserDTO() {
    }

    public UserDTO(String token, long id, String username, String fullName, List<String> role, String money,String timeRemaining) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.money = money;
        this.timeRemaining = timeRemaining;
        this.role = role;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
