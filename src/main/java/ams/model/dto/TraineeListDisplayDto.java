package ams.model.dto;


import ams.enums.AllowanceGroup;
import ams.enums.Gender;
import ams.enums.TraineeStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TraineeListDisplayDto {

    private Long id;

    private String account;

    private String fullName;

    private LocalDate dob;

    private Gender gender;

    private String university;

    private String faculty;

    private String phone;

    private String email;

    private String status;

}
