package com.Cesde.donantes.Servicio;

import com.Cesde.donantes.Modelo.MDonante;
import com.Cesde.donantes.Repositorio.RDonante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SDonante {

    @Autowired
    RDonante rDonante;

    public SDonante(RDonante rDonante) {
        this.rDonante = rDonante;
    }

    // Adicion de registros de donante
    public MDonante adicionarDonante(MDonante mDonante) throws Exception {
        try {
            return rDonante.save(mDonante);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta general de donantes
    public List<MDonante> consultaGeneralDonante() throws Exception {
        try {
            return rDonante.findAll();
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta individual por llave primaria
    public MDonante consultaIndividualId(Integer idDonante) throws Exception {
        try {
            Optional<MDonante> registroEncontrado = rDonante.findById(idDonante);
            if (registroEncontrado.isPresent())
                return registroEncontrado.get();
            else
                throw new Exception("Donante no registrado");
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta por tipo de sangre
    public List<MDonante> consultaPorTipoSangre(String tipoSangre) throws Exception {
        try {
            return rDonante.findByTipoSangre(tipoSangre);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta donantes activos
    public List<MDonante> consultaDonantesActivos() throws Exception {
        try {
            return rDonante.findByActivo(true);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta donantes activos por tipo de sangre
    public List<MDonante> consultaActivosPorTipoSangre(String tipoSangre) throws Exception {
        try {
            return rDonante.findByTipoSangreAndActivo(tipoSangre, true);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Modificar un registro de donante
    public MDonante modificarDonante(Integer idDonante, MDonante mDonante) throws Exception {
        try {
            Optional<MDonante> registroEncontrado = rDonante.findById(idDonante);
            if (registroEncontrado.isPresent()) {
                MDonante nuevoRegistro = registroEncontrado.get();
                nuevoRegistro.setNombre(mDonante.getNombre());
                nuevoRegistro.setApellido(mDonante.getApellido());
                nuevoRegistro.setFechaNacimiento(mDonante.getFechaNacimiento());
                nuevoRegistro.setTipoSangre(mDonante.getTipoSangre());
                nuevoRegistro.setTelefono(mDonante.getTelefono());
                nuevoRegistro.setEmail(mDonante.getEmail());
                nuevoRegistro.setActivo(mDonante.getActivo());
                return rDonante.save(nuevoRegistro);
            } else {
                throw new Exception("No se puede modificar porque el donante no esta registrado");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Eliminar un registro donante
    public Boolean eliminarDonante(Integer idDonante) throws Exception {
        try {
            Optional<MDonante> registroEncontrado = rDonante.findById(idDonante);
            if (registroEncontrado.isPresent()) {
                rDonante.deleteById(idDonante);
                return true;
            } else {
                throw new Exception("No se puede eliminar porque el donante no esta registrado");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Anular registro donante
    public MDonante anularDonante(Integer idDonante, MDonante mDonante) throws Exception {
        try {
            Optional<MDonante> registroEncontrado = rDonante.findById(idDonante);
            if (registroEncontrado.isPresent()) {
                MDonante nuevoRegistro = registroEncontrado.get();
                nuevoRegistro.setActivo(mDonante.getActivo());
                return rDonante.save(nuevoRegistro);
            } else {
                throw new Exception("No se puede anular porque el donante no esta registrado");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }
}
