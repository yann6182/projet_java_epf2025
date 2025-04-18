package com.epf.service;

import com.epf.persistance.models.Zombie;


import java.util.List;

public interface ZombieService {
    public List<Zombie> getAllZombies();

    public Zombie getZombie(int id);
    public List<Zombie> getZombiesByMapId(int mapId) ;
    public int addZombie(Zombie zombie) ;
    public int updateZombie(Zombie zombie) ;
    public int deleteZombie(int id) ;
}
