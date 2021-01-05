package c0520g1.sprint_3.dto;

public class DepositDTO {
    private Long idService;
    private Long idUser;

    public DepositDTO(Long idService, Long idUser) {
        this.idService = idService;
        this.idUser = idUser;
    }

    public DepositDTO() {
    }

    public Long getIdService() {
        return idService;
    }

    public void setIdService(Long idService) {
        this.idService = idService;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
