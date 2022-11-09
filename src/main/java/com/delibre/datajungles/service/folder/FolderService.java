package com.delibre.datajungles.service.folder;

import com.delibre.datajungles.model.File;
import com.delibre.datajungles.model.Folder;
import com.delibre.datajungles.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class FolderService {
    @Autowired
    private FolderRepository folderRepository;

    public FolderRepository getFolderRepository() {
        return folderRepository;
    }

    public Folder addFolder(Long id, Folder folder) {
        Folder rootFolder = folderRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        folder.setRootFolder(rootFolder);
        if (rootFolder.getFolders() == null) {
            rootFolder.setFolders(new HashSet<>());
        }
        rootFolder.getFolders().add(folder);

        return folderRepository.save(folder);
    }

    public Folder addRootFolder() {
        String namespaceName = "RootFolder";
        if (folderRepository.findByName(namespaceName) == null) {
            folderRepository.createRootFolder(namespaceName);
        }
        return folderRepository.findByName(namespaceName);
    }

    public Folder addFile(Long id, File file) {
        Folder parentFolder = folderRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        file.setParentFolder(parentFolder);
        if (parentFolder.getFolders() == null) {
            parentFolder.setFolders(new HashSet<>());
        }
        parentFolder.getFiles().add(file);

        return folderRepository.save(parentFolder);
    }
}
