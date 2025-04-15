package com.epf.persistance.dto;

public class ZombieDTO {
    private int id;
    private String nom;
    private int point_de_vie;
    private Long attaque_par_seconde;
    private String degat_attaque;
    private Long vitesse;
    private String cheminImage;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public int getPoint_de_vie() { return point_de_vie; }
    public void setPoint_de_vie(int point_de_vie) { this.point_de_vie = point_de_vie; }

    public Long getAttaque_par_seconde() { return attaque_par_seconde; }
    public void setAttaque_par_seconde(Long attaque_par_seconde) { this.attaque_par_seconde = attaque_par_seconde; }

    public String getDegat_attaque() { return degat_attaque; }
    public void setDegat_attaque(String degat_attaque) { this.degat_attaque = degat_attaque; }

    public Long getVitesse() { return vitesse; }
    public void setVitesse(Long vitesse) { this.vitesse = vitesse; }

    public String getCheminImage() { return cheminImage; }
    public void setCheminImage(String cheminImage) { this.cheminImage = cheminImage; }
}