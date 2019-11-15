package utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;


import java.util.ArrayList;

import model.Gestion;
import model.Local;

public class LogicDataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BaseDeDatos.db";
    private static final int DATABASE_VERSION = 4;

    public LogicDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBase.SQL_CREATE_TABLE_LOCALESCOMERCIALES);
        db.execSQL(DataBase.SQL_CREATE_TABLE_GESTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("ALTER TABLE " + DataBase.TABLE_LOCALES + " ADD COLUMN " + DataBase.DataLocalColumns.LOCAL_ID + " TEXT NOT NULL DEFAULT '-'");
    }

    public String getTableAsString(String tableName) {
        SQLiteDatabase db = getReadableDatabase();
        String tableString = String.format("Table %s:\n", tableName);
        Cursor allRows = db.rawQuery("SELECT * FROM " + tableName, null);
        if (allRows.moveToFirst()) {
            String[] columnNames = allRows.getColumnNames();
            do {
                for (String name : columnNames) {
                    tableString += String.format("%s: %s\n", name,
                            allRows.getString(allRows.getColumnIndex(name)));
                }
                tableString += "\n---------------\n";

            } while (allRows.moveToNext());
        }

        return "----"+tableName+"----\n"+tableString;
    }
    public String getTableAsStringParam(String tableName, String param) {
        SQLiteDatabase db = getReadableDatabase();
        String tableString = String.format("Table %s:\n", tableName);
        Cursor allRows = db.rawQuery("SELECT * FROM " + tableName + " WHERE centrocomercial='"+param+"'", null);
        if (allRows.moveToFirst()) {
            String[] columnNames = allRows.getColumnNames();
            do {
                for (String name : columnNames) {
                    tableString += String.format("%s: %s\n", name,
                            allRows.getString(allRows.getColumnIndex(name)));
                }
                tableString += "\n---------------\n";

            } while (allRows.moveToNext());
        }

        return "----"+tableName+"----\n"+tableString;
    }

    public void addLocal(Local local){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null){
            ContentValues values = new ContentValues();
            values.put(DataBase.DataLocalColumns.LOCAL_ID, local.getIdLocal());
            values.put(DataBase.DataLocalColumns.LOCAL_NOMBRE, local.getNombre());
            values.put(DataBase.DataLocalColumns.LOCAL_NUMERO, local.getNumero());
            values.put(DataBase.DataLocalColumns.LOCAL_AREA, local.getArea());
            values.put(DataBase.DataLocalColumns.LOCAL_CODIGOCATEGORIA, local.getCodigoCategoria());
            values.put(DataBase.DataLocalColumns.LOCAL_CODIGOSUBCATEGORIA, local.getCodigoSubcategoria());
            values.put(DataBase.DataLocalColumns.LOCAL_CODIGOBIEN, local.getCodigoBien());
            values.put(DataBase.DataLocalColumns.LOCAL_CENTROCOMERCIAL, local.getCentroComercial());
            values.put(DataBase.DataLocalColumns.LOCAL_GESTIONADO, local.getGestionado());
            db.insert(DataBase.TABLE_LOCALES, null, values);
            db.close();
        }
    }

    public void addGestion(Gestion gestion){
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            ContentValues values =  new ContentValues();
            values.put(DataBase.DataGestionColumns.GESTION_LOCALID, gestion.getIdLocalTabla());
            values.put(DataBase.DataGestionColumns.GESTION_NOMBRENUEVO, gestion.getNombreNuevo());
            values.put(DataBase.DataGestionColumns.GESTION_CATEGORIANUEVA, gestion.getCategoriaNueva());
            values.put(DataBase.DataGestionColumns.GESTION_SUBCATEGORIANUEVA, gestion.getSubcategoriaNueva());
            values.put(DataBase.DataGestionColumns.GESTION_DESCRIPCIONBIENNUEVA, gestion.getDescripcionBienNueva());
            values.put(DataBase.DataGestionColumns.GESTION_ONLINE, gestion.getOnline()+"");
            db.insert(DataBase.TABLE_GESTION,null,values);
            db.close();
        }
    }

    public ArrayList<Local> selectLocales(String centroComercial){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Local> localesCentroComercial = new ArrayList <Local>();

        //Donde esta que la tabla sea locales, o como from locales
        try{
            //Cursor cursor = db.query(DataBase.TABLE_LOCALES,columns,columns[11] + "="+centroComercial,null,null,null,null);
            Cursor cursor = db.rawQuery("SELECT * FROM locales WHERE centroComercial='"+centroComercial+"'", null);
            if(cursor.moveToFirst()){
                while(!cursor.isAfterLast()){
                    String idLocal = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_ID));
                    String nom = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_NOMBRE));
                    String num = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_NUMERO));
                    String area = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_AREA));
                    //String tipo = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_TIPO));
                    //String tipoBien = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_TIPOBIEN));
                    String codCat = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_CODIGOCATEGORIA));
                    //String cat = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_CATEGORIA));
                    String codSub = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_CODIGOSUBCATEGORIA));
                    //String sub = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_SUBCATEGORIA));
                    String codBien = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_CODIGOBIEN));
                    //String descripBien = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_DESCRIPCIONBIEN));
                    String cc = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_CENTROCOMERCIAL));
                    String gestionado = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_GESTIONADO));
                    int key = cursor.getInt(cursor.getColumnIndex("key_id"));

                    //Local l = new Local(nom,num,area,tipo,tipoBien,codCat,cat,codSub,sub,codBien,descripBien,cc);

                    Local l = new Local(idLocal,key,nom, num, area, codCat, codSub, codBien, cc, gestionado);

                    localesCentroComercial.add(l);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("-----------------------\nSize ArrayList en DB: "+localesCentroComercial.size());
        return localesCentroComercial;
    }

    public void gestionLocales(){
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            Cursor cursor = db.rawQuery("select * from " + DataBase.TABLE_GESTION,null);
            if(cursor.moveToNext()){
                while(!cursor.isAfterLast()){
                    String idLocalTabla = cursor.getString(cursor.getColumnIndex("localID"));
                    String nomNuevo = cursor.getString(cursor.getColumnIndex("nombreNuevo"));
                    String catNueva = cursor.getString(cursor.getColumnIndex("categoriaNueva"));
                    String subcatNueva = cursor.getString(cursor.getColumnIndex("subcategoriaNueva"));
                    String descripNueva = cursor.getString(cursor.getColumnIndex("descripcionBienNueva"));
                    actualizarLocal(idLocalTabla, nomNuevo, catNueva, subcatNueva, descripNueva,"","");
                    //Borrar este registro
                    int key = cursor.getInt(cursor.getColumnIndex("key_id"));
                    String k1 = Integer.toString(key);
                    String[] selectionArgs = {k1};
                    db.delete(DataBase.TABLE_GESTION,"key_id = ?",selectionArgs);
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void resetLocalesComerciales(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from "+ DataBase.TABLE_LOCALES);
    }

    public ArrayList<String> getCCs(){

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> ccs = new ArrayList<String>();
        try {
            Cursor cursor = db.rawQuery("select distinct "+ DataBase.DataLocalColumns.LOCAL_CENTROCOMERCIAL + " from "+DataBase.TABLE_LOCALES, null);
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String cc = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_CENTROCOMERCIAL));
                    ccs.add(cc);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return ccs;
    }

    public Local searchLocal(String idLocal){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Local> localActualizar = new ArrayList<Local>();

        String[] selectionArgs = {idLocal};

        try {
            //Cursor cursor = db.query(DataBase.TABLE_LOCALES,columns,columns[11] + "="+centroComercial,null,null,null,null);
            Cursor cursor = db.rawQuery("select * from " + DataBase.TABLE_LOCALES + " where key_id ='?'",selectionArgs);
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    int key = cursor.getInt(cursor.getColumnIndex("key_id"));
                    String idLocalCRM = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_ID));
                    String nom = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_NOMBRE));
                    String num = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_NUMERO));
                    String area = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_AREA));
                    String codCat = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_CODIGOCATEGORIA));
                    String codSub = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_CODIGOSUBCATEGORIA));
                    String codBien = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_CODIGOBIEN));
                    String cc = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_CENTROCOMERCIAL));
                    String gestionado = cursor.getString(cursor.getColumnIndex(DataBase.DataLocalColumns.LOCAL_GESTIONADO));

                    Local l = new Local(idLocalCRM,key,nom,num,area,codCat,codSub,codBien,cc,gestionado);
                    System.out.println("_________________\nsearchLocal: "+l.toStringRaw());
                    localActualizar.add(l);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("-----------------------\nSize ArrayList en DB searchlocal: "+localActualizar.size());
        if(localActualizar.isEmpty()){
            //System.out.print();
        }
        return localActualizar.get(0);
    }

    public void actualizarLocal(String idLocalTabla, String nombreNuevo, String areaNueva, String catNueva, String subcatNueva, String descripNueva, String observacion){
        SQLiteDatabase db = this.getWritableDatabase();
        Local localaActualizar = searchLocal(idLocalTabla);

        localaActualizar.setDescripcion(catNueva,subcatNueva,descripNueva);

        String[] selectionArgs = {idLocalTabla};

        if(db != null){
            ContentValues values =  new ContentValues();
            values.put(DataBase.DataLocalColumns.LOCAL_NOMBRE, nombreNuevo);
            values.put(DataBase.DataLocalColumns.LOCAL_CATEGORIA, catNueva);
            values.put(DataBase.DataLocalColumns.LOCAL_SUBCATEGORIA, subcatNueva);
            values.put(DataBase.DataLocalColumns.LOCAL_DESCRIPCIONBIEN, descripNueva);
            values.put(DataBase.DataLocalColumns.LOCAL_CODIGOCATEGORIA, localaActualizar.getCodigoCategoria());
            values.put(DataBase.DataLocalColumns.LOCAL_CODIGOSUBCATEGORIA, localaActualizar.getCodigoSubcategoria());
            values.put(DataBase.DataLocalColumns.LOCAL_CODIGOBIEN, localaActualizar.getCodigoBien());
            values.put(DataBase.DataLocalColumns.LOCAL_AREA, areaNueva);
            values.put(DataBase.DataLocalColumns.LOCAL_GESTIONADO, "1");
            db.update(DataBase.TABLE_LOCALES,values, "key_id = ?",selectionArgs);
            db.close();
        }

    }

    public void updateLocal(Local loc, String nomN, String areaN, String catN, String subN, String bienN){
        SQLiteDatabase db = getWritableDatabase();

        System.out.println("UPDATE:\ncatN: "+catN+"\nsubN: "+subN+"\nbienN: "+bienN);

        Local locPr = new Local(catN,subN,bienN);
        String codCatN = locPr.getCodigoCategoria();
        String codSubN = locPr.getCodigoSubcategoria();
        String codBienN = locPr.getCodigoBien();

        ContentValues cv = new ContentValues();
        cv.put(DataBase.DataLocalColumns.LOCAL_NOMBRE,nomN);
        cv.put(DataBase.DataLocalColumns.LOCAL_AREA,areaN);
        cv.put(DataBase.DataLocalColumns.LOCAL_CODIGOCATEGORIA,codCatN);
        cv.put(DataBase.DataLocalColumns.LOCAL_CODIGOSUBCATEGORIA,codSubN);
        cv.put(DataBase.DataLocalColumns.LOCAL_CODIGOBIEN,codBienN);
        cv.put(DataBase.DataLocalColumns.LOCAL_GESTIONADO,"1");

        int key = loc.getKey_id();
        db.update(DataBase.TABLE_LOCALES, cv, "key_id = ?", new String[]{key+""});
    }

}
