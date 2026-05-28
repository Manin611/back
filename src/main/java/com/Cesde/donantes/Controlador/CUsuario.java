package com.Cesde.donantes.Controlador;

import com.Cesde.donantes.Modelo.MUsuario;
import com.Cesde.donantes.Servicio.SUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuario")
// Origen
@CrossOrigin(origins = "*")
public class CUsuario {

    @Autowired
    SUsuario sUsuario;

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) throws Exception {
        try {
            String email = credenciales.get("email");
            String password = credenciales.get("password");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sUsuario.login(email, password));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(error.getMessage());
        }
    }

    // Adicion de registros de usuario
    @PostMapping
    public ResponseEntity<?> adicionarUsuario(@RequestBody MUsuario mUsuario) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.sUsuario.adicionarUsuario(mUsuario));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta general de usuarios
    @GetMapping
    public ResponseEntity<?> consultaGeneralUsuario() throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sUsuario.consultaGeneralUsuario());
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta individual por llave primaria
    @GetMapping("/{idUsuario}")
    public ResponseEntity<?> consultaIndividualId(@PathVariable Integer idUsuario) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sUsuario.consultaIndividualId(idUsuario));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Modificar un registro de usuario
    @PutMapping("/{idUsuario}")
    public ResponseEntity<?> modificarUsuario(@PathVariable Integer idUsuario,
                                               @RequestBody MUsuario mUsuario) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sUsuario.modificarUsuario(idUsuario, mUsuario));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Eliminar un registro usuario
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer idUsuario) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sUsuario.eliminarUsuario(idUsuario));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Anular registro usuario
    @PatchMapping("/{idUsuario}")
    public ResponseEntity<?> anularUsuario(@PathVariable Integer idUsuario,
                                            @RequestBody MUsuario mUsuario) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sUsuario.anularUsuario(idUsuario, mUsuario));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
}
