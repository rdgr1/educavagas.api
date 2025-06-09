package org.educavagas.api.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioEscolaRepositoryImpl implements UsuarioEscolaRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}