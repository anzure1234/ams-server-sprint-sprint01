package ams.model.entity;

import ams.enums.AllowanceGroup;
import ams.enums.TraineeClassStatus;
import ams.enums.Gender;
import ams.enums.TraineeStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class Trainee extends BaseEntity {
//    @NotNull
    private String account;

//    @NotNull
    private String fullName;

//    @NotNull
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Pattern(regexp = "^(\\+84|0)\\d{9,10}$")
    private String phone;

    @Email
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private TraineeStatus traineeStatus;

    @Enumerated(EnumType.STRING)
    private TraineeClassStatus traineeClassStatus;

    private String tpbAccount;

    private Boolean salary = false;

    private String commitment;

    private AllowanceGroup allowanceGroup;

    @OneToOne
    private Faculty faculty;

    @OneToOne
    private University university;

    @OneToMany(mappedBy = "trainee")
    private Set<ClassTrainee> classTraineeSet;
}
