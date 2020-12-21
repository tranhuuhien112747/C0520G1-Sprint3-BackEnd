package c0520g1.sprint_3.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class TypeServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeServices;

    private String typeServicesName;

    @OneToMany(mappedBy = "typeServices", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("typeServices")
    private Collection<Services> servicesCollection;

    public TypeServices() {
    }

    public Long getIdTypeServices() {
        return idTypeServices;
    }

    public void setIdTypeServices(Long idTypeServices) {
        this.idTypeServices = idTypeServices;
    }

    public String getTypeServicesName() {
        return typeServicesName;
    }

    public void setTypeServicesName(String typeServicesName) {
        this.typeServicesName = typeServicesName;
    }

    public Collection<Services> getServicesCollection() {
        return servicesCollection;
    }

    public void setServicesCollection(Collection<Services> servicesCollection) {
        this.servicesCollection = servicesCollection;
    }
}
