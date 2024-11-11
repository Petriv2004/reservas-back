package main.java.laboratorio.lab.controllers;

import main.java.laboratorio.lab.models.Estudiantes;
import main.java.laboratorio.lab.models.Inventarios;
import main.java.laboratorio.lab.models.Laboratorista;
import main.java.laboratorio.lab.services.AutenticationLaboratoristaService;
import main.java.laboratorio.lab.services.AutenticationService;
import main.java.laboratorio.lab.services.GerenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Gerente")

public class GerenteController {

    private AutenticationLaboratoristaService autenticationLaboratoristaService;
    private GerenteService gerenteService;


    public GerenteController(AutenticationLaboratoristaService autenticationLaboratoristaService, GerenteService gerenteService) {
        this.autenticationLaboratoristaService = autenticationLaboratoristaService;
        this.gerenteService = gerenteService;
    }

    @GetMapping("/total-reservas")
    public long contarReservas() {
        return gerenteService.contarReservas();
    }

    @GetMapping("/reservas-Activas")
    public long contarReservasActivas(){
        return gerenteService.obtenerReservasActivas();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Laboratorista jefeLab) {

        boolean esAutenticado = autenticationLaboratoristaService.autenticar(jefeLab.getCorreo(), jefeLab.getContrasena());
        if (esAutenticado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
