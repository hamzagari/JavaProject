
package com.example;

import java.sql.Date;


public class productData {

    private Integer id;
    private String libelle ;
    private Integer stock;
    private Double prix;
    private String id_categorie;
    private Date date_creation ;
    private String image;
    public productData(Integer id, String libelle, Integer stock, Double prix, String id_categorie, Date date_creation,
            String image) {
        this.id = id;
        this.libelle = libelle;
        this.stock = stock;
        this.prix = prix;
        this.id_categorie = id_categorie;
        this.date_creation = date_creation;
        this.image = image;
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
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public Double getPrix() {
        return prix;
    }
    public void setPrix(Double prix) {
        this.prix = prix;
    }
    public String getId_categorie() {
        return id_categorie;
    }
    public void setId_categorie(String id_categorie) {
        this.id_categorie = id_categorie;
    }
    public Date getDate_creation() {
        return date_creation;
    }
    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    
    
}
