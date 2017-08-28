package br.com.mattsousa.minhagrandefamilia.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO extends SQLiteOpenHelper{
    public static final String DBASE_NAME = "BigFamily";
    public static final int DBASE_VER = 1;

    public static final String TABLES[] = {"tbPerson", "tbRelative", "tbKinship"};


    public DAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE "+ TABLES[0] +" (");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT,");
        builder.append("sex CHARACTER(1) NOT NULL,");
        builder.append("name VARCHAR NOT NULL,");
        builder.append("birthday VARCHAR NOT NULL,");
        builder.append("email VARCHAR NOT NULL,");
        builder.append("isUser CHARACTER");
        builder.append(");");

        sqLiteDatabase.execSQL(builder.toString());
        builder.delete(0, builder.length());

        builder.append("CREATE TABLE "+ TABLES[1] +" (");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT,");
        builder.append("id_person INTEGER NOT NULL,");
        builder.append("photo BLOB ,");
        builder.append("is_married BOOLEAN ,");
        builder.append("lives_user BOOLEAN ,");
        builder.append("phone VARCHAR ,");
        builder.append("kinship INTEGER ");
        builder.append(");");

        sqLiteDatabase.execSQL(builder.toString());
        builder.delete(0, builder.length());

        builder.append("CREATE TABLE "+ TABLES[2] +" (");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT,");
        builder.append("name VARCHAR NOT NULL,");
        builder.append("parentage INTEGER NOT NULL");
        builder.append(");");

        sqLiteDatabase.execSQL(builder.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String ddl = "DROP DATABASE "+DBASE_NAME+" IF EXIST";
        sqLiteDatabase.execSQL(ddl);
    }
}
