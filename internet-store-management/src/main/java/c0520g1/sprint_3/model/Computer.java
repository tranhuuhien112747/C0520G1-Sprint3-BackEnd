package c0520g1.sprint_3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComputer;
    private String computerName;
    private String timeStart;
    private String timeUser;
    private String status;
    @ManyToMany()
    @JoinTable(name = "computer_user", joinColumns = @JoinColumn(name="idComputer"), inverseJoinColumns = @JoinColumn(name="idUser"))
    @JsonIgnoreProperties("computers")
    private Set<User> users;

    @ManyToOne
    @JoinColumn(name = "idStatusComputer")
    @JsonIgnoreProperties("computerCollection")
    private StatusComputer statusComputer;

    public Long getIdComputer() {
        return idComputer;
    }

    public void setIdComputer(Long idComputer) {
        this.idComputer = idComputer;
    }

    public String getComputerName() {
        return computerName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public StatusComputer getStatusComputer() {
        return statusComputer;
    }

    public void setStatusComputer(StatusComputer statusComputer) {
        this.statusComputer = statusComputer;
    }
}
