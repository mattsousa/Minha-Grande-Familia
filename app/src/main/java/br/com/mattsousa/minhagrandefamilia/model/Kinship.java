package br.com.mattsousa.minhagrandefamilia.model;


import android.content.res.Resources;

import br.com.mattsousa.minhagrandefamilia.R;
import br.com.mattsousa.minhagrandefamilia.gof.Singleton;

public enum Kinship {
    ME(),
    SON(1, 1, Singleton.getContext().getString(R.string.global_kinship_son)),
    DAUGHTER(2, 1,Singleton.getContext().getString(R.string.global_kinship_daughter)),
    GRANDSON(3, 2,Singleton.getContext().getString(R.string.global_kinship_grandson)),
    GRANDDAUGHTER(4, 2,Singleton.getContext().getString(R.string.global_kinship_grandaughter)),
    BROTHER(5, 2,Singleton.getContext().getString(R.string.global_kinship_brother)),
    SISTER(6, 2, Singleton.getContext().getString(R.string.global_kinship_sister)),
    SIBLING(7, 2,Singleton.getContext().getString(R.string.global_kinship_sibling)),
    FATHER(8, 1,Singleton.getContext().getString(R.string.global_kinship_father)),
    MOTHER(9, 1,Singleton.getContext().getString(R.string.global_kinship_mother)),
    UNCLE(10, 3,Singleton.getContext().getString(R.string.global_kinship_uncle)),
    AUNT(11, 3,Singleton.getContext().getString(R.string.global_kinship_aunt)),
    COUSIN(12, 4,Singleton.getContext().getString(R.string.global_kinship_cousin)),
    GRANDMOTHER(13, 2,Singleton.getContext().getString(R.string.global_kinship_mother)),
    GRANDFATHER(14, 2,Singleton.getContext().getString(R.string.global_kinship_grandfather));

    final int value, parentage;
    final String relationName;
    Kinship(int value, int parentage, String relationName) {
        this.value = value;
        this.parentage = parentage;
        this.relationName = relationName;
    }

    Kinship() {
        this.relationName = "teste";
        value = 0;
        parentage = 0;
    }

    public int getValue() {
        return value;
    }

    public int getParentage() {
        return parentage;
    }

    public String getRelationName() {
        return relationName;
    }

}
