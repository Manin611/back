package com.Cesde.donantes.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="donantes")
public class MDonante {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_donante", nullable = false)
    Integer idDonante;

    @Column(length = 60, nullable = false)
    String nombre;

    @Column(length = 60, nullable = false)
    String apellido;

    @Column(name="fecha_nacimiento")
    LocalDate fechaNacimiento;

    @Column(name="tipo_sangre", length = 5, nullable = false)
    String tipoSangre;

    @Column(length = 20)
    String telefono;

    @Column(length = 100)
    String email;

    @Column(nullable = false)
    Boolean activo;

    // Relaciones
    @OneToMany(mappedBy = "donante")
    @JsonIgnore
    List<MDonacion> donaciones;

    // Constructores
    public MDonante(Integer idDonante, String nombre, String apellido, LocalDate fechaNacimiento,
                    String tipoSangre, String telefono, String email, Boolean activo) {
        this.idDonante = idDonante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSangre = tipoSangre;
        this.telefono = telefono;
        this.email = email;
        this.activo = activo;
    }
    public MDonante() {
    }

    // Encapsulamiento
    public Integer getIdDonante() { return idDonante; }
    public void setIdDonante(Integer idDonante) { this.idDonante = idDonante; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getTipoSangre() { return tipoSangre; }
    public void setTipoSangre(String tipoSangre) { this.tipoSangre = tipoSangre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public List<MDonacion> getDonaciones() { return donaciones; }
    public void setDonaciones(List<MDonacion> donaciones) { this.donaciones = donaciones; }
}
