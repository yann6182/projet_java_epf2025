package com.epf.api;
import com.epf.persistance.models.Map;
import com.epf.persistance.models.Zombie;
import com.epf.service.MapService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/maps")
public class MapController {
    public final MapService mapService;

    public MapController (MapService mapService) {
        this.mapService = mapService;
    }
    @GetMapping
    public List<Map> getAllMaps(){
        return mapService.getAllMaps();
    }

}
