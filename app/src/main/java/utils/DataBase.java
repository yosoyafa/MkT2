package utils;

import android.provider.BaseColumns;

public class DataBase {

    public static final String TABLE_LOCALES = "locales";
    public static final String TABLE_GESTION = "gestion";


    public static final String SQL_CREATE_TABLE_LOCALESCOMERCIALES = "CREATE TABLE IF NOT EXISTS "
            + DataBase.TABLE_LOCALES + " ("
            + "key_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DataLocalColumns.LOCAL_ID + " TEXT,"
            + DataLocalColumns.LOCAL_NOMBRE + " TEXT,"
            + DataLocalColumns.LOCAL_NUMERO + " TEXT,"
            + DataLocalColumns.LOCAL_AREA + " TEXT,"
            + DataLocalColumns.LOCAL_TIPO + " TEXT,"
            + DataLocalColumns.LOCAL_CODIGOCATEGORIA + " TEXT,"
            + DataLocalColumns.LOCAL_CODIGOSUBCATEGORIA + " TEXT,"
            + DataLocalColumns.LOCAL_CODIGOBIEN + " TEXT,"
            + DataLocalColumns.LOCAL_GESTIONADO + " TEXT,"
            + DataLocalColumns.LOCAL_CENTROCOMERCIAL + " TEXT)";

    //+ DataLocalColumns.LOCAL_NIT + " TEXT,"
    //+ DataLocalColumns.LOCAL_TIPOBIEN + " TEXT,"
    //+ DataLocalColumns.LOCAL_CATEGORIA + " TEXT,"
    //+ DataLocalColumns.LOCAL_SUBCATEGORIA + " TEXT,"
    //+ DataLocalColumns.LOCAL_DESCRIPCIONBIEN + " TEXT,"


    //TODO
    public static final String SQL_CREATE_TABLE_GESTION = "CREATE TABLE IF NOT EXISTS "
            + DataBase.TABLE_GESTION + " ("
            + "key_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DataGestionColumns.GESTION_LOCALID + " TEXT,"
            + DataGestionColumns.GESTION_NOMBRENUEVO + " TEXT,"
            + DataGestionColumns.GESTION_CATEGORIANUEVA + " TEXT,"
            + DataGestionColumns.GESTION_SUBCATEGORIANUEVA + " TEXT,"
            + DataGestionColumns.GESTION_DESCRIPCIONBIENNUEVA + " TEXT,"
            + DataGestionColumns.GESTION_ONLINE + "TEXT)";

    public static abstract class DataLocalColumns implements BaseColumns {
        public static final String LOCAL_ID = "idLocal";
        public static final String LOCAL_NOMBRE = "nombre";
        public static final String LOCAL_NUMERO = "numero";
        public static final String LOCAL_AREA = "area";
        public static final String LOCAL_TIPO = "tipo";
        //public static final String LOCAL_NIT = "nit";
        public static final String LOCAL_CODIGOCATEGORIA = "codigoCategoria";
        public static final String LOCAL_CATEGORIA= "categoria";
        public static final String LOCAL_CODIGOSUBCATEGORIA = "codigoSubcategoria";
        public static final String LOCAL_SUBCATEGORIA = "subcategoria";
        public static final String LOCAL_CODIGOBIEN = "codigoBien";
        public static final String LOCAL_DESCRIPCIONBIEN = "descripcionBien";
        public static final String LOCAL_CENTROCOMERCIAL = "centroComercial";
        public static final String LOCAL_GESTIONADO = "gestionado";
    }

    //TODO
    public static abstract class DataGestionColumns implements BaseColumns {
        public static final String GESTION_LOCALID = "localID";
        public static final String GESTION_NOMBRENUEVO = "nombreNuevo";
        public static final String GESTION_CATEGORIANUEVA = "categoriaNueva";
        public static final String GESTION_SUBCATEGORIANUEVA = "subcategoriaNueva";
        public static final String GESTION_DESCRIPCIONBIENNUEVA = "descripcionBienNueva";
        public static final String GESTION_ONLINE = "online";

    }

}
