package utils;

public class Links {
    public final static String SERVER = "https://cc.integracionip.com/ws_cc/";

    public final static String DOWNLOAD_CCS = "getLocal.php?user_id=2345";
    public final static String ACTUALIZACION = "updateLocal.php?";

    public Links(){

    }

    public String getActualizacion(String idLocal, String centroComercial, String nombreActual, String nombreNuevo, String areaActual, String areaNueva, String categoriaActual, String categoriaNueva, String subcategoriaActual, String subcategoriaNueva, String tipoBienActual, String tipoBienNuevo, String observacion){
        return SERVER + ACTUALIZACION + "idLocal=" + idLocal + "&centroComercial=" + centroComercial + "&nombreActual=" + nombreActual + "&nombreNuevo=" + nombreNuevo + "&areaActual=" + areaActual + "&areaNueva=" + areaNueva + "&categoriaActual=" + categoriaActual + "&categoriaNueva=" + categoriaNueva + "&subcategoriaActual=" + subcategoriaActual + "&subcategoriaNueva=" + subcategoriaNueva + "&tipoBienActual=" + tipoBienActual + "&tipoBienNuevo=" + tipoBienNuevo + "&observacion=" + observacion;
    }

    public String getDownloadAllCCs(){
        return SERVER + DOWNLOAD_CCS;
    }
}
