package model;

public class Local {

    private String idLocal, nombre, numero, area, tipo, tipoBien, codigoCategoria, categoria, codigoSubcategoria, subcategoria, codigoBien, descripcionBien, centroComercial, gestionado;
    private int key_id;

    /*public Local(String nombre, String numero, String area, String codigoCategoria, String codigoSubcategoria, String codigoBien, String centroComercial, String gestionado) {
        this.nombre = nombre;
        this.numero = numero;
        this.area = area;
        this.codigoCategoria = codigoCategoria;
        this.codigoSubcategoria = codigoSubcategoria;
        this.codigoBien = codigoBien;
        this.centroComercial = centroComercial;
        this.gestionado = gestionado;
        setCodigos(codigoCategoria,codigoSubcategoria,codigoBien);
    }*/

    public Local(String idLocal, String nombre, String numero, String area, String codigoCategoria, String codigoSubcategoria, String codigoBien, String centroComercial) {
        this.idLocal = idLocal;
        this.nombre = nombre;
        this.numero = numero;
        this.area = area;
        this.codigoCategoria = codigoCategoria;
        this.codigoSubcategoria = codigoSubcategoria;
        this.codigoBien = codigoBien;
        this.centroComercial = centroComercial;
        setCodigos(codigoCategoria,codigoSubcategoria,codigoBien);
        this.gestionado = "0";
    }


    public Local(String idLocal, int key_id, String nombre, String numero, String area, String codigoCategoria, String codigoSubcategoria, String codigoBien, String centroComercial, String gestionado) {
        this.idLocal = idLocal;
        this.key_id = key_id;
        this.nombre = nombre;
        this.numero = numero;
        this.area = area;
        this.codigoCategoria = codigoCategoria;
        this.codigoSubcategoria = codigoSubcategoria;
        this.codigoBien = codigoBien;
        this.centroComercial = centroComercial;
        this.gestionado = gestionado;
        setCodigos(codigoCategoria,codigoSubcategoria,codigoBien);
    }

    public void setDescripcion(String categoria, String subcategoria, String descripcionBien){
        if(categoria.equalsIgnoreCase("Moda, Muebles y Enseres")) {
            codigoCategoria = 1 + "";
            if (subcategoria.equalsIgnoreCase("Moda")) {
                codigoSubcategoria = 1 + "";
                if (descripcionBien.equalsIgnoreCase("Ropa para damas")) {
                    codigoBien = 1 + "";
                } else if (descripcionBien.equalsIgnoreCase("Ropa para hombre")) {
                    codigoBien = 2 + "";
                } else if (descripcionBien.equalsIgnoreCase("Ropa para niños")) {
                    codigoBien = 3 + "";
                } else if (descripcionBien.equalsIgnoreCase("Ropa interior")) {
                    codigoBien = 4 + "";
                } else if (descripcionBien.equalsIgnoreCase("Calzado para dama")) {
                    codigoBien = 5 + "";
                } else if (descripcionBien.equalsIgnoreCase("Calzado para hombre")) {
                    codigoBien = 6 + "";
                } else if (descripcionBien.equalsIgnoreCase("Calzado para niños")) {
                    codigoBien = 7 + "";
                } else if (descripcionBien.equalsIgnoreCase("Ropa y Calzado Deportivo")) {
                    codigoBien = 8 + "";
                } else if (descripcionBien.equalsIgnoreCase("Accesorios")) {
                    codigoBien = 9 + "";
                } else if (descripcionBien.equalsIgnoreCase("Calzado para la Familia")) {
                    codigoBien = 10 + "";
                } else if (descripcionBien.equalsIgnoreCase("Ropa para la Familia")) {
                    codigoBien = 11 + "";
                }
            } else if (subcategoria.equalsIgnoreCase("Muebles y Enseres")) {
                codigoSubcategoria = 3 + "";
                if (descripcionBien.equalsIgnoreCase("Sala, Comedor y Alcoba")) {
                    codigoBien = 1 + "";
                } else if (descripcionBien.equalsIgnoreCase("Equipo de iluminación y decoración")) {
                    codigoBien = 2 + "";
                } else if (descripcionBien.equalsIgnoreCase("Electrodomésticos")) {
                    codigoBien = 3 + "";
                } else if (descripcionBien.equalsIgnoreCase("Video, Sonido y Tecnología")) {
                    codigoBien = 4 + "";
                } else if (descripcionBien.equalsIgnoreCase("Utensilios domésticos")) {
                    codigoBien = 5 + "";
                } else if (descripcionBien.equalsIgnoreCase("Ropa de Hogar")) {
                    codigoBien = 6 + "";
                } else if (descripcionBien.equalsIgnoreCase("Mejoras y cuidado para el hogar")) {
                    codigoBien = 7 + "";
                } else if (descripcionBien.equalsIgnoreCase("Artículos y productos para el hogar")) {
                    codigoBien = 8 + "";
                }
            } else if (subcategoria.equalsIgnoreCase("Departamental")) {
                codigoSubcategoria = 4 + "";
                codigoBien = 1 + "";
            }
        } else if(categoria.equalsIgnoreCase("Bienes y Servicios de Conveniencia")){
            codigoCategoria = 2 + "";
            if(subcategoria.equalsIgnoreCase("Supermercado")){
                codigoSubcategoria = 1 + "";
                codigoBien = 1 + "";
            }else if(subcategoria.equalsIgnoreCase("Comidas y Bebidas")){
                codigoSubcategoria = 2 + "";
                if (descripcionBien.equalsIgnoreCase("Restaurantes a mantel")){
                    codigoBien = 1 + "";
                }else if (descripcionBien.equalsIgnoreCase("Comida informal")){
                    codigoBien = 2 + "";
                }else if (descripcionBien.equalsIgnoreCase("Panadería y Pastelería")){
                    codigoBien = 3 + "";
                }else if (descripcionBien.equalsIgnoreCase("Café, Postres y Helados")){
                    codigoBien = 4 + "";
                }else if (descripcionBien.equalsIgnoreCase("Bares")){
                    codigoBien = 5 + "";
                }
            }else if(subcategoria.equalsIgnoreCase("Cuidado Personal")){
                codigoSubcategoria = 3 + "";
                if(descripcionBien.equalsIgnoreCase("Farmacia")){
                    codigoBien = 1 + "";
                }else if(descripcionBien.equalsIgnoreCase("Gimnasio")){
                    codigoBien = 2 + "";
                }else if(descripcionBien.equalsIgnoreCase("Salones de Belleza")){
                    codigoBien = 3 + "";
                }else if(descripcionBien.equalsIgnoreCase("Belleza, salud, perfumería")){
                    codigoBien = 5 + "";
                }
            }else if(subcategoria.equalsIgnoreCase("Servicios")) {
                codigoSubcategoria = 4 + "";
                if (descripcionBien.equalsIgnoreCase("Servicios para mascotas")) {
                    codigoBien = 1 + "";
                } else if (descripcionBien.equalsIgnoreCase("Servicios y equipos de telefonía")) {
                    codigoBien = 2 + "";
                } else if (descripcionBien.equalsIgnoreCase("Servicios de Lavandería")) {
                    codigoBien = 3 + "";
                } else if (descripcionBien.equalsIgnoreCase("Reparación de Vestuario")) {
                    codigoBien = 4 + "";
                } else if (descripcionBien.equalsIgnoreCase("Reparación de Calzado")) {
                    codigoBien = 5 + "";
                } else if (descripcionBien.equalsIgnoreCase("Servicio de Mensajería")) {
                    codigoBien = 6 + "";
                } else if (descripcionBien.equalsIgnoreCase("Turismo, tiquetes aereos y terrestres")) {
                    codigoBien = 7 + "";
                } else if (descripcionBien.equalsIgnoreCase("Servicios financieros")) {
                    codigoBien = 8 + "";
                } else if (descripcionBien.equalsIgnoreCase("Otros servicios")) {
                    codigoBien = 9 + "";
                }
            }
        }else if(categoria.equalsIgnoreCase("Otras Categorías")) {
            codigoCategoria = 3 + "";
            if(subcategoria.equalsIgnoreCase("Otras Categorías")){
                codigoSubcategoria = 5 + "";
                if(descripcionBien.equalsIgnoreCase("Librería, textos y papelería")){
                    codigoBien = 1 + "";
                }else if(descripcionBien.equalsIgnoreCase("Artículos Deportivos")){
                    codigoBien = 2 + "";
                }else if(descripcionBien.equalsIgnoreCase("Joyerías y relojerías")){
                    codigoBien = 3 + "";
                }else if(descripcionBien.equalsIgnoreCase("Juguetería")){
                    codigoBien = 4 + "";
                }else if(descripcionBien.equalsIgnoreCase("Serv. Médicos(consultas,laboratorio,imágenes)")){
                    codigoBien = 5 + "";
                }else if(descripcionBien.equalsIgnoreCase("Fotografía")){
                    codigoBien = 6 + "";
                }else if(descripcionBien.equalsIgnoreCase("Otras categorías")){
                    codigoBien = 7 + "";
                }
            }
        }else if(categoria.equalsIgnoreCase("Entretenimiento")){
            codigoCategoria = 5 + "";
            if (subcategoria.equalsIgnoreCase("Entretenimiento")){
                codigoSubcategoria = 1 + "";
                if(descripcionBien.equalsIgnoreCase("Cine")){
                    codigoBien = 1 + "";
                }else if(descripcionBien.equalsIgnoreCase("Bolera")){
                    codigoBien = 2 + "";
                }else if(descripcionBien.equalsIgnoreCase("Parque infantil")){
                    codigoBien = 3 + "";
                }if(descripcionBien.equalsIgnoreCase("Juegos de azar/casino")){
                    codigoBien = 4 + "";
                }if(descripcionBien.equalsIgnoreCase("Cancha Sintetica")){
                    codigoBien = 5 + "";
                }
            }
        }else if(categoria.equalsIgnoreCase("Vehículos y Serviteca")){
            codigoCategoria = 10 + "";
            if(subcategoria.equalsIgnoreCase("Vehículos y Serviteca")){
                codigoSubcategoria = 1 + "";
                if(descripcionBien.equalsIgnoreCase("Vehículos/Motos")) {
                    codigoBien = 1 + "";
                }
            }
        }
    }


    private void setCodigos(String codigoCategoria, String codigoSubcategoria, String codigoBien) {
        switch(codigoCategoria){
            case "1":
                categoria = "Moda, Muebles y Enseres";
                switch (codigoSubcategoria){
                    case "1":
                        subcategoria = "Moda";

                        switch(codigoBien){
                            case "1":
                                tipoBien = "Ropa para damas";
                                break;
                            case "2":
                                tipoBien = "Ropa para hombres";
                                break;
                            case "3":
                                tipoBien = "Ropa para niños";
                                break;
                            case "4":
                                tipoBien = "Ropa Interior";
                                break;
                            case "5":
                                tipoBien = "Calzado para dama";
                                break;
                            case "6":
                                tipoBien = "Calzado para hombre";
                                break;
                            case "7":
                                tipoBien = "Calzado para niños";
                                break;
                            case "8":
                                tipoBien = "Ropa y Calzado Deportivo";
                                break;
                            case "9":
                                tipoBien = "Accesorios";
                                break;
                            case "10":
                                tipoBien = "Calzado para la Familia ";
                                break;
                            case "11":
                                tipoBien = "Ropa para la Familia ";
                                break;
                        }

                        break;

                    case "3":
                        subcategoria = "Muebles y Enseres";

                        switch(codigoBien){
                            case "1":
                                tipoBien = "Sala, Comedor y Alcoba";
                                break;
                            case "2":
                                tipoBien = "Equipo de iluminación y decoración";
                                break;
                            case "3":
                                tipoBien = "Electrodomésticos";
                                break;
                            case "4":
                                tipoBien = "Video, Sonido y Tecnologia";
                                break;
                            case "5":
                                tipoBien = "Utensilios domésticos";
                                break;
                            case "6":
                                tipoBien = "Ropa de Hogar";
                                break;
                            case "7":
                                tipoBien = "Mejoras y cuidado para el hogar";
                                break;
                            case "8":
                                tipoBien = "Artículos y productos para el hogar";
                                break;
                        }

                        break;

                    case "4":
                        subcategoria = "Departamental";
                        tipoBien = subcategoria;
                        break;
                }
                break;

            case "2":
                categoria = "Bienes y  Servicios de Conveniencia";
                switch (codigoSubcategoria){
                    case "1":
                        subcategoria = "Supermercado";
                        tipoBien = subcategoria;
                        break;

                    case "2":
                        subcategoria = "Comidas y Bebidas";

                        switch(codigoBien){
                            case "1":
                                tipoBien = "Restaurantes a mantel";
                                break;
                            case "2":
                                tipoBien = "Comida informal";
                                break;
                            case "3":
                                tipoBien = "Panadería y Pasteleria";
                                break;
                            case "4":
                                tipoBien = "Cafe, Postres y Helados";
                                break;
                            case "5":
                                tipoBien = "Bares";
                                break;
                        }

                        break;

                    case "3":
                        subcategoria = "Cuidado Personal";

                        switch(codigoBien){
                            case "1":
                                tipoBien = "Farmacia";
                                break;
                            case "2":
                                tipoBien = "Gimnasio";
                                break;
                            case "3":
                                tipoBien = "Salones de Belleza";
                                break;
                            case "5":
                                tipoBien = "Belleza, salud, perfumería";
                                break;
                        }

                        break;

                    case "4":
                        subcategoria = "Servicios";

                        switch(codigoBien){
                            case "1":
                                tipoBien = "Servicios para mascotas";
                                break;
                            case "2":
                                tipoBien = "Servicios y equipos de telefonia";
                                break;
                            case "3":
                                tipoBien = "Servicio de Lavanderia";
                                break;
                            case "4":
                                tipoBien = "Reparacion de Vestuario";
                                break;
                            case "5":
                                tipoBien = "Reparacion de Calzado";
                                break;
                            case "6":
                                tipoBien = "Servicio de Mensajería";
                                break;
                            case "7":
                                tipoBien = "Turismo, tiquetes aereos y terrestres";
                                break;
                            case "8":
                                tipoBien = "Servicios financieros";
                                break;
                            case "9":
                                tipoBien = "Otros servicios";
                                break;
                        }

                        break;
                }
                break;

            case "3":
                categoria = "Otras Categorías";
                subcategoria = categoria;

                switch(codigoBien){
                    case "1":
                        tipoBien = "Librería, textos y papelería";
                        break;
                    case "2":
                        tipoBien = "Artículos Deportivos";
                        break;
                    case "3":
                        tipoBien = "Joyerías y relojerías";
                        break;
                    case "4":
                        tipoBien = "Juguetería";
                        break;
                    case "5":
                        tipoBien = "Serv. Médicos (consultas, laboratorio, imágenes)";
                        break;
                    case "6":
                        tipoBien = "Fotografia";
                        break;
                    case "7":
                        tipoBien = "Otras categorías";
                        break;
                }

                break;

            case "5":
                categoria = "Entretenimiento";
                subcategoria = categoria;

                switch(codigoBien){
                    case "1":
                        tipoBien = "Cine";
                        break;
                    case "2":
                        tipoBien = "Bolera";
                        break;
                    case "3":
                        tipoBien = "Parque infantil";
                        break;
                    case "4":
                        tipoBien = "Juegos de azar /casino";
                        break;
                    case "5":
                        tipoBien = "Cancha Sintetica";
                        break;
                }

                break;

            case "10":
                categoria = "Vehículos y Serviteca";
                subcategoria = categoria;

                switch(codigoBien){
                    case "1":
                        tipoBien = "Vehículos / Motos";
                        break;
                    case "2":
                        tipoBien = "Servicios de Serviteca";
                        break;
                }

                break;

            case "11":
                categoria = "Disponible";
                subcategoria = categoria;
                tipoBien = subcategoria;
                break;
        }
    }

    public String toStringRaw(){
        return idLocal+","+key_id+","+nombre+","+numero+","+area+","+codigoCategoria+","+codigoSubcategoria+"," +codigoBien+","+ centroComercial+","+gestionado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String local) {
        this.numero = local;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoBien() {
        return codigoBien;
    }

    public void setCodigoBien(String codigoBien) {
        this.codigoBien = codigoBien;
    }

    public String getTipoBien() {
        return tipoBien;
    }

    public void setTipoBien(String tipoBien) {
        this.tipoBien = tipoBien;
    }

    public String getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(String codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCodigoSubcategoria() {
        return codigoSubcategoria;
    }

    public void setCodigoSubcategoria(String codigoSubcategoria) {
        this.codigoSubcategoria = codigoSubcategoria;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getDescripcionBien() {
        return descripcionBien;
    }

    public void setDescripcionBien(String descripcionBien) {
        this.descripcionBien = descripcionBien;
    }

    public String getCentroComercial() {
        return centroComercial;
    }

    public void setCentroComercial(String centroComercial) {
        this.centroComercial = centroComercial;
    }

    public int getKey_id() {
        return key_id;
    }

    public void setKey_id(int key_id) {
        this.key_id = key_id;
    }

    public String getGestionado() {
        return gestionado;
    }

    public void setGestionado(String gestionado) {
        this.gestionado = gestionado;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }
}
