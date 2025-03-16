package com.epf.service;

import com.epf.persistance.Dao.MapDAO;
import com.epf.persistance.models.Map;
import com.epf.persistance.models.Zombie;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MapService {
    private final MapDAO mapDAO;
    public MapService(MapDAO mapDAO) {
        this.mapDAO = mapDAO;
    }
    public List<Map> getAllMaps(){
        return mapDAO.getAllMaps();
    }


}
