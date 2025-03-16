package com.epf.persistance.Dao;

import com.epf.persistance.models.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MapDAO {
    private final JdbcTemplate jdbcTemplate;

    public MapDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Map> mapRowMapper = (rs, rowNum) ->
            new Map(
                    rs.getInt("id_map"),
                    rs.getInt("ligne"),
                    rs.getInt("colonne"),
                    rs.getString("chemin_image")
            );

    public List<Map> getAllMaps() {
        return jdbcTemplate.query("SELECT * FROM map", mapRowMapper);
    }

    public Map getMapById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM map WHERE id_map = ?", mapRowMapper, id);
    }

    public int addMap(Map map) {
        return jdbcTemplate.update("INSERT INTO map (ligne, colonne, chemin_image) VALUES (?, ?, ?)",
                map.getLigne(), map.getColonne(), map.getCheminImage());
    }

    public int deleteMap(int id) {
        return jdbcTemplate.update("DELETE FROM map WHERE id_map = ?", id);
    }
}
