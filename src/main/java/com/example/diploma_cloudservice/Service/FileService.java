package com.example.diploma_cloudservice.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.example.diploma_cloudservice.Entity.File;
import com.example.diploma_cloudservice.Entity.User;
import com.example.diploma_cloudservice.Exeptions.InputDataException;
import com.example.diploma_cloudservice.Exeptions.UnauthorizedException;
import com.example.diploma_cloudservice.Dto.FileResponse;
import com.example.diploma_cloudservice.Repo.AuthorizationRepository;
import com.example.diploma_cloudservice.Repo.FileRepository;
import com.example.diploma_cloudservice.Repo.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class FileService {

    FileRepository fileRepository;
    AuthorizationRepository authorizationRepository;
    UserRepository userRepository;

    public void uploadFile(String authToken, String filename, File file) {
        final User user = getUser(authToken);
        if (user == null) {
            throw new UnauthorizedException("Unauthorized error");
        }
        fileRepository.save(new File(filename, file.getSize(), file.getType(), file.getContent(), user));
        log.info("User {} upload file {}", user.getLogin(), filename);
    }

    public void deleteFile(String authToken, String filename) {
        final User user = getUser(authToken);
        if (user == null) {
            log.error("Delete file error");
            throw new UnauthorizedException("Unauthorized error");
        }
        log.info("User {} delete file {}", user.getLogin(), filename);
        fileRepository.removeByUserAndFilename(user, filename);
    }

    public File downloadFile(String authToken, String filename) {
        final User user = getUser(authToken);
        if (user == null) {
            throw new UnauthorizedException("Unauthorized error");
        }
        final File file = fileRepository.findByUserAndFilename(user, filename);
        if (file == null) {
            log.error("Download file error");
            throw new InputDataException("Error input data");
        }
        log.info("User {} download file {}", user.getLogin(), filename);
        return file;
    }

    public void editFileName(String authToken, String filename, String newFileName) {
        final User user = getUser(authToken);
        if (user == null) {
            log.error("Edit file error");
            throw new UnauthorizedException("Unauthorized error");
        }
        if (newFileName != null) {
            fileRepository.editFileNameByUser(user, filename, newFileName);
            log.info("User {} edit file {}", user.getLogin(), filename);
        } else {
            throw new InputDataException("Error input data");
        }
    }

    public List<FileResponse> getAllFiles(String authToken, Integer limit) {
        final User user = getUser(authToken);
        if (user == null) {
            log.error("Get all files error");
            throw new UnauthorizedException("Unauthorized error");
        }
        log.info("User {} get all files", user.getLogin());
        return fileRepository.findAllByUser(user, Sort.by("filename")).stream()
                .map(f -> new FileResponse(f.getFilename(), f.getSize()))
                .collect(Collectors.toList());
    }

    private User getUser(String authToken) {
        if (authToken.startsWith("Bearer ")) {
            authToken = authToken.substring(7);
        }
        final String username = authorizationRepository.getUserNameByToken(authToken);
        return userRepository.findByLogin(username)
                .orElseThrow(() -> new UnauthorizedException("Unauthorized error"));
    }
}
