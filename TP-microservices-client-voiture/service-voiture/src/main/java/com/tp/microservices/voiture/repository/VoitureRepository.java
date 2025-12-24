package com.tp.microservices.voiture.repository;

import com.tp.microservices.voiture.model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long> {

    // Récupérer les voitures d'un client
    List<Voiture> findByClientId(Long clientId);

    // Récupérer les voitures par marque
    List<Voiture> findByMarque(String marque);
}
