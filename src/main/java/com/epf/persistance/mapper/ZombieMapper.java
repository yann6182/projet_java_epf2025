package com.epf.persistance.mapper;

        import com.epf.persistance.dto.ZombieDTO;
        import com.epf.persistance.models.Zombie;

        public class ZombieMapper {

            public static ZombieDTO toDTO(Zombie zombie) {
                if (zombie == null) return null;

                ZombieDTO dto = new ZombieDTO();
                dto.setId_zombie(zombie.getId());
                dto.setNom(zombie.getNom());
                dto.setPoint_de_vie(zombie.getPoint_de_vie());
                dto.setAttaque_par_seconde(zombie.getAttaque_par_seconde() != null ? zombie.getAttaque_par_seconde() : 0);
                dto.setDegat_attaque(zombie.getDegat_attaque() != null ? Integer.parseInt(zombie.getDegat_attaque()) : 0);
                dto.setVitesse_de_deplacement(zombie.getVitesse() != null ? zombie.getVitesse() : 0);
                dto.setChemin_image(zombie.getCheminImage());
                // Note: id_map is not set here as it's not available in the Zombie entity

                return dto;
            }

            public static Zombie toEntity(ZombieDTO dto) {
                if (dto == null) return null;

                Zombie zombie = new Zombie();
                zombie.setId(dto.getId_zombie());
                zombie.setNom(dto.getNom());
                zombie.setPoint_de_vie(dto.getPoint_de_vie());
                zombie.setAttaque_par_seconde(dto.getAttaque_par_seconde() != 0 ? (long)dto.getAttaque_par_seconde() : null);
                zombie.setDegat_attaque(String.valueOf(dto.getDegat_attaque()));
                zombie.setVitesse(dto.getVitesse_de_deplacement() != 0 ? (long)dto.getVitesse_de_deplacement() : null);
                zombie.setCheminImage(dto.getChemin_image());
                // Note: id_map from DTO is not mapped to entity as there's no corresponding field

                return zombie;
            }
        }