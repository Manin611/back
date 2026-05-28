package com.Cesde.donantes.Servicio;

import com.Cesde.donantes.Modelo.MHospital;
import com.Cesde.donantes.Repositorio.RHospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SHospital {

    @Autowired
    RHospital rHospital;

    public SHospital(RHospital rHospital) {
        this.rHospital = rHospital;
    }

    // Adicion de registros de hospital
    public MHospital adicionarHospital(MHospital mHospital) throws Exception {
        try {
            return rHospital.save(mHospital);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta general de hospitales
    public List<MHospital> consultaGeneralHospital() throws Exception {
        try {
            return rHospital.findAll();
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta individual por llave primaria
    public MHospital consultaIndividualId(Integer idHospital) throws Exception {
        try {
            Optional<MHospital> registroEncontrado = rHospital.findById(idHospital);
            if (registroEncontrado.isPresent())
                return registroEncontrado.get();
            else
                throw new Exception("Hospital no registrado");
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta por ciudad
    public List<MHospital> consultaPorCiudad(String ciudad) throws Exception {
        try {
            return rHospital.findByCiudad(ciudad);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Modificar un registro de hospital
    public MHospital modificarHospital(Integer idHospital, MHospital mHospital) throws Exception {
        try {
            Optional<MHospital> registroEncontrado = rHospital.findById(idHospital);
            if (registroEncontrado.isPresent()) {
                MHospital nuevoRegistro = registroEncontrado.get();
                nuevoRegistro.setNombre(mHospital.getNombre());
                nuevoRegistro.setDireccion(mHospital.getDireccion());
                nuevoRegistro.setTelefono(mHospital.getTelefono());
                nuevoRegistro.setCiudad(mHospital.getCiudad());
                return rHospital.save(nuevoRegistro);
            } else {
                throw new Exception("No se puede modificar porque el hospital no esta registrado");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Eliminar un registro hospital
    public Boolean eliminarHospital(Integer idHospital) throws Exception {
        try {
            Optional<MHospital> registroEncontrado = rHospital.findById(idHospital);
            if (registroEncontrado.isPresent()) {
                rHospital.deleteById(idHospital);
                return true;
            } else {
                throw new Exception("No se puede eliminar porque el hospital no esta registrado");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }
}
