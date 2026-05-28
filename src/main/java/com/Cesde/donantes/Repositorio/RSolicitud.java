package com.Cesde.donantes.Repositorio;

import com.Cesde.donantes.Modelo.MSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RSolicitud extends JpaRepository<MSolicitud, Integer> {

    List<MSolicitud> findByTipoSangreReq(String tipoSangreReq);

    List<MSolicitud> findByHospitalIdHospital(Integer idHospital);

    List<MSolicitud> findByEstado(String estado);

}
