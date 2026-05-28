package com.Cesde.donantes.Controlador;

import com.Cesde.donantes.Servicio.SAnalitica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analitica")
@CrossOrigin(origins = "*")
public class CAnalitica {

    @Autowired
    SAnalitica sAnalitica;

    @GetMapping("/donaciones/por-mes")
    public ResponseEntity<?> donacionesPorMes() throws Exception {
        try {
            List<Map<String, Object>> data = sAnalitica.donacionesPorMes();
            return ResponseEntity.status(HttpStatus.OK).body(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/solicitudes/pendientes-por-hospital")
    public ResponseEntity<?> pendientesPorHospital() throws Exception {
        try {
            List<Map<String, Object>> data = sAnalitica.solicitudesPendientesPorHospital();
            return ResponseEntity.status(HttpStatus.OK).body(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/tipo-sangre/demanda")
    public ResponseEntity<?> demandaTipoSangre() throws Exception {
        try {
            List<Map<String, Object>> data = sAnalitica.demandaPorTipoSangre();
            return ResponseEntity.status(HttpStatus.OK).body(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/donantes/actividad")
    public ResponseEntity<?> actividadDonantes() throws Exception {
        try {
            List<Map<String, Object>> data = sAnalitica.actividadDonantes();
            return ResponseEntity.status(HttpStatus.OK).body(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/top-hospitales")
    public ResponseEntity<?> topHospitales() throws Exception {
        try {
            List<Map<String, Object>> data = sAnalitica.topHospitalesPorDonaciones();
            return ResponseEntity.status(HttpStatus.OK).body(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
