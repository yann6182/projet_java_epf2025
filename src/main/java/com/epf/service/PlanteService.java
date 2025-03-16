package com.epf.service;

import com.epf.persistance.Dao.PlanteDAO;
import com.epf.persistance.models.Plante;

import java.util.List;

public class PlanteService {
    private final PlanteDAO planteDAO;
    public PlanteService(PlanteDAO planteDAO) {
        this.planteDAO = planteDAO;
    }
    public List<Plante> getAllPlantes(){
        return planteDAO.getAllPlantes();
    }

}
