package com.epf.persistance.models;

public class Zombie {
    private int id;
    private String nom;
    private int point_de_vie;
    private Long attaque_par_seconde ;
    private String degat_attaque;
    private Long vitesse;
    private String cheminImage;



    public Zombie(int id, String nom, int point_de_vie, Long attaque_par_seconde, String degat_attaque, Long vitesse, String cheminImage) {
        this.id = id;
        this.nom = nom;
        this.point_de_vie = point_de_vie;
        this.attaque_par_seconde = attaque_par_seconde;
        this.degat_attaque = degat_attaque;
        this.vitesse = vitesse;
        this.cheminImage = cheminImage;

    }

    public Zombie() {

    }

    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public int getPoint_de_vie() {
        return point_de_vie;
    }
    public Long getAttaque_par_seconde() {
        return attaque_par_seconde;
    }
    public String getDegat_attaque() {
        return degat_attaque;
    }
    public Long getVitesse() {
        return vitesse;
    }
    public String getCheminImage() {
        return cheminImage;
    }

    public void setVitesse(Long vitesse) {
        this.vitesse = vitesse;
    }
    public void setPoint_de_vie(int point_de_vie) {
        this.point_de_vie = point_de_vie;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setAttaque_par_seconde(Long attaque_par_seconde) {
        this.attaque_par_seconde = attaque_par_seconde;
    }
    public void setDegat_attaque(String degat_attaque) {
        this.degat_attaque = degat_attaque;
    }
    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }


    public void setId(int id) {
        this.id = id;
    }


}
