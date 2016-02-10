package basesDeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.Currency;

/**
 * Created by christianalos on 10/2/16.
 */
public class SqlControllerBaseDatosBotones {
    //variables
    private BaseDatosBotones baseDatosBotones;
    private Context context;
    private SQLiteDatabase database;

    //constructor
    public SqlControllerBaseDatosBotones (Context c){
        this.context = c;
    }
    //abrir base de datos
    public SqlControllerBaseDatosBotones open() throws SQLException{
        baseDatosBotones = new BaseDatosBotones(context);
        database = baseDatosBotones.getWritableDatabase();
        return this;
    }
    //cerrar base de datos
    public void close(){
        baseDatosBotones.close();
    }

    //inserción de datos en la base de datos
    public void insertData(String nom, String other, String col){
        ContentValues contentValues =  new ContentValues();
        contentValues.put(baseDatosBotones.NAME_BUT, nom);
        contentValues.put(baseDatosBotones.OTHER, other);
        contentValues.put(baseDatosBotones.COLOR, col);

        database.insert(baseDatosBotones.TABLE, null, contentValues);
    }

    public Cursor readEntry (){
        String [] allColumns = new String[]{
                baseDatosBotones._ID, baseDatosBotones.NAME_BUT,
                baseDatosBotones.OTHER, baseDatosBotones.COLOR
        };
        Cursor cursor = database.query(baseDatosBotones.TABLE, allColumns,
                null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void deleteAll(){
        database.rawQuery("DELETE FROM " + baseDatosBotones.TABLE, null);
    }


}
