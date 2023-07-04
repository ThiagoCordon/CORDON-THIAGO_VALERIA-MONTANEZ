package com.digital.ClinicaOdontologica.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ODONTOLOGOS")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 50, message = "Se Permite Hasta 50 Caracteres")
    @NotNull(message = "Se Debe Rellenar Este Campo")
    @NotBlank(message = "Debe Especificar El Numero De Matricula")
    @Pattern(regexp = "^[A-Z]{2}-\\d{1,3}\\d*$", message="La matricula debe cumplir con el formato")
    private String matricula;
    @Size(max = 50, message = "Se Permite Hasta 50 Caracteres")
    @NotNull(message = "Se Debe Rellenar Este Campo")
    @NotBlank(message = "Debe Especificar El Nombre Del Odontologo")
    private String nombre;
    @Size(max = 50, message = "Se Permite Hasta 50 Caracteres")
    @NotNull(message = "Se Debe Rellenar Este Campo")
    @NotBlank(message = "Debe Especificar El Apellido Del Odontologo")
    private String apellido;

    public Odontologo() {
    }


    public Odontologo(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    @Override
    public String toString() {
        return "Id: " + id + " - Nombre: " + nombre + " - Apellido: " + apellido + " - Matricula: " + matricula;
    }
}
