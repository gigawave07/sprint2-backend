package com.sprint2.backend.services.invoice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import com.sprint2.backend.entity.Invoice;

public interface InvoiceService {
    List<Invoice> findAll();

    Page<Invoice> findAllByCustomerID(Long  id, int page);

    void save(Invoice invoice);
}
