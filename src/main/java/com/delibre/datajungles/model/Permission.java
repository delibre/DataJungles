package com.delibre.datajungles.model;

import com.delibre.datajungles.model.user.UserRole;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long id;

    private Rights rights;

    @ElementCollection
    private Set<UserRole> roles;

    @ManyToOne
    @JoinColumn(name = "folder_id", nullable = false)
    private Folder folder;
}
