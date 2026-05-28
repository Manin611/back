package com.Cesde.donantes.Controlador;

import com.Cesde.donantes.Modelo.MHospital;
import com.Cesde.donantes.Servicio.SHospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital")
// Origen
@CrossOrigin(origins = "*")
public class CHospital {

    @Autowired
    SHospital sHospital;

    // Adicion de registros de hospital
    @PostMapping
    public ResponseEntity<?> adicionarHospital(@RequestBody MHospital mHospital) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.sHospital.adicionarHospital(mHospital));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta general de hospitales
    @GetMapping
    public ResponseEntity<?> consultaGeneralHospital() throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sHospital.consultaGeneralHospital());
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta individual por llave primaria
    @GetMapping("/{idHospital}")
    public ResponseEntity<?> consultaIndividualId(@PathVariable Integer idHospital) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sHospital.consultaIndividualId(idHospital));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta por ciudad
    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<?> consultaPorCiudad(@PathVariable String ciudad) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sHospital.consultaPorCiudad(ciudad));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Modificar un registro de hospital
    @PutMapping("/{idHospital}")
    public ResponseEntity<?> modificarHospital(@PathVariable Integer idHospital,
                                                @RequestBody MHospital mHospital) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sHospital.modificarHospital(idHospital, mHospital));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Eliminar un registro hospital
    @DeleteMapping("/{idHospital}")
    public ResponseEntity<?> eliminarHospital(@PathVariable Integer idHospital) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sHospital.eliminarHospital(idHospital));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
}
