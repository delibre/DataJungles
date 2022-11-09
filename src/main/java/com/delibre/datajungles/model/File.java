package com.delibre.datajungles.model;

import javax.persistence.*;

@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long id;

    private String name;

    private String dataset;

    @ManyToOne
    @JoinColumn(name = "parentfolder_id", nullable = false)
    private Folder parentFolder;

    @OneToOne(mappedBy = "file", cascade = CascadeType.ALL)
    private FileImport fileImport;

    @OneToOne(mappedBy = "file", cascade = CascadeType.ALL)
    private FileModification fileModification;

    public File() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDataset() {
        return dataset;
    }

    public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder folder) {
        this.parentFolder = folder;
    }
}
