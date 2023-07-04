package com.digital.ClinicaOdontologica.service;

import com.digital.ClinicaOdontologica.dto.TurnoDto;
import com.digital.ClinicaOdontologica.entity.Turno;
import com.digital.ClinicaOdontologica.exception.BadRequestException;
import com.digital.ClinicaOdontologica.exception.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {

        TurnoDto guardarTurno( Turno turno ) throws BadRequestException;

        List<TurnoDto> listarTodos();

        TurnoDto buscarTurnoPorId( Long id );

        TurnoDto actualizarTurno( Turno turno );

        void eliminarTurno( Long id  ) throws ResourceNotFoundException;

    }
