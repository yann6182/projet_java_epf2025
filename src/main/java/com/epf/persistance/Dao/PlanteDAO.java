package com.epf.persistance.Dao;

import com.epf.persistance.models.Plante;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanteDAO {
    private final JdbcTemplate jdbcTemplate;

    public PlanteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Plante> planteRowMapper = (rs, rowNum) ->
            new Plante(
                    rs.getInt("id_plante"),
                    rs.getString("nom"),
                    rs.getInt("point_de_vie"),
                    rs.getDouble("attaque_par_seconde"),
                    rs.getInt("degat_attaque"),
                    rs.getInt("cout"),
                    rs.getDouble("soleil_par_seconde"),
                    rs.getString("effet"),
                    rs.getString("chemin_image")
            );

    public List<Plante> getAllPlantes() {
        return jdbcTemplate.query("SELECT * FROM plante", planteRowMapper);
    }

    public Plante getPlanteById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM plante WHERE id_plante = ?", planteRowMapper, id);
    }

    public int addPlante(Plante plante) {
        return jdbcTemplate.update("INSERT INTO plante (nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                plante.getNom(), plante.getPointDeVie(), plante.getAttaqueParSeconde(), plante.getDegatAttaque(), plante.getCout(), plante.getSoleilParSeconde(), plante.getEffet(), plante.getCheminImage());
    }

    public int deletePlante(int id) {
        return jdbcTemplate.update("DELETE FROM plante WHERE id_plante = ?", id);
    }
}
