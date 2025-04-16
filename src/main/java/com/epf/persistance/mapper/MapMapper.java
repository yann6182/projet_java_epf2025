package com.epf.persistance.mapper;

public class MapMapper {
    public static com.epf.persistance.models.Map mapToModel(com.epf.persistance.dto.MapDTO mapDTO) {
        com.epf.persistance.models.Map map = new com.epf.persistance.models.Map();
        map.setIdMap(mapDTO.getId_map());
        map.setLigne(mapDTO.getLigne());
        map.setColonne(mapDTO.getColonne());
        map.setCheminImage(mapDTO.getChemin_image());
        return map;
    }

    public static com.epf.persistance.dto.MapDTO mapToDto(com.epf.persistance.models.Map map) {
        com.epf.persistance.dto.MapDTO mapDTO = new com.epf.persistance.dto.MapDTO();
        mapDTO.setId_map(map.getIdMap());
        mapDTO.setLigne(map.getLigne());
        mapDTO.setColonne(map.getColonne());
        mapDTO.setChemin_image(map.getCheminImage());
        return mapDTO;
    }
}