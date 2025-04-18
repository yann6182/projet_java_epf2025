package com.epf.api;

import com.epf.persistance.dto.PlanteDTO;
import com.epf.persistance.mapper.PlanteMapper;
import com.epf.service.PlanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plantes")
public class PlanteController {
    private final PlanteService planteService;

    public PlanteController(PlanteService planteService) {
        this.planteService = planteService;
    }

    @GetMapping
    public List<PlanteDTO> getAllPlantes() {
        return planteService.getAllPlantes().stream()
                .map(PlanteMapper::toPlanteDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PlanteDTO getPlanteById(@PathVariable int id) {
        return PlanteMapper.toPlanteDTO(planteService.getPlanteById(id));
    }

    @PostMapping
    public ResponseEntity<Integer> addPlante(@Valid @RequestBody PlanteDTO planteDTO) {
        int id = planteService.addPlante(PlanteMapper.toPlante(planteDTO));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updatePlante(@PathVariable int id, @Valid @RequestBody PlanteDTO planteDTO) {
        planteDTO.setId_plante(id);
        int result = planteService.updatePlante(PlanteMapper.toPlante(planteDTO));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deletePlante(@PathVariable int id) {
        int result = planteService.deletePlante(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}