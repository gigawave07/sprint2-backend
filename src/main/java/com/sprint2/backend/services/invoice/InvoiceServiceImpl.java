package com.sprint2.backend.services.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import com.sprint2.backend.entity.Invoice;
import com.sprint2.backend.repository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    Sort sort = Sort.by(Sort.Direction.ASC, "pay_date");
    Pageable pageable = PageRequest.of(0,3, sort);
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> findAll() {
        return this.invoiceRepository.findAll();
    }

    @Override
    public Page<Invoice> findAllByCustomerID(Long  id, int currentPage) {
        if(currentPage>0){
            Pageable pageable = PageRequest.of(currentPage-1,5, sort);
            return this.invoiceRepository.aaa(id,pageable);
        }
        return this.invoiceRepository.aaa(id, pageable);
    }

    @Override
    public void save(Invoice invoice) {
        this.invoiceRepository.save(invoice);
    }
}
