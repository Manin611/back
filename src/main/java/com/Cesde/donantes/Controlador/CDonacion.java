package com.Cesde.donantes.Controlador;

import com.Cesde.donantes.Modelo.MDonacion;
import com.Cesde.donantes.Servicio.SDonacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donacion")
// Origen
@CrossOrigin(origins = "*")
public class CDonacion {

    @Autowired
    SDonacion sDonacion;

    // Adicion de registros de donacion
    @PostMapping
    public ResponseEntity<?> adicionarDonacion(@RequestBody MDonacion mDonacion) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.sDonacion.adicionarDonacion(mDonacion));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta general de donaciones
    @GetMapping
    public ResponseEntity<?> consultaGeneralDonacion() throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sDonacion.consultaGeneralDonacion());
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta individual por llave primaria
    @GetMapping("/{idDonacion}")
    public ResponseEntity<?> consultaIndividualId(@PathVariable Integer idDonacion) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sDonacion.consultaIndividualId(idDonacion));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta donaciones por donante
    @GetMapping("/donante/{idDonante}")
    public ResponseEntity<?> consultaPorDonante(@PathVariable Integer idDonante) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sDonacion.consultaPorDonante(idDonante));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta donaciones por solicitud
    @GetMapping("/solicitud/{idSolicitud}")
    public ResponseEntity<?> consultaPorSolicitud(@PathVariable Integer idSolicitud) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sDonacion.consultaPorSolicitud(idSolicitud));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Modificar un registro de donacion
    @PutMapping("/{idDonacion}")
    public ResponseEntity<?> modificarDonacion(@PathVariable Integer idDonacion,
                                                @RequestBody MDonacion mDonacion) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sDonacion.modificarDonacion(idDonacion, mDonacion));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Eliminar un registro donacion
    @DeleteMapping("/{idDonacion}")
    public ResponseEntity<?> eliminarDonacion(@PathVariable Integer idDonacion) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sDonacion.eliminarDonacion(idDonacion));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Anular registro donacion
    @PatchMapping("/{idDonacion}")
    public ResponseEntity<?> anularDonacion(@PathVariable Integer idDonacion,
                                             @RequestBody MDonacion mDonacion) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sDonacion.anularDonacion(idDonacion, mDonacion));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
}
