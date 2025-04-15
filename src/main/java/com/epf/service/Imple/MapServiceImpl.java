package com.epf.service.Imple;


import com.epf.persistance.Dao.MapDAO;
import com.epf.persistance.Dao.ZombieDAO;
import com.epf.persistance.models.Map;
import com.epf.persistance.models.Zombie;
import com.epf.service.MapService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapServiceImpl implements MapService {
    private final MapDAO mapDAO;
    private final ZombieDAO zombieDAO;
    private final JdbcTemplate jdbcTemplate;

    public MapServiceImpl(MapDAO mapDAO, JdbcTemplate jdbcTemplate, ZombieDAO zombieDAO) {
        this.mapDAO = mapDAO;
        this.jdbcTemplate = jdbcTemplate;
        this.zombieDAO = zombieDAO;
    }

    @Override
    public List<Map> getAllMaps() {
        return mapDAO.getAllMaps();
    }

    @Override
    public Map getMapById(int id) {
        return mapDAO.getMapById(id);

    }

    @Override
    public int addMap(Map map) {
        return mapDAO.addMap(map);
    }

    @Override
    public int updateMap(Map map) {
        return mapDAO.updateMap(map);
    }

    @Override
    public int deleteMap(int idMap) {
        List<Zombie> zombies = zombieDAO.getZombiesByMapId(idMap);
        for (Zombie zombie : zombies) {
            zombieDAO.deleteZombie(zombie.getId());
        }
        return mapDAO.deleteMap(idMap);
    }
}
