package com.epf.test.ZombieTest;

import com.epf.api.ZombieController;
import com.epf.persistance.dto.ZombieDTO;
import com.epf.persistance.models.Zombie;
import com.epf.service.ZombieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ZombieControllerTest {

    @Mock
    private ZombieService zombieService;

    private ZombieController zombieController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        zombieController = new ZombieController(zombieService);
    }

    @Test
    public void testGetAllZombies() {
        Zombie zombie1 = new Zombie(1, "Basic Zombie", 100, 1L, "10", 5L, "zombie1.png");
        Zombie zombie2 = new Zombie(2, "Cone Zombie", 150, 1L, "15", 4L, "zombie2.png");
        List<Zombie> zombies = Arrays.asList(zombie1, zombie2);

        when(zombieService.getAllZombies()).thenReturn(zombies);

        List<ZombieDTO> result = zombieController.getAllZombies();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Basic Zombie", result.get(0).getNom());
        assertEquals(100, result.get(0).getPoint_de_vie());

        assertEquals(2, result.get(1).getId());
        assertEquals("Cone Zombie", result.get(1).getNom());

        verify(zombieService, times(1)).getAllZombies();
    }

    @Test
    public void testGetZombie() {
        int zombieId = 1;
        Zombie zombie = new Zombie(zombieId, "Basic Zombie", 100, 1L, "10", 5L, "zombie1.png");

        when(zombieService.getZombie(zombieId)).thenReturn(zombie);

        ZombieDTO result = zombieController.getZombie(zombieId);

        assertNotNull(result);
        assertEquals(zombieId, result.getId());
        assertEquals("Basic Zombie", result.getNom());
        assertEquals(100, result.getPoint_de_vie());
        assertEquals(1L, result.getAttaque_par_seconde());
        assertEquals("10", result.getDegat_attaque());
        assertEquals(5L, result.getVitesse());
        assertEquals("zombie1.png", result.getCheminImage());

        verify(zombieService, times(1)).getZombie(zombieId);
    }

    @Test
    public void testGetZombiesByMapId() {
        int mapId = 1;
        Zombie zombie1 = new Zombie(1, "Basic Zombie", 100, 1L, "10", 5L, "zombie1.png");
        Zombie zombie2 = new Zombie(2, "Cone Zombie", 150, 1L, "15", 4L, "zombie2.png");
        List<Zombie> zombies = Arrays.asList(zombie1, zombie2);

        when(zombieService.getZombiesByMapId(mapId)).thenReturn(zombies);

        List<ZombieDTO> result = zombieController.getZombiesByMapId(mapId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());

        verify(zombieService, times(1)).getZombiesByMapId(mapId);
    }

    @Test
    public void testAddZombie() {
        ZombieDTO zombieDTO = new ZombieDTO();
        zombieDTO.setNom("Basic Zombie");
        zombieDTO.setPoint_de_vie(100);
        zombieDTO.setAttaque_par_seconde(1L);
        zombieDTO.setDegat_attaque("10");
        zombieDTO.setVitesse(5L);
        zombieDTO.setCheminImage("zombie1.png");

        when(zombieService.addZombie(any(Zombie.class))).thenReturn(1);

        int result = zombieController.addZombie(zombieDTO);

        assertEquals(1, result);

        verify(zombieService, times(1)).addZombie(any(Zombie.class));
    }

    @Test
    public void testUpdateZombie() {
        int zombieId = 1;
        ZombieDTO zombieDTO = new ZombieDTO();
        zombieDTO.setNom("Updated Zombie");
        zombieDTO.setPoint_de_vie(120);
        zombieDTO.setAttaque_par_seconde(1L);
        zombieDTO.setDegat_attaque("12");
        zombieDTO.setVitesse(4L);
        zombieDTO.setCheminImage("zombie_updated.png");

        when(zombieService.updateZombie(any(Zombie.class))).thenReturn(1);

        int result = zombieController.updateZombie(zombieId, zombieDTO);

        assertEquals(1, result);
        assertEquals(zombieId, zombieDTO.getId()); // Check ID was set

        verify(zombieService, times(1)).updateZombie(any(Zombie.class));
    }

    @Test
    public void testDeleteZombie() {
        int zombieId = 1;
        when(zombieService.deleteZombie(zombieId)).thenReturn(1);

        int result = zombieController.deleteZombie(zombieId);

        assertEquals(1, result);
        verify(zombieService, times(1)).deleteZombie(zombieId);
    }
}