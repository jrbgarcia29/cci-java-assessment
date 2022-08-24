package com.cci.assessment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user.id", nullable=false)
    private User user;

    @Column
    @NonNull
    private String name;

    @Column
    @NonNull
    private String description;

    @Column
    @NonNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateTime;

    @Column
    @NonNull()
    private String status = "Pending";
}
