package com.Cesde.donantes.Controlador;

import com.Cesde.donantes.Modelo.MDonante;
import com.Cesde.donantes.Servicio.SDonante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donante")
// Origen
@CrossOrigin(origins = "*")
public class CDonante {

    @Autowired
    SDonante sDonante;

    // Adicion de registros de donante
    @PostMapping
    public ResponseEntity<?> adicionarDonante(@RequestBody MDonante mDonante) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.sDonante.adicionarDonante(mDonante));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta general de donantes
    @GetMapping
    public ResponseEntity<?> consultaGeneralDonante() throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sDonante.consultaGeneralDonante());
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta individual por llave primaria
    @GetMapping("/{idDonante}")
    public ResponseEntity<?> consultaIndividualId(@PathVariable Integer idDonante) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sDonante.consultaIndividualId(idDonante));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta por tipo de sangre
    @GetMapping("/sangre/{tipoSangre}")
    public ResponseEntity<?> consultaPorTipoSangre(@PathVariable String tipoSangre) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sDonante.consultaPorTipoSangre(tipoSangre));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta donantes activos
    @GetMapping("/activos")
    public ResponseEntity<?> consultaDonantesActivos() throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sDonante.consultaDonantesActivos());
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta donantes activos por tipo de sangre
    @GetMapping("/activos/sangre/{tipoSangre}")
    public ResponseEntity<?> consultaActivosPorTipoSangre(@PathVariable String tipoSangre) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sDonante.consultaActivosPorTipoSangre(tipoSangre));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Modificar un registro de donante
    @PutMapping("/{idDonante}")
    public ResponseEntity<?> modificarDonante(@PathVariable Integer idDonante,
                                               @RequestBody MDonante mDonante) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sDonante.modificarDonante(idDonante, mDonante));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Eliminar un registro donante
    @DeleteMapping("/{idDonante}")
    public ResponseEntity<?> eliminarDonante(@PathVariable Integer idDonante) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sDonante.eliminarDonante(idDonante));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Anular registro donante
    @PatchMapping("/{idDonante}")
    public ResponseEntity<?> anularDonante(@PathVariable Integer idDonante,
                                            @RequestBody MDonante mDonante) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sDonante.anularDonante(idDonante, mDonante));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
}
