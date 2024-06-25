package com.example.travelagency.service;

import com.example.travelagency.model.Destino;
import com.example.travelagency.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinoService {
    @Autowired
    private DestinoRepository destinoRepository;

    public Destino save(Destino destino) {
        return destinoRepository.save(destino);
    }

    public List<Destino> findAll() {
        return destinoRepository.findAll();
    }

    public Optional<Destino> findById(Long id) {
        return destinoRepository.findById(id);
    }

    public List<Destino> search(String termo) {
        return destinoRepository.findByNomeContainingOrLocalizacaoContaining(termo, termo);
    }

    public void deleteById(Long id) {
        destinoRepository.deleteById(id);
    }

    public Destino avaliarDestino(Long id, int nota) {
        Destino destino = destinoRepository.findById(id).orElseThrow(() -> new RuntimeException("Destino não encontrado"));
        double totalNotas = destino.getMediaAvaliacao() * destino.getNumeroAvaliacoes();
        totalNotas += nota;
        destino.setNumeroAvaliacoes(destino.getNumeroAvaliacoes() + 1);
        destino.setMediaAvaliacao(totalNotas / destino.getNumeroAvaliacoes());
        return destinoRepository.save(destino);
    }
}
