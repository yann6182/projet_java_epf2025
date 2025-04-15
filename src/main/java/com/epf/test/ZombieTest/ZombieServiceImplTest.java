package com.epf.test.ZombieTest;


import com.epf.persistance.Dao.ZombieDAO;
import com.epf.persistance.models.Zombie;
import com.epf.service.Imple.ZombieServiceImpl;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ZombieServiceImplTest {

    @Mock
    private ZombieDAO zombieDAO;

    @InjectMocks
    private ZombieServiceImpl zombieService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllZombies() {
        Zombie zombie1 = new Zombie(1, "Basic Zombie", 100, 1L, "10", 5L, "zombie1.png");
        Zombie zombie2 = new Zombie(2, "Cone Zombie", 150, 1L, "15", 4L, "zombie2.png");
        List<Zombie> expectedZombies = Arrays.asList(zombie1, zombie2);

        when(zombieDAO.getAllZombies()).thenReturn(expectedZombies);

        List<Zombie> actualZombies = zombieService.getAllZombies();

        assertNotNull(actualZombies);
        assertEquals(2, actualZombies.size());
        assertEquals(expectedZombies, actualZombies);
        verify(zombieDAO, times(1)).getAllZombies();
    }

    @Test
    public void testGetZombie() {
        int zombieId = 1;
        Zombie expectedZombie = new Zombie(zombieId, "Basic Zombie", 100, 1L, "10", 5L, "zombie1.png");

        when(zombieDAO.getZombie(zombieId)).thenReturn(expectedZombie);

        Zombie actualZombie = zombieService.getZombie(zombieId);

        assertNotNull(actualZombie);
        assertEquals(expectedZombie, actualZombie);
        verify(zombieDAO, times(1)).getZombie(zombieId);
    }

    @Test
    public void testAddZombie() {
        Zombie zombie = new Zombie(0, "New Zombie", 120, 1L, "12", 4L, "newzombie.png");
        int expectedResult = 1;

        when(zombieDAO.addZombie(any(Zombie.class))).thenReturn(expectedResult);

        int actualResult = zombieService.addZombie(zombie);

        assertEquals(expectedResult, actualResult);
        verify(zombieDAO, times(1)).addZombie(zombie);
    }

    @Test
    public void testUpdateZombie() {
        Zombie zombie = new Zombie(1, "Updated Zombie", 120, 1L, "12", 4L, "updatedzombie.png");
        int expectedResult = 1;

        when(zombieDAO.updateZombie(any(Zombie.class))).thenReturn(expectedResult);

        int actualResult = zombieService.updateZombie(zombie);

        assertEquals(expectedResult, actualResult);
        verify(zombieDAO, times(1)).updateZombie(zombie);
    }

    @Test
    public void testDeleteZombie() {
        int zombieId = 1;
        int expectedResult = 1;

        when(zombieDAO.deleteZombie(anyInt())).thenReturn(expectedResult);

        int actualResult = zombieService.deleteZombie(zombieId);

        assertEquals(expectedResult, actualResult);
        verify(zombieDAO, times(1)).deleteZombie(zombieId);
    }

    @Test
    public void testGetZombiesByMapId() {
        int mapId = 1;
        Zombie zombie1 = new Zombie(1, "Basic Zombie", 100, 1L, "10", 5L, "zombie1.png");
        Zombie zombie2 = new Zombie(2, "Cone Zombie", 150, 1L, "15", 4L, "zombie2.png");
        List<Zombie> expectedZombies = Arrays.asList(zombie1, zombie2);

        when(zombieDAO.getZombiesByMapId(mapId)).thenReturn(expectedZombies);

        List<Zombie> actualZombies = zombieService.getZombiesByMapId(mapId);

        assertNotNull(actualZombies);
        assertEquals(2, actualZombies.size());
        assertEquals(expectedZombies, actualZombies);
        verify(zombieDAO, times(1)).getZombiesByMapId(mapId);
    }

    @Test
    public void testGetAllZombies_EmptyList() {
        when(zombieDAO.getAllZombies()).thenReturn(Collections.emptyList());

        List<Zombie> zombies = zombieService.getAllZombies();


        assertNotNull(zombies);
        assertEquals(0, zombies.size());
        verify(zombieDAO, times(1)).getAllZombies();
    }

    @Test
    public void testGetZombiesByMapId_EmptyList() {
        int mapId = 999;
        when(zombieDAO.getZombiesByMapId(mapId)).thenReturn(Collections.emptyList());

        List<Zombie> zombies = zombieService.getZombiesByMapId(mapId);

        assertNotNull(zombies);
        assertEquals(0, zombies.size());
        verify(zombieDAO, times(1)).getZombiesByMapId(mapId);
    }
}