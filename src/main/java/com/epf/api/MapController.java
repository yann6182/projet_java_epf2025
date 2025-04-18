package com.epf.api;

import com.epf.persistance.dto.MapDTO;
import com.epf.persistance.mapper.MapMapper;
import com.epf.service.MapService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
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
    public ResponseEntity<Integer> addMap(@Valid @RequestBody MapDTO mapDTO) {
        int id = mapService.addMap(MapMapper.mapToModel(mapDTO));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateMap(@PathVariable int id, @Valid @RequestBody MapDTO mapDTO) {
        mapDTO.setId_map(id);
        int result = mapService.updateMap(MapMapper.mapToModel(mapDTO));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteMap(@PathVariable int id) {
        int result = mapService.deleteMap(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}