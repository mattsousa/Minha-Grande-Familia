package br.com.mattsousa.minhagrandefamilia.dao;

import android.content.ContentValues;

import br.com.mattsousa.minhagrandefamilia.gof.Singleton;
import br.com.mattsousa.minhagrandefamilia.model.Kinship;

// TODO: 25/09/17 End CRUD Operations with KinshipDAO
public class KinshipDAO {
    private static final String coluns[] = {"id","name","parentage"};

    public static void insert(Kinship kinship){
        DAO dao = Singleton.getDao(Singleton.getContext());
        ContentValues values = new ContentValues();
        int i = 0;
        values.put(coluns[i],kinship.getValue());
        i+=1;
        values.put(coluns[i],kinship.getRelationName());
        i+=1;
        values.put(coluns[i],kinship.getParentage());
        dao.getWritableDatabase().insert(DAO.TABLES[2],null,values);
    }
}
