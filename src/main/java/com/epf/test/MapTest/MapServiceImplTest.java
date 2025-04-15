package com.epf.test.MapTest;


import com.epf.persistance.Dao.MapDAO;
import com.epf.persistance.Dao.ZombieDAO;
import com.epf.persistance.models.Map;
import com.epf.persistance.models.Zombie;
import com.epf.service.Imple.MapServiceImpl;
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

public class MapServiceImplTest {

    @Mock
    private MapDAO mapDAO;

    @Mock
    private ZombieDAO zombieDAO;

    @InjectMocks
    private MapServiceImpl mapService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllMaps() {
        Map map1 = new Map(1, 5, 10, "image1.png");
        Map map2 = new Map(2, 8, 12, "image2.png");
        List<Map> expectedMaps = Arrays.asList(map1, map2);

        when(mapDAO.getAllMaps()).thenReturn(expectedMaps);

        List<Map> actualMaps = mapService.getAllMaps();

        assertNotNull(actualMaps);
        assertEquals(2, actualMaps.size());
        assertEquals(expectedMaps, actualMaps);
        verify(mapDAO, times(1)).getAllMaps();
    }

    @Test
    public void testGetMapById() {
        int mapId = 1;
        Map expectedMap = new Map(mapId, 5, 10, "image.png");

        when(mapDAO.getMapById(mapId)).thenReturn(expectedMap);

        Map actualMap = mapService.getMapById(mapId);

        assertNotNull(actualMap);
        assertEquals(expectedMap, actualMap);
        verify(mapDAO, times(1)).getMapById(mapId);
    }

    @Test
    public void testAddMap() {
        Map map = new Map(0, 5, 10, "image.png");
        int expectedResult = 1;  

        when(mapDAO.addMap(any(Map.class))).thenReturn(expectedResult);

        
        int actualResult = mapService.addMap(map);

        assertEquals(expectedResult, actualResult);
        verify(mapDAO, times(1)).addMap(map);
    }

    @Test
    public void testUpdateMap() {
        
        Map map = new Map(1, 5, 10, "image.png");
        int expectedResult = 1;  

        when(mapDAO.updateMap(any(Map.class))).thenReturn(expectedResult);

         int actualResult = mapService.updateMap(map);

         assertEquals(expectedResult, actualResult);
        verify(mapDAO, times(1)).updateMap(map);
    }

    @Test
    public void testDeleteMap() {
        
        int mapId = 1;
        Zombie zombie1 = new Zombie(1, "Zombie1", 100, 1L, "10", 5L, "zombie1.png");
        Zombie zombie2 = new Zombie(2, "Zombie2", 150, 1L, "15", 4L, "zombie2.png");
        List<Zombie> zombies = Arrays.asList(zombie1, zombie2);

        when(zombieDAO.getZombiesByMapId(mapId)).thenReturn(zombies);
        when(zombieDAO.deleteZombie(anyInt())).thenReturn(1);
        when(mapDAO.deleteMap(mapId)).thenReturn(1);

         int actualResult = mapService.deleteMap(mapId);

         assertEquals(1, actualResult);
        verify(zombieDAO, times(1)).getZombiesByMapId(mapId);
        verify(zombieDAO, times(2)).deleteZombie(anyInt());
        verify(mapDAO, times(1)).deleteMap(mapId);
    }

    @Test
    public void testDeleteMap_NoZombies() {
        
        int mapId = 1;

        when(zombieDAO.getZombiesByMapId(mapId)).thenReturn(Collections.emptyList());
        when(mapDAO.deleteMap(mapId)).thenReturn(1);

         int actualResult = mapService.deleteMap(mapId);

         assertEquals(1, actualResult);
        verify(zombieDAO, times(1)).getZombiesByMapId(mapId);
        verify(zombieDAO, never()).deleteZombie(anyInt());
        verify(mapDAO, times(1)).deleteMap(mapId);
    }
}
