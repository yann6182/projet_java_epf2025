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
        assertEquals(1, dto.getIdPlante());
        assertEquals("Pea Shooter", dto.getNom());
        assertEquals("peashooter.png", dto.getImage());


    }

    @Test
    public void testToPlante() {
        PlanteDTO dto = new PlanteDTO();
        dto.setIdPlante(1);
        dto.setNom("Pea Shooter");
        dto.setImage("peashooter.png");
        dto.setDescription("Basic plant");
        dto.setDegat(20);
        dto.setCout(100);

        Plante plante = PlanteMapper.toPlante(dto);

        assertNotNull(plante);
        assertEquals(1, plante.getIdPlante());
        assertEquals("Pea Shooter", plante.getNom());
        assertEquals("peashooter.png", plante.getCheminImage());

    }

    @Test
    public void testNullHandling() {
        assertNull(PlanteMapper.toPlanteDTO(null));
        assertNull(PlanteMapper.toPlante(null));
    }
}