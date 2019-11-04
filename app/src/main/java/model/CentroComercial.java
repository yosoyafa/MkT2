package model;

import java.util.ArrayList;

public class CentroComercial {
    private String nombre, direccion, paginaWeb;
    private ArrayList<Local> locales;

    public CentroComercial(String nombre, String direccion, String paginaWeb, ArrayList<Local> locales) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.paginaWeb = paginaWeb;
        this.locales = locales;
    }

    public CentroComercial(String nombre, String direccion, String paginaWeb) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.paginaWeb = paginaWeb;
    }

    public String toStringRaw(){
        return nombre+","+direccion+","+paginaWeb;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public ArrayList<Local> getLocales() {
        return locales;
    }

    public void setLocales(ArrayList<Local> locales) {
        this.locales = locales;
    }
}
