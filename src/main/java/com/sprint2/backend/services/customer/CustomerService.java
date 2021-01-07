package com.sprint2.backend.services.customer;


import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.model.InformationCustomerDTO;
import com.sprint2.backend.model.ListEntryLogDTO;
import org.springframework.data.domain.Page;

public interface CustomerService {
    // Đin

    void updateCustomer(InformationCustomerDTO customerDTO);

    Customer findByAppAccount(Long accountId);

    Page<ListEntryLogDTO> findListEntryLog(Long accountId, int pageable);
    // End
}
