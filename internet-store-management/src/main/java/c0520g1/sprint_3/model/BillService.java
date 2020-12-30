//package c0520g1.sprint_3.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//@Entity
//public class BillService {
//    @Id
//    private Long idBillService;
//    private Long quantityBooked;
//    @ManyToOne
//    @JoinColumn(name = "idBill")
//    @JsonIgnoreProperties("billServiceCollection")
//    private Bill bill;
//
//    @ManyToOne
//    @JoinColumn(name = "idService")
//    @JsonIgnoreProperties("billServiceCollection")
//    private Services services;
//
//    public Long getIdBillService() {
//        return idBillService;
//    }
//
//    public void setIdBillService(Long idBillService) {
//        this.idBillService = idBillService;
//    }
//
//    public Long getQuantityBooked() {
//        return quantityBooked;
//    }
//
//    public void setQuantityBooked(Long quantityBooked) {
//        this.quantityBooked = quantityBooked;
//    }
//
//    public Bill getBill() {
//        return bill;
//    }
//
//    public void setBill(Bill bill) {
//        this.bill = bill;
//    }
//
//    public Services getServices() {
//        return services;
//    }
//
//    public void setServices(Services services) {
//        this.services = services;
//    }
//}
