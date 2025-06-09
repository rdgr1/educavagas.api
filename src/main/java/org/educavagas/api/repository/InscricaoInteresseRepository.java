package org.educavagas.api.repository;

import org.educavagas.api.model.InscricaoInteresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InscricaoInteresseRepository extends JpaRepository<InscricaoInteresse, UUID> {
}
