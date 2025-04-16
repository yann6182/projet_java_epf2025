package com.epf.persistance.Dao;

import com.epf.persistance.models.Zombie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ZombieDAO {
    private final JdbcTemplate jdbcTemplate;

    public ZombieDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private RowMapper<Zombie> zombieRowMapper = (rs, rowNum) ->
            new Zombie(rs.getInt("id_zombie"), rs.getString("nom"), rs.getInt("point_de_vie"), rs.getLong("attaque_par_seconde"), rs.getString("degat_attaque"),rs.getLong("vitesse_de_deplacement"),rs.getString("chemin_image"));



    public List<Zombie> getAllZombies() {
        return jdbcTemplate.query("SELECT * FROM zombie", zombieRowMapper);
    }

    public Zombie getZombie(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM zombie WHERE id_zombie = ?", zombieRowMapper, id);
    }
    public int addZombie(Zombie zombie) {
        return jdbcTemplate.update(
                "INSERT INTO zombie (nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse_de_deplacement, chemin_image) VALUES (?, ?, ?, ?, ?, ?)",
                zombie.getNom(), zombie.getPoint_de_vie(), zombie.getAttaque_par_seconde(), zombie.getDegat_attaque(), zombie.getVitesse(), zombie.getCheminImage()
        );
    }

    public int updateZombie(Zombie zombie) {
        return jdbcTemplate.update(
                "UPDATE zombie SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, vitesse_de_deplacement = ?, chemin_image = ? WHERE id_zombie = ?",
                zombie.getNom(), zombie.getPoint_de_vie(), zombie.getAttaque_par_seconde(), zombie.getDegat_attaque(), zombie.getVitesse(), zombie.getCheminImage(), zombie.getId()
        );
    }

    public int deleteZombie(int id) {
        return jdbcTemplate.update("DELETE FROM zombie WHERE id_zombie = ?", id);
    }

    public List<Zombie> getZombiesByMapId(int mapId) {
        return jdbcTemplate.query("SELECT * FROM zombie WHERE id_map = ?", zombieRowMapper, mapId);
    }



}
