package com.epf.persistance.mapper;

import com.epf.persistance.dto.PlanteDTO;
import com.epf.persistance.models.Plante;

public class PlanteMapper {
    public static PlanteDTO toPlanteDTO(Plante plante) {
        if (plante == null) return null;
        PlanteDTO planteDTO = new PlanteDTO();
        planteDTO.setIdPlante(plante.getIdPlante());
        planteDTO.setNom(plante.getNom());
        planteDTO.setImage(plante.getCheminImage());
        return planteDTO;
    }

    public static Plante toPlante(PlanteDTO planteDTO) {
        if (planteDTO == null) return null;
        Plante plante = new Plante();
        plante.setIdPlante(planteDTO.getIdPlante());
        plante.setNom(planteDTO.getNom());
        plante.setCheminImage(planteDTO.getImage());
        return plante;
    }

}
