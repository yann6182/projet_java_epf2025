package com.epf.persistance.mapper;

public class MapMapper {
    public static com.epf.persistance.models.Map mapToModel(com.epf.persistance.dto.MapDTO mapDTO) {
        com.epf.persistance.models.Map map = new com.epf.persistance.models.Map();
        map.setIdMap(mapDTO.getIdMap());
        map.setLigne(mapDTO.getLigne());
        map.setColonne(mapDTO.getColonne());
        map.setCheminImage(mapDTO.getCheminImage());
        return map;
    }

    public static com.epf.persistance.dto.MapDTO mapToDto(com.epf.persistance.models.Map map) {
        com.epf.persistance.dto.MapDTO mapDTO = new com.epf.persistance.dto.MapDTO();
        mapDTO.setIdMap(map.getIdMap());
        mapDTO.setLigne(map.getLigne());
        mapDTO.setColonne(map.getColonne());
        mapDTO.setCheminImage(map.getCheminImage());
        return mapDTO;
    }

}
