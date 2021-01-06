package com.sprint2.backend.services.customer;

import com.sprint2.backend.entity.Customer;

import java.text.ParseException;
import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer findByID(Long id);

    // Thống kê số lượng khách hàng
    Long getTotalCustomer();

    //  Thống kê số lượng khách hàng đăng ký trong khoảng thời gian
    Object getToTalCustomerRegisterPeriod(String fromDay, String toDay) throws ParseException;
}
