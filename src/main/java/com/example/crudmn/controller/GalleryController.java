package com.example.crudmn.controller;


import com.example.crudmn.entity.Gallery;
import com.example.crudmn.entity.Song;
import com.example.crudmn.exception.GalleryNotFoundException;
import com.example.crudmn.service.CategoryService;
import com.example.crudmn.service.GalleryService;
import com.example.crudmn.service.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gallery")
@CrossOrigin("*")
public class GalleryController {

    private final Logger log = LoggerFactory.getLogger(GalleryController.class);

    @Autowired
    private GalleryService galleryService;
    @Autowired
    private SongService songService;
    @Autowired
    private CategoryService categoryService;

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping( "/add")
    public ResponseEntity<String> addGallery(HttpServletRequest req) {
        String title = req.getParameter("title").trim();
        String song_id = req.getParameter("songs").trim();
        String sectionType = req.getParameter("sectionType").trim();
        Boolean status = Boolean.valueOf(req.getParameter("status"));
        log.info(title);
        log.info(song_id);

        try {
            Path root = Paths.get(uploadPath);

            Gallery gallery = new Gallery();
            gallery.setTitle(title);
            gallery.setSectionType(sectionType);
            gallery.setEnabled(status);
            if(song_id.length() > 0) {
                for(String i: song_id.split(",")) {
                    Song song = songService.get(Integer.parseInt(i));
                    gallery.addSong(song);
                }
            }
            else {
                gallery.setSongs(null);
            }

            boolean res = galleryService.addGallery(gallery);
            if (res) {
                log.info("success");
                return new ResponseEntity<String>("Created gallery is successfully", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<String>("Saved gallery is fail", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/page/{pageNum}")
    public Map<String, Object> getListGalleryByPage(@PathVariable(name = "pageNum") Integer pageNum,
                                                    @RequestParam(required = false) String keyword) {
        Map<String, Object> response = new HashMap<>();
        Page<Gallery> page = galleryService.listByPage(pageNum, keyword);
        List<Gallery> listGallery = page.getContent();
        response.put("listGallery", listGallery);
        response.put("totalGallery", galleryService.getTotalGallery());
        response.put("status", HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public HttpEntity<? extends Object> getGalleryById(@PathVariable(name = "id") Integer id) {
        try {
            Gallery gallery = galleryService.get(id);
            return new ResponseEntity<Gallery>(gallery,HttpStatus.OK);
        }
        catch (GalleryNotFoundException e) {
            return new ResponseEntity<String>("Could not find any song with ID " + id, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping( "/edit/{id}")
    public ResponseEntity<String> editGallery(@PathVariable(name = "id") Integer id, HttpServletRequest req)
            throws GalleryNotFoundException {

        String title = req.getParameter("title").trim();
        String sectionType = req.getParameter("sectionType").trim();
        String song_id = req.getParameter("songs").trim();
        Boolean status = Boolean.valueOf(req.getParameter("status"));
        Gallery existGallery = galleryService.get(id);



        try {

            if(song_id.length() > 0) {
                existGallery.setSongs(new HashSet<>());

                for(String i : song_id.split(",")) {
                    Song song =  songService.get(Integer.parseInt(i));
                    existGallery.addSong(song);
                }
            }
            else {
                existGallery.setSongs(new HashSet<>());
            }

            existGallery.setTitle(title);
            existGallery.setSectionType(sectionType);

            existGallery.setEnabled(status);
            boolean res = galleryService.addGallery(existGallery);
            if (res) {
                log.info("success");
                return new ResponseEntity<String>("Updated song is successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Updated song is fail", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteGalleryById(@PathVariable(name = "id") Integer id) throws GalleryNotFoundException {
        try {
            galleryService.delete(id);
            return new ResponseEntity<String>("Deleted song is successfully", HttpStatus.OK);
        }
        catch (GalleryNotFoundException ex) {
            return new ResponseEntity<String>("Deleted song is fail",HttpStatus.BAD_REQUEST);
        }

    }
}
