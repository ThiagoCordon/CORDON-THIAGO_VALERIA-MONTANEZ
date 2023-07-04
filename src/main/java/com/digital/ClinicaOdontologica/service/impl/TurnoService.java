package com.digital.ClinicaOdontologica.service.impl;
import com.digital.ClinicaOdontologica.dto.OdontologoDto;
import com.digital.ClinicaOdontologica.dto.PacienteDto;
import com.digital.ClinicaOdontologica.dto.TurnoDto;
import com.digital.ClinicaOdontologica.entity.Turno;
import com.digital.ClinicaOdontologica.exception.BadRequestException;
import com.digital.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.digital.ClinicaOdontologica.repository.TurnoRepository;
import com.digital.ClinicaOdontologica.service.ITurnoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class TurnoService implements ITurnoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    @Autowired
    public TurnoService( TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService ) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Override
    public TurnoDto guardarTurno( Turno turno ) throws BadRequestException {
        TurnoDto turnoDto = null;
        PacienteDto paciente = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
        OdontologoDto odontologo = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());

        if (paciente == null || odontologo == null) {
            if (paciente == null && odontologo == null) {
                LOGGER.error("El paciente y el odontologo no se encuentran en nuestra base de datos");
                throw new BadRequestException("El paciente no se encuentra en nuestra base de datos");
            } else if (paciente == null) {
                LOGGER.error("El paciente no se encuentra en nuestra base de datos");
                throw new BadRequestException("El paciente no se encuentra en nuestra base de datos");
            } else {
                LOGGER.error("El odontologo no se encuentra en nuestra base de datos");
                throw new BadRequestException("El odontologo no se encuentra en nuestra base de datos");
            }

        } else {
            turnoDto = TurnoDto.fromTurno(turnoRepository.save(turno));
            LOGGER.info("Nuevo turno registrado con exito: {}", turnoDto);
        }

        return turnoDto;
    }

    @Override
    public List<TurnoDto> listarTodos() {
        List<Turno> turnosLista = turnoRepository.findAll();
        List<TurnoDto> turnoDtoList = turnosLista.stream()
                .map(TurnoDto::fromTurno)
                .toList();

        LOGGER.info("Lista de todos los turnos: {}", turnoDtoList);
        return turnoDtoList;
    }

    @Override
    public TurnoDto buscarTurnoPorId( Long id ) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoDto turnoBuscadoDto = null;
        if (turnoBuscado != null) {
            turnoBuscadoDto = TurnoDto.fromTurno(turnoBuscado);
            LOGGER.info("El Turno fue encontrado: {}", turnoBuscadoDto);
        } else {
            LOGGER.info("El id del Turno no se encuentra registrado en la base de datos");

        }
        return turnoBuscadoDto;
    }


    @Override
    public TurnoDto actualizarTurno( Turno turno ) {
        Turno turnoAModificar = turnoRepository.findById(turno.getId()).orElse(null);
        TurnoDto turnoDtoActualizado = null;
        if (turnoAModificar != null) {
            turnoAModificar = turno;
            turnoRepository.save(turnoAModificar);
            turnoDtoActualizado = TurnoDto.fromTurno(turnoAModificar);
            LOGGER.warn("Turno actualizado con exito: {}", turnoDtoActualizado);
        } else {
            LOGGER.error("El turno no se encuentra registrado en nuestra base de datos, no fue posible actualizarlo.");
        }
        return turnoDtoActualizado;
    }

    @Override
    public void eliminarTurno( Long id ) throws ResourceNotFoundException {
        if (buscarTurnoPorId(id) != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se elimino el turno con id {}", id);
        } else {
            LOGGER.error("No se ha encontrado el turno con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el turno con id " + id);
        }
    }

}