package com.sprint2.backend.controller;

import com.sprint2.backend.model.StatisticCarDTO;
import com.sprint2.backend.services.car.CarService;
import com.sprint2.backend.services.customer.CustomerService;
import com.sprint2.backend.services.employee.EmployeeService;
import com.sprint2.backend.services.member_card.MemberCardService;
import com.sprint2.backend.services.parking_slot.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/statistic")
@CrossOrigin
public class StatisticController {

    @Autowired
    public ParkingSlotService parkingSlotService;

    @Autowired
    public CustomerService customerService;

    @Autowired
    public EmployeeService employeeService;

    @Autowired
    public MemberCardService memberCardService;

    @Autowired
    public CarService carService;

    // Thống kê số lượng các hãng xe đang có tại bãi
    @RequestMapping(value = "/total-car-type-parking-slot", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalCarTypeParkingSlot() {
        Object toTalCarTypeParkingSlot = this.parkingSlotService.getTotalCarTypeParkingSlot();
        return new ResponseEntity<>(toTalCarTypeParkingSlot, HttpStatus.OK);
    }

    // Thống kê số lượng khách hàng
    @RequestMapping(value = "/total-customer", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalCustomer() {
        Long totalCustomer = this.customerService.getTotalCustomer();
        return new ResponseEntity<>(totalCustomer, HttpStatus.OK);
    }

    // Thống kê số lượng nhân viên
    @RequestMapping(value = "/total-employee", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalEmployee() {
        Long totalEmployee = this.employeeService.getTotalEmployee();
        return new ResponseEntity<>(totalEmployee, HttpStatus.OK);
    }

    // Thống kê số lượng xe đang có tại bãi
    @RequestMapping(value = "/total-car-parking", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalCarParking() {
        Long totalCarParking = this.parkingSlotService.getTotalCarParking();
        return new ResponseEntity<>(totalCarParking, HttpStatus.OK);
    }

    // Thống kê tổng số lượng vị trí đỗ xe của bãi
    @RequestMapping(value = "/total-parking-slot", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalParkingSlot() {
        Long totalParkingSlot = this.parkingSlotService.getTotalParkingSlot();
        return new ResponseEntity<>(totalParkingSlot, HttpStatus.OK);
    }

    // Thống kê tổng số lượng vé mỗi loại theo tuần tháng năm
    @RequestMapping(value = "/total-member-card-type", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalMemberCardType() {
        Object totalMemberCardType = this.memberCardService.getTotalMemberCardType();
        return new ResponseEntity<>(totalMemberCardType, HttpStatus.OK);
    }

    // Thống kê số lượng xe của mỗi khách hàng
    @RequestMapping(value = "/total-car-of-customer", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalCarOfCustomer() {
        Object totalCarOfCustomer = this.carService.getTotalCarOfCustomer();
        return new ResponseEntity<>(totalCarOfCustomer, HttpStatus.OK);
    }

    //  Thống kê số lượng khách hàng đăng ký trong khoảng thời gian
    @RequestMapping(value = "/total-customer-register-period", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getToTalCustomerRegisterPeriod(@RequestBody StatisticCarDTO statisticCarDTO) throws ParseException {
        Object toTalCustomerRegisterPeriod = this.carService.getToTalCustomerRegisterPeriod(statisticCarDTO.getFromDay(), statisticCarDTO.getToDay());
        return new ResponseEntity<>(toTalCustomerRegisterPeriod, HttpStatus.OK);
    }
}
