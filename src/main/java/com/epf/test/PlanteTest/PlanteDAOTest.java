package com.epf.test.PlanteTest;

    import com.epf.persistance.Dao.PlanteDAO;
    import com.epf.persistance.models.Plante;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.mockito.Mock;
    import org.mockito.MockitoAnnotations;
    import org.springframework.jdbc.core.JdbcTemplate;
    import org.springframework.jdbc.core.RowMapper;

    import java.util.Arrays;
    import java.util.List;

    import static org.junit.jupiter.api.Assertions.*;
    import static org.mockito.ArgumentMatchers.*;
    import static org.mockito.Mockito.*;

    public class PlanteDAOTest {

        @Mock
        private JdbcTemplate jdbcTemplate;

        private PlanteDAO planteDAO;

        @BeforeEach
        public void setup() {
            MockitoAnnotations.openMocks(this);
            planteDAO = new PlanteDAO(jdbcTemplate);
        }

        @Test
        public void testGetAllPlantes() {
 
            Plante plante1 = new Plante(1, "Pea Shooter", 100, 1.5, 20, 100, 0, "Basic attack", "pea.png");
            Plante plante2 = new Plante(2, "Sunflower", 80, 0, 0, 50, 25.0, "Produces sun", "sunflower.png");
            List<Plante> expectedPlantes = Arrays.asList(plante1, plante2);

            when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedPlantes);

 
            List<Plante> actualPlantes = planteDAO.getAllPlantes();

 
            assertNotNull(actualPlantes);
            assertEquals(2, actualPlantes.size());
            assertEquals(expectedPlantes, actualPlantes);
            verify(jdbcTemplate, times(1)).query(eq("SELECT * FROM plante"), any(RowMapper.class));
        }

        @Test
        public void testGetPlanteById() {
 
            int planteId = 1;
            Plante expectedPlante = new Plante(planteId, "Pea Shooter", 100, 1.5, 20, 100, 0, "Basic attack", "pea.png");

            when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), eq(planteId))).thenReturn(expectedPlante);

 
            Plante actualPlante = planteDAO.getPlanteById(planteId);

 
            assertNotNull(actualPlante);
            assertEquals(expectedPlante, actualPlante);
            verify(jdbcTemplate, times(1)).queryForObject(eq("SELECT * FROM plante WHERE id_plante = ?"), any(RowMapper.class), eq(planteId));
        }

        @Test
        public void testAddPlante() {
 
            Plante plante = new Plante(0, "Wall-nut", 400, 0, 0, 50, 0, "Defensive plant", "wallnut.png");
            when(jdbcTemplate.update(anyString(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(1);

 
            int result = planteDAO.addPlante(plante);

 
            assertEquals(1, result);
            verify(jdbcTemplate, times(1)).update(
                    eq("INSERT INTO plante (nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"),
                    eq(plante.getNom()),
                    eq(plante.getPointDeVie()),
                    eq(plante.getAttaqueParSeconde()),
                    eq(plante.getDegatAttaque()),
                    eq(plante.getCout()),
                    eq(plante.getSoleilParSeconde()),
                    eq(plante.getEffet()),
                    eq(plante.getCheminImage())
            );
        }

        @Test
        public void testUpdatePlante() {
 
            Plante plante = new Plante(1, "Repeater", 120, 2.0, 40, 200, 0, "Double pea shooter", "repeater.png");
            when(jdbcTemplate.update(anyString(), any(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(1);

 
            int result = planteDAO.updatePlante(plante);

 
            assertEquals(1, result);
            verify(jdbcTemplate, times(1)).update(
                    eq("UPDATE plante SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, cout = ?, soleil_par_seconde = ?, effet = ?, chemin_image = ? WHERE id_plante = ?"),
                    eq(plante.getNom()),
                    eq(plante.getPointDeVie()),
                    eq(plante.getAttaqueParSeconde()),
                    eq(plante.getDegatAttaque()),
                    eq(plante.getCout()),
                    eq(plante.getSoleilParSeconde()),
                    eq(plante.getEffet()),
                    eq(plante.getCheminImage()),
                    eq(plante.getIdPlante())
            );
        }

        @Test
        public void testDeletePlante() {
 
            int planteId = 1;
            when(jdbcTemplate.update(anyString(), eq(planteId))).thenReturn(1);

 
            int result = planteDAO.deletePlante(planteId);

 
            assertEquals(1, result);
            verify(jdbcTemplate, times(1)).update(eq("DELETE FROM plante WHERE id_plante = ?"), eq(planteId));
        }
    }