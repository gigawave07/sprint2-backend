package com.sprint2.backend.services.customer;


import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.model.InformationCustomerDTO;
import com.sprint2.backend.model.ListEntryLogDTO;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.List;

public interface CustomerService {
    // Đin

    void updateCustomer(InformationCustomerDTO customerDTO);

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
    Customer findByAppAccount(Long accountId);

    Page<ListEntryLogDTO> findListEntryLog(Long accountId, int pageable);
    // End

    // ----------------------- Vinh Begin -------------------------

    Customer findByAccountId(Long id);

    // ----------------------- Vinh End -------------------------

    // ------------------------ Hoàng Begin -----------------------------
    void save(Customer customer);

    List<Customer> findAll();

    void deleteByID(Long id);
    // ------------------------ Hoàng ENd -----------------------------
}
