package com.sprint2.backend.services.customer;

import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findByID(Long id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    /**
     * nguyen quoc khanh
     * @param id
     * @return
     */
    @Override
    public Customer findCustomerByAppAccountId(Long id) {
        return this.customerRepository.findCustomerByAppAccountId(id);
    }


    /**
     * Nguyen Quang Danh
     *
     * @return Thống kê số lượng khách hàng
     */
    @Override
    public Long getTotalCustomer() {
        return this.customerRepository.getTotalCustomer();
    }


    /**
     * @param fromDay
     * @param toDay
     * @return Thống kê số lượng khách hàng đăng ký trong khoảng thời gian
     * @throws ParseException
     */
    @Override
    public Object getToTalCustomerRegisterPeriod(String fromDay, String toDay) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate localFromDay = LocalDate.parse(fromDay, formatter);
        LocalDate localToDay = LocalDate.parse(toDay, formatter);
        return this.customerRepository.getToTalCustomerRegisterPeriod(localFromDay, localToDay);
    }


}
