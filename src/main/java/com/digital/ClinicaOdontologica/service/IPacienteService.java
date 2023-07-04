package com.digital.ClinicaOdontologica.service;

import com.digital.ClinicaOdontologica.dto.PacienteDto;
import com.digital.ClinicaOdontologica.entity.Paciente;
import com.digital.ClinicaOdontologica.exception.BadRequestException;
import com.digital.ClinicaOdontologica.exception.ResourceNotFoundException;


import java.util.List;

public interface IPacienteService {
    List<PacienteDto> listarPacientes();

    PacienteDto buscarPacientePorId(Long id);

    PacienteDto guardarPaciente(Paciente paciente) throws BadRequestException;

    PacienteDto actualizarPaciente(Paciente paciente);

    void eliminarPaciente(Long id) throws ResourceNotFoundException;

}