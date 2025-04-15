package com.epf.test.MapTest;

import com.epf.persistance.dto.MapDTO;
import com.epf.persistance.mapper.MapMapper;
import com.epf.persistance.models.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapMapperTest {

    @Test
    public void testMapToModel() {
        MapDTO mapDTO = new MapDTO();
        mapDTO.setIdMap(1);
        mapDTO.setLigne(5);
        mapDTO.setColonne(10);
        mapDTO.setCheminImage("test_map.png");

        Map map = MapMapper.mapToModel(mapDTO);

        assertNotNull(map);
        assertEquals(1, map.getIdMap());
        assertEquals(5, map.getLigne());
        assertEquals(10, map.getColonne());
        assertEquals("test_map.png", map.getCheminImage());
    }

    @Test
    public void testMapToDto() {
        Map map = new Map(1, 5, 10, "test_map.png");

        MapDTO mapDTO = MapMapper.mapToDto(map);


        assertNotNull(mapDTO);
        assertEquals(1, mapDTO.getIdMap());
        assertEquals(5, mapDTO.getLigne());
        assertEquals(10, mapDTO.getColonne());
        assertEquals("test_map.png", mapDTO.getCheminImage());
    }

    @Test
    public void testNullHandling() {
        assertNull(MapMapper.mapToDto(null));
        assertNull(MapMapper.mapToModel(null));
    }
}