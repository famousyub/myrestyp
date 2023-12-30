package com.example.crudmn.controller;


import com.example.crudmn.entity.Artist;
import com.example.crudmn.exception.ArtistNotFoundException;
import com.example.crudmn.helpers.TheMe;
import com.example.crudmn.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService ;

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/add")
    public ResponseEntity<?> addSong(HttpServletRequest req , @RequestParam("photo")MultipartFile photoFile)
    {
        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();
        String birthday = req.getParameter("birthday").trim();
        String realName = req.getParameter("realName").trim();
        String nation = req.getParameter("nation").trim();

        Boolean status = Boolean.valueOf(req.getParameter("status"));

        if(photoFile.isEmpty())
        {
            return new ResponseEntity<>("photo artist is empty", HttpStatus.BAD_REQUEST);
        }


        try  {

            Path root = Paths.get(uploadPath);
            Artist art = new Artist();
            art.setName(name);
            art.setIntroduction(introduction);
            art.setBirthday(birthday);
            art.setEnabled(status);
            art.setNation(nation);
            art.setRealName(realName);
            File file = TheMe.convert(photoFile);
            Files.copy(photoFile.getInputStream(),root.resolve(photoFile.getOriginalFilename()));

            art.setPhoto(file.getName());

            boolean res = artistService.addArtist(art);
            if(res)
            {
                return new ResponseEntity<>("created Artist",HttpStatus.ACCEPTED);
            }

            else {
                return new ResponseEntity<>("errror",HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e)
        {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }


    }


    @GetMapping("/page/{pageNum}")
    public Map<String, Object> getListArtistByPage(@PathVariable(name = "pageNum") Integer pageNum,
                                                   @RequestParam(required = false) String keyword) {
        //log.info("Page num is: " + pageNum);
        Map<String, Object> response = new HashMap<>();
        Page<Artist> page = artistService.listByPage(pageNum, keyword);
        List<Artist> listArtists = page.getContent();

        response.put("totalArtists", artistService.getTotalArtists());
        response.put("status", HttpStatus.OK);
        response.put("listArtists", listArtists);

        return response;
    }

    @GetMapping("/{id}")
    public HttpEntity<? extends Object> getArtistById(@PathVariable(name = "id") Integer id) {
      //  log.info("Artist id is: " + id);
        try {
            Artist artist = artistService.get(id);
            return new ResponseEntity<Artist>(artist,HttpStatus.OK);
        }
        catch (ArtistNotFoundException e) {
            return new ResponseEntity<String>("Could not find any song with ID " + id, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping( "/edit/{id}")
    public ResponseEntity<String> editArtist(@PathVariable(name = "id") Integer id, HttpServletRequest req,
                                             @RequestParam(value = "photo", required = false) MultipartFile photoFile) {

        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();
        String nation = req.getParameter("nation").trim();
        String realName = req.getParameter("realName").trim();
        String birthday = req.getParameter("birthday").trim();


        try {
            Artist existArtist = artistService.get(id);
            Boolean status = Boolean.valueOf(req.getParameter("status"));

            if(photoFile != null ) {
                File artistPhoto = TheMe.convert(photoFile);

                existArtist.setPhoto(artistPhoto.getName());

            }

            existArtist.setRealName(realName);
            existArtist.setBirthday(birthday);
            existArtist.setName(name);
            existArtist.setIntroduction(introduction);
            existArtist.setNation(nation);
            existArtist.setEnabled(status);
            //log.info(existArtist.toString());
            boolean res = artistService.addArtist(existArtist);
            if (res) {
              //  log.info("success");
                return new ResponseEntity<String>("Updated artist is successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Updated artist is fail", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteArtistById(@PathVariable(name = "id") Integer id) {
        try {
            artistService.delete(id);
            return new ResponseEntity<String>("Deleted artist is successfully",HttpStatus.OK);
        }
        catch (ArtistNotFoundException ex) {
            return new ResponseEntity<String>("Deleted artist is fail",HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getlist")
    public ResponseEntity<List<Artist>> getListArtist() {
        List<Artist> listArtists = artistService.listArtists();
        return new ResponseEntity<List<Artist>>(listArtists,HttpStatus.OK);
    }


}
