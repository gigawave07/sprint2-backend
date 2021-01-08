package com.sprint2.backend.services.customer;

import com.sprint2.backend.entity.Customer;

import java.text.ParseException;
import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer findByID(Long id);
    Customer findCustomerByAppAccountId(Long id);


    /**
     * Nguyen Quang Danh
     *
     * @return Thống kê số lượng khách hàng
     */
    Long getTotalCustomer();


    /**
     * @param fromDay
     * @param toDay
     * @return Thống kê số lượng khách hàng đăng ký trong khoảng thời gian
     * @throws ParseException
     */
    Object getToTalCustomerRegisterPeriod(String fromDay, String toDay) throws ParseException;
}
