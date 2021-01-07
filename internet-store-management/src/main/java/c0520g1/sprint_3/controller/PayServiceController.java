package c0520g1.sprint_3.controller;

import c0520g1.sprint_3.dto.BillDTO;
import c0520g1.sprint_3.model.Bill;
import c0520g1.sprint_3.model.BillServices;
import c0520g1.sprint_3.model.Services;
import c0520g1.sprint_3.model.User;
import c0520g1.sprint_3.service.BillService;
import c0520g1.sprint_3.service.BillServiceService;
import c0520g1.sprint_3.service.ServicesService;
import c0520g1.sprint_3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * controller ExamController
 * <p>
 * Version 1.0
 * <p>
 * Date: 10/12/2020
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 01/01/2020        Trần Hữu Hiên          Payment Service
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/request")
public class PayServiceController {
    @Autowired
    private ServicesService services;
    @Autowired
    private UserService userService;
    @Autowired
    private BillService bill;

    @Autowired
    private BillServiceService billServiceService;


    /**
     * show all service
     *
     * @return list <Service>
     */
    @GetMapping(value = "/service")
    public ResponseEntity<List<Services>> getListService() {
        List<Services> servicesList = services.findAll();
        if (servicesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(servicesList, HttpStatus.OK);
    }

    /**
     * show all bill status true
     *
     * @return list <Bill>
     */
    @GetMapping(value = "/listBill")
    public ResponseEntity<List<Bill>> getListBill() {
        List<Bill> billList = bill.findBillByStatusDisplayTrue();
        if (billList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(billList, HttpStatus.OK);
    }

    /**
     * payment by account
     *
     * @param billDTO
     * @return void
     */
    @PostMapping(value = "/payment-by-account")
    public ResponseEntity<?> creatBillPaymentByAccount(@RequestBody BillDTO billDTO) {
        User user = userService.findById(billDTO.getIdUser());
        Services newService;
        BillServices billServices;
        Bill newBill = new Bill();
        int totalPayMoney = 0;
        int moneyUser = Integer.parseInt(user.getMoney());
        int size = billDTO.getList().size();
        newBill.setStatus(true);
        newBill.setStatusDisplay(true);
        newBill.setUser(userService.findById(billDTO.getIdUser()));
        bill.create(newBill);
        for (int i = 0; i < size; i++) {
            totalPayMoney += (Integer.parseInt(billDTO.getList().get(i).getPrice()) * billDTO.getList().get(i).getQuantityPurchased());
            newService = services.findById(billDTO.getList().get(i).getIdService());
            newService.setQuantity(String.valueOf(Integer.parseInt(newService.getQuantity()) - billDTO.getList().get(i).getQuantityPurchased()));
            services.save(newService);
            billServices = new BillServices();
            billServices.setBill(bill.findById(newBill.getIdBill()));
            billServices.setServices(newService);
            billServices.setQuantityBooked(billDTO.getList().get(i).getQuantityPurchased());
            billServiceService.create(billServices);
        }
        user.setMoney(String.valueOf(moneyUser - totalPayMoney));
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * payment direct
     *
     * @param billDTO
     * @return void
     */
    @PostMapping(value = "/payment-direct")
    public ResponseEntity<?> createBillPaymentDirect(@RequestBody BillDTO billDTO) {
        Services newService;
        Bill newBill = new Bill();
        BillServices billServices;
        int size = billDTO.getList().size();
        newBill.setStatus(false);
        newBill.setStatusDisplay(true);
        newBill.setUser(userService.findById(billDTO.getIdUser()));
        bill.create(newBill);
        for (int i = 0; i < size; i++) {
            newService = services.findById(billDTO.getList().get(i).getIdService());
            newService.setQuantity(String.valueOf(Integer.parseInt(newService.getQuantity()) - billDTO.getList().get(i).getQuantityPurchased()));
            services.save(newService);
            billServices = new BillServices();
            billServices.setBill(bill.findById(newBill.getIdBill()));
            billServices.setServices(newService);
            billServices.setQuantityBooked(billDTO.getList().get(i).getQuantityPurchased());
            billServiceService.create(billServices);

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * payment PayPal
     * Payment for services by PayPal
     *
     * @param billDTO
     * @return void
     */
    @PostMapping(value = "/payment-paypal")
    public ResponseEntity<?> createBillPaymentPayPal(@RequestBody BillDTO billDTO) {
        Services newService;
        Bill newBill = new Bill();
        BillServices billServices;
        int size = billDTO.getList().size();
        newBill.setStatus(true);
        newBill.setStatusDisplay(true);
        newBill.setUser(userService.findById(billDTO.getIdUser()));
        bill.create(newBill);
        for (int i = 0; i < size; i++) {
            newService = services.findById(billDTO.getList().get(i).getIdService());
            newService.setQuantity(String.valueOf(Integer.parseInt(newService.getQuantity()) - billDTO.getList().get(i).getQuantityPurchased()));
            services.save(newService);
            billServices = new BillServices();
            billServices.setBill(bill.findById(newBill.getIdBill()));
            billServices.setServices(newService);
            billServices.setQuantityBooked(billDTO.getList().get(i).getQuantityPurchased());
            billServiceService.create(billServices);

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * create bill add money to account direct
     *
     * @param billDTO
     * @return void
     */
    @PostMapping(value = "/deposit")
    public ResponseEntity<?> createBillDepositDirect(@RequestBody BillDTO billDTO) {
        Services newService = null;
        Bill newBill = new Bill();
        BillServices billServices = new BillServices();
        newBill.setStatus(false);
        newBill.setStatusDisplay(true);
        newBill.setUser(userService.findById(billDTO.getIdUser()));
        bill.create(newBill);
        newService = services.findById(billDTO.getIdService());
        billServices.setBill(bill.findById(newBill.getIdBill()));
        billServices.setServices(newService);
        billServices.setQuantityBooked(Long.parseLong(newService.getQuantity()));
        billServiceService.create(billServices);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Deposit money into your account with PayPal
     *
     * @param billDTO
     * @return void
     */
    @PostMapping(value = "/deposit-payPal")
    public ResponseEntity<?> PaymentDepositPayPal(@RequestBody BillDTO billDTO) {
        Services newService = null;
        Bill newBill = new Bill();
        BillServices billServices = new BillServices();
        newBill.setStatus(true);
        newBill.setStatusDisplay(true);
        newBill.setUser(userService.findById(billDTO.getIdUser()));
        bill.create(newBill);
        newService = services.findById(billDTO.getIdService());
        billServices.setBill(bill.findById(newBill.getIdBill()));
        billServices.setServices(newService);
        billServices.setQuantityBooked(Long.parseLong(newService.getQuantity()));
        billServiceService.create(billServices);
        String deposit = null;
        String moneyUser = null;
        String total = null;
        User user = userService.findById(newBill.getUser().getIdUser());
        deposit = newService.getPrice();
        moneyUser = user.getMoney();
        total = String.valueOf(Integer.parseInt(moneyUser) + Integer.parseInt(deposit));
        user.setMoney(total);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * confirm pouring money into account blazing
     *
     * @PathVariable idBill
     * @return void
     */
    @PutMapping(value = "/deposit/{idBill}")
    public ResponseEntity<Void> payDepositAccountUser(@PathVariable Long idBill) {
        Services newService = null;
        String deposit = null;
        String moneyUser = null;
        String total = null;
        BillServices billServices = billServiceService.findById(idBill);
        Bill newBill = bill.findById(billServices.getBill().getIdBill());
        newBill.setStatus(true);
        newBill.setStatusDisplay(false);
        bill.create(newBill);
        newService = services.findById(billServices.getServices().getIdService());
        User user = userService.findById(newBill.getUser().getIdUser());
        deposit = newService.getPrice();
        moneyUser = user.getMoney();
        total = String.valueOf(Integer.parseInt(moneyUser) + Integer.parseInt(deposit));
        user.setMoney(total);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/listBill/{idBill}")
    public ResponseEntity<Bill> getBillByIdBill(@PathVariable Long idBill) {
        Bill newBill = bill.findById(idBill);
        if (newBill == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newBill, HttpStatus.OK);
    }

    @PutMapping(value = "/listBill/setStatus/{idBill}")
    public ResponseEntity<Void> setStatusBill(@PathVariable Long idBill) {
        System.err.println(idBill);
        Bill newBill = bill.findById(idBill);
        newBill.setStatus(true);
        newBill.setStatusDisplay(false);
        bill.create(newBill);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getBuyHours")
    public ResponseEntity<?> getTimeRemainingUser(@RequestParam String idUser, @RequestParam String priceHour) {
        User user = userService.findById(Long.parseLong(idUser));
        int price = Integer.parseInt(priceHour);
        int currentMoneyUser = Integer.parseInt(user.getMoney());
        int moneyUser = currentMoneyUser - price;
        int time = (price / 5000) * 60;
        int timeUser;
        if (user.getTimeRemaining() == null) {
            timeUser = 0;
        } else {
            timeUser = Integer.parseInt(user.getTimeRemaining());
        }
        user.setMoney(String.valueOf(moneyUser));
        user.setTimeRemaining(String.valueOf(timeUser + time));
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
