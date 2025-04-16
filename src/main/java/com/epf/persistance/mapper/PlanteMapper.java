package com.epf.persistance.mapper;

                import com.epf.persistance.dto.PlanteDTO;
                import com.epf.persistance.models.Plante;

                public class PlanteMapper {
                    public static PlanteDTO toPlanteDTO(Plante plante) {
                        if (plante == null) return null;
                        PlanteDTO planteDTO = new PlanteDTO();
                        planteDTO.setId_plante(plante.getIdPlante());
                        planteDTO.setNom(plante.getNom());
                        planteDTO.setChemin_image(plante.getCheminImage());
                        planteDTO.setPoint_de_vie(plante.getPointDeVie());
                        planteDTO.setAttaque_par_seconde(plante.getAttaqueParSeconde());
                        planteDTO.setDegat_attaque(plante.getDegatAttaque());
                        planteDTO.setCout(plante.getCout());
                        planteDTO.setSoleil_par_seconde(plante.getSoleilParSeconde());
                        planteDTO.setEffet(plante.getEffet());
                        return planteDTO;
                    }

                    public static Plante toPlante(PlanteDTO planteDTO) {
                        if (planteDTO == null) return null;
                        Plante plante = new Plante();
                        plante.setIdPlante(planteDTO.getId_plante());
                        plante.setNom(planteDTO.getNom());
                        plante.setCheminImage(planteDTO.getChemin_image());
                        plante.setPointDeVie(planteDTO.getPoint_de_vie());
                        plante.setAttaqueParSeconde(planteDTO.getAttaque_par_seconde());
                        plante.setDegatAttaque(planteDTO.getDegat_attaque());
                        plante.setCout(planteDTO.getCout());
                        plante.setSoleilParSeconde(planteDTO.getSoleil_par_seconde());
                        plante.setEffet(planteDTO.getEffet());
                        return plante;
                    }
                }