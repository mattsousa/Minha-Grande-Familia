package br.com.mattsousa.minhagrandefamilia.model;


import java.util.ArrayList;

public class User extends Person{
    private ArrayList<Relative> relatives;

    public User(char sex, String name, String birthday) {
        super(sex, name, birthday);
        relatives = new ArrayList<>();
    }

    public void orderRelativeByAge(){}
    public void orderRelativeByParentage(){}
    public void orderRelativeByName(){}

    public ArrayList<Relative> getRelatives() {
        return relatives;
    }

    public void setRelatives(ArrayList<Relative> relatives) {
        this.relatives = relatives;
    }
}
