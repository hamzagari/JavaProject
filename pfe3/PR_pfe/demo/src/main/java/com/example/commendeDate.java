package com.example;

import java.sql.Date;

public class commendeDate {
    private Integer id;
   
    private String nom_serveur;
    private Integer num_table;
    private Double total;
   // private Integer valide;
    private  String date;
   
    
    public commendeDate(Integer id, String nom_serveur, Integer num_table, Double total, Integer valide, String date) {
        this.id = id;
        this.nom_serveur = nom_serveur;
        this.num_table = num_table;
        this.total = total;
       // this.valide = valide;
        this.date = date;

    }
    public String getNom_serveur() {
        return nom_serveur;
    }
    public void setNom_serveur(String nom_serveur) {
        this.nom_serveur = nom_serveur;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
   
    public Integer getNum_table() {
        return num_table;
    }
    public void setNum_table(Integer num_table) {
        this.num_table = num_table;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
  /*   public Integer getValider() {
        return valide;
    }
    public void setValider(Integer valider) {
        this.valide = valider;
    } */
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
   
}
