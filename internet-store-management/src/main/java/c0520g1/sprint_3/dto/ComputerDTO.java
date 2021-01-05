package c0520g1.sprint_3.dto;

import org.hibernate.validator.constraints.Length;

public class ComputerDTO {
    private Long idComputer;
    @Length(min = 3,max = 20,message = "Vui lòng nhập tên máy từ 3 đến 20 ký tự")
    private String computerName;
    @Length(min = 3,max = 20,message = "Vui lòng nhập thowif gian từ 3 đến 20 ký tự")
    private String timeStart;
    @Length(min = 3,max = 20,message = "Vui lòng nhập su dung từ 3 đến 20 ký tự")
    private String timeUser;
    private Boolean status;
    private Long idUser;
    private String fullName;
    private Long idStatusComputer;
    private String statusComputerName;
    private String money;

    public ComputerDTO() {
    }

    public ComputerDTO(String computerName, String timeStart, String timeUser, Boolean status, Long idUser, String fullName, Long idStatusComputer, String statusComputerName) {
        this.computerName = computerName;
        this.timeStart = timeStart;
        this.timeUser = timeUser;
        this.status = status;
        this.idUser = idUser;
        this.fullName = fullName;
        this.idStatusComputer = idStatusComputer;
        this.statusComputerName = statusComputerName;
    }

    public Long getIdComputer() {
        return idComputer;
    }

    public void setIdComputer(Long idComputer) {
        this.idComputer = idComputer;
    }

    public String getComputerName() {
        return computerName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeUser() {
        return timeUser;
    }

    public void setTimeUser(String timeUser) {
        this.timeUser = timeUser;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getIdStatusComputer() {
        return idStatusComputer;
    }

    public void setIdStatusComputer(Long idStatusComputer) {
        this.idStatusComputer = idStatusComputer;
    }

    public String getStatusComputerName() {
        return statusComputerName;
    }

    public void setStatusComputerName(String statusComputerName) {
        this.statusComputerName = statusComputerName;
    }
}
