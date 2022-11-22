package org.kawahedukasi.model;

import org.hibernate.annotations.GenericGenerator;
import org.kawahedukasi.model.base.CreatedBase;

import javax.persistence.*;

@Entity
@Table(name = "job_position")
public class JobPosition extends CreatedBase {

    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id" , length = 36, nullable = false)
    private String id;

    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @Column(name = "salary" , nullable = false)
    private Double salary;
}
