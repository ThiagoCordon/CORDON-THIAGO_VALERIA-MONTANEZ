package com.digital.ClinicaOdontologica.controller.service.impl;


import com.digital.ClinicaOdontologica.dto.OdontologoDto;
import com.digital.ClinicaOdontologica.entity.Odontologo;
import com.digital.ClinicaOdontologica.exception.BadRequestException;
import com.digital.ClinicaOdontologica.exception.ResourceNotFoundException;
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
        Odontologo odontologoARegistrar = new Odontologo("MA-0012354", "Valeria", "Montañez");
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologoARegistrar);

        Assertions.assertNotNull(odontologoDto);
        Assertions.assertNotNull(odontologoDto.getId());
    }

    @Test
    @Order(2)
    void cuandoNoSeCumpleElPatronDeLaMatricula_noDeberiaInsertarUnOdontologo(){
        Odontologo odontologoARegistrar = new Odontologo("abcdefsdhijfkñakdsdlas", "Juan", "Perez");
        Assertions.assertThrows(ConstraintViolationException.class, () -> odontologoService.registrarOdontologo(odontologoARegistrar));
    }

    @Test
    @Order(3)
    void deberiaEliminarElOdontologoConId1() throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(1L);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }


}
