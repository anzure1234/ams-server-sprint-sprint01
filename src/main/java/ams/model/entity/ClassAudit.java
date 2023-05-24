package ams.model.entity;

import ams.enums.EventCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class ClassAudit extends BaseEntity{
    @Column
    private LocalDate auditDate;//validate auditDate < deadline

    @Column
    private EventCategory event_category;

    @Column
    private String relatedPeople;

    @Column
    private String action;

    @Column
    private String pic;

    @Column
    private LocalDate deadline;

    @Column
    private String note;

    @ManyToOne
    private Clazz aClazz;

}
