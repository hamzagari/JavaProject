package com.example;

import java.sql.Date;

public class categorieData {
    private Integer id;
    private String libelle ;

    public categorieData(Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
      
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    @Override
    public String toString() {
        return "categorieData [id=" + id + ", libelle=" + libelle + "]";
    }
 

}
