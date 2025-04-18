package com.epf.persistance.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ZombieDTO {
    private int id_zombie;
    
    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
    private String nom;
    
    @NotNull(message = "Les points de vie sont obligatoires")
    @Min(value = 1, message = "Les points de vie doivent être supérieurs à 0")
    private int point_de_vie;
    
    @NotNull(message = "La vitesse d'attaque est obligatoire")
    @Min(value = 0, message = "La vitesse d'attaque doit être positive")
    private double attaque_par_seconde;
    
    @NotNull(message = "Les dégâts d'attaque sont obligatoires")
    @Min(value = 0, message = "Les dégâts d'attaque doivent être positifs")
    private int degat_attaque;
    
    @NotNull(message = "La vitesse de déplacement est obligatoire")
    @Min(value = 0, message = "La vitesse de déplacement doit être positive")
    private double vitesse_de_deplacement;
    
    private String chemin_image;
    
    private Integer id_map;

    public int getId_zombie() {
        return id_zombie;
    }

    public void setId_zombie(int id_zombie) {
        this.id_zombie = id_zombie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPoint_de_vie() {
        return point_de_vie;
    }

    public void setPoint_de_vie(int point_de_vie) {
        this.point_de_vie = point_de_vie;
    }

    public double getAttaque_par_seconde() {
        return attaque_par_seconde;
    }

    public void setAttaque_par_seconde(double attaque_par_seconde) {
        this.attaque_par_seconde = attaque_par_seconde;
    }

    public int getDegat_attaque() {
        return degat_attaque;
    }

    public void setDegat_attaque(int degat_attaque) {
        this.degat_attaque = degat_attaque;
    }

    public double getVitesse_de_deplacement() {
        return vitesse_de_deplacement;
    }

    public void setVitesse_de_deplacement(double vitesse_de_deplacement) {
        this.vitesse_de_deplacement = vitesse_de_deplacement;
    }

    public String getChemin_image() {
        return chemin_image;
    }

    public void setChemin_image(String chemin_image) {
        this.chemin_image = chemin_image;
    }

    public Integer getId_map() {
        return id_map;
    }

    public void setId_map(Integer id_map) {
        this.id_map = id_map;
    }
}