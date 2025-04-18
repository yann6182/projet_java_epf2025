package com.epf.test.ZombieTest;

        import com.epf.persistance.dto.ZombieDTO;
        import com.epf.persistance.mapper.ZombieMapper;
        import com.epf.persistance.models.Zombie;
        import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.*;

        public class ZombieMapperTest {

            @Test
            public void testToDTO() {
                Zombie zombie = new Zombie(1, "Test Zombie", 100, 1L, "10", 5L, "zombie.png");

                ZombieDTO dto = ZombieMapper.toDTO(zombie);

                assertNotNull(dto);
                assertEquals(1, dto.getId_zombie());
                assertEquals("Test Zombie", dto.getNom());
                assertEquals(100, dto.getPoint_de_vie());
                assertEquals(1, dto.getAttaque_par_seconde());
                assertEquals(10, dto.getDegat_attaque());
                assertEquals(5, dto.getVitesse_de_deplacement());
                assertEquals("zombie.png", dto.getChemin_image());
            }

            @Test
            public void testToEntity() {
                ZombieDTO dto = new ZombieDTO();
                dto.setId_zombie(1);
                dto.setNom("Test Zombie");
                dto.setPoint_de_vie(100);
                dto.setAttaque_par_seconde(1);
                dto.setDegat_attaque(10);
                dto.setVitesse_de_deplacement(5);
                dto.setChemin_image("zombie.png");

                Zombie zombie = ZombieMapper.toEntity(dto);

                assertNotNull(zombie);
                assertEquals(1, zombie.getId());
                assertEquals("Test Zombie", zombie.getNom());
                assertEquals(100, zombie.getPoint_de_vie());
                assertEquals(1L, zombie.getAttaque_par_seconde());
                assertEquals("10", zombie.getDegat_attaque());
                assertEquals(5L, zombie.getVitesse());
                assertEquals("zombie.png", zombie.getCheminImage());
            }

            @Test
            public void testNullHandling() {
                assertNull(ZombieMapper.toDTO(null));
                assertNull(ZombieMapper.toEntity(null));
            }
        }