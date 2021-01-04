package c0520g1.sprint_3.dto;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
/**
 * ChangePasswordDTO
 * <p>
 * Version 1.0
 * <p>
 * Date: 24-11-2020
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 22-11-2020         HienTH           CRUD
 */
public class ChangePasswordDTO {
    private long id;
    private String userName;

    @NotEmpty(message = "Vui lòng nhập mật khẩu")
    @Pattern(regexp = "^[a-z0-9]{6,30}$", message = "Mật khẩu không hợp lệ")
    private String newPassword;
    private String message;
    public ChangePasswordDTO(long id, String userName, String newPassword) {
        this.id = id;
        this.userName = userName;
        this.newPassword = newPassword;
    }
    public ChangePasswordDTO( String message) {
        this.message = message;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}