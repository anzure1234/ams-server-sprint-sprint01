package ams.model.entity;

import ams.enums.OverBudget;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ClassBudget extends BaseEntity{
    @Column
    private String item;

    @Column
    private String unit;

    @Column
    private Double unit_expense;

    @Column
    private Integer quantity;

    @Column
    private Double amount;

    @Column
    private Double tax;

//    @Column
//    private Double sum;

    @Column
    private String note;

    @Column
    private OverBudget overBudget;

    @ManyToOne
    private Clazz aClazz;


}
