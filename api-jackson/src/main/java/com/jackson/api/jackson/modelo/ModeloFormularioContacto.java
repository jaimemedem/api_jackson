package com.jackson.api.jackson.modelo;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModeloFormularioContacto (
       @NotBlank(message = "El nombre no puede estar vacío")
       String name,
       @Email(message = "Introduzca un email válido")
       @NotBlank(message = "El emai no puede estar vacío")
       String email,
       @Digits(message = "introduzca un número", integer = 0, fraction = 0)
       @NotBlank(message = "El número no puede estar vacío")
       String number,
       @NotBlank(message = "El mensaje nopuede estar vacío")
       @Size(min=10)
       String mensaje
)
{

}