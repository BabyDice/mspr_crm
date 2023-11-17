package com.msprcrm.msprcrm.repository;

import com.msprcrm.msprcrm.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> getClientByIdent(Long ident);
}
