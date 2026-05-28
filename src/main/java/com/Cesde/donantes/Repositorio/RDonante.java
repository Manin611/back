package com.Cesde.donantes.Repositorio;

import com.Cesde.donantes.Modelo.MDonante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RDonante extends JpaRepository<MDonante, Integer> {

    List<MDonante> findByTipoSangre(String tipoSangre);

    List<MDonante> findByActivo(Boolean activo);

    List<MDonante> findByTipoSangreAndActivo(String tipoSangre, Boolean activo);

}
