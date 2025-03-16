package com.epf.api;
import com.epf.persistance.models.Zombie;
import com.epf.service.ZombieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/zombies")
public class ZombieController {
    public final ZombieService zombieService;

    public ZombieController(ZombieService zombieService) {
        this.zombieService = zombieService;
    }
    @GetMapping
    public List<Zombie> getAllZombies(){
        return zombieService.getAllZombies();
    }
    @GetMapping("/{id}")
    public Zombie getZombie(int id) {
        return zombieService.getZombie(id);
    }
}
