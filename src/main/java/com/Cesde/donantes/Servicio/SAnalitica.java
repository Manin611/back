package com.Cesde.donantes.Servicio;

import com.Cesde.donantes.Modelo.MDonacion;
import com.Cesde.donantes.Modelo.MDonante;
import com.Cesde.donantes.Modelo.MHospital;
import com.Cesde.donantes.Modelo.MSolicitud;
import com.Cesde.donantes.Repositorio.RDonacion;
import com.Cesde.donantes.Repositorio.RDonante;
import com.Cesde.donantes.Repositorio.RHospital;
import com.Cesde.donantes.Repositorio.RSolicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SAnalitica {

    @Autowired
    RDonacion rDonacion;

    @Autowired
    RSolicitud rSolicitud;

    @Autowired
    RDonante rDonante;

    @Autowired
    RHospital rHospital;

    public List<Map<String, Object>> donacionesPorMes() {
        List<MDonacion> all = rDonacion.findAll();

        Map<YearMonth, Long> grouped = all.stream()
                .filter(d -> d.getFechaDonacion() != null)
                .collect(Collectors.groupingBy(d -> YearMonth.from(d.getFechaDonacion()), Collectors.counting()));

        return grouped.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(e -> {
                    Map<String, Object> m = new HashMap<>();
                    m.put("year", e.getKey().getYear());
                    m.put("month", e.getKey().getMonthValue());
                    m.put("count", e.getValue());
                    return m;
                })
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> solicitudesPendientesPorHospital() {
        List<MHospital> hospitals = rHospital.findAll();

        List<Map<String, Object>> result = new ArrayList<>();

        for (MHospital h : hospitals) {
            List<MSolicitud> solicitudes = rSolicitud.findByHospitalIdHospital(h.getIdHospital());
            long pend = solicitudes.stream()
                    .filter(s -> s.getEstado() != null && s.getEstado().toLowerCase().contains("pend"))
                    .count();

            Map<String, Object> m = new HashMap<>();
            m.put("idHospital", h.getIdHospital());
            m.put("nombre", h.getNombre());
            m.put("pendientes", pend);
            result.add(m);
        }

        return result.stream().sorted((a, b) -> Long.compare((Long)b.get("pendientes"), (Long)a.get("pendientes"))).collect(Collectors.toList());
    }

    public List<Map<String, Object>> demandaPorTipoSangre() {
        List<MSolicitud> solicitudes = rSolicitud.findAll();

        Map<String, Long> grouped = solicitudes.stream()
                .filter(s -> s.getTipoSangreReq() != null)
                .collect(Collectors.groupingBy(s -> s.getTipoSangreReq(), Collectors.counting()));

        return grouped.entrySet().stream()
                .map(e -> {
                    Map<String, Object> m = new HashMap<>();
                    m.put("tipoSangre", e.getKey());
                    m.put("solicitudes", e.getValue());
                    return m;
                })
                .sorted((a, b) -> Long.compare((Long)b.get("solicitudes"), (Long)a.get("solicitudes")))
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> actividadDonantes() {
        List<MDonante> donantes = rDonante.findAll();

        return donantes.stream()
                .map(d -> {
                    Map<String, Object> m = new HashMap<>();
                    int donations = (d.getDonaciones() == null) ? 0 : d.getDonaciones().size();
                    m.put("idDonante", d.getIdDonante());
                    m.put("nombre", (d.getNombre() == null ? "" : d.getNombre()) + " " + (d.getApellido() == null ? "" : d.getApellido()));
                    m.put("donaciones", donations);
                    return m;
                })
                .sorted((a, b) -> Integer.compare((Integer)b.get("donaciones"), (Integer)a.get("donaciones")))
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> topHospitalesPorDonaciones() {
        List<MDonacion> donaciones = rDonacion.findAll();

        Map<Integer, Long> grouped = donaciones.stream()
                .filter(d -> d.getSolicitud() != null && d.getSolicitud().getHospital() != null)
                .collect(Collectors.groupingBy(d -> d.getSolicitud().getHospital().getIdHospital(), Collectors.counting()));

        List<MHospital> hospitals = rHospital.findAll();

        Map<Integer, String> names = hospitals.stream().collect(Collectors.toMap(MHospital::getIdHospital, MHospital::getNombre));

        return grouped.entrySet().stream()
                .map(e -> {
                    Map<String, Object> m = new HashMap<>();
                    m.put("idHospital", e.getKey());
                    m.put("nombre", names.getOrDefault(e.getKey(), ""));
                    m.put("donaciones", e.getValue());
                    return m;
                })
                .sorted((a, b) -> Long.compare((Long)b.get("donaciones"), (Long)a.get("donaciones")))
                .collect(Collectors.toList());
    }
}
