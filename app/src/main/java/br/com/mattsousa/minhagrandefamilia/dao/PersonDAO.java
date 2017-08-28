package br.com.mattsousa.minhagrandefamilia.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import br.com.mattsousa.minhagrandefamilia.gof.Singleton;
import br.com.mattsousa.minhagrandefamilia.model.Person;
import br.com.mattsousa.minhagrandefamilia.model.Relative;
import br.com.mattsousa.minhagrandefamilia.model.User;

public class PersonDAO {
    private static final String coluns[] = {"id","sex","name","birthday", "email", "isUser"};

    public static long insert( Person person, boolean isUser){
        DAO dao = Singleton.getDao(Singleton.getContext());
        ContentValues values = new ContentValues();
        int i = 1;
        values.put(coluns[i], String.valueOf(person.getSex()));
        i+=1;
        values.put(coluns[i], person.getName());
        i+=1;
        values.put(coluns[i], person.getBirthday());
        i+=1;
        values.put(coluns[i], person.getEmail());
        i+=1;
        if(isUser)
            values.put(coluns[i],"y");
        else
            values.put(coluns[i],"n");
        return dao.getWritableDatabase().insert(DAO.TABLES[0],null,values);
    }

    public static Person getPersonByID(int id){
        DAO dao = Singleton.getDao(Singleton.getContext());
        String dql = "SELECT "+
                coluns[0]+" , "+
                coluns[1]+" , "+
                coluns[2]+" , "+
                coluns[3]+" , "+
                coluns[4]+" , "+
                coluns[5]+" "+
                " FROM "+DAO.TABLES[0]+
                " WHERE "+coluns[0]+" = "+id+";";
        Person person = null;
        Cursor cursor = dao.getReadableDatabase().rawQuery(dql,null);
        if(cursor.moveToFirst()){
            do{
                // If this tuple is a User object
                if(cursor.getString(cursor.getColumnIndex(coluns[5])).equalsIgnoreCase("y")) {
                    person = new User(
                            // Get sex
                            cursor.getString(cursor.getColumnIndex(coluns[1])).charAt(0),
                            // Get Name
                            cursor.getString(cursor.getColumnIndex(coluns[2])),
                            // Get Birthday
                            cursor.getString(cursor.getColumnIndex(coluns[3]))
                    );
                }
                // Relative object
                else{
                    person = new Relative(
                            // Get sex
                            cursor.getString(cursor.getColumnIndex(coluns[1])).charAt(0),
                            // Get Name
                            cursor.getString(cursor.getColumnIndex(coluns[2])),
                            // Get Birthday
                            cursor.getString(cursor.getColumnIndex(coluns[3]))
                    );
                }
                person.setEmail(cursor.getString(cursor.getColumnIndex(coluns[4])));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return person;
    }

    public static ArrayList<Person> getPeople(){
        DAO dao = Singleton.getDao(Singleton.getContext());
        String dql = "SELECT "+
                coluns[0]+" , "+
                coluns[1]+" , "+
                coluns[2]+" , "+
                coluns[3]+" , "+
                coluns[4]+" , "+
                coluns[5]+" "+
                " FROM "+DAO.TABLES[1]+";";
        Cursor cursor = dao.getReadableDatabase().rawQuery(dql,null);
        Person person;
        ArrayList<Person> people = new ArrayList<>();
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                // If this tuple is a User object
                if(cursor.getExtras().getBoolean(coluns[5])) {
                    person = new User(
                            // Get sex
                            cursor.getString(cursor.getColumnIndex(coluns[1])).charAt(0),
                            // Get Name
                            cursor.getString(cursor.getColumnIndex(coluns[2])),
                            // Get Birthday
                            cursor.getString(cursor.getColumnIndex(coluns[3]))
                    );
                }
                // Relative object
                else{
                    person = new Relative(
                            // Get sex
                            cursor.getString(cursor.getColumnIndex(coluns[1])).charAt(0),
                            // Get Name
                            cursor.getString(cursor.getColumnIndex(coluns[2])),
                            // Get Birthday
                            cursor.getString(cursor.getColumnIndex(coluns[3]))
                    );
                }
                person.setEmail(cursor.getString(cursor.getColumnIndex(coluns[4])));
                people.add(person);
            }
        }
        cursor.close();
        return people;
    }

    public static void alterPersonById(int id, Person newTuple){
        DAO dao = Singleton.getDao(Singleton.getContext());
        Person relative = PersonDAO.getPersonByID(id);
        if(relative != null){
            ContentValues contentValues = new ContentValues();
            //{"id","sex","name","birthday", "email", "isUser"};
            contentValues.put(coluns[1],String.valueOf(newTuple.getSex()));
            contentValues.put(coluns[2],newTuple.getName());
            contentValues.put(coluns[3],newTuple.getBirthday());
            contentValues.put(coluns[4],newTuple.getEmail());
            contentValues.put(coluns[5],relative instanceof User);

            int erro = dao.getWritableDatabase().update(DAO.TABLES[0],
                    contentValues,
                    coluns[0]+"="+id,
                    null);
        }
    }

    public static boolean isFirstUse(){
        DAO dao = Singleton.getDao(Singleton.getContext());
        Cursor cursor = dao.getReadableDatabase().
                rawQuery("SELECT * FROM "+DAO.TABLES[0]+";",null);
        cursor.moveToFirst();
        return cursor.getCount() == 0;
    }
}
