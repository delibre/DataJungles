package com.delibre.datajungles.model;

import com.delibre.datajungles.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class FileModification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long id;

    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "file_id")
    private File file;

}
