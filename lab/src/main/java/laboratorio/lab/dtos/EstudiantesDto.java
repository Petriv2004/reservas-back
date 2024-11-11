package main.java.laboratorio.lab.dtos;

import main.java.laboratorio.lab.models.Carreras;

public record EstudiantesDto(String nombre, int cedula, Carreras carrera) {
}
