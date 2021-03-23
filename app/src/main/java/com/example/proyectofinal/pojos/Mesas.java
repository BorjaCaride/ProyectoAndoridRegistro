package com.example.proyectofinal.pojos;

public class Mesas {

        private int NumeroMesa;
        private String Estado;

    public Mesas() {
    }

    public Mesas(int numeroMesa, String estado) {
        NumeroMesa = numeroMesa;
        Estado = estado;
    }

    public int getNumeroMesa() {
        return NumeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        NumeroMesa = numeroMesa;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
