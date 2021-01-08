package com.sprint2.backend.controller;

import com.sprint2.backend.model.StatisticCarDTO;
import com.sprint2.backend.model.StatisticRevenueDTO;
import com.sprint2.backend.services.car.CarService;
import com.sprint2.backend.services.customer.CustomerService;
import com.sprint2.backend.services.employee.EmployeeService;
import com.sprint2.backend.services.member_card.MemberCardService;
import com.sprint2.backend.services.parking_slot.ParkingSlotService;
import com.sprint2.backend.services.ticket.TicketService;
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

    @Autowired
    public TicketService ticketService;

    /**
     * Nguyen Quang Danh
     * Begin
     *
     * @return Thống kê số lượng các hãng xe đang có tại bãi
     */
    @RequestMapping(value = "/total-car-type-parking-slot", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalCarTypeParkingSlot() {
        Object toTalCarTypeParkingSlot = this.parkingSlotService.getTotalCarTypeParkingSlot();
        return new ResponseEntity<>(toTalCarTypeParkingSlot, HttpStatus.OK);
    }

    /**
     * @return Thống kê số lượng khách hàng
     */
    @RequestMapping(value = "/total-customer", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalCustomer() {
        Long totalCustomer = this.customerService.getTotalCustomer();
        return new ResponseEntity<>(totalCustomer, HttpStatus.OK);
    }

    /**
     * @return Thống kê số lượng nhân viên
     */
    @RequestMapping(value = "/total-employee", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalEmployee() {
        Long totalEmployee = this.employeeService.getTotalEmployee();
        return new ResponseEntity<>(totalEmployee, HttpStatus.OK);
    }

    /**
     * @return Thống kê số lượng xe đang có tại bãi
     */
    @RequestMapping(value = "/total-car-parking", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalCarParking() {
        Long totalCarParking = this.parkingSlotService.getTotalCarParking();
        return new ResponseEntity<>(totalCarParking, HttpStatus.OK);
    }


    /**
     * @return Thống kê tổng số lượng vị trí đỗ xe của bãi
     */
    @RequestMapping(value = "/total-parking-slot", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalParkingSlot() {
        Long totalParkingSlot = this.parkingSlotService.getTotalParkingSlot();
        return new ResponseEntity<>(totalParkingSlot, HttpStatus.OK);
    }


    /**
     * @return Thống kê tổng số lượng vé mỗi loại theo tuần tháng năm
     */
    @RequestMapping(value = "/total-member-card-type", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalMemberCardType() {
        Object totalMemberCardType = this.memberCardService.getTotalMemberCardType();
        return new ResponseEntity<>(totalMemberCardType, HttpStatus.OK);
    }


    /**
     * @return Thống kê số lượng xe của mỗi khách hàng
     */
    @RequestMapping(value = "/total-car-of-customer", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalCarOfCustomer() {
        Object totalCarOfCustomer = this.carService.getTotalCarOfCustomer();
        return new ResponseEntity<>(totalCarOfCustomer, HttpStatus.OK);
    }


    /**
     * @param statisticCarDTO
     * @return Thống kê số lượng khách hàng đăng ký trong khoảng thời gian
     * @throws ParseException
     */
    @RequestMapping(value = "/total-customer-register-period", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getToTalCustomerRegisterPeriod(@RequestBody StatisticCarDTO statisticCarDTO) throws ParseException {
        Object toTalCustomerRegisterPeriod = this.customerService.getToTalCustomerRegisterPeriod(statisticCarDTO.getFromDay(), statisticCarDTO.getToDay());
        return new ResponseEntity<>(toTalCustomerRegisterPeriod, HttpStatus.OK);
    }


    /**
     * @param revenueDTO
     * @return Thống kê doanh thu trong khoảng thời gian (member card)
     */
    @RequestMapping(value = "/total-revenue-member-card-period", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalRevenueMemberCardPeriod(@RequestBody StatisticRevenueDTO revenueDTO) {
        Object totalRevenueMemberCardPeriod = this.memberCardService.getTotalRevenueMemberCardPeriod(revenueDTO.getFromDayPayment(), revenueDTO.getToDayPayment());
        return new ResponseEntity<>(totalRevenueMemberCardPeriod, HttpStatus.OK);
    }

    /**
     * @param revenueDTO
     * @return Thống kê doanh thu trong khoảng thời gian (ticket)
     */
    @RequestMapping(value = "/total-revenue-ticket-period", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalRevenueTicketPeriod(@RequestBody StatisticRevenueDTO revenueDTO) {
        Object totalRevenueTicketPeriod = this.ticketService.getTotalRevenueTicketPeriod(revenueDTO.getFromDayPayment(), revenueDTO.getToDayPayment());
        return new ResponseEntity<>(totalRevenueTicketPeriod, HttpStatus.OK);
    }


    /**
     * @param carDTO
     * @return Thống kê số vé theo tuần 1 (member card)
     */
    @RequestMapping(value = "/total-member-card-week1", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalMemberCardWeek1(@RequestBody StatisticCarDTO carDTO) {
        Object totalMemberCardWeek = this.memberCardService.getTotalMemberCardWeek1(carDTO.getMonthParam(), carDTO.getYearParam());
        return new ResponseEntity<>(totalMemberCardWeek, HttpStatus.OK);
    }

    /**
     * @param carDTO
     * @return Thống kê số vé theo tuần 2 (member card)
     */
    @RequestMapping(value = "/total-member-card-week2", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalMemberCardWeek2(@RequestBody StatisticCarDTO carDTO) {
        Object totalMemberCardWeek = this.memberCardService.getTotalMemberCardWeek2(carDTO.getMonthParam(), carDTO.getYearParam());
        return new ResponseEntity<>(totalMemberCardWeek, HttpStatus.OK);
    }

    /**
     * @param carDTO
     * @return Thống kê số vé theo tuần 3 (member card)
     */
    @RequestMapping(value = "/total-member-card-week3", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalMemberCardWeek3(@RequestBody StatisticCarDTO carDTO) {
        Object totalMemberCardWeek = this.memberCardService.getTotalMemberCardWeek3(carDTO.getMonthParam(), carDTO.getYearParam());
        return new ResponseEntity<>(totalMemberCardWeek, HttpStatus.OK);
    }

    /**
     * @param carDTO
     * @return Thống kê số vé theo tuần 4 (member card)
     */
    @RequestMapping(value = "/total-member-card-week4", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalMemberCardWeek4(@RequestBody StatisticCarDTO carDTO) {
        Object totalMemberCardWeek = this.memberCardService.getTotalMemberCardWeek4(carDTO.getMonthParam(), carDTO.getYearParam());
        return new ResponseEntity<>(totalMemberCardWeek, HttpStatus.OK);
    }


    /**
     * @param carDTO
     * @return Thống kê số vé theo tháng (member card)
     */
    @RequestMapping(value = "/total-member-card-month", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalMemberCardMonth(@RequestBody StatisticCarDTO carDTO) {
        Object totalMemberCardMonth = this.memberCardService.getTotalMemberCardMonth(carDTO.getMonthParam(), carDTO.getYearParam());
        return new ResponseEntity<>(totalMemberCardMonth, HttpStatus.OK);
    }


    /**
     * Nguyen Quang Danh
     * End
     *
     * @param carDTO
     * @return Thống kê số vé theo năm (member card)
     */
    @RequestMapping(value = "/total-member-card-year", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTotalMemberCardYear(@RequestBody StatisticCarDTO carDTO) {
        Object totalMemberCardYear = this.memberCardService.getTotalMemberCardYear(carDTO.getYearParam());
        return new ResponseEntity<>(totalMemberCardYear, HttpStatus.OK);
    }

}
