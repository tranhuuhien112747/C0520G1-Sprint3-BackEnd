package c0520g1.sprint_3.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Set;

@Entity
public class Services {
    public interface checkCreate {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;

    @NotEmpty(message = " Nhập tên dịch vụ!", groups = checkCreate.class)
    @Pattern(regexp = "(/^[a-zA-Zà-ỹÀ-Ỹ_0-9\\s]{3,20}$/)", message = "Tên dịch vụ không hợp lệ", groups = checkCreate.class)
    private String serviceName;

    @NotEmpty(message = "Nhập giá", groups = checkCreate.class)
    @Pattern(regexp = "(/^\\d{3,5}$/)", message = "Giá không hợp lệ", groups = checkCreate.class)
    private String price;

    @NotEmpty(message = "Nhập đơn vị", groups = checkCreate.class)
    @Pattern(regexp = "(/^[a-zA-Zà-ỹÀ-Ỹ_0-9\\s]{2,6}$/)", message = "Đơn vị không hợp lệ", groups = checkCreate.class)
    private String unit;

    @NotEmpty(message = "Nhập số lượng", groups = checkCreate.class)
    @Pattern(regexp = "(/^\\d{1,3}$/)", message = "Số lượng không hợp lệ", groups = checkCreate.class)
    private String quantity;

    @ManyToOne
    @JoinColumn(name = "idTypeServices")
    @JsonIgnoreProperties("servicesCollection")
    private TypeServices typeServices;
  
    @OneToMany(mappedBy = "services", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("services")
    private Collection<BillService> billServiceCollection;

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
}
