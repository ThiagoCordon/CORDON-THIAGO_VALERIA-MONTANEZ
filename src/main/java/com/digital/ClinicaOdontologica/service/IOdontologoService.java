package com.digital.ClinicaOdontologica.service;



import com.digital.ClinicaOdontologica.dto.OdontologoDto;
import com.digital.ClinicaOdontologica.entity.Odontologo;
import com.digital.ClinicaOdontologica.exception.BadRequestException;
import com.digital.ClinicaOdontologica.exception.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {
    OdontologoDto buscarOdontologoPorId( Long id);

    List<OdontologoDto> listarOdontologos();
    OdontologoDto registrarOdontologo( Odontologo odontologo) throws BadRequestException;

    OdontologoDto actualizarOdontologo( Odontologo odontologo);

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
}
