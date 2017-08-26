package br.com.mattsousa.minhagrandefamilia.model;


public abstract class Person {
    public static final char SEX_MALE = 'M';
    public static final char SEX_FEMALE = 'F';

    protected char sex;
    protected String name;
    protected String birthday;
    protected String email;

    public Person(char sex, String name, String birthday) {
        this.sex = sex;
        this.name = name;
        this.birthday = birthday;
        this.email = "";
    }

    public int getAge(){
        return 0;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
