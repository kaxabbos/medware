package com.medware.repo;

import com.medware.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    List<Client> findAllByOrderByIdDesc();
    Client findClientById(Long id);
}
