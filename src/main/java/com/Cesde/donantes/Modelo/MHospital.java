package com.Cesde.donantes.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="hospitales")
public class MHospital {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_hospital", nullable = false)
    Integer idHospital;

    @Column(length = 100, nullable = false)
    String nombre;

    @Column(length = 200)
    String direccion;

    @Column(length = 20)
    String telefono;

    @Column(length = 60)
    String ciudad;

    // Relaciones
    @OneToMany(mappedBy = "hospital")
    @JsonIgnore
    List<MSolicitud> solicitudes;

    // Constructores
    public MHospital(Integer idHospital, String nombre, String direccion, String telefono, String ciudad) {
        this.idHospital = idHospital;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
    }
    public MHospital() {
    }

    // Encapsulamiento
    public Integer getIdHospital() { return idHospital; }
    public void setIdHospital(Integer idHospital) { this.idHospital = idHospital; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public List<MSolicitud> getSolicitudes() { return solicitudes; }
    public void setSolicitudes(List<MSolicitud> solicitudes) { this.solicitudes = solicitudes; }
}
