package com.bankcore;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String estado;  
    private String pais;
    private String codigoPostal;
    private boolean isActive;

    private List<Cuenta> cuentas = new ArrayList<>();

    public Cliente(int id, String nombre, String apellido, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getDireccion() {
        return direccion;
    }
    public String getCiudad() {
        return ciudad;
    }
    public String getEstado() {
        return estado;
    }
    public String getPais() {
        return pais;
    }
    public String getCodigoPostal() {
        return codigoPostal;
    }
    public boolean isActive() {
        return isActive;
    }
    public List<Cuenta> getCuentas() {
        return List.copyOf(cuentas);
    }
    public void addCuenta(Cuenta cuenta) {
        if(cuenta != null) {
            cuenta.asignarCliente(this);
            cuentas.add(cuenta);
            System.out.println("Cuenta agregada correctamente");
        } else {
            System.out.println("No se puede agregar una cuenta nula");
        }
    }
    public void removeCuenta(Cuenta cuenta) {
        if(cuenta != null) {
            cuenta.desasignarCliente();
            cuentas.remove(cuenta);
            System.out.println("Cuenta eliminada correctamente");
        } else {
            System.out.println("No se puede eliminar una cuenta nula");
        }
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getCompleteName() {
        return this.nombre + " " + this.apellido;
    }
}
