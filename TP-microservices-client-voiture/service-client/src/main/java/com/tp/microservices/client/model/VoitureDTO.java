package com.tp.microservices.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoitureDTO {

    // Objet de Transfert de Données pour Voiture

    private Long id;
    private String marque;
    private String modele;
    private String immatriculation;
    private Integer annee;
    private Double prix;
    private Long clientId;
}
