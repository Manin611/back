package com.Cesde.donantes.Modelo;

import jakarta.persistence.*;

@Entity
@Table(name="usuarios")
public class MUsuario {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_usuario", nullable = false)
    Integer idUsuario;

    @Column(length = 100, nullable = false)
    String nombre;

    @Column(length = 100, nullable = false, unique = true)
    String email;

    @Column(length = 100, nullable = false)
    String password;

    @Column(length = 20, nullable = false)
    String rol;

    @Column(nullable = false)
    Boolean activo;

    // Constructores
    public MUsuario(Integer idUsuario, String nombre, String email,
                    String password, String rol, Boolean activo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.activo = activo;
    }
    public MUsuario() {
    }

    // Encapsulamiento
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}
