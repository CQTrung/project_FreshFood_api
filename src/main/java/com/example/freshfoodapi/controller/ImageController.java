package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.dto.ImageDto;
import com.example.freshfoodapi.entity.Image;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.service.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/v1/image/")
public class ImageController extends BaseController{

    private static String UPLOAD_DIR  = System.getProperty("user.dir") + "/src/main/resources/static/photos/";

    @Autowired
    ImageServiceImpl service;
    

    @PostMapping(value = "save")
    public ResponseEntity<ImageDto> save(@RequestBody ImageDto ImageDto, HttpServletRequest request) {
        if (Objects.isNull(ImageDto)) {
            return null;
        }
        ImageDto result = service.save(ImageDto);
        if (!Objects.isNull(result)) {
            return ResponseEntity.ok(result);
        }
        return null;
    }

    @GetMapping(value = "detail")
    public ResponseEntity<ImageDto> getDetail(@RequestParam(required = false) Long id, HttpServletRequest request) {
        if (id == null) {
            throw new BusinessException("400","id invalid");
        }
        ImageDto ImageDto = service.getImageById(id);
        return ResponseEntity.ok(ImageDto);
    }

    @DeleteMapping(value ="delete")
    public ResponseEntity<?> delete(@RequestParam(required = false,value = "id") Long id, HttpServletRequest request) {
        if (id == null) {
            throw new BusinessException("400","id invalid");
        }
        boolean result = service.delete(id);
        if(result){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(false);
    }

    @PostMapping("upload-file")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String name = originalFilename.substring(0,originalFilename.lastIndexOf("."));

        if (originalFilename != null && originalFilename.length() > 0) {
            if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("gif") && !extension.equals("svg") && !extension.equals("jpeg")) {
                throw new BusinessException("Not support type file");
            }
            try {
                ImageDto img = new ImageDto();
                img.setName(name);
                img.setSize(file.getSize());
                img.setType(extension);
                img.setData(file.getBytes());

//                String uid = UUID.randomUUID().toString();
                String link = UPLOAD_DIR + name + "." + extension;
                // Create file
                File serverFile = new File(link);
                System.out.println(serverFile.getName());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(file.getBytes());
                stream.close();
                service.save(img);
                return ResponseEntity.ok(img);
            } catch (Exception e) {
                throw new BusinessException("error upload file");
            }
        }

        throw new BusinessException("File invalid");

    }
}
