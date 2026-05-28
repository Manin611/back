package com.Cesde.donantes.Servicio;

import com.Cesde.donantes.Modelo.MUsuario;
import com.Cesde.donantes.Repositorio.RUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SUsuario {

    @Autowired
    RUsuario rUsuario;

    public SUsuario(RUsuario rUsuario) {
        this.rUsuario = rUsuario;
    }

    // Login
    public MUsuario login(String email, String password) throws Exception {
        try {
            Optional<MUsuario> registroEncontrado = rUsuario.findByEmail(email);
            if (registroEncontrado.isPresent()) {
                MUsuario usuario = registroEncontrado.get();
                if (!usuario.getActivo()) {
                    throw new Exception("Usuario inactivo");
                }
                if (usuario.getPassword().equals(password)) {
                    return usuario;
                } else {
                    throw new Exception("Contrasena incorrecta");
                }
            } else {
                throw new Exception("Usuario no registrado");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Adicion de registros de usuario
    public MUsuario adicionarUsuario(MUsuario mUsuario) throws Exception {
        try {
            return rUsuario.save(mUsuario);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta general de usuarios
    public List<MUsuario> consultaGeneralUsuario() throws Exception {
        try {
            return rUsuario.findAll();
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta individual por llave primaria
    public MUsuario consultaIndividualId(Integer idUsuario) throws Exception {
        try {
            Optional<MUsuario> registroEncontrado = rUsuario.findById(idUsuario);
            if (registroEncontrado.isPresent())
                return registroEncontrado.get();
            else
                throw new Exception("Usuario no registrado");
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Modificar un registro de usuario
    public MUsuario modificarUsuario(Integer idUsuario, MUsuario mUsuario) throws Exception {
        try {
            Optional<MUsuario> registroEncontrado = rUsuario.findById(idUsuario);
            if (registroEncontrado.isPresent()) {
                MUsuario nuevoRegistro = registroEncontrado.get();
                nuevoRegistro.setNombre(mUsuario.getNombre());
                nuevoRegistro.setEmail(mUsuario.getEmail());
                nuevoRegistro.setPassword(mUsuario.getPassword());
                nuevoRegistro.setRol(mUsuario.getRol());
                nuevoRegistro.setActivo(mUsuario.getActivo());
                return rUsuario.save(nuevoRegistro);
            } else {
                throw new Exception("No se puede modificar porque el usuario no esta registrado");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Eliminar un registro usuario
    public Boolean eliminarUsuario(Integer idUsuario) throws Exception {
        try {
            Optional<MUsuario> registroEncontrado = rUsuario.findById(idUsuario);
            if (registroEncontrado.isPresent()) {
                rUsuario.deleteById(idUsuario);
                return true;
            } else {
                throw new Exception("No se puede eliminar porque el usuario no esta registrado");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Anular registro usuario
    public MUsuario anularUsuario(Integer idUsuario, MUsuario mUsuario) throws Exception {
        try {
            Optional<MUsuario> registroEncontrado = rUsuario.findById(idUsuario);
            if (registroEncontrado.isPresent()) {
                MUsuario nuevoRegistro = registroEncontrado.get();
                nuevoRegistro.setActivo(mUsuario.getActivo());
                return rUsuario.save(nuevoRegistro);
            } else {
                throw new Exception("No se puede anular porque el usuario no esta registrado");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }
}
