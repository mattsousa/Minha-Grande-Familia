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

    public static long insert(Context context, Person person, boolean isUser){
        DAO dao = Singleton.getDao(context);
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

    public static Person getPersonByID(Context context,int id){
        DAO dao = Singleton.getDao(context);
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

    public static ArrayList<Person> getPeople(Context context){
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

    public static boolean isFirstUse(Context context){
        DAO dao = Singleton.getDao(context);
        return dao.getReadableDatabase().
                rawQuery("SELECT * FROM "+DAO.TABLES[0]+";",null).getCount() == 0;
    }

    public static int nTuples(){
        DAO dao = Singleton.getDao(Singleton.getContext());
        return dao.getReadableDatabase().
                rawQuery("SELECT * FROM "+DAO.TABLES[0]+";",null).getCount();
    }
}
