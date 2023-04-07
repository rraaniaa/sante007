package com.iset.sante.service;


import com.iset.sante.entities.Evenement;
import com.iset.sante.repositories.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EvenementServiceImpl implements EvenementService {

    @Autowired
    private EvenementRepository EvenementRepository;

    @Override
    public List<Evenement> getAllEvenement() {
        return EvenementRepository.findAll();
    }

    @Override
    public void saveEvent(Evenement evenement) {
        this.EvenementRepository.save(evenement);
    }

    @Override
    public Evenement getEvenementById(long id) {
        Optional<Evenement> optional = EvenementRepository.findById(id);
        Evenement evenement = null;
        if (optional.isPresent()) {
            evenement = optional.get();
        } else {
            throw new RuntimeException(" Evenement not found for id :: " + id);
        }
        return evenement;
    }

    @Override
    public void deleteEvenementById(long id) {
        this.EvenementRepository.deleteById(id);
    }

    @Override
    public Page<Evenement> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.EvenementRepository.findAll(pageable);
    }

}
