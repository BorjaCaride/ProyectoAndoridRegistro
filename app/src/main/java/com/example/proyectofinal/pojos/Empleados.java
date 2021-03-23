package com.example.proyectofinal.pojos;

public class Empleados {

    private String IdEmpleado;
    private String nombre;
    private String apellido;
    private int telefono;
    private int IdHorario;

    public Empleados() {
    }

    public Empleados(String idEmpleado, String nombre, String apellido, int telefono, int idHorario) {
        IdEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        IdHorario = idHorario;
    }

    public String getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        IdEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getIdHorario() {
        return IdHorario;
    }

    public void setIdHorario(int idHorario) {
        IdHorario = idHorario;
    }
}
