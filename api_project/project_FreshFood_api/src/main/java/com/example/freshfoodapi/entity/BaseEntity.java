package com.example.freshfoodapi.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "warehouse")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@MappedSuperclass
public abstract class BaseEntity implements Serializable {




}
