package com.Cesde.donantes.Repositorio;

import com.Cesde.donantes.Modelo.MDonacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RDonacion extends JpaRepository<MDonacion, Integer> {

    List<MDonacion> findByDonanteIdDonante(Integer idDonante);

    List<MDonacion> findBySolicitudIdSolicitud(Integer idSolicitud);

}
