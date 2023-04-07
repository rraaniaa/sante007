package com.iset.sante.service;


import com.iset.sante.entities.Evenement;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EvenementService {
    List<Evenement> getAllEvenement();
    void saveEvent(Evenement evenement);
    Evenement getEvenementById(long id);
    void deleteEvenementById(long id);
    Page<Evenement> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);


}
