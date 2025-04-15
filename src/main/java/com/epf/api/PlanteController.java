package com.epf.api;

    import com.epf.persistance.dto.PlanteDTO;
    import com.epf.persistance.mapper.PlanteMapper;
    import com.epf.service.PlanteService;
    import org.springframework.web.bind.annotation.*;

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
        public int addPlante(@RequestBody PlanteDTO planteDTO) {
            return planteService.addPlante(PlanteMapper.toPlante(planteDTO));
        }

        @PutMapping("/{id}")
        public int updatePlante(@PathVariable int id, @RequestBody PlanteDTO planteDTO) {
            planteDTO.setIdPlante(id);
            return planteService.updatePlante(PlanteMapper.toPlante(planteDTO));
        }

        @DeleteMapping("/{id}")
        public int deletePlante(@PathVariable int id) {
            return planteService.deletePlante(id);
        }
    }