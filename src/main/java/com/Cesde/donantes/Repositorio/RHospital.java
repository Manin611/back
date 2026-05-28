package com.Cesde.donantes.Repositorio;

import com.Cesde.donantes.Modelo.MHospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RHospital extends JpaRepository<MHospital, Integer> {

    List<MHospital> findByCiudad(String ciudad);

}
