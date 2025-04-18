package com.epf.test.ZombieTest;

import com.epf.api.ZombieController;
import com.epf.persistance.dto.ZombieDTO;
import com.epf.persistance.models.Zombie;
import com.epf.service.ZombieService;
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
        assertEquals(1, result.get(0).getId_zombie());
        assertEquals("Basic Zombie", result.get(0).getNom());
        assertEquals(100, result.get(0).getPoint_de_vie());

        assertEquals(2, result.get(1).getId_zombie());
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
        assertEquals(zombieId, result.getId_zombie());
        assertEquals("Basic Zombie", result.getNom());
        assertEquals(100, result.getPoint_de_vie());
        assertEquals(1, result.getAttaque_par_seconde());
        assertEquals(10, result.getDegat_attaque());
        assertEquals(5, result.getVitesse_de_deplacement());
        assertEquals("zombie1.png", result.getChemin_image());

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
        assertEquals(1, result.get(0).getId_zombie());
        assertEquals(2, result.get(1).getId_zombie());

        verify(zombieService, times(1)).getZombiesByMapId(mapId);
    }

    @Test
    public void testAddZombie() {
        ZombieDTO zombieDTO = new ZombieDTO();
        zombieDTO.setNom("Basic Zombie");
        zombieDTO.setPoint_de_vie(100);
        zombieDTO.setAttaque_par_seconde(1);
        zombieDTO.setDegat_attaque(10);
        zombieDTO.setVitesse_de_deplacement(5);
        zombieDTO.setChemin_image("zombie1.png");

        when(zombieService.addZombie(any(Zombie.class))).thenReturn(1);

        ResponseEntity<Integer> response = zombieController.addZombie(zombieDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, response.getBody());

        verify(zombieService, times(1)).addZombie(any(Zombie.class));
    }

    @Test
    public void testUpdateZombie() {
        int zombieId = 1;
        ZombieDTO zombieDTO = new ZombieDTO();
        zombieDTO.setNom("Updated Zombie");
        zombieDTO.setPoint_de_vie(120);
        zombieDTO.setAttaque_par_seconde(1);
        zombieDTO.setDegat_attaque(12);
        zombieDTO.setVitesse_de_deplacement(4);
        zombieDTO.setChemin_image("zombie_updated.png");

        when(zombieService.updateZombie(any(Zombie.class))).thenReturn(1);

        ResponseEntity<Integer> response = zombieController.updateZombie(zombieId, zombieDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody());
        assertEquals(zombieId, zombieDTO.getId_zombie()); // Check ID was set

        verify(zombieService, times(1)).updateZombie(any(Zombie.class));
    }

    @Test
    public void testDeleteZombie() {
        int zombieId = 1;
        when(zombieService.deleteZombie(zombieId)).thenReturn(1);

        ResponseEntity<Integer> response = zombieController.deleteZombie(zombieId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody());
        verify(zombieService, times(1)).deleteZombie(zombieId);
    }
}