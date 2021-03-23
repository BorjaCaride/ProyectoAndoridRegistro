package com.example.proyectofinal.pojos;

public class Registros {

    private String idRegistro;
    private int mesa;
    private String Nombre;
    private int  Telefono;
    private int  Telefono2;
    private int  Telefono3;
    private String fecha;
    private String fechaSalida;
    private int HoraE;
    private int minutoE;
    private int HoraS;
    private int minutoS;
    private int nPersonas;
    private String corona;

    public Registros() {
    }

    public Registros(String idRegistro, int mesa, String nombre, int telefono, int telefono2, int telefono3, String fecha, String fechaSalida, int horaE, int minutoE, int horaS, int minutoS, int nPersonas, String corona) {
        this.idRegistro = idRegistro;
        this.mesa = mesa;
        Nombre = nombre;
        Telefono = telefono;
        Telefono2 = telefono2;
        Telefono3 = telefono3;
        this.fecha = fecha;
        this.fechaSalida = fechaSalida;
        HoraE = horaE;
        this.minutoE = minutoE;
        HoraS = horaS;
        this.minutoS = minutoS;
        this.nPersonas = nPersonas;
        this.corona = corona;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int telefono) {
        Telefono = telefono;
    }

    public int getTelefono2() {
        return Telefono2;
    }

    public void setTelefono2(int telefono2) {
        Telefono2 = telefono2;
    }

    public int getTelefono3() {
        return Telefono3;
    }

    public void setTelefono3(int telefono3) {
        Telefono3 = telefono3;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getHoraE() {
        return HoraE;
    }

    public void setHoraE(int horaE) {
        HoraE = horaE;
    }

    public int getMinutoE() {
        return minutoE;
    }

    public void setMinutoE(int minutoE) {
        this.minutoE = minutoE;
    }

    public int getHoraS() {
        return HoraS;
    }

    public void setHoraS(int horaS) {
        HoraS = horaS;
    }

    public int getMinutoS() {
        return minutoS;
    }

    public void setMinutoS(int minutoS) {
        this.minutoS = minutoS;
    }

    public int getnPersonas() {
        return nPersonas;
    }

    public void setnPersonas(int nPersonas) {
        this.nPersonas = nPersonas;
    }

    public String getCorona() {
        return corona;
    }

    public void setCorona(String corona) {
        this.corona = corona;
    }
}
