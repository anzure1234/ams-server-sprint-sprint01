package ams.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Faculty extends BaseEntity {
    @NotNull
    private String name;

    @OneToOne(mappedBy = "faculty")
    private Trainee trainee;
}
