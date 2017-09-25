package br.com.mattsousa.minhagrandefamilia.model;


import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Relative extends Person implements Comparable<Relative>{
    private Kinship parentage;
    private byte[] photo;
    private boolean isMarried;
    private boolean livesUser;
    private String phone;
    private int personId;

    public static String DATE_FORMAT = "dd/MM/yyyy";

    public Relative(char sex, String name, String birthday, Kinship parentage) {
        super(sex, name, birthday);
        this.parentage = parentage;
        photo = new byte[0];
        isMarried = false;
        livesUser = false;
        phone = "";
        personId = 0;
    }

    public Relative(char sex, String name, String birthday) {
        super(sex, name, birthday);
    }

    public Kinship getParentage() {
        return parentage;
    }

    public void setParentage(Kinship parentage) {
        this.parentage = parentage;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public boolean isLivesUser() {
        return livesUser;
    }

    public void setLivesUser(boolean livesUser) {
        this.livesUser = livesUser;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPrettyDate(){
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        try {
            return DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault()).
                    format(format.parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Date getBirthdayDate(){
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        try {
            return format.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int compareTo(@NonNull Relative o) {
        return 0;
    }
}
