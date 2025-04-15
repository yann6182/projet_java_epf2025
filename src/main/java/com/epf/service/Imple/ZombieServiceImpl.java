package com.epf.service.Imple;

import com.epf.persistance.Dao.ZombieDAO;
import com.epf.persistance.models.Zombie;
import com.epf.service.ZombieService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ZombieServiceImpl implements ZombieService {
    private final ZombieDAO zombieDAO;

    public ZombieServiceImpl(ZombieDAO zombieDAO) {
        this.zombieDAO = zombieDAO;
    }

    @Override
    public List<Zombie> getAllZombies() {
        return zombieDAO.getAllZombies();
    }

    @Override
    public Zombie getZombie(int id) {
        return zombieDAO.getZombie(id);
    }

    @Override
    public List<Zombie> getZombiesByMapId(int mapId) {
        return zombieDAO.getZombiesByMapId(mapId);
    }

    @Override
    public int addZombie(Zombie zombie) {
        return zombieDAO.addZombie(zombie);
    }

    @Override
    public int updateZombie(Zombie zombie) {
        return zombieDAO.updateZombie(zombie);
    }

    @Override
    public int deleteZombie(int id) {
        return zombieDAO.deleteZombie(id);
    }
}
