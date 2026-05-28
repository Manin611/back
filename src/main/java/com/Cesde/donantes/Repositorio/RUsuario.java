package com.Cesde.donantes.Repositorio;

import com.Cesde.donantes.Modelo.MUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RUsuario extends JpaRepository<MUsuario, Integer> {

    Optional<MUsuario> findByEmail(String email);

}
