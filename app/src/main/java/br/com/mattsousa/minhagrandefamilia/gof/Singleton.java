package br.com.mattsousa.minhagrandefamilia.gof;


import android.content.Context;

import br.com.mattsousa.minhagrandefamilia.dao.DAO;
import br.com.mattsousa.minhagrandefamilia.model.User;

public class Singleton {
    private static Singleton instance = null;

    private static DAO dao;
    private static User user;
    private static Context context;

    private Singleton(Context context, User user) {
        dao = new DAO(context,DAO.DBASE_NAME, null, DAO.DBASE_VER);
        Singleton.user = user;
        Singleton.context = context;
    }

    public static Singleton getInstance(Context context, User user){
        if(instance == null || Singleton.user == null){
            instance = new Singleton(context, user);
        }
        return instance;
    }

    public static Singleton getInstance(Context context){
        if(instance == null){
            instance = new Singleton(context, Singleton.user);
        }
        return instance;
    }

    public static Singleton getInstance(){
        return instance;
    }

    public static DAO getDao(Context context) {
        return dao = new DAO(context,DAO.DBASE_NAME, null, DAO.DBASE_VER);
    }

    public static User getUser() {
        return user;
    }

    public static void setDao(DAO dao) {
        Singleton.dao = dao;
    }

    public static void setUser(User user) {
        Singleton.user = user;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Singleton.context = context;
    }
}
