package com.Cesde.donantes.Servicio;

import com.Cesde.donantes.Modelo.MDonacion;
import com.Cesde.donantes.Repositorio.RDonacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SDonacion {

    @Autowired
    RDonacion rDonacion;

    public SDonacion(RDonacion rDonacion) {
        this.rDonacion = rDonacion;
    }

    // Adicion de registros de donacion
    public MDonacion adicionarDonacion(MDonacion mDonacion) throws Exception {
        try {
            return rDonacion.save(mDonacion);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta general de donaciones
    public List<MDonacion> consultaGeneralDonacion() throws Exception {
        try {
            return rDonacion.findAll();
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta individual por llave primaria
    public MDonacion consultaIndividualId(Integer idDonacion) throws Exception {
        try {
            Optional<MDonacion> registroEncontrado = rDonacion.findById(idDonacion);
            if (registroEncontrado.isPresent())
                return registroEncontrado.get();
            else
                throw new Exception("Donacion no registrada");
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta donaciones por donante
    public List<MDonacion> consultaPorDonante(Integer idDonante) throws Exception {
        try {
            return rDonacion.findByDonanteIdDonante(idDonante);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta donaciones por solicitud
    public List<MDonacion> consultaPorSolicitud(Integer idSolicitud) throws Exception {
        try {
            return rDonacion.findBySolicitudIdSolicitud(idSolicitud);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Modificar un registro de donacion
    public MDonacion modificarDonacion(Integer idDonacion, MDonacion mDonacion) throws Exception {
        try {
            Optional<MDonacion> registroEncontrado = rDonacion.findById(idDonacion);
            if (registroEncontrado.isPresent()) {
                MDonacion nuevoRegistro = registroEncontrado.get();
                nuevoRegistro.setDonante(mDonacion.getDonante());
                nuevoRegistro.setSolicitud(mDonacion.getSolicitud());
                nuevoRegistro.setFechaDonacion(mDonacion.getFechaDonacion());
                nuevoRegistro.setCantidadMl(mDonacion.getCantidadMl());
                nuevoRegistro.setEstado(mDonacion.getEstado());
                return rDonacion.save(nuevoRegistro);
            } else {
                throw new Exception("No se puede modificar porque la donacion no esta registrada");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Eliminar un registro donacion
    public Boolean eliminarDonacion(Integer idDonacion) throws Exception {
        try {
            Optional<MDonacion> registroEncontrado = rDonacion.findById(idDonacion);
            if (registroEncontrado.isPresent()) {
                rDonacion.deleteById(idDonacion);
                return true;
            } else {
                throw new Exception("No se puede eliminar porque la donacion no esta registrada");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Anular registro donacion
    public MDonacion anularDonacion(Integer idDonacion, MDonacion mDonacion) throws Exception {
        try {
            Optional<MDonacion> registroEncontrado = rDonacion.findById(idDonacion);
            if (registroEncontrado.isPresent()) {
                MDonacion nuevoRegistro = registroEncontrado.get();
                nuevoRegistro.setEstado(mDonacion.getEstado());
                return rDonacion.save(nuevoRegistro);
            } else {
                throw new Exception("No se puede anular porque la donacion no esta registrada");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }
}
