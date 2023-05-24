package ams.resource;

import ams.constant.AppConstant;
import ams.model.dto.TraineeListDisplayDto;
import ams.model.entity.Trainee;
import ams.security.SecurityUtil;
import ams.service.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/trainees")
public class TraineeResource {
    private final BaseService traineeService;


    @GetMapping
    public ResponseEntity<Page<TraineeListDisplayDto>> showList(@RequestParam(required = false, defaultValue = AppConstant.DEFAULT_PAGE_STR) Integer page,
                                                                @RequestParam(required = false, defaultValue = AppConstant.DEFAULT_PAGE_SIZE_STR) Integer size,
                                                                @RequestParam(required = false, name = "sort", defaultValue = AppConstant.DEFAULT_SORT_FIELD) List<String> sorts,
                                                                @RequestParam(required = false, name = "q") Optional<String> keywordOpt) {

        List<Sort.Order> orders = new ArrayList<>();
        for (String sortField : sorts) {
            boolean isDesc = sortField.startsWith("-");
            orders.add(isDesc ? Sort.Order.desc(sortField.substring(1)) : Sort.Order.asc(sortField));
        }
        Specification<Trainee> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("deleted"), false);

        if (keywordOpt.isPresent()) {
            Specification<Trainee> specByKeyword = (root, query, criteriaBuilder) ->
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get("account"), "%" + keywordOpt.get() + "%"),
                            criteriaBuilder.like(root.get("fullName"), "%" + keywordOpt.get() + "%"),
                            criteriaBuilder.like(root.get("gender"), "%" + keywordOpt.get() + "%"),
                            criteriaBuilder.like(root.get("university"), "%" + keywordOpt.get() + "%"),
                            criteriaBuilder.like(root.get("phone"), "%" + keywordOpt.get() + "%"),
                            criteriaBuilder.like(root.get("email"), "%" + keywordOpt.get() + "%"),
                            criteriaBuilder.like(root.get("status"), "%" + keywordOpt.get() + "%"),
                            criteriaBuilder.like(root.get("tpbAccount"), "%" + keywordOpt.get() + "%")
                    );
            spec = spec.and(specByKeyword);


        }

        if (!SecurityUtil.isDeliveryManager() && !SecurityUtil.isClassAdmin()) {
            return null;
        }

        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(orders));
        Page<Trainee> traineePage = traineeService.findAll(spec, pageRequest);

        // List<Trainee> -> List<TraineeDisplayDto>
        List<TraineeListDisplayDto> displayDtos = traineePage.getContent().stream()
                .map(trainee -> {
                    TraineeListDisplayDto traineeListDisplayDto = new TraineeListDisplayDto();
                    BeanUtils.copyProperties(trainee, traineeListDisplayDto);

                    return traineeListDisplayDto;
                }).collect(Collectors.toList());

        // Page<Event> -> Page<EventListDisplayDto>
        Page<TraineeListDisplayDto> result = new PageImpl<>(displayDtos, traineePage.getPageable(), traineePage.getTotalElements());

        return ResponseEntity.ok(result);

    }


}
