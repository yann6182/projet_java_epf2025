package com.epf.service;

import com.epf.persistance.models.Map;

import java.util.List;

public interface MapService {
    public List<Map> getAllMaps();
    public Map getMapById(int id) ;
    public int addMap(Map map) ;
    public int updateMap(Map map);
    public int deleteMap(int id) ;

}
