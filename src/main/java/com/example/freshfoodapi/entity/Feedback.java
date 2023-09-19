package com.example.freshfoodapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

<<<<<<< HEAD
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "feedbacks")
@SuperBuilder
public class Feedback extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
=======
@Entity()
@Table(name = "feedback")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
>>>>>>> b54d56492e30cd1470fd67bacdcaa7da428c7876
    private long id;
    @Column(name = "subject_name", nullable = true)
    private String subjectName;
    @Column(name = "note", nullable = false)
    private String note;

    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

<<<<<<< HEAD
=======
    @Column(name = "inserted_time", nullable = true)
    private Date insertedTime;
    @Column(name = "updated_time", nullable = true)
    private Date updatedTime;

    @Column(name = "isDeleted", nullable = true)
    private Boolean isDeleted;

    @PrePersist
    private void beforeInsert() {
        this.insertedTime = new Date();
        this.isDeleted = false;
    }

    @PreUpdate
    private void beforeUpdate() {
        this.updatedTime = new Date();
    }
>>>>>>> b54d56492e30cd1470fd67bacdcaa7da428c7876

}
