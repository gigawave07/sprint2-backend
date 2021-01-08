package com.sprint2.backend.services.invoice;

import com.sprint2.backend.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InvoiceService {
    List<Invoice> findAll();

    Page<Invoice> findAllByCustomerID(Pageable pageable, Long id);

    void save(Invoice invoice);
}
