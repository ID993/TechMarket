package com.aegis.TechMarket.Views;

import com.aegis.TechMarket.Services.FileStorageService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;


@Controller
@RequestMapping()
@AllArgsConstructor
public class Display {

    private FileStorageService fileStorageService;

    ResourceLoader resourceLoader;

    @GetMapping("/img/{filename}")
    public String getTheImage(@PathVariable String filename, Model model) {
        var image = fileStorageService.load(filename);
        model.addAttribute("images", image);
        return "display";
    }

    @GetMapping("/img")
    public String getAllImage(Model model) {
        var images = fileStorageService.loadAll().toList();
        model.addAttribute("images", images);
        return "display";
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<ByteArrayResource> getImages(@PathVariable String filename) throws IOException {
        File file = new File("src/main/resources/static/images/" + filename);
        byte[] data = FileUtils.readFileToByteArray(file);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(data.length)
                .body(resource);
    }



}
