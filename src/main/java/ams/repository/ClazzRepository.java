package ams.repository;

import ams.model.entity.Clazz;

import java.util.Optional;

public interface ClazzRepository extends BaseRepository<Clazz,Long>{

    Optional<Clazz> findByIdAndDeletedFalse(Long id);

}
