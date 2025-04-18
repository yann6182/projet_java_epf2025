package com.epf.api;

import com.epf.persistance.dto.ZombieDTO;
import com.epf.persistance.mapper.ZombieMapper;
import com.epf.service.ZombieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/zombies")
@Tag(name = "Zombies", description = "API pour la gestion des zombies")
public class ZombieController {
    private final ZombieService zombieService;

    public ZombieController(ZombieService zombieService) {
        this.zombieService = zombieService;
    }

    @Operation(summary = "Récupérer tous les zombies", description = "Renvoie la liste complète des zombies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des zombies récupérée avec succès"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public List<ZombieDTO> getAllZombies() {
        return zombieService.getAllZombies().stream()
                .map(ZombieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Récupérer un zombie par son ID", description = "Renvoie un zombie spécifique par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Zombie trouvé", content = @Content(schema = @Schema(implementation = ZombieDTO.class))),
            @ApiResponse(responseCode = "404", description = "Zombie non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{id}")
    public ZombieDTO getZombie(
            @Parameter(description = "ID du zombie à récupérer", required = true)
            @PathVariable int id) {
        return ZombieMapper.toDTO(zombieService.getZombie(id));
    }

    @Operation(summary = "Récupérer les zombies par l'ID de la carte", description = "Renvoie tous les zombies associés à une carte spécifique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des zombies récupérée avec succès"),
            @ApiResponse(responseCode = "404", description = "Carte non trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/map/{mapId}")
    public List<ZombieDTO> getZombiesByMapId(
            @Parameter(description = "ID de la carte", required = true)
            @PathVariable int mapId) {
        return zombieService.getZombiesByMapId(mapId).stream()
                .map(ZombieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Ajouter un nouveau zombie", description = "Crée un nouveau zombie dans le système")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Zombie créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<Integer> addZombie(
            @Parameter(description = "Zombie à ajouter", required = true, schema = @Schema(implementation = ZombieDTO.class))
            @Valid @RequestBody ZombieDTO zombieDTO) {
        int id = zombieService.addZombie(ZombieMapper.toEntity(zombieDTO));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @Operation(summary = "Mettre à jour un zombie", description = "Met à jour les informations d'un zombie existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Zombie mis à jour avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "404", description = "Zombie non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateZombie(
            @Parameter(description = "ID du zombie à mettre à jour", required = true)
            @PathVariable int id,
            @Parameter(description = "Informations du zombie mises à jour", required = true, schema = @Schema(implementation = ZombieDTO.class))
            @Valid @RequestBody ZombieDTO zombieDTO) {
        zombieDTO.setId_zombie(id);
        int result = zombieService.updateZombie(ZombieMapper.toEntity(zombieDTO));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "Supprimer un zombie", description = "Supprime un zombie existant du système")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Zombie supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Zombie non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteZombie(
            @Parameter(description = "ID du zombie à supprimer", required = true)
            @PathVariable int id) {
        int result = zombieService.deleteZombie(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}