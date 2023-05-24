package ams.model.dto;

import ams.enums.BudgetCode;
import ams.enums.ClassStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClazzDto extends BaseResponseDto{
    private String className;

    private ClassStatus classStatus;

    private Integer plannedTraineeNo;

    private Integer acceptedTraineeNo;//validate acceptedTraineeNo <= plannedTraineeNo

    private Integer actualTraineeNo;// validate actualTraineeNo <= acceptedTraineeNo

    private LocalDate expectedStartDate;//can validate expectedStartDate <= expectedEndDate

    private LocalDate expectedEndDate;

    private String location;

    private String detailLocation;


    private BudgetCode budgetCode;

    private Double estimatedBudget;

    private String classAdmin;

    private String learningPath;

    private String history;

}
