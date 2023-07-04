package com.digital.ClinicaOdontologica.controller;
import com.digital.ClinicaOdontologica.dto.TurnoDto;
import com.digital.ClinicaOdontologica.entity.Turno;
import com.digital.ClinicaOdontologica.exception.BadRequestException;
import com.digital.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.digital.ClinicaOdontologica.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private final ITurnoService turnoService;

    @Autowired
    public TurnoController( ITurnoService turnoService ) {
        this.turnoService = turnoService;
    }

    //GET
    @GetMapping()
    public List<TurnoDto> listarTurnos() {
        return turnoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurnoPorId( @PathVariable Long id ) {
        ResponseEntity<TurnoDto> respuesta;
        TurnoDto turnoDto = turnoService.buscarTurnoPorId(id);
        if (turnoDto != null) respuesta = new ResponseEntity<>(turnoDto, null, HttpStatus.OK);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }

    //POST
    @PostMapping("/guardar")
    public ResponseEntity<TurnoDto> guardarTurno( @RequestBody Turno turno ) throws BadRequestException {
        ResponseEntity<TurnoDto> respuesta;
        TurnoDto turnoDto = turnoService.guardarTurno(turno);
        if (turnoDto != null) respuesta = new ResponseEntity<>(turnoDto, null, HttpStatus.CREATED);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }

    //PUT
    @PutMapping("/actualizar")
    public ResponseEntity<TurnoDto> actualizarTurno( @RequestBody Turno turno ) {
        ResponseEntity<TurnoDto> respuesta;
        TurnoDto turnoDto = turnoService.actualizarTurno(turno);
        if (turnoDto != null) respuesta = new ResponseEntity<>(turnoDto, null, HttpStatus.OK);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public void eliminarTurno( @PathVariable Long id ) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
    }

}
