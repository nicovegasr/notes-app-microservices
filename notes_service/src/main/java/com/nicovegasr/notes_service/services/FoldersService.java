package com.nicovegasr.notes_service.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nicovegasr.notes_service.models.entities.Folder;
import com.nicovegasr.notes_service.models.value_objects.Username;
import com.nicovegasr.notes_service.repositories.FolderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoldersService {

    private final FolderRepository folderRepository;

    public void createFolder(String username, String folderName) {
        Username usernameVo = Username.create(username);

        Folder folder = Folder.builder()
                .username(usernameVo.getValue())
                .folderId(UUID.randomUUID().toString())
                .name(folderName)
                .build();

        folderRepository.save(folder);
    }

    public List<Folder> getFoldersByUsername(String username) {
        Username usernameVo = Username.create(username);

        return folderRepository.findByUsername(usernameVo.getValue());
    }

}
