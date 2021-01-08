package com.sprint2.backend.services.invoice;

import com.sprint2.backend.entity.Invoice;
import com.sprint2.backend.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> findAll() {
        return this.invoiceRepository.findAll();
    }

    @Override
    public Page<Invoice> findAllByCustomerID(Pageable pageable, Long id) {
        return this.invoiceRepository.findByCustomer_Id(pageable, id);
    }

    @Override
    public void save(Invoice invoice) {
        this.invoiceRepository.save(invoice);
    }
}
