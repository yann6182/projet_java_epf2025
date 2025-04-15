package com.epf.service;

import com.epf.persistance.models.Plante;

import java.util.List;

public interface PlanteService {

    public List<Plante> getAllPlantes();
    public Plante getPlanteById(int id);
    public int addPlante(Plante plante) ;
    public int updatePlante(Plante plante) ;
    public int deletePlante(int id) ;
}
