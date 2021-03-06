package ru.smart.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Calendar;

@Entity
@Table(name = "deal")
@FilterDefs({
        @FilterDef(
                name = "userIdEquals",
                parameters = @ParamDef(name = "user_id", type = "int")
        )
})
@Filters({
        @Filter(name="userIdEquals", condition = "user_id = :user_id")
})
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String fio;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Calendar created;

    private Calendar provided;
}
