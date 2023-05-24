package ams.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class University extends BaseEntity {
    @NotNull
    @Column(unique = true)
    private String name;

    @OneToOne(mappedBy = "university")
    private Trainee trainee;
}
