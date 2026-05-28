package com.Cesde.donantes.Modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="donaciones")
public class MDonacion {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_donacion", nullable = false)
    Integer idDonacion;

    @Column(name="fecha_donacion", nullable = false)
    LocalDate fechaDonacion;

    @Column(name="cantidad_ml", nullable = false)
    Integer cantidadMl;

    @Column(length = 20, nullable = false)
    String estado;

    // Relaciones
    @ManyToOne
    @JoinColumn(name="id_donante", nullable = false)
    MDonante donante;

    @ManyToOne
    @JoinColumn(name="id_solicitud", nullable = false)
    MSolicitud solicitud;

    // Constructores
    public MDonacion(Integer idDonacion, LocalDate fechaDonacion, Integer cantidadMl,
                     String estado, MDonante donante, MSolicitud solicitud) {
        this.idDonacion = idDonacion;
        this.fechaDonacion = fechaDonacion;
        this.cantidadMl = cantidadMl;
        this.estado = estado;
        this.donante = donante;
        this.solicitud = solicitud;
    }
    public MDonacion() {
    }

    // Encapsulamiento
    public Integer getIdDonacion() { return idDonacion; }
    public void setIdDonacion(Integer idDonacion) { this.idDonacion = idDonacion; }

    public LocalDate getFechaDonacion() { return fechaDonacion; }
    public void setFechaDonacion(LocalDate fechaDonacion) { this.fechaDonacion = fechaDonacion; }

    public Integer getCantidadMl() { return cantidadMl; }
    public void setCantidadMl(Integer cantidadMl) { this.cantidadMl = cantidadMl; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public MDonante getDonante() { return donante; }
    public void setDonante(MDonante donante) { this.donante = donante; }

    public MSolicitud getSolicitud() { return solicitud; }
    public void setSolicitud(MSolicitud solicitud) { this.solicitud = solicitud; }
}
