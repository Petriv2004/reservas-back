package main.java.laboratorio.lab.services;

import main.java.laboratorio.lab.models.Estudiantes;
import main.java.laboratorio.lab.models.Reservas;
import main.java.laboratorio.lab.repositories.EstudiantesRepository;
import main.java.laboratorio.lab.repositories.LaboratoristaRepository;
import main.java.laboratorio.lab.repositories.ReservasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class LaboratoristaService {
    @Autowired
    private EstudiantesRepository estudiantesRepository;
    @Autowired
    private ReservasRepository reservasRepository;
    @Autowired
    private LaboratoristaRepository laboratoristaRepository;

    public LaboratoristaService(EstudiantesRepository estudiantesRepository, ReservasRepository reservasRepository, LaboratoristaRepository laboratoristaRepository) {
        this.estudiantesRepository = estudiantesRepository;
        this.reservasRepository = reservasRepository;
        this.laboratoristaRepository = laboratoristaRepository;
    }

    public List<Reservas> ReservasporEstudiante(Integer idCodigo) {
        return estudiantesRepository.findActiveReservationsByStudent(idCodigo);
    }

    public List<Reservas> ReservasporFecha(Date fecha) {
        return laboratoristaRepository.reservasPorFecha(fecha);
    }

    public Integer obtenerTotalVisitas() {
        return estudiantesRepository.totalVisitas();
    }

    public Integer obtenerVisitasPorCodigo(Integer idCodigo) {
        Estudiantes estudiante = estudiantesRepository.findByIdCodigo(idCodigo);
        return (estudiante != null) ? estudiante.getVisitas() : 0;
    }

    public List<Reservas> obtenerTodasLasReservas() {
        return reservasRepository.findAll();
    }

    public long contarReservas() {
        return reservasRepository.count();
    }
}


