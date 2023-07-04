package com.digital.ClinicaOdontologica.repository;

import com.digital.ClinicaOdontologica.entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {


}

