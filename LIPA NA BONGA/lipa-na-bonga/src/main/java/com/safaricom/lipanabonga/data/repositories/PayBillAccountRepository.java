package com.safaricom.lipanabonga.data.repositories;


import com.safaricom.lipanabonga.data.entity.PayBillAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PayBillAccountRepository extends JpaRepository<PayBillAccountEntity, Long> {


    List<PayBillAccountEntity> findByMonitoringIsActiveTrue();
}
