package main.java.laboratorio.lab.controllers;

import main.java.laboratorio.lab.models.Estudiantes;
import main.java.laboratorio.lab.models.Inventarios;
import main.java.laboratorio.lab.models.Laboratorista;
import main.java.laboratorio.lab.services.AutenticationLaboratoristaService;
import main.java.laboratorio.lab.services.AutenticationService;
import main.java.laboratorio.lab.services.JefeLabService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/JefeL")
public class JefeLabController {
    private AutenticationService autenticacionService;
    private JefeLabService jefeLabService;
    private AutenticationLaboratoristaService autenticationLaboratoristaService;

    public JefeLabController(AutenticationService autenticacionService, JefeLabService jefeLabService, AutenticationLaboratoristaService autenticationLaboratoristaService) {
        this.autenticacionService = autenticacionService;
        this.jefeLabService = jefeLabService;
        this.autenticationLaboratoristaService = autenticationLaboratoristaService;
    }

    @PostMapping("/abastecer")
    public void abastecer(@RequestBody Inventarios inv){
        System.out.println(inv.getEquipo());
        jefeLabService.abastecerInv(inv.getEquipo(),inv.getCantidad());
    }

//    @PostMapping("/abastecer")
//    public ResponseEntity<String> abastecer(@RequestBody Inventarios inv) {
//        try {
//            System.out.println(inv.getEquipo());
//            jefeLabService.abastecerInv(inv.getEquipo(), inv.getCantidad());
//            return ResponseEntity.ok("Abastecimiento exitoso");
//        } catch (Exception e) {
//            // Manejar errores si es necesario
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error al abastecer el inventario");
//        }
//    }

    @GetMapping("/stats/{fecha}")
    public HashMap<String,Double> statsDia(@PathVariable String fecha){
        Date sqlDate = Date.valueOf(fecha.trim());
        return jefeLabService.statsDia(sqlDate);
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
