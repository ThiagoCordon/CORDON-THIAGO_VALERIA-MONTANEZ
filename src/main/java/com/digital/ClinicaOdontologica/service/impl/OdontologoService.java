package com.digital.ClinicaOdontologica.service.impl;
;
import com.digital.ClinicaOdontologica.dto.OdontologoDto;
import com.digital.ClinicaOdontologica.entity.Odontologo;
import com.digital.ClinicaOdontologica.exception.BadRequestException;
import com.digital.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.digital.ClinicaOdontologica.repository.OdontologoRepository;
import com.digital.ClinicaOdontologica.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OdontologoService implements IOdontologoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final OdontologoRepository odontologoRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public OdontologoService( OdontologoRepository odontologoRepository, ObjectMapper objectMapper ) {
        this.odontologoRepository = odontologoRepository;
        this.objectMapper = objectMapper;
    }

    public List<OdontologoDto> listarOdontologos() {
        List<Odontologo> odontologosList = odontologoRepository.findAll();
        List<OdontologoDto> odontologoDtoList = odontologosList.stream()
                .map(odontologo -> new OdontologoDto(odontologo.getId(),odontologo.getMatricula(), odontologo.getNombre(), odontologo.getApellido()))
                .toList();

        LOGGER.info("Lista de todos los odontologos: {}", odontologoDtoList);

        return odontologoDtoList;
    }

    public OdontologoDto buscarOdontologoPorId( Long id ) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoDto odontologoDto = null;
        if (odontologoBuscado != null) {
            odontologoDto = objectMapper.convertValue(odontologoBuscado, OdontologoDto.class);
            LOGGER.info("El Odontologo fue encontrado: {}", odontologoDto);

        } else LOGGER.info("El id no se encuentra registrado en la base de datos");

        return odontologoDto;
    }


    @Override
    public OdontologoDto registrarOdontologo( Odontologo odontologo ) throws BadRequestException {
        Odontologo odontologoNuevo = null;
        OdontologoDto odontologoDtoNuevo = null;

        if (odontologo == null) {
            LOGGER.error("El odontologo no se encuentra en nuestra base de datos");
            throw new BadRequestException("El odontologo no se encuentra en nuestra base de datos");
        } else {
            odontologoNuevo = odontologoRepository.save(odontologo);
            odontologoDtoNuevo = objectMapper.convertValue(odontologoNuevo, OdontologoDto.class);

            LOGGER.info("Se registro el odontologo con exito: {}", odontologoDtoNuevo);
        }
        return odontologoDtoNuevo;


    }

    @Override
    public OdontologoDto actualizarOdontologo(Odontologo odontologo ) {
        Odontologo odontologoAModificar = odontologoRepository.findById(odontologo.getId()).orElse(null);
        OdontologoDto odontologoModificadoDto = null;

        if (odontologoAModificar != null) {
            odontologoAModificar = odontologo;
            odontologoRepository.save(odontologoAModificar);

            odontologoModificadoDto = objectMapper.convertValue(odontologoAModificar, OdontologoDto.class);
            LOGGER.info("Odontologo actualizado con exito: {}", odontologoModificadoDto);

        } else LOGGER.error("No fue posible actualizar los datos ya que el odontologo no se encuentra en nuestra base de datos");

        return odontologoModificadoDto;
    }
    @Override
    public void eliminarOdontologo( Long id ) throws ResourceNotFoundException {
        if (buscarOdontologoPorId(id) != null) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el odontologo con id {}", id);
        } else {
            LOGGER.error("No se ha encontrado odontologo con id: {}", id);
            throw new ResourceNotFoundException("No se ha encontrado odontologo con id: " + id);
        }

    }


}
