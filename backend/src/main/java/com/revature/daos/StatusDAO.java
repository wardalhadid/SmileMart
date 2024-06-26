package com.revature.daos;

import com.revature.models.Order;
import com.revature.models.Status;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusDAO extends JpaRepository<Status, String> {
    public Optional<Status> findByStatusId(String status);
}
