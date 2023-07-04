package com.digital.ClinicaOdontologica.controller.service.impl;


import com.digital.ClinicaOdontologica.dto.OdontologoDto;
import com.digital.ClinicaOdontologica.entity.Odontologo;
import com.digital.ClinicaOdontologica.exception.BadRequestException;
import com.digital.ClinicaOdontologica.service.impl.OdontologoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaInsertarUnOdontologo() throws BadRequestException {
        Odontologo odontologoARegistrar = new Odontologo("MA-0012211", "Pedro", "Avila");
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologoARegistrar);

        Assertions.assertNotNull(odontologoDto);
        Assertions.assertNotNull(odontologoDto.getId());
    }

    @Test
    @Order(2)
    void cuandoNoSeCumpleElPatronDeLaMatricula_noDeberiaInsertarUnOdontologo(){
        Odontologo odontologoARegistrar = new Odontologo("knjdjfnedf", "Valeria", "Cardozo");
        Assertions.assertThrows(ConstraintViolationException.class, () -> odontologoService.registrarOdontologo(odontologoARegistrar));
    }


}
