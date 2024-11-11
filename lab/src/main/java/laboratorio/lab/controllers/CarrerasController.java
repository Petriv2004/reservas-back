package main.java.laboratorio.lab.controllers;

import main.java.laboratorio.lab.services.CarrerasService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarrerasController {
    private CarrerasService carrerasService;

    public CarrerasController(CarrerasService carrerasService) {
        this.carrerasService = carrerasService;
    }

}

