package com.epf.test.PlanteTest;

    import com.epf.api.PlanteController;
    import com.epf.persistance.dto.PlanteDTO;
    import com.epf.persistance.models.Plante;
    import com.epf.service.PlanteService;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.mockito.Mock;
    import org.mockito.MockitoAnnotations;

    import java.util.Arrays;
    import java.util.List;

    import static org.junit.jupiter.api.Assertions.*;
    import static org.mockito.ArgumentMatchers.any;
    import static org.mockito.Mockito.*;

    public class PlanteControllerTest {

        @Mock
        private PlanteService planteService;

        private PlanteController planteController;

        @BeforeEach
        public void setup() {
            MockitoAnnotations.openMocks(this);
            planteController = new PlanteController(planteService);
        }

        @Test
        public void testGetAllPlantes() {
            Plante plante1 = new Plante();
            plante1.setIdPlante(1);
            plante1.setNom("Tournesol");
            plante1.setCheminImage("tournesol.png");

            Plante plante2 = new Plante();
            plante2.setIdPlante(2);
            plante2.setNom("Pisto-pois");
            plante2.setCheminImage("pistopois.png");

            List<Plante> plantes = Arrays.asList(plante1, plante2);

            when(planteService.getAllPlantes()).thenReturn(plantes);

            List<PlanteDTO> result = planteController.getAllPlantes();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(1, result.get(0).getId_plante());
            assertEquals("Tournesol", result.get(0).getNom());

            verify(planteService, times(1)).getAllPlantes();
        }

        @Test
        public void testGetPlanteById() {
            int planteId = 1;
            Plante plante = new Plante();
            plante.setIdPlante(planteId);
            plante.setNom("Tournesol");
            plante.setCheminImage("tournesol.png");

            when(planteService.getPlanteById(planteId)).thenReturn(plante);

            PlanteDTO result = planteController.getPlanteById(planteId);

            assertNotNull(result);
            assertEquals(planteId, result.getId_plante());
            assertEquals("Tournesol", result.getNom());

            verify(planteService, times(1)).getPlanteById(planteId);
        }

        @Test
        public void testAddPlante() {
            PlanteDTO planteDTO = new PlanteDTO();
            planteDTO.setNom("Tournesol");
            planteDTO.setChemin_image("tournesol.png");

            when(planteService.addPlante(any(Plante.class))).thenReturn(1);

            int result = planteController.addPlante(planteDTO);

            assertEquals(1, result);

            verify(planteService, times(1)).addPlante(any(Plante.class));
        }

        @Test
        public void testUpdatePlante() {
            int planteId = 1;
            PlanteDTO planteDTO = new PlanteDTO();
            planteDTO.setNom("Tournesol");
            planteDTO.setChemin_image("tournesol.png");

            when(planteService.updatePlante(any(Plante.class))).thenReturn(1);

            int result = planteController.updatePlante(planteId, planteDTO);

            assertEquals(1, result);
            assertEquals(planteId, planteDTO.getId_plante());

            verify(planteService, times(1)).updatePlante(any(Plante.class));
        }

        @Test
        public void testDeletePlante() {
            int planteId = 1;
            when(planteService.deletePlante(planteId)).thenReturn(1);

            int result = planteController.deletePlante(planteId);

            assertEquals(1, result);
            verify(planteService, times(1)).deletePlante(planteId);
        }
    }