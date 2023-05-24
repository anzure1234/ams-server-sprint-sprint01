package ams.model.entity;

import ams.enums.BudgetCode;
import ams.enums.ClassStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class Clazz extends BaseEntity{

    private String className;
    @Enumerated(EnumType.STRING)
    private ClassStatus classStatus;

    private Integer plannedTraineeNo;

    private Integer acceptedTraineeNo;//validate acceptedTraineeNo <= plannedTraineeNo

    private Integer actualTraineeNo;// validate actualTraineeNo <= acceptedTraineeNo

    private LocalDate expectedStartDate;//can validate expectedStartDate <= expectedEndDate

    private LocalDate expectedEndDate;

    private String location;

    private String detailLocation;

    @Enumerated(EnumType.STRING)
    private BudgetCode budgetCode;

    private Double estimatedBudget;

    private String classAdmin;

    private String learningPath;

    private String history;

    @OneToMany(mappedBy = "aClazz")
    private Set<ClassBudget> classBudgets;

    @OneToMany(mappedBy = "aClazz")
    private Set<ClassAudit> classAudits;

    @OneToOne(mappedBy = "aClazz")
    @PrimaryKeyJoinColumn
    private ClassDetail classDetail;

    @OneToMany(mappedBy = "clazz")
    private Set<ClassTrainee> classTraineeSet;













}
