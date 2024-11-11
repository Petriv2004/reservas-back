package main.java.laboratorio.lab.mappers;

import main.java.laboratorio.lab.dtos.EstudiantesDto;
import main.java.laboratorio.lab.models.Estudiantes;
import org.springframework.stereotype.Service;

@Service
public class EstudiantesMapper {
    public EstudiantesDto EstudiantesToDto (Estudiantes estudiantes){
        var est = new EstudiantesDto(estudiantes.getNombre(),estudiantes.getCedula(),estudiantes.getId_carrera());
        return est;
    }
}
