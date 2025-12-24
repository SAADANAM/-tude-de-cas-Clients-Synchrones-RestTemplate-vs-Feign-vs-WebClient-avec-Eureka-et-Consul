package com.tp.microservices.voiture.config;

import com.tp.microservices.voiture.model.Voiture;
import com.tp.microservices.voiture.repository.VoitureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final VoitureRepository repository;

    @Override
    public void run(String... arguments) {
        // Initialisation des données de test pour les voitures
        repository.save(new Voiture(null, "Renault", "Clio", "AB-123-CD", 2020, 15000.0, 1L));
        repository.save(new Voiture(null, "Peugeot", "308", "EF-456-GH", 2021, 22000.0, 1L));
        repository.save(new Voiture(null, "Citroen", "C3", "IJ-789-KL", 2019, 12000.0, 2L));
        repository.save(new Voiture(null, "Volkswagen", "Golf", "MN-012-OP", 2022, 28000.0, 2L));
        repository.save(new Voiture(null, "BMW", "Serie 3", "QR-345-ST", 2023, 45000.0, 3L));
    }
}
