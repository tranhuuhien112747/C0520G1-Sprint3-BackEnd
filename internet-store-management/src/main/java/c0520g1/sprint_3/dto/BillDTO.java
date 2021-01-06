package c0520g1.sprint_3.dto;


import c0520g1.sprint_3.model.Services;

import java.util.List;

/**
 * BillDTO
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
public class BillDTO {
    private Long idService;
    private Long idBill;
    private Long idUser;
    private boolean status;
    private List<ServiceBookedDTO> list;

    public BillDTO(Long idBill, Long idUser, boolean status, List<ServiceBookedDTO> list) {
        this.idBill = idBill;
        this.idUser = idUser;
        this.status = status;
        this.list = list;
    }

    public BillDTO() {
    }

    public BillDTO(Long idService, Long idUser) {
        this.idService = idService;
        this.idUser = idUser;
    }

    public Long getIdService() {
        return idService;
    }

    public void setIdService(Long idService) {
        this.idService = idService;
    }

    public Long getIdBill() {
        return idBill;
    }

    public void setIdBill(Long idBill) {
        this.idBill = idBill;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ServiceBookedDTO> getList() {
        return list;
    }

    public void setList(List<ServiceBookedDTO> list) {
        this.list = list;
    }
}
