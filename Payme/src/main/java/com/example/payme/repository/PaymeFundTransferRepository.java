package com.example.payme.repository;


import java.util.List;

import com.example.payme.entity.PaymeFundTransfer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PaymeFundTransferRepository extends JpaRepository<PaymeFundTransfer, Long>{
	public List<PaymeFundTransfer> findByFromPhoneNumberOrToPhoneNumberAllIgnoreCase(String fromPhoneNumber, String toPhoneNumber,Pageable pageable);
}
