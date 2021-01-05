package com.sprint2.backend.services.customer;

import java.text.ParseException;
import java.util.List;

import com.sprint2.backend.entity.Customer;

public interface CustomerService {
    List<Customer> findAll();

    Customer findByID(Long id);

    // Thống kê số lượng khách hàng
    Long getTotalCustomer();

    //  Thống kê số lượng khách hàng đăng ký trong khoảng thời gian
    Object getToTalCustomerRegisterPeriod(String fromDay, String toDay) throws ParseException;
}
