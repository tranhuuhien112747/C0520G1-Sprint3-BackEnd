package c0520g1.sprint_3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Bill {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idBill;
  private boolean status;
  private boolean statusDisplay;
  @ManyToOne
  @JoinColumn(name = "idUser")
  @JsonIgnoreProperties("billCollection")
  private User user;
  @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
  @JsonIgnoreProperties("bill")
  private Collection<BillServices> billServiceCollection;

  //    @ManyToMany(mappedBy = "bills", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("bills")
//    private Set<Services> services;
  public Collection<BillServices> getBillServiceCollection() {
    return billServiceCollection;
  }

  public void setBillServiceCollection(Collection<BillServices> billServiceCollection) {
    this.billServiceCollection = billServiceCollection;
  }

  public Long getIdBill() {
    return idBill;
  }

  public void setIdBill(Long idBill) {
    this.idBill = idBill;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public boolean isStatusDisplay() {
    return statusDisplay;
  }

  public void setStatusDisplay(boolean statusDisplay) {
    this.statusDisplay = statusDisplay;
  }
  //    public Set<Services> getServices() {
//        return services;
//    }
//
//    public void setServices(Set<Services> services) {
//        this.services = services;
//    }
}