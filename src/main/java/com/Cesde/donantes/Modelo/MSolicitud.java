package com.Cesde.donantes.Modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="solicitudes")
public class MSolicitud {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_solicitud", nullable = false)
    Integer idSolicitud;

    @Column(name="tipo_sangre_req", length = 5, nullable = false)
    String tipoSangreReq;

    @Column(name="cantidad_req", nullable = false)
    Integer cantidadReq;

    @Column(name="fecha_solicitud", nullable = false)
    LocalDate fechaSolicitud;

    @Column(length = 20, nullable = false)
    String estado;

    // Relaciones
    @ManyToOne
    @JoinColumn(name="id_hospital", nullable = false)
    MHospital hospital;

    // Constructores
    public MSolicitud(Integer idSolicitud, String tipoSangreReq, Integer cantidadReq,
                      LocalDate fechaSolicitud, String estado, MHospital hospital) {
        this.idSolicitud = idSolicitud;
        this.tipoSangreReq = tipoSangreReq;
        this.cantidadReq = cantidadReq;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
        this.hospital = hospital;
    }
    public MSolicitud() {
    }

    // Encapsulamiento
    public Integer getIdSolicitud() { return idSolicitud; }
    public void setIdSolicitud(Integer idSolicitud) { this.idSolicitud = idSolicitud; }

    public String getTipoSangreReq() { return tipoSangreReq; }
    public void setTipoSangreReq(String tipoSangreReq) { this.tipoSangreReq = tipoSangreReq; }

    public Integer getCantidadReq() { return cantidadReq; }
    public void setCantidadReq(Integer cantidadReq) { this.cantidadReq = cantidadReq; }

    public LocalDate getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDate fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public MHospital getHospital() { return hospital; }
    public void setHospital(MHospital hospital) { this.hospital = hospital; }
}
