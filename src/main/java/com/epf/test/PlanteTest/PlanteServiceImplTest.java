package com.epf.test.PlanteTest;


import com.epf.persistance.Dao.PlanteDAO;
import com.epf.persistance.models.Plante;
import com.epf.service.Imple.PlanteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class PlanteServiceImplTest {

    @Mock
    private PlanteDAO planteDAO;

    @InjectMocks
    private PlanteServiceImpl planteService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPlantes() {
        Plante plante1 = new Plante(1, "Tournesol", 100, 0.5, 0, 50, 1.0, "Produit du soleil", "tournesol.png");
        Plante plante2 = new Plante(2, "Pisto-pois", 100, 1.0, 20, 100, 0.0, "Tire des pois", "pistopois.png");
        List<Plante> expectedPlantes = Arrays.asList(plante1, plante2);

        when(planteDAO.getAllPlantes()).thenReturn(expectedPlantes);

        List<Plante> actualPlantes = planteService.getAllPlantes();

        assertNotNull(actualPlantes);
        assertEquals(2, actualPlantes.size());
        assertEquals(expectedPlantes, actualPlantes);
        verify(planteDAO, times(1)).getAllPlantes();
    }

    @Test
    public void testGetPlanteById() {
        int planteId = 1;
        Plante expectedPlante = new Plante(planteId, "Tournesol", 100, 0.5, 0, 50, 1.0, "Produit du soleil", "tournesol.png");

        when(planteDAO.getPlanteById(planteId)).thenReturn(expectedPlante);

        Plante actualPlante = planteService.getPlanteById(planteId);

        assertNotNull(actualPlante);
        assertEquals(expectedPlante, actualPlante);
        verify(planteDAO, times(1)).getPlanteById(planteId);
    }

    @Test
    public void testAddPlante() {
        Plante plante = new Plante(0, "Tournesol", 100, 0.5, 0, 50, 1.0, "Produit du soleil", "tournesol.png");
        int expectedResult = 1;

        when(planteDAO.addPlante(any(Plante.class))).thenReturn(expectedResult);

        int actualResult = planteService.addPlante(plante);

        assertEquals(expectedResult, actualResult);
        verify(planteDAO, times(1)).addPlante(plante);
    }

    @Test
    public void testUpdatePlante() {
        Plante plante = new Plante(1, "Tournesol", 100, 0.5, 0, 50, 1.0, "Produit du soleil", "tournesol.png");
        int expectedResult = 1;

        when(planteDAO.updatePlante(any(Plante.class))).thenReturn(expectedResult);

        int actualResult = planteService.updatePlante(plante);

        assertEquals(expectedResult, actualResult);
        verify(planteDAO, times(1)).updatePlante(plante);
    }

    @Test
    public void testDeletePlante() {
        int planteId = 1;
        int expectedResult = 1;

        when(planteDAO.deletePlante(anyInt())).thenReturn(expectedResult);

        int actualResult = planteService.deletePlante(planteId);

        assertEquals(expectedResult, actualResult);
        verify(planteDAO, times(1)).deletePlante(planteId);
    }

    @Test
    public void testGetAllPlantes_EmptyList() {
        when(planteDAO.getAllPlantes()).thenReturn(Collections.emptyList());

        List<Plante> plantes = planteService.getAllPlantes();

        assertNotNull(plantes);
        assertEquals(0, plantes.size());
        verify(planteDAO, times(1)).getAllPlantes();
    }
}
