package com.epf.persistance.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MapDTO {
    private int id_map;
    
    @NotNull(message = "Le nombre de lignes est obligatoire")
    @Min(value = 1, message = "Le nombre de lignes doit être au moins 1")
    private int ligne;
    
    @NotNull(message = "Le nombre de colonnes est obligatoire")
    @Min(value = 1, message = "Le nombre de colonnes doit être au moins 1")
    private int colonne;
    
    private String chemin_image;

    public int getId_map() {
        return id_map;
    }

    public void setId_map(int id_map) {
        this.id_map = id_map;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public String getChemin_image() {
        return chemin_image;
    }

    public void setChemin_image(String chemin_image) {
        this.chemin_image = chemin_image;
    }
}