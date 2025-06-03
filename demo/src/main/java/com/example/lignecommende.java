package com.example;

public class lignecommende {
    private Integer id;
    private String prod_name;
    private String etat;
    
   
   
    private Double price;
    private Integer quantite ;
    private Double total;
    public String getProd_name() {
        return prod_name;
    }
    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }
   
    public String getEtat() {
        return etat;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }

    public lignecommende(Integer id, String prod_name, Double price, Integer quantite, Double total,String etat) {
        this.id = id;
        this.prod_name =prod_name;
        this.price = price;
        this.quantite = quantite;
        this.total = total;
        this.etat= etat;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
 
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getQuantite() {
        return quantite;
    }
    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    
}
