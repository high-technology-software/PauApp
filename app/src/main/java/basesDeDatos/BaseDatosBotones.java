package basesDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by christianalos on 8/2/16.
 */
public class BaseDatosBotones extends SQLiteOpenHelper{

    //table
    public static final String TABLE = "MENU_PRINCIPAL";
    public static final String _ID = "_ID";
    public static final String NAME_BUT = "NAME_BUTTON";
    //por determinar
    public static final String OTHER = "OTHER_OPTION";
    public static final String COLOR = "COLOR";
    //nombre y otros de la base de datos
    private static final String DB_NAME = "PRINCIPAL.sqlite";
    private static final int DB_VERSION = 1;
    //statement creation
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE
            + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME_BUT + " TEXT NOT NULL, "
            + OTHER + " TEXT NOT NULL, "
            + COLOR + " TEXT NOT NULL);";

    //constructor principal
    public BaseDatosBotones (Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }


}
