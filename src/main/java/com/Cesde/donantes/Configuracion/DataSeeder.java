package com.Cesde.donantes.Configuracion;

import com.Cesde.donantes.Modelo.MDonacion;
import com.Cesde.donantes.Modelo.MDonante;
import com.Cesde.donantes.Modelo.MHospital;
import com.Cesde.donantes.Modelo.MSolicitud;
import com.Cesde.donantes.Modelo.MUsuario;
import com.Cesde.donantes.Repositorio.RDonacion;
import com.Cesde.donantes.Repositorio.RDonante;
import com.Cesde.donantes.Repositorio.RHospital;
import com.Cesde.donantes.Repositorio.RSolicitud;
import com.Cesde.donantes.Repositorio.RUsuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedDemoData(
            RUsuario rUsuario,
            RHospital rHospital,
            RDonante rDonante,
            RSolicitud rSolicitud,
            RDonacion rDonacion) {
        return args -> {
            MUsuario admin = ensureUser(rUsuario,
                    new MUsuario(null, "Administrador Demo", "admin@donantes.com", "Admin123!", "administrador", true));
            MUsuario hospitalUser = ensureUser(rUsuario,
                    new MUsuario(null, "Hospital Central", "hospital@donantes.com", "Hospital123!", "hospital", true));
            MUsuario donorUser = ensureUser(rUsuario,
                    new MUsuario(null, "Ana García", "ana.garcia@donantes.com", "Donante123!", "donante", true));

            MHospital hospitalCentral = ensureHospital(rHospital,
                    new MHospital(null, "Hospital General Central", "Calle 1 # 10-20", "3001234567", "Bogotá"));
            MHospital hospitalSur = ensureHospital(rHospital,
                    new MHospital(null, "Clínica del Sur", "Carrera 45 # 12-34", "3007654321", "Medellín"));

            MDonante ana = ensureDonor(rDonante,
                    new MDonante(null, "Ana", "García", LocalDate.of(1995, 5, 14), "O+", "3112223344", donorUser.getEmail(), true));
            MDonante luis = ensureDonor(rDonante,
                    new MDonante(null, "Luis", "Pérez", LocalDate.of(1992, 8, 3), "A-", "3125557788", "luis.perez@donantes.com", true));
            MDonante maria = ensureDonor(rDonante,
                    new MDonante(null, "María", "López", LocalDate.of(1990, 11, 21), "B+", "3134445566", "maria.lopez@donantes.com", false));

            MSolicitud solicitud1 = ensureSolicitud(rSolicitud,
                    new MSolicitud(null, "O+", 2, LocalDate.now().minusDays(3), "Pendiente", hospitalCentral));
            MSolicitud solicitud2 = ensureSolicitud(rSolicitud,
                    new MSolicitud(null, "A-", 3, LocalDate.now().minusDays(5), "Atendida", hospitalSur));
            MSolicitud solicitud3 = ensureSolicitud(rSolicitud,
                    new MSolicitud(null, "B+", 1, LocalDate.now().minusDays(1), "Pendiente", hospitalCentral));

            ensureDonation(rDonacion,
                    new MDonacion(null, LocalDate.now().minusDays(2), 450, "Atendida", ana, solicitud1));
            ensureDonation(rDonacion,
                    new MDonacion(null, LocalDate.now().minusDays(4), 900, "Atendida", luis, solicitud2));
            ensureDonation(rDonacion,
                    new MDonacion(null, LocalDate.now().minusDays(1), 450, "Pendiente", ana, solicitud3));
        };
    }

    private MUsuario ensureUser(RUsuario rUsuario, MUsuario usuario) {
        return rUsuario.findByEmail(usuario.getEmail()).orElseGet(() -> rUsuario.save(usuario));
    }

    private MHospital ensureHospital(RHospital rHospital, MHospital hospital) {
        return rHospital.findAll().stream()
                .filter(item -> hospital.getNombre().equalsIgnoreCase(item.getNombre()))
                .findFirst()
                .orElseGet(() -> rHospital.save(hospital));
    }

    private MDonante ensureDonor(RDonante rDonante, MDonante donante) {
        return rDonante.findAll().stream()
                .filter(item -> donante.getEmail().equalsIgnoreCase(item.getEmail()))
                .findFirst()
                .orElseGet(() -> rDonante.save(donante));
    }

    private MSolicitud ensureSolicitud(RSolicitud rSolicitud, MSolicitud solicitud) {
        return rSolicitud.findAll().stream()
                .filter(item -> solicitud.getTipoSangreReq().equalsIgnoreCase(item.getTipoSangreReq())
                        && solicitud.getHospital().getNombre().equalsIgnoreCase(item.getHospital().getNombre())
                        && solicitud.getEstado().equalsIgnoreCase(item.getEstado()))
                .findFirst()
                .orElseGet(() -> rSolicitud.save(solicitud));
    }

    private MDonacion ensureDonation(RDonacion rDonacion, MDonacion donacion) {
        return rDonacion.findAll().stream()
                .filter(item -> item.getDonante() != null
                        && item.getSolicitud() != null
                        && donacion.getDonante().getEmail().equalsIgnoreCase(item.getDonante().getEmail())
                        && donacion.getSolicitud().getTipoSangreReq().equalsIgnoreCase(item.getSolicitud().getTipoSangreReq())
                        && donacion.getFechaDonacion().equals(item.getFechaDonacion()))
                .findFirst()
                .orElseGet(() -> rDonacion.save(donacion));
    }
}