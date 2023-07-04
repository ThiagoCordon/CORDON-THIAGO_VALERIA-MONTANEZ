package com.digital.ClinicaOdontologica.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "PACIENTES")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Size(max = 50, message = "Maximo De 50 Caracteres")
    @NotNull(message = "Se Debe Rellenar Este Campo")
    @NotBlank(message = "Debe Especificar El Nombre Del Paciente")
    private String nombre;

    @Size(max = 50, message = "Maximo De 50 Caracteres")
    @NotNull(message = "Se Debe Rellenar Este Campo")
    @NotBlank(message = "Debe Especificar El Apellido Del Paciente")
    private String apellido;
    @Size(max = 12, message = "Maximo De 12 Caracteres")
    @NotNull(message = "Se Debe Rellenar Este Campo")
    @Pattern(regexp = "\\d+", message = "El campo dni sólo admite caracteres numéricos")
    @NotBlank(message = "Debe Especificar El DNI Del Paciente")
    private String dni;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Se Debe Rellenar Este Campo")
    @FutureOrPresent (message = "La fecha de ingreso no debe ser anterior al dia de hoy")
    private LocalDate fechaIngreso;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "domicilio_id")
    private Domicilio domicilio;

    public Paciente() {
    }

    public Paciente(String nombre, String apellido, String dni, LocalDate fechaIngreso, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }


    public Long getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Id: " + id + " - Nombre: " + nombre + " - Apellido: " + apellido + " - DNI: " + dni + " - Fecha de ingreso: " + fechaIngreso + " - Domicilio: " + domicilio;
    }
}

