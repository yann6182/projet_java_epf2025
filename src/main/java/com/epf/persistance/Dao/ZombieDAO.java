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



}
