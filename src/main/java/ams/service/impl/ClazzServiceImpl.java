package ams.service.impl;

import ams.exception.ResourceNotFoundException;
import ams.model.entity.Clazz;
import ams.repository.ClazzRepository;
import ams.repository.CommonSpecifications;
import ams.service.ClazzService;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClazzServiceImpl implements ClazzService {

    private final ClazzRepository clazzRepository;


    public ClazzServiceImpl(ClazzRepository clazzRepository) {
        this.clazzRepository = clazzRepository;
    }


    @Override
    public Clazz createOrUpdate(Clazz model) {
        return null;
    }

    @Override
    public Clazz create(Clazz model) {
        model.setDeleted(false);
        model.setCreatedDate(LocalDate.now());
        model.setLastModifiedDate(LocalDate.now());
        return clazzRepository.save(model);
    }

    @Override
    public Clazz update(Clazz model) {
        model.setLastModifiedDate(LocalDate.now());
        return clazzRepository.save(model);
    }

    @Override
    public List<Clazz> createOrUpdate(List<Clazz> models) {
        return null;
    }

    @Override
    public void delete(Long aLong) {
        Optional<Clazz> clazzOpt = clazzRepository.findByIdAndDeletedFalse(aLong);
        Clazz model = clazzOpt.orElseThrow(ResourceNotFoundException::new);
        model.setDeleted(true);
        clazzRepository.save(model);

    }

    @Override
    public Clazz findOne(Long aLong) {
        return null;
    }

    @Override
    public Optional<Clazz> findOneOpt(Long aLong) {
        return clazzRepository.findById(aLong);
    }

    @Override
    public List<Clazz> findAll() {

        return null;
    }

    @Override
    public Page<Clazz> findAll(@Nullable Specification<Clazz> spec, Pageable page) {
        return clazzRepository.findAll(spec, page);
    }

    @Override
    public Page<Clazz> findUndeletedAll(Pageable page) {
        CommonSpecifications<Clazz> spec = new CommonSpecifications<>();
        Specification<Clazz> undeletedSpec = spec.unDeleted();
        return clazzRepository.findAll(undeletedSpec, page);
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }
}
