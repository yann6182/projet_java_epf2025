package com.epf.service.Imple;

import com.epf.persistance.Dao.PlanteDAO;
import com.epf.persistance.models.Plante;
import com.epf.service.PlanteService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlanteServiceImpl implements PlanteService {
    private final PlanteDAO planteDAO;

    public PlanteServiceImpl(PlanteDAO planteDAO) {
        this.planteDAO = planteDAO;
    }

    @Override
    public List<Plante> getAllPlantes() {
        return planteDAO.getAllPlantes();
    }

    @Override
    public Plante getPlanteById(int id) {
        return planteDAO.getPlanteById(id);
    }

    @Override
    public int addPlante(Plante plante) {
        return planteDAO.addPlante(plante);
    }

    @Override
    public int updatePlante(Plante plante) {
        return planteDAO.updatePlante(plante);
    }

    @Override
    public int deletePlante(int id) {
        return planteDAO.deletePlante(id);
    }
}
