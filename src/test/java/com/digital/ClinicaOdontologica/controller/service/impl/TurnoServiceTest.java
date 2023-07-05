package com.digital.ClinicaOdontologica.controller.service.impl;


import com.digital.ClinicaOdontologica.dto.OdontologoDto;
import com.digital.ClinicaOdontologica.dto.PacienteDto;
import com.digital.ClinicaOdontologica.dto.TurnoDto;
import com.digital.ClinicaOdontologica.entity.Domicilio;
import com.digital.ClinicaOdontologica.entity.Odontologo;
import com.digital.ClinicaOdontologica.entity.Paciente;
import com.digital.ClinicaOdontologica.entity.Turno;
import com.digital.ClinicaOdontologica.exception.BadRequestException;
import com.digital.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.digital.ClinicaOdontologica.service.impl.OdontologoService;
import com.digital.ClinicaOdontologica.service.impl.PacienteService;
import com.digital.ClinicaOdontologica.service.impl.TurnoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    private static Paciente paciente;
    private static Odontologo odontologo;

    @BeforeAll
    public static void init(){
        Domicilio domicilio = new Domicilio("Colon", 525, "Villa Maria", "Cordoba");
        paciente = new Paciente("Thiago", "Cordon", "3361420873", LocalDate.of(2024, 8, 11), domicilio);
        odontologo = new Odontologo("MA-46664646", "Valeria", "Montañez");
    }


    @Test
    @Order(1)
    void deberiaInsertarUnTurno() throws BadRequestException, ResourceNotFoundException {
        PacienteDto pacienteDto = pacienteService.guardarPaciente(paciente);
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);

        Turno turnoAInsertar = new Turno(paciente, odontologo, LocalDateTime.of(LocalDate.of(2023, 12, 3), LocalTime.of(9, 00)));
        TurnoDto turnoDto = turnoService.guardarTurno(turnoAInsertar);

        Assertions.assertNotNull(turnoDto);
        Assertions.assertNotNull(turnoDto.getId());
        Assertions.assertEquals(turnoDto.getPaciente(), pacienteDto.getNombre()+" "+ pacienteDto.getApellido());
        Assertions.assertEquals(turnoDto.getOdontologo(), odontologoDto.getNombre()+" "+ odontologoDto.getApellido());
    }

    @Test
    @Order(2)
    void noDeberiaInsertarUnTurnoSiOdontologoEsNull() throws BadRequestException {
        PacienteDto pacienteDto = pacienteService.guardarPaciente(paciente);
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);

        Turno turnoAInsertar = new Turno(paciente, null, LocalDateTime.of(LocalDate.of(2023, 12, 3), LocalTime.of(9, 00)));
        TurnoDto turnoDto = turnoService.guardarTurno(turnoAInsertar);

        Assertions.assertThrows(BadRequestException.class, () -> turnoService.guardarTurno(turnoAInsertar));
    }

    @Test
    @Order(3)
    void deberiaEliminarElTurnoConId1() throws ResourceNotFoundException {
        turnoService.eliminarTurno(1L);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(1L));
    }



}