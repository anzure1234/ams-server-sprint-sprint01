package ams.resource;

import ams.constant.AppConstant;
import ams.model.dto.ClazzDisplayDto;
import ams.model.dto.ClazzDto;
import ams.model.entity.Clazz;

import ams.service.ClazzService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/class")
public class ClazzResource {
    private final ClazzService clazzService;

    public ClazzResource(ClazzService clazzService) {
        this.clazzService = clazzService;
    }


    @PostMapping("/create")
    public ResponseEntity<ClazzDisplayDto>create(@RequestBody @Valid ClazzDto clazzDto){
        Clazz clazz = new Clazz();
        BeanUtils.copyProperties(clazzDto,clazz);
        clazzService.create(clazz);
        ClazzDisplayDto clazzDisplayDto = new ClazzDisplayDto();
        BeanUtils.copyProperties(clazz, clazzDisplayDto);
        return ResponseEntity.ok(clazzDisplayDto);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClazzDisplayDto> update(@PathVariable("id") Long id, @RequestBody @Valid ClazzDto clazzDto) {
        Optional<Clazz> clazz = clazzService.findOneOpt(id);
        if (clazz.isPresent()) {
            BeanUtils.copyProperties(clazzDto, clazz.get());
            clazzService.update(clazz.get());
            ClazzDisplayDto clazzDisplayDto = new ClazzDisplayDto();
            BeanUtils.copyProperties(clazz.get(), clazzDisplayDto);
            return ResponseEntity.ok(clazzDisplayDto);
        }
        return ResponseEntity.notFound().build();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        Optional<Clazz> clazz = clazzService.findOneOpt(id);
        if (clazz.isPresent()) {
            clazzService.delete(id);
        }

    }

    @GetMapping
    public ResponseEntity<Page<ClazzDisplayDto>> show(
            @RequestParam(required = false, defaultValue = AppConstant.DEFAULT_PAGE_STR) Integer page,
            @RequestParam(required = false, defaultValue = AppConstant.DEFAULT_PAGE_SIZE_STR) Integer size,
            @RequestParam(required = false, name = "sort",defaultValue = AppConstant.DEFAULT_SORT_FIELD) List<String> sorts,
            @RequestParam(required = false, name = "search") Optional<String> keywordOpt) {

        List<Sort.Order> orders = new ArrayList<>();
        for (String sortField : sorts) {
            boolean isDesc = sortField.startsWith("-");
            orders.add(isDesc ? Sort.Order.desc(sortField.substring(1)) : Sort.Order.asc(sortField));
        }

        Specification<Clazz> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("deleted"), false);
        if (keywordOpt.isPresent()) {
            Specification<Clazz> specByKeyWord = (root, query, criteriaBuilder) ->
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get("classCode"), "%" + keywordOpt.get() + "%"),
                            criteriaBuilder.like(root.get("className"), "%" + keywordOpt.get() + "%")
                    );
            spec = spec.and(specByKeyWord);

        }

        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(orders));
        Page<Clazz> classPage = clazzService.findAll(spec, pageRequest);
        List<ClazzDisplayDto> clazzDisplayDtos = classPage.getContent().stream().map(clazz -> {
            ClazzDisplayDto clazzDisplayDto = new ClazzDisplayDto();
            BeanUtils.copyProperties(clazz, clazzDisplayDto);
            return clazzDisplayDto;
        }).collect(Collectors.toList());

        Page<ClazzDisplayDto> result = new PageImpl<>(clazzDisplayDtos, pageRequest, classPage.getTotalElements());
        return ResponseEntity.ok(result);

    }


}
