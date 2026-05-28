package com.Cesde.donantes.Controlador;

import com.Cesde.donantes.Modelo.MSolicitud;
import com.Cesde.donantes.Servicio.SSolicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solicitud")
// Origen
@CrossOrigin(origins = "*")
public class CSolicitud {

    @Autowired
    SSolicitud sSolicitud;

    // Adicion de registros de solicitud
    @PostMapping
    public ResponseEntity<?> adicionarSolicitud(@RequestBody MSolicitud mSolicitud) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.sSolicitud.adicionarSolicitud(mSolicitud));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta general de solicitudes
    @GetMapping
    public ResponseEntity<?> consultaGeneralSolicitud() throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sSolicitud.consultaGeneralSolicitud());
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta individual por llave primaria
    @GetMapping("/{idSolicitud}")
    public ResponseEntity<?> consultaIndividualId(@PathVariable Integer idSolicitud) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sSolicitud.consultaIndividualId(idSolicitud));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta por tipo de sangre requerido
    @GetMapping("/sangre/{tipoSangreReq}")
    public ResponseEntity<?> consultaPorTipoSangre(@PathVariable String tipoSangreReq) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sSolicitud.consultaPorTipoSangre(tipoSangreReq));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta por hospital
    @GetMapping("/hospital/{idHospital}")
    public ResponseEntity<?> consultaPorHospital(@PathVariable Integer idHospital) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sSolicitud.consultaPorHospital(idHospital));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> consultaPorEstado(@PathVariable String estado) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sSolicitud.consultaPorEstado(estado));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Modificar un registro de solicitud
    @PutMapping("/{idSolicitud}")
    public ResponseEntity<?> modificarSolicitud(@PathVariable Integer idSolicitud,
                                                 @RequestBody MSolicitud mSolicitud) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sSolicitud.modificarSolicitud(idSolicitud, mSolicitud));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Eliminar un registro solicitud
    @DeleteMapping("/{idSolicitud}")
    public ResponseEntity<?> eliminarSolicitud(@PathVariable Integer idSolicitud) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sSolicitud.eliminarSolicitud(idSolicitud));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Anular registro solicitud
    @PatchMapping("/{idSolicitud}")
    public ResponseEntity<?> anularSolicitud(@PathVariable Integer idSolicitud,
                                              @RequestBody MSolicitud mSolicitud) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sSolicitud.anularSolicitud(idSolicitud, mSolicitud));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
}
