package com.epf.test.MapTest;

import com.epf.persistance.Dao.MapDAO;
import com.epf.persistance.models.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MapDAOIntegrationTest {

    private EmbeddedDatabase db;
    private JdbcTemplate jdbcTemplate;
    private MapDAO mapDAO;

    @BeforeEach
    public void setUp() {
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .addScript("test-data.sql")
                .build();

        jdbcTemplate = new JdbcTemplate(db);
        mapDAO = new MapDAO(jdbcTemplate);

        jdbcTemplate.update("DELETE FROM zombie");
        jdbcTemplate.update("DELETE FROM map");
        jdbcTemplate.update("INSERT INTO map (id_map, ligne, colonne, chemin_image) VALUES (1, 5, 10, 'test1.png')");
        jdbcTemplate.update("INSERT INTO map (id_map, ligne, colonne, chemin_image) VALUES (2, 6, 12, 'test2.png')");
    }

    @AfterEach
    public void tearDown() {
        db.shutdown();
    }

    @Test
    public void testGetAllMaps() {
        List<Map> maps = mapDAO.getAllMaps();

        assertNotNull(maps);
        assertEquals(2, maps.size());

        Map map1 = maps.get(0);
        assertEquals(1, map1.getIdMap());
        assertEquals(5, map1.getLigne());
        assertEquals(10, map1.getColonne());
        assertEquals("test1.png", map1.getCheminImage());

        Map map2 = maps.get(1);
        assertEquals(2, map2.getIdMap());
        assertEquals(6, map2.getLigne());
        assertEquals(12, map2.getColonne());
        assertEquals("test2.png", map2.getCheminImage());
    }

    @Test
    public void testGetMapById() {
        Map map = mapDAO.getMapById(1);

        assertNotNull(map);
        assertEquals(1, map.getIdMap());
        assertEquals(5, map.getLigne());
        assertEquals(10, map.getColonne());
        assertEquals("test1.png", map.getCheminImage());
    }

    @Test
    public void testAddMap() {
        Map newMap = new Map(0, 8, 15, "new_map.png");

        int result = mapDAO.addMap(newMap);

        assertEquals(1, result);

        List<Map> maps = mapDAO.getAllMaps();
        assertEquals(3, maps.size());

        Map addedMap = null;
        for (Map map : maps) {
            if (map.getLigne() == 8 && map.getColonne() == 15 && "new_map.png".equals(map.getCheminImage())) {
                addedMap = map;
                break;
            }
        }

        assertNotNull(addedMap);
        assertEquals(8, addedMap.getLigne());
        assertEquals(15, addedMap.getColonne());
        assertEquals("new_map.png", addedMap.getCheminImage());
    }

    @Test
    public void testUpdateMap() {
        Map mapToUpdate = new Map(1, 10, 20, "updated.png");

        int result = mapDAO.updateMap(mapToUpdate);

        assertEquals(1, result);

        Map updatedMap = mapDAO.getMapById(1);
        assertEquals(1, updatedMap.getIdMap());
        assertEquals(10, updatedMap.getLigne());
        assertEquals(20, updatedMap.getColonne());
        assertEquals("updated.png", updatedMap.getCheminImage());
    }

    @Test
    public void testDeleteMap() {
        int result = mapDAO.deleteMap(1);

        assertEquals(1, result);

        List<Map> maps = mapDAO.getAllMaps();
        assertEquals(1, maps.size());
        assertEquals(2, maps.get(0).getIdMap());
    }
}