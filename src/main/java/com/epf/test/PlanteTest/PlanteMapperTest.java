package com.epf.test.PlanteTest;

import com.epf.persistance.dto.PlanteDTO;
import com.epf.persistance.mapper.PlanteMapper;
import com.epf.persistance.models.Plante;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlanteMapperTest {

    @Test
    public void testToPlanteDTO() {
        Plante plante = new Plante(1, "Pea Shooter", 100, 1.5, 20, 100, 0, "Basic attack", "peashooter.png");

        PlanteDTO dto = PlanteMapper.toPlanteDTO(plante);

        assertNotNull(dto);
        assertEquals(1, dto.getId_plante());
        assertEquals("Pea Shooter", dto.getNom());
        assertEquals(100, dto.getPoint_de_vie());
        assertEquals(1.5, dto.getAttaque_par_seconde());
        assertEquals(20, dto.getDegat_attaque());
        assertEquals(100, dto.getCout());
        assertEquals(0, dto.getSoleil_par_seconde());
        assertEquals("Basic attack", dto.getEffet());
        assertEquals("peashooter.png", dto.getChemin_image());
    }

    @Test
    public void testToPlante() {
        PlanteDTO dto = new PlanteDTO();
        dto.setId_plante(1);
        dto.setNom("Pea Shooter");
        dto.setPoint_de_vie(100);
        dto.setAttaque_par_seconde(1.5);
        dto.setDegat_attaque(20);
        dto.setCout(100);
        dto.setSoleil_par_seconde(0);
        dto.setEffet("Basic attack");
        dto.setChemin_image("peashooter.png");

        Plante plante = PlanteMapper.toPlante(dto);

        assertNotNull(plante);
        assertEquals(1, plante.getIdPlante());
        assertEquals("Pea Shooter", plante.getNom());
        assertEquals(100, plante.getPointDeVie());
        assertEquals(1.5, plante.getAttaqueParSeconde());
        assertEquals(20, plante.getDegatAttaque());
        assertEquals(100, plante.getCout());
        assertEquals(0, plante.getSoleilParSeconde());
        assertEquals("Basic attack", plante.getEffet());
        assertEquals("peashooter.png", plante.getCheminImage());
    }

    @Test
    public void testNullHandling() {
        assertNull(PlanteMapper.toPlanteDTO(null));
        assertNull(PlanteMapper.toPlante(null));
    }
}