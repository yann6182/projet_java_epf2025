package com.epf.api;

        import com.epf.persistance.dto.ZombieDTO;
        import com.epf.persistance.mapper.ZombieMapper;
        import com.epf.service.ZombieService;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.stream.Collectors;

        @RestController
        @RequestMapping("/zombies")
        public class ZombieController {
            private final ZombieService zombieService;

            public ZombieController(ZombieService zombieService) {
                this.zombieService = zombieService;
            }

            @GetMapping
            public List<ZombieDTO> getAllZombies() {
                return zombieService.getAllZombies().stream()
                        .map(ZombieMapper::toDTO)
                        .collect(Collectors.toList());
            }

            @GetMapping("/{id}")
            public ZombieDTO getZombie(@PathVariable int id) {
                return ZombieMapper.toDTO(zombieService.getZombie(id));
            }

            @GetMapping("/map/{mapId}")
            public List<ZombieDTO> getZombiesByMapId(@PathVariable int mapId) {
                return zombieService.getZombiesByMapId(mapId).stream()
                        .map(ZombieMapper::toDTO)
                        .collect(Collectors.toList());
            }

            @PostMapping
            public int addZombie(@RequestBody ZombieDTO zombieDTO) {
                return zombieService.addZombie(ZombieMapper.toEntity(zombieDTO));
            }

            @PutMapping("/{id}")
            public int updateZombie(@PathVariable int id, @RequestBody ZombieDTO zombieDTO) {
                zombieDTO.setId_zombie(id);
                return zombieService.updateZombie(ZombieMapper.toEntity(zombieDTO));
            }

            @DeleteMapping("/{id}")
            public int deleteZombie(@PathVariable int id) {
                return zombieService.deleteZombie(id);
            }
        }