package com.caltex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caltex.model.Customer;
import com.caltex.model.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
