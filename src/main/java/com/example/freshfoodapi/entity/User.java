package com.example.freshfoodapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String email;
    private String tel;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @Column(name = "inserted_time", nullable = true)
    private Date insertedTime;
    @Column(name = "updated_time", nullable = true)
    private Date updatedTime;
    @Column(name = "inserted_by", nullable = true)
    private String insertedBy;
    @Column(name = "updated_by", nullable = true)
    private String updatedBy;
    @OneToMany(mappedBy = "order")
    private List<Order> orderList;
    @ManyToMany(mappedBy = "userList",cascade = CascadeType.ALL)
    private List<Voucher> voucherList;

    public User(String username, String email, String hashedPassword) {
        this.username = username;
        this.email = email;
        this.password = hashedPassword;
    }

    public User(String username, String email, String hashedPassword,String tel) {
        this.username = username;
        this.email = email;
        this.password = hashedPassword;
        this.tel=tel;
    }


    @PrePersist
    private void beforeInsert() {
        this.insertedTime = new Date();
    }

    @PreUpdate
    private void beforeUpdate() {
        this.updatedTime = new Date();
    }
}
