package com.epf.service;

import com.epf.persistance.Dao.ZombieDAO;
import com.epf.persistance.models.Zombie;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ZombieService {
    private final ZombieDAO zombieDAO;

    public ZombieService(ZombieDAO zombieDAO) {
        this.zombieDAO = zombieDAO;
    }
    public List<Zombie> getAllZombies(){
        return zombieDAO.getAllZombies();
    }

    public Zombie getZombie(int id){
        return zombieDAO.getZombie(id);
    }
}
