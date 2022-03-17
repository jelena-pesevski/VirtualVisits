package org.unibl.etf.virtualvisits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.virtualvisits.models.entities.MuseumEntity;

import java.util.List;

public interface MuseumEntityRepository extends JpaRepository<MuseumEntity, Integer> {

    List<MuseumEntity> getAllByNameStartingWith(String prefix);

    List<MuseumEntity> getAllByCityStartingWith(String prefix);

}
