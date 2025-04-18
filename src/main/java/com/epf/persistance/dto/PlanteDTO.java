package com.epf.persistance.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PlanteDTO {
    private int id_plante;
    
    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
    private String nom;
    
    @NotNull(message = "Les points de vie sont obligatoires")
    @Min(value = 0, message = "Les points de vie doivent être supérieurs à 0")
    private int point_de_vie;
    
    @NotNull(message = "La vitesse d'attaque est obligatoire")
    @Min(value = 0, message = "La vitesse d'attaque doit être positive")
    private double attaque_par_seconde;
    
    @NotNull(message = "Les dégâts d'attaque sont obligatoires")
    @Min(value = 0, message = "Les dégâts d'attaque doivent être positifs")
    private int degat_attaque;
    
    @NotNull(message = "Le coût est obligatoire")
    @Min(value = 0, message = "Le coût doit être positif")
    private int cout;
    
    @NotNull(message = "La production de soleil est obligatoire")
    @Min(value = 0, message = "La production de soleil doit être positive")
    private double soleil_par_seconde;
    
    private String effet;
    
    private String chemin_image;

    public int getId_plante() {
        return id_plante;
    }

    public void setId_plante(int id_plante) {
        this.id_plante = id_plante;
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

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public double getSoleil_par_seconde() {
        return soleil_par_seconde;
    }

    public void setSoleil_par_seconde(double soleil_par_seconde) {
        this.soleil_par_seconde = soleil_par_seconde;
    }

    public String getEffet() {
        return effet;
    }

    public void setEffet(String effet) {
        this.effet = effet;
    }

    public String getChemin_image() {
        return chemin_image;
    }

    public void setChemin_image(String chemin_image) {
        this.chemin_image = chemin_image;
    }
}