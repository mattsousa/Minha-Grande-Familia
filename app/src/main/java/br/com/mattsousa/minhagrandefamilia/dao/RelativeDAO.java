package br.com.mattsousa.minhagrandefamilia.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.mattsousa.minhagrandefamilia.gof.Singleton;
import br.com.mattsousa.minhagrandefamilia.model.Kinship;
import br.com.mattsousa.minhagrandefamilia.model.Relative;

public class RelativeDAO {
    private static final String coluns[] =
            {"id","id_person","photo","is_married", "lives_user", "phone", "kinship"};

    public static long insert(Relative person){
        DAO dao = Singleton.getDao(Singleton.getContext());
        ContentValues values = new ContentValues();
        int i = 1;
        values.put(coluns[i],((int) PersonDAO.insert(person, false)));
        i+=1;
        values.put(coluns[i], person.getPhoto());
        i+=1;
        values.put(coluns[i], person.isMarried());
        i+=1;
        values.put(coluns[i], person.isLivesUser());
        i+=1;
        values.put(coluns[i], person.getPhone());
        i+=1;
        values.put(coluns[i], person.getParentage().getValue());
        return dao.getWritableDatabase().insert(DAO.TABLES[1],null,values);
    }


    public static Relative getRelativeByID(int id){
        DAO dao = Singleton.getDao(Singleton.getContext());
        String dql = "SELECT "+
                coluns[0]+" , "+
                coluns[1]+" , "+
                coluns[2]+" , "+
                coluns[3]+" , "+
                coluns[4]+" , "+
                coluns[5]+" , "+
                coluns[6]+" "+
                " FROM "+DAO.TABLES[1]+
                " WHERE "+coluns[0]+" = "+id+";";
        Cursor cursor = dao.getReadableDatabase().rawQuery(dql,null);
        Relative relative = null;
        if(cursor.moveToFirst()){
            relative = (Relative)PersonDAO.getPersonByID(
                    cursor.getInt(cursor.getColumnIndex(coluns[1])));
            while(cursor.moveToNext()){
                relative.setId(cursor.getInt(cursor.getColumnIndex(coluns[0])));
                relative.setPersonId(cursor.getInt(cursor.getColumnIndex(coluns[1])));
                relative.setPhoto(cursor.getBlob(cursor.getColumnIndex(coluns[2])));
                relative.setMarried(cursor.getExtras().getBoolean(coluns[3]));
                relative.setLivesUser(cursor.getExtras().getBoolean(coluns[4]));
                relative.setPhone(cursor.getString(cursor.getColumnIndex(coluns[5])));
                relative.setParentage(Kinship.getKinshipByValue(
                        cursor.getInt(cursor.getColumnIndex(coluns[6]))));
            }
        }
        cursor.close();
        return relative;
    }

    public static ArrayList<Relative> getRelativesByKinship(Kinship kinship){
        DAO dao = Singleton.getDao(Singleton.getContext());
        String dql = "SELECT "+
                coluns[0]+" , "+
                coluns[1]+" , "+
                coluns[2]+" , "+
                coluns[3]+" , "+
                coluns[4]+" , "+
                coluns[5]+" , "+
                coluns[6]+" "+
                " FROM "+DAO.TABLES[1]+" "+
                " WHERE "+coluns[6]+"="+kinship.getValue()+";";
        Cursor cursor = dao.getReadableDatabase().rawQuery(dql,null);
        Relative relative;
        ArrayList<Relative> relatives = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                relative = (Relative)
                        PersonDAO.getPersonByID(
                                //Foreign key
                                cursor.getInt(cursor.getColumnIndex(coluns[1])));
                relative.setId(cursor.getInt(cursor.getColumnIndex(coluns[0])));
                relative.setPersonId(cursor.getInt(cursor.getColumnIndex(coluns[1])));
                relative.setPhoto(cursor.getBlob(cursor.getColumnIndex(coluns[2])));
                relative.setMarried(cursor.getExtras().getBoolean(coluns[3]));
                relative.setLivesUser(cursor.getExtras().getBoolean(coluns[4]));
                relative.setPhone(cursor.getString(cursor.getColumnIndex(coluns[5])));
                relative.setParentage(Kinship.getKinshipByValue(
                        cursor.getInt(cursor.getColumnIndex(coluns[6]))));
                relatives.add(relative);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return relatives;
    }

    public static ArrayList<Relative> getRelativesByKinship(ArrayList<Kinship> kinships){
        DAO dao = Singleton.getDao(Singleton.getContext());
        String dql = "SELECT "+
                coluns[0]+" , "+
                coluns[1]+" , "+
                coluns[2]+" , "+
                coluns[3]+" , "+
                coluns[4]+" , "+
                coluns[5]+" , "+
                coluns[6]+" "+
                " FROM "+DAO.TABLES[1]+" "+
                " WHERE ";
        for(Kinship kinship : kinships){
            dql = dql.concat(coluns[6]+"="+kinship.getValue()+" ");
            if(kinships.get(kinships.size()-1) != kinship)
                dql = dql.concat("OR ");
            else
                dql = dql.concat(" ;");

        }
        Cursor cursor = dao.getReadableDatabase().rawQuery(dql,null);
        Relative relative;
        ArrayList<Relative> relatives = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                relative = (Relative)
                        PersonDAO.getPersonByID(
                                //Foreign key
                                cursor.getInt(cursor.getColumnIndex(coluns[1])));
                relative.setId(cursor.getInt(cursor.getColumnIndex(coluns[0])));
                relative.setPersonId(cursor.getInt(cursor.getColumnIndex(coluns[1])));
                relative.setPhoto(cursor.getBlob(cursor.getColumnIndex(coluns[2])));
                relative.setMarried(cursor.getExtras().getBoolean(coluns[3]));
                relative.setLivesUser(cursor.getExtras().getBoolean(coluns[4]));
                relative.setPhone(cursor.getString(cursor.getColumnIndex(coluns[5])));
                relative.setParentage(Kinship.getKinshipByValue(
                        cursor.getInt(cursor.getColumnIndex(coluns[6]))));
                relatives.add(relative);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return relatives;
    }

    public static ArrayList<Relative> getRelatives(){
        DAO dao = Singleton.getDao(Singleton.getContext());
        String dql = "SELECT "+
                coluns[0]+" , "+
                coluns[1]+" , "+
                coluns[2]+" , "+
                coluns[3]+" , "+
                coluns[4]+" , "+
                coluns[5]+" , "+
                coluns[6]+" "+
                " FROM "+DAO.TABLES[1]+";";
        Cursor cursor = dao.getReadableDatabase().rawQuery(dql,null);
        Relative relative;
        ArrayList<Relative> relatives = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                relative = (Relative)
                        PersonDAO.getPersonByID(
                                //Foreign key
                                cursor.getInt(cursor.getColumnIndex(coluns[1])));
                relative.setId(cursor.getInt(cursor.getColumnIndex(coluns[0])));
                relative.setPersonId(cursor.getInt(cursor.getColumnIndex(coluns[1])));
                relative.setPhoto(cursor.getBlob(cursor.getColumnIndex(coluns[2])));
                relative.setMarried(cursor.getExtras().getBoolean(coluns[3]));
                relative.setLivesUser(cursor.getExtras().getBoolean(coluns[4]));
                relative.setPhone(cursor.getString(cursor.getColumnIndex(coluns[5])));
                relative.setParentage(Kinship.getKinshipByValue(
                        cursor.getInt(cursor.getColumnIndex(coluns[6]))));
                relatives.add(relative);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return relatives;
    }

    public static void removeById(int id){
        DAO dao = Singleton.getDao(Singleton.getContext());
        Relative relative = getRelativeByID(id);
        int i;
        if(relative != null){
            dao.getWritableDatabase().delete(DAO.TABLES[1],"id=?",
                    new String[]{String.valueOf(id)});
            i = dao.getWritableDatabase().delete(DAO.TABLES[0],"id=?",
                    new String[]{String .valueOf(relative.getPersonId())});
        }

    }

    public static int nTuples(){
        DAO dao = Singleton.getDao(Singleton.getContext());
        Cursor cursor = dao.getReadableDatabase().
                rawQuery("SELECT * FROM "+DAO.TABLES[1]+";",null);
        cursor.moveToFirst();
        return cursor.getCount();
    }
}
