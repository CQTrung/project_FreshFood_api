package com.example.freshfoodapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "feedbacks")
@SuperBuilder
public class Feedback extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "subject_name", nullable = true)
    private String subjectName;
    @Column(name = "note", nullable = false)
    private String note;

    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;


}
