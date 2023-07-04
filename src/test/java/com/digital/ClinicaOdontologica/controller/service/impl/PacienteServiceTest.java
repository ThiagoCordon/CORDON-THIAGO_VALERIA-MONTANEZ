package com.digital.ClinicaOdontologica.controller.service.impl;


import com.digital.ClinicaOdontologica.dto.PacienteDto;
import com.digital.ClinicaOdontologica.entity.Domicilio;
import com.digital.ClinicaOdontologica.entity.Paciente;
import com.digital.ClinicaOdontologica.exception.BadRequestException;
import com.digital.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.digital.ClinicaOdontologica.service.impl.PacienteService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void deberiaPoderRegristrarUnPaciente() throws BadRequestException {
        Domicilio domicilio = new Domicilio("Colon", 525, "Villa Maria", "Cordoba");
        Paciente pacienteAGuardar = new Paciente("Thiago", "Cordon", "3361420873", LocalDate.of(2024, 8, 11), domicilio);
        PacienteDto pacienteDto = pacienteService.guardarPaciente(pacienteAGuardar);

        Assertions.assertNotNull(pacienteDto);
        Assertions.assertNotNull(pacienteDto.getId());
    }

    @Test
    @Order(2)
    void noDeberiaRegistrarPaciente_ElFormatoDniEsIncorrecto() {
        Paciente pacienteAGuardar = new Paciente("Thiago", "Cordon", "336jbjbj0008732", LocalDate.of(2023, 8, 11), new Domicilio("Colon", 525, "Villa Maria", "Cordoba"));
        Assertions.assertThrows(ConstraintViolationException.class, () -> pacienteService.guardarPaciente(pacienteAGuardar));
    }

    @Test
    @Order(3)
    void deberiaEliminarElPacienteoConId1() throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(1L);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(1L));
    }

}


