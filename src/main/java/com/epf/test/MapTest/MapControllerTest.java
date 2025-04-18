package com.epf.test.MapTest;

import com.epf.api.MapController;
import com.epf.persistance.dto.MapDTO;
import com.epf.persistance.models.Map;
import com.epf.service.MapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class MapControllerTest {

    @Mock
    private MapService mapService;

    private MapController mapController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mapController = new MapController(mapService);
    }

    @Test
    public void testGetAllMaps() {
        Map map1 = new Map(1, 5, 10, "image1.png");
        Map map2 = new Map(2, 8, 12, "image2.png");
        List<Map> maps = Arrays.asList(map1, map2);

        when(mapService.getAllMaps()).thenReturn(maps);

        List<MapDTO> result = mapController.getAllMaps();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId_map());
        assertEquals(5, result.get(0).getLigne());
        assertEquals(10, result.get(0).getColonne());
        assertEquals("image1.png", result.get(0).getChemin_image());

        assertEquals(2, result.get(1).getId_map());
        assertEquals(8, result.get(1).getLigne());
        assertEquals(12, result.get(1).getColonne());
        assertEquals("image2.png", result.get(1).getChemin_image());

        verify(mapService, times(1)).getAllMaps();
    }

    @Test
    public void testGetMapById() {
        int mapId = 1;
        Map map = new Map(mapId, 5, 10, "image.png");

        when(mapService.getMapById(mapId)).thenReturn(map);

        MapDTO result = mapController.getMapById(mapId);

        assertNotNull(result);
        assertEquals(mapId, result.getId_map());
        assertEquals(5, result.getLigne());
        assertEquals(10, result.getColonne());
        assertEquals("image.png", result.getChemin_image());

        verify(mapService, times(1)).getMapById(mapId);
    }

    @Test
    public void testAddMap() {
        MapDTO mapDTO = new MapDTO();
        mapDTO.setLigne(5);
        mapDTO.setColonne(10);
        mapDTO.setChemin_image("image.png");

        when(mapService.addMap(any(Map.class))).thenReturn(1);

        ResponseEntity<Integer> response = mapController.addMap(mapDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, response.getBody());

        verify(mapService, times(1)).addMap(any(Map.class));
    }

    @Test
    public void testUpdateMap() {
        int mapId = 1;
        MapDTO mapDTO = new MapDTO();
        mapDTO.setLigne(5);
        mapDTO.setColonne(10);
        mapDTO.setChemin_image("image.png");

        when(mapService.updateMap(any(Map.class))).thenReturn(1);

        ResponseEntity<Integer> response = mapController.updateMap(mapId, mapDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody());
        assertEquals(mapId, mapDTO.getId_map());

        verify(mapService, times(1)).updateMap(any(Map.class));
    }

    @Test
    public void testDeleteMap() {
        int mapId = 1;
        when(mapService.deleteMap(mapId)).thenReturn(1);

        ResponseEntity<Integer> response = mapController.deleteMap(mapId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody());
        verify(mapService, times(1)).deleteMap(mapId);
    }
}