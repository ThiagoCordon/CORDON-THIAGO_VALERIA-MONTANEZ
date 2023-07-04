package com.digital.ClinicaOdontologica.controller;
import com.digital.ClinicaOdontologica.dto.OdontologoDto;
import com.digital.ClinicaOdontologica.entity.Odontologo;
import com.digital.ClinicaOdontologica.exception.BadRequestException;
import com.digital.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.digital.ClinicaOdontologica.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController( OdontologoService odontologoService ) {
        this.odontologoService = odontologoService;
    }

    //GET
    @GetMapping()
    public List<OdontologoDto> listarOdontologos() {
        return odontologoService.listarOdontologos();
    }


    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> buscarOdontologoPorId( @PathVariable Long id ) {
        ResponseEntity<OdontologoDto> respuesta;
        OdontologoDto odontologoDto = odontologoService.buscarOdontologoPorId(id);
        if (odontologoDto != null) respuesta = new ResponseEntity<>(odontologoDto, null, HttpStatus.OK);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }


    //POST
    @PostMapping("/guardar")
    public ResponseEntity<OdontologoDto> guardarOdontologo( @RequestBody Odontologo odontologo ) throws BadRequestException {
        ResponseEntity<OdontologoDto> respuesta;
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);
        respuesta = new ResponseEntity<>(odontologoDto, null, HttpStatus.CREATED);
        return respuesta;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDto> actualizarTurno(@RequestBody Odontologo odontologo ) {
        ResponseEntity<OdontologoDto> respuesta;
        OdontologoDto odontologoDto = odontologoService.actualizarOdontologo(odontologo);
        respuesta = new ResponseEntity<>(odontologoDto, null, HttpStatus.OK);
        return respuesta;
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Se elimino el odontologo con exito");
    }

}
