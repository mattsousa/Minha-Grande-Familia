package br.com.mattsousa.minhagrandefamilia.model;


import android.support.annotation.NonNull;

public class Relative extends Person implements Comparable<Relative>{
    private Kinship parentage;
    private byte[] photo;
    private boolean isMarried;
    private boolean livesUser;
    private String phone;

    public Relative(char sex, String name, String birthday, Kinship parentage) {
        super(sex, name, birthday);
        this.parentage = parentage;
        photo = new byte[0];
        isMarried = false;
        livesUser = false;
        phone = "";
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

    @Override
    public int compareTo(@NonNull Relative o) {
        return 0;
    }
}
