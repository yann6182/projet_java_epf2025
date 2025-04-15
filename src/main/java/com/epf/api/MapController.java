package com.epf.api;

        import com.epf.persistance.dto.MapDTO;
        import com.epf.persistance.mapper.MapMapper;
        import com.epf.service.MapService;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.stream.Collectors;

        @RestController
        @RequestMapping("/maps")
        public class MapController {
            private final MapService mapService;

            public MapController(MapService mapService) {
                this.mapService = mapService;
            }

            @GetMapping
            public List<MapDTO> getAllMaps() {
                return mapService.getAllMaps().stream()
                        .map(MapMapper::mapToDto)
                        .collect(Collectors.toList());
            }

            @GetMapping("/{id}")
            public MapDTO getMapById(@PathVariable int id) {
                return MapMapper.mapToDto(mapService.getMapById(id));
            }

            @PostMapping
            public int addMap(@RequestBody MapDTO mapDTO) {
                return mapService.addMap(MapMapper.mapToModel(mapDTO));
            }

            @PutMapping("/{id}")
            public int updateMap(@PathVariable int id, @RequestBody MapDTO mapDTO) {
                mapDTO.setIdMap(id);
                return mapService.updateMap(MapMapper.mapToModel(mapDTO));
            }

            @DeleteMapping("/{id}")
            public int deleteMap(@PathVariable int id) {
                return mapService.deleteMap(id);
            }
        }