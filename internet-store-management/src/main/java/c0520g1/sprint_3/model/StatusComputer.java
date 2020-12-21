package c0520g1.sprint_3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.Collection;

@Entity
public class StatusComputer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStatusComputer;
    private String statusComputerName;

    @OneToMany(mappedBy = "statusComputer", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("statusComputer")
    private Collection<Computer> computerCollection;

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

    public Collection<Computer> getComputerCollection() {
        return computerCollection;
    }

    public void setComputerCollection(Collection<Computer> computerCollection) {
        this.computerCollection = computerCollection;
    }
}
