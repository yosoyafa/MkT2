package model;

public class Gestion {
    private String idLocalTabla, nombreNuevo, categoriaNueva, subcategoriaNueva, descripcionBienNueva, areaNueva;
    private int online;

    public Gestion(String idLocalTabla, String nombreNuevo, String areaNueva, String categoriaNueva, String subcategoriaNueva, String descripcionBienNueva, int online) {
        this.idLocalTabla = idLocalTabla;
        this.nombreNuevo = nombreNuevo;
        this.categoriaNueva = categoriaNueva;
        this.subcategoriaNueva = subcategoriaNueva;
        this.descripcionBienNueva = descripcionBienNueva;
        this.online = online;
        this.areaNueva = areaNueva;
    }

    public String getIdLocalTabla() {
        return idLocalTabla;
    }

    public void setIdLocalTabla(String idLocalTabla) {
        this.idLocalTabla = idLocalTabla;
    }

    public String getNombreNuevo() {
        return nombreNuevo;
    }

    public void setNombreNuevo(String nombreNuevo) {
        this.nombreNuevo = nombreNuevo;
    }

    public String getCategoriaNueva() {
        return categoriaNueva;
    }

    public void setCategoriaNueva(String categoriaNueva) {
        this.categoriaNueva = categoriaNueva;
    }

    public String getSubcategoriaNueva() {
        return subcategoriaNueva;
    }

    public void setSubcategoriaNueva(String subcategoriaNueva) {
        this.subcategoriaNueva = subcategoriaNueva;
    }

    public String getDescripcionBienNueva() {
        return descripcionBienNueva;
    }

    public void setDescripcionBienNueva(String descripcionBienNueva) {
        this.descripcionBienNueva = descripcionBienNueva;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getAreaNueva() {
        return areaNueva;
    }

    public void setAreaNueva(String areaNueva) {
        this.areaNueva = areaNueva;
    }
}
