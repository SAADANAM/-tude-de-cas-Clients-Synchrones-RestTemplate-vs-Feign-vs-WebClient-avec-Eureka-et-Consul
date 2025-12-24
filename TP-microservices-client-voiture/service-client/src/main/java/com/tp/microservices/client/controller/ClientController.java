package com.tp.microservices.client.controller;

import com.tp.microservices.client.feign.VoitureFeignClient;
import com.tp.microservices.client.model.Client;
import com.tp.microservices.client.model.VoitureDTO;
import com.tp.microservices.client.repository.ClientRepository;
import com.tp.microservices.client.resttemplate.VoitureRestTemplateService;
import com.tp.microservices.client.webclient.VoitureWebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientRepository repository;
    private final VoitureRestTemplateService serviceRestTemplate;
    private final VoitureFeignClient feignClient;
    private final VoitureWebClientService webClientService;

    // ***** Gestion des Clients (CRUD) *****

    @GetMapping
    public List<Client> getAllClients() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return repository.save(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setNom(client.getNom());
                    existing.setPrenom(client.getPrenom());
                    existing.setEmail(client.getEmail());
                    existing.setTelephone(client.getTelephone());
                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        return repository.findById(id)
                .map(client -> {
                    repository.delete(client);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ***** Appels via RestTemplate *****

    @GetMapping("/{id}/voitures/resttemplate")
    public ResponseEntity<Client> getClientWithVoituresRestTemplate(@PathVariable Long id) {
        return repository.findById(id)
                .map(client -> {
                    List<VoitureDTO> voitures = serviceRestTemplate.getVoituresByClientId(id);
                    client.setVoitures(voitures);
                    return ResponseEntity.ok(client);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/voitures/resttemplate")
    public List<VoitureDTO> getAllVoituresRestTemplate() {
        return serviceRestTemplate.getAllVoitures();
    }

    // ==================== Appels avec Feign ====================

    @GetMapping("/{id}/voitures/feign")
    public ResponseEntity<Client> getClientWithVoituresFeign(@PathVariable Long id) {
        return repository.findById(id)
                .map(client -> {
                    List<VoitureDTO> voitures = feignClient.getVoituresByClientId(id);
                    client.setVoitures(voitures);
                    return ResponseEntity.ok(client);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/voitures/feign")
    public List<VoitureDTO> getAllVoituresFeign() {
        return feignClient.getAllVoitures();
    }

    // ==================== Appels avec WebClient ====================

    @GetMapping("/{id}/voitures/webclient")
    public ResponseEntity<Client> getClientWithVoituresWebClient(@PathVariable Long id) {
        return repository.findById(id)
                .map(client -> {
                    List<VoitureDTO> voitures = webClientService.getVoituresByClientId(id);
                    client.setVoitures(voitures);
                    return ResponseEntity.ok(client);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/voitures/webclient")
    public List<VoitureDTO> getAllVoituresWebClient() {
        return webClientService.getAllVoitures();
    }
}
