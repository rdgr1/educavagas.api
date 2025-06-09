package org.educavagas.api.repository;

import jakarta.persistence.EntityManager;

public interface UsuarioEscolaRepositoryCustom {
    EntityManager getEntityManager();
}