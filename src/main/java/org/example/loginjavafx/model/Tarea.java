package org.example.loginjavafx.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Tarea {
    // Datos de BD
    private int id_tarea;
    private String titulo;
    private String prioridad;
    private int progreso;
    private LocalDate fecha_limite;

    // Constructor
    public Tarea(int id_tarea, String titulo, String prioridad, int progreso, LocalDate fecha_limite) {
        this.id_tarea = id_tarea;
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.progreso = progreso;
        this.fecha_limite = fecha_limite;
    }

    // Getters básicos
    public String getTitulo() { return titulo; }
    public String getPrioridad() { return prioridad; }
    public int getProgreso() { return progreso; }
    public LocalDate getFecha_limite() { return fecha_limite; }

    public long getDiasRestantes() {
        if (fecha_limite == null) return 0;
        return ChronoUnit.DAYS.between(LocalDate.now(), fecha_limite);
    }

    public String getEstadoTexto() {
        if (progreso >= 100) return "¡Completada!";
        if (getDiasRestantes() < 0) return "Vencida";
        return "En proceso (" + progreso + "%)";
    }
}