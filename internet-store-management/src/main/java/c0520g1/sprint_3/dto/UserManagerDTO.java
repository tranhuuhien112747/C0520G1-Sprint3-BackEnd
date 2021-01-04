package c0520g1.sprint_3.dto;

public class UserManagerDTO {
    private Long idUser;
    private String username;
    private String fullName;
    private String password;
    private String newPassword;
    private Boolean gender;
    private String image;
    private String email;
    private String money;
    private String timeRemaining;
    private String birthday;
    private Boolean status;

    public UserManagerDTO() {
    }

    public UserManagerDTO(Long idUser, String username, String fullName, String password, String newPassword, Boolean gender, String image, String email, String money, String timeRemaining, String birthday, Boolean status) {
        this.idUser = idUser;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.newPassword = newPassword;
        this.gender = gender;
        this.image = image;
        this.email = email;
        this.money = money;
        this.timeRemaining = timeRemaining;
        this.birthday = birthday;
        this.status = status;
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
