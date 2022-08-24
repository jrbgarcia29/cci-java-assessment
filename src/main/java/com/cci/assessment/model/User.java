package com.cci.assessment.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String username;

    @Column
    @NonNull
    private String firstName;

    @Column
    @NonNull
    private String lastName;

    @JsonManagedReference
    @OneToMany(mappedBy="user" ,cascade=CascadeType.ALL)
    private List<Task> tasks;

}
