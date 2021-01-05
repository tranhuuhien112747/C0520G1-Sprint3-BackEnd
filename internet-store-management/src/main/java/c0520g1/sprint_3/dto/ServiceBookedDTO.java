package c0520g1.sprint_3.dto;

/**
 * ServiceBookedDTO
 * <p>
 * Version 1.0
 * <p>
 * Date: 01-01-2021
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 01-01-2021       Trần Hữu Hiên
 */

public class ServiceBookedDTO {
    private Long idService;
    private String serviceName;
    private String price;
    private String quantity;
    private Long quantityPurchased;

    public ServiceBookedDTO(Long idService, String serviceName, String price, String quantity, Long quantityPurchased) {
        this.idService = idService;
        this.serviceName = serviceName;
        this.price = price;
        this.quantity = quantity;
        this.quantityPurchased = quantityPurchased;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Long getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(Long quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }
}
