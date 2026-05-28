package com.Cesde.donantes.Servicio;

import com.Cesde.donantes.Modelo.MSolicitud;
import com.Cesde.donantes.Repositorio.RSolicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SSolicitud {

    @Autowired
    RSolicitud rSolicitud;

    public SSolicitud(RSolicitud rSolicitud) {
        this.rSolicitud = rSolicitud;
    }

    // Adicion de registros de solicitud
    public MSolicitud adicionarSolicitud(MSolicitud mSolicitud) throws Exception {
        try {
            return rSolicitud.save(mSolicitud);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta general de solicitudes
    public List<MSolicitud> consultaGeneralSolicitud() throws Exception {
        try {
            return rSolicitud.findAll();
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta individual por llave primaria
    public MSolicitud consultaIndividualId(Integer idSolicitud) throws Exception {
        try {
            Optional<MSolicitud> registroEncontrado = rSolicitud.findById(idSolicitud);
            if (registroEncontrado.isPresent())
                return registroEncontrado.get();
            else
                throw new Exception("Solicitud no registrada");
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta por tipo de sangre requerido
    public List<MSolicitud> consultaPorTipoSangre(String tipoSangreReq) throws Exception {
        try {
            return rSolicitud.findByTipoSangreReq(tipoSangreReq);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta por hospital
    public List<MSolicitud> consultaPorHospital(Integer idHospital) throws Exception {
        try {
            return rSolicitud.findByHospitalIdHospital(idHospital);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta por estado
    public List<MSolicitud> consultaPorEstado(String estado) throws Exception {
        try {
            return rSolicitud.findByEstado(estado);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Modificar un registro de solicitud
    public MSolicitud modificarSolicitud(Integer idSolicitud, MSolicitud mSolicitud) throws Exception {
        try {
            Optional<MSolicitud> registroEncontrado = rSolicitud.findById(idSolicitud);
            if (registroEncontrado.isPresent()) {
                MSolicitud nuevoRegistro = registroEncontrado.get();
                nuevoRegistro.setHospital(mSolicitud.getHospital());
                nuevoRegistro.setTipoSangreReq(mSolicitud.getTipoSangreReq());
                nuevoRegistro.setCantidadReq(mSolicitud.getCantidadReq());
                nuevoRegistro.setFechaSolicitud(mSolicitud.getFechaSolicitud());
                nuevoRegistro.setEstado(mSolicitud.getEstado());
                return rSolicitud.save(nuevoRegistro);
            } else {
                throw new Exception("No se puede modificar porque la solicitud no esta registrada");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Eliminar un registro solicitud
    public Boolean eliminarSolicitud(Integer idSolicitud) throws Exception {
        try {
            Optional<MSolicitud> registroEncontrado = rSolicitud.findById(idSolicitud);
            if (registroEncontrado.isPresent()) {
                rSolicitud.deleteById(idSolicitud);
                return true;
            } else {
                throw new Exception("No se puede eliminar porque la solicitud no esta registrada");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Anular registro solicitud
    public MSolicitud anularSolicitud(Integer idSolicitud, MSolicitud mSolicitud) throws Exception {
        try {
            Optional<MSolicitud> registroEncontrado = rSolicitud.findById(idSolicitud);
            if (registroEncontrado.isPresent()) {
                MSolicitud nuevoRegistro = registroEncontrado.get();
                nuevoRegistro.setEstado(mSolicitud.getEstado());
                return rSolicitud.save(nuevoRegistro);
            } else {
                throw new Exception("No se puede anular porque la solicitud no esta registrada");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }
}
