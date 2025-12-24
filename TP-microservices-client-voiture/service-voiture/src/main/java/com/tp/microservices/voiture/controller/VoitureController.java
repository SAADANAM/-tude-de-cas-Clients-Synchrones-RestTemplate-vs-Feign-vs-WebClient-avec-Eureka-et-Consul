package com.tp.microservices.voiture.controller;

import com.tp.microservices.voiture.model.Voiture;
import com.tp.microservices.voiture.repository.VoitureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voitures")
@RequiredArgsConstructor
public class VoitureController {

    private final VoitureRepository repository;

    @GetMapping
    public List<Voiture> getAllVoitures() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voiture> getVoitureById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    public List<Voiture> getVoituresByClientId(@PathVariable Long clientId) {
        return repository.findByClientId(clientId);
    }

    @PostMapping
    public Voiture createVoiture(@RequestBody Voiture voiture) {
        return repository.save(voiture);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voiture> updateVoiture(@PathVariable Long id, @RequestBody Voiture voiture) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setMarque(voiture.getMarque());
                    existing.setModele(voiture.getModele());
                    existing.setImmatriculation(voiture.getImmatriculation());
                    existing.setAnnee(voiture.getAnnee());
                    existing.setPrix(voiture.getPrix());
                    existing.setClientId(voiture.getClientId());
                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoiture(@PathVariable Long id) {
        return repository.findById(id)
                .map(voiture -> {
                    repository.delete(voiture);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
