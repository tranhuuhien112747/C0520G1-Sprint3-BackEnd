package c0520g1.sprint_3.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
@Entity
public class Services {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idService;
  private String serviceName;
  private String price;
  private String unit;
  private String quantity;
  @ManyToOne
  @JoinColumn(name = "idTypeServices")
  @JsonIgnoreProperties("servicesCollection")
  private TypeServices typeServices;
  @OneToMany(mappedBy = "services", cascade = CascadeType.ALL)
  @JsonIgnoreProperties("services")
  private Collection<BillService> billServiceCollection;
  //    @ManyToMany()
//    @JoinTable(name = "services_bill", joinColumns = @JoinColumn(name="idService"), inverseJoinColumns = @JoinColumn(name="idBill"))
//    @JsonIgnoreProperties("services")
//    private Set<Bill> bills;
  public Services() {
  }
  public Collection<BillService> getBillServiceCollection() {
    return billServiceCollection;
  }
  public void setBillServiceCollection(Collection<BillService> billServiceCollection) {
    this.billServiceCollection = billServiceCollection;
  }
  public Long getIdService() {
    return idService;
  }
  public void setIdService(Long idService) {
    this.idService = idService;
  }
  public String getServiceName() {
    return serviceName;
  }
  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }
  public String getPrice() {
    return price;
  }
  public void setPrice(String price) {
    this.price = price;
  }
  public String getUnit() {
    return unit;
  }
  public void setUnit(String unit) {
    this.unit = unit;
  }
  public String getQuantity() {
    return quantity;
  }
  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }
  public TypeServices getTypeServices() {
    return typeServices;
  }
  public void setTypeServices(TypeServices typeServices) {
    this.typeServices = typeServices;
  }
//    public Set<Bill> getBills() {
//        return bills;
//    }
//
//    public void setBills(Set<Bill> bills) {
//        this.bills = bills;
//    }
}
