package br.com.mattsousa.minhagrandefamilia.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.mattsousa.minhagrandefamilia.gof.Singleton;
import br.com.mattsousa.minhagrandefamilia.model.Relative;

public class RelativeDAO {
    private static final String coluns[] =
            {"id","id_person","photo","is_married", "lives_user", "phone"};

    public static long insert(Context context, Relative person){
        DAO dao = Singleton.getDao(context);
        ContentValues values = new ContentValues();
        int i = 2;
        PersonDAO.insert(context, person, false);
        values.put(coluns[i], person.getPhoto());
        i+=1;
        values.put(coluns[i], person.isMarried());
        i+=1;
        values.put(coluns[i], person.isLivesUser());
        i+=1;
        values.put(coluns[i], person.getPhone());
        PersonDAO.insert(context, person, false);
        return dao.getWritableDatabase().insert(DAO.TABLES[1],null,values);
    }


    public static Relative getRelativeByID(Context context, int id){
        DAO dao = Singleton.getDao(context);
        String dql = "SELECT "+
                coluns[0]+" , "+
                coluns[1]+" , "+
                coluns[2]+" , "+
                coluns[3]+" , "+
                coluns[4]+" , "+
                coluns[5]+" "+
                " FROM "+DAO.TABLES[1]+
                "WHERE "+coluns[0]+" = "+id;
        Cursor cursor = dao.getReadableDatabase().rawQuery(dql,null);
        Relative relative = null;
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                relative = (Relative)PersonDAO.getPersonByID(context,
                        cursor.getInt(cursor.getColumnIndex(coluns[1])));
                relative.setPhoto(cursor.getBlob(cursor.getColumnIndex(coluns[2])));
                relative.setMarried(cursor.getExtras().getBoolean(coluns[3]));
                relative.setLivesUser(cursor.getExtras().getBoolean(coluns[4]));
                relative.setPhone(cursor.getString(cursor.getColumnIndex(coluns[5])));
            }
        }
        cursor.close();
        return relative;
    }

    public static ArrayList<Relative> getRelatives(Context context){
        DAO dao = Singleton.getDao(context);
        String dql = "SELECT "+
                coluns[0]+" , "+
                coluns[1]+" , "+
                coluns[2]+" , "+
                coluns[3]+" , "+
                coluns[4]+" , "+
                coluns[5]+" "+
                " FROM "+DAO.TABLES[1]+";";
        Cursor cursor = dao.getReadableDatabase().rawQuery(dql,null);
        Relative relative;
        ArrayList<Relative> relatives = new ArrayList<>();
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                relative = (Relative)
                        PersonDAO.getPersonByID(context,
                                //Foreign key
                                cursor.getInt(cursor.getColumnIndex(coluns[1])));
                relative.setPhoto(cursor.getBlob(cursor.getColumnIndex(coluns[2])));
                relative.setMarried(cursor.getExtras().getBoolean(coluns[3]));
                relative.setLivesUser(cursor.getExtras().getBoolean(coluns[4]));
                relative.setPhone(cursor.getString(cursor.getColumnIndex(coluns[5])));
                relatives.add(relative);
            }
        }
        cursor.close();
        return relatives;
    }
}
