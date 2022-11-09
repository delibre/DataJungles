package com.delibre.datajungles.controller.folder;

import com.delibre.datajungles.model.File;
import com.delibre.datajungles.model.Folder;
import com.delibre.datajungles.service.folder.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/folder")
//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class FolderController {
    @Autowired
    private FolderService folderService;

    @PostMapping("{id}")
    public ResponseEntity<Folder> addFolder(@PathVariable Long id, @RequestBody Folder folder) {
        return ResponseEntity.ok(folderService.addFolder(id, folder));
    }

    @GetMapping("/root-folder")
    @Transactional
    public ResponseEntity<Folder> addRootFolder() {
        return ResponseEntity.ok(folderService.addRootFolder());
    }

    @GetMapping("{id}")
    public ResponseEntity<Folder> getFolder(@PathVariable Long id) {
        return ResponseEntity.ok(folderService.getFolderRepository().findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @GetMapping("{id}/inner-folders")
    public ResponseEntity<List<Folder>> getInnerFolders(@PathVariable Long id) {
        return ResponseEntity.ok(folderService.getFolderRepository().findAllByRootFolderId(id));
    }

    @PostMapping("{id}/add-file")
    public ResponseEntity<Folder> addFile(@PathVariable Long id, @RequestBody File file) {
        return ResponseEntity.ok(folderService.addFile(id, file));
    }

}
