package com.epf.test.ZombieTest;

import com.epf.persistance.Dao.ZombieDAO;
import com.epf.persistance.models.Zombie;
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

public class ZombieDAOTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private ZombieDAO zombieDAO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        zombieDAO = new ZombieDAO(jdbcTemplate);
    }

    @Test
    public void testGetAllZombies() {
        Zombie zombie1 = new Zombie(1, "Zombie1", 100, 1L, "10", 5L, "zombie1.png");
        Zombie zombie2 = new Zombie(2, "Zombie2", 150, 1L, "15", 4L, "zombie2.png");
        List<Zombie> expectedZombies = Arrays.asList(zombie1, zombie2);

        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedZombies);

        List<Zombie> actualZombies = zombieDAO.getAllZombies();

        assertNotNull(actualZombies);
        assertEquals(2, actualZombies.size());
        assertEquals(expectedZombies, actualZombies);
        verify(jdbcTemplate, times(1)).query(eq("SELECT * FROM zombie"), any(RowMapper.class));
    }

    @Test
    public void testGetZombieById() {

        int zombieId = 1;
        Zombie expectedZombie = new Zombie(zombieId, "Zombie1", 100, 1L, "10", 5L, "zombie1.png");

        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), eq(zombieId))).thenReturn(expectedZombie);

        Zombie actualZombie = zombieDAO.getZombie(zombieId);

        assertNotNull(actualZombie);
        assertEquals(expectedZombie, actualZombie);
        verify(jdbcTemplate, times(1)).queryForObject(eq("SELECT * FROM zombie WHERE id = ?"), any(RowMapper.class), eq(zombieId));
    }

    @Test
    public void testAddZombie() {
        Zombie zombie = new Zombie(0, "Zombie1", 100, 1L, "10", 5L, "zombie1.png");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any(), any(), any(), any())).thenReturn(1);

        int result = zombieDAO.addZombie(zombie);

        assertEquals(1, result);
        verify(jdbcTemplate, times(1)).update(eq("INSERT INTO zombie (nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse, chemin_image) VALUES (?, ?, ?, ?, ?, ?)"),
                eq(zombie.getNom()), eq(zombie.getPoint_de_vie()), eq(zombie.getAttaque_par_seconde()), eq(zombie.getDegat_attaque()), eq(zombie.getVitesse()), eq(zombie.getCheminImage()));
    }

    @Test
    public void testUpdateZombie() {
        Zombie zombie = new Zombie(1, "Updated Zombie", 120, 1L, "12", 4L, "updated.png");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any(), any(), any(), any())).thenReturn(1);

        int result = zombieDAO.updateZombie(zombie);

        assertEquals(1, result);
        verify(jdbcTemplate, times(1)).update(eq("UPDATE zombie SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, vitesse = ?, chemin_image = ?, id_map = ? WHERE id = ?"),
                eq(zombie.getNom()), eq(zombie.getPoint_de_vie()), eq(zombie.getAttaque_par_seconde()), eq(zombie.getDegat_attaque()), eq(zombie.getVitesse()), eq(zombie.getCheminImage()), eq(zombie.getId()));
    }

    @Test
    public void testDeleteZombie() {
        int zombieId = 1;
        when(jdbcTemplate.update(anyString(), eq(zombieId))).thenReturn(1);

        int result = zombieDAO.deleteZombie(zombieId);

        assertEquals(1, result);
        verify(jdbcTemplate, times(1)).update(eq("DELETE FROM zombie WHERE id = ?"), eq(zombieId));
    }

    @Test
    public void testGetZombiesByMapId() {
        int mapId = 1;
        Zombie zombie1 = new Zombie(1, "Zombie1", 100, 1L, "10", 5L, "zombie1.png");
        Zombie zombie2 = new Zombie(2, "Zombie2", 150, 1L, "15", 4L, "zombie2.png");
        List<Zombie> expectedZombies = Arrays.asList(zombie1, zombie2);

        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(mapId))).thenReturn(expectedZombies);

        List<Zombie> actualZombies = zombieDAO.getZombiesByMapId(mapId);

        assertNotNull(actualZombies);
        assertEquals(2, actualZombies.size());
        assertEquals(expectedZombies, actualZombies);
        verify(jdbcTemplate, times(1)).query(eq("SELECT * FROM zombie WHERE id_map = ?"), any(RowMapper.class), eq(mapId));
    }
}