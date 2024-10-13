package sn.sae.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.sae.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}

