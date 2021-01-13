package com.sprint2.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    /**
     * nguyen quoc khanh
     * @param id
     * @param pageable
     * @return
     */
    @Query( value = "select * from invoice where customer_id =?",nativeQuery = true)
    Page<Invoice> aaa(Long id, Pageable pageable);
}
