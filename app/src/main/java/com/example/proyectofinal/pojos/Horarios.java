package com.example.proyectofinal.pojos;

public class Horarios {

    private int IdHorario;
    private String Nombre;
    private int HoraEntrada;
    private int MinutoEntrada;
    private int HoraSalida;
    private int MinutoSalida;
    private String Lunes;
    private String Martes;
    private String Miercoles;
    private String Jueves;
    private String Viernes;
    private String Sabado;
    private String Domingo;

    public Horarios() {
    }


    public Horarios(int idHorario, String nombre, int horaEntrada, int minutoEntrada, int horaSalida, int minutoSalida, String lunes, String martes, String miercoles, String jueves, String viernes, String sabado, String domingo) {
        IdHorario = idHorario;
        Nombre = nombre;
        HoraEntrada = horaEntrada;
        MinutoEntrada = minutoEntrada;
        HoraSalida = horaSalida;
        MinutoSalida = minutoSalida;
        Lunes = lunes;
        Martes = martes;
        Miercoles = miercoles;
        Jueves = jueves;
        Viernes = viernes;
        Sabado = sabado;
        Domingo = domingo;
    }

    public int getIdHorario() {
        return IdHorario;
    }

    public void setIdHorario(int idHorario) {
        IdHorario = idHorario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getHoraEntrada() {
        return HoraEntrada;
    }

    public void setHoraEntrada(int horaEntrada) {
        HoraEntrada = horaEntrada;
    }

    public int getMinutoEntrada() {
        return MinutoEntrada;
    }

    public void setMinutoEntrada(int minutoEntrada) {
        MinutoEntrada = minutoEntrada;
    }

    public int getHoraSalida() {
        return HoraSalida;
    }

    public void setHoraSalida(int horaSalida) {
        HoraSalida = horaSalida;
    }

    public int getMinutoSalida() {
        return MinutoSalida;
    }

    public void setMinutoSalida(int minutoSalida) {
        MinutoSalida = minutoSalida;
    }

    public String getLunes() {
        return Lunes;
    }

    public void setLunes(String lunes) {
        Lunes = lunes;
    }

    public String getMartes() {
        return Martes;
    }

    public void setMartes(String martes) {
        Martes = martes;
    }

    public String getMiercoles() {
        return Miercoles;
    }

    public void setMiercoles(String miercoles) {
        Miercoles = miercoles;
    }

    public String getJueves() {
        return Jueves;
    }

    public void setJueves(String jueves) {
        Jueves = jueves;
    }

    public String getViernes() {
        return Viernes;
    }

    public void setViernes(String viernes) {
        Viernes = viernes;
    }

    public String getSabado() {
        return Sabado;
    }

    public void setSabado(String sabado) {
        Sabado = sabado;
    }

    public String getDomingo() {
        return Domingo;
    }

    public void setDomingo(String domingo) {
        Domingo = domingo;
    }
}