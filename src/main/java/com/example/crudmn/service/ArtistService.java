package com.example.crudmn.service;

import com.example.crudmn.entity.Artist;
import com.example.crudmn.exception.ArtistNotFoundException;
import com.example.crudmn.repository.ArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class ArtistService {


    private final Logger log = LoggerFactory.getLogger(ArtistService.class);

    public static final int ARTISTS_PER_PAGE = 9;

    @Autowired
    ArtistRepository artistRepository ;



    public List<Artist> listAll() {
        return (List<Artist>) artistRepository.findAll();
    }

    public boolean addArtist(Artist artist) {
        artistRepository.save(artist);
        return true;
    }

    public String checkUnique(Integer id, String name) {
        Artist artistByName = artistRepository.findByName(name);
        if (artistByName != null && artistByName.getId() != id) {
            return "Duplicate";
        }

        return "Ok";
    }

    public void updateArtistEnabledStatus(Integer id, boolean enabled) {
        artistRepository.updateEnabledStatus(id, enabled);
    }

    public void delete(Integer id) throws ArtistNotFoundException {
        Long countById = artistRepository.countById(id);
        if (countById == null || countById == 0) {
            throw new ArtistNotFoundException("Could not find any artist with ID " + id);
        }
        artistRepository.deleteById(id);


    }

    public Artist get(Integer id) throws ArtistNotFoundException {
        try {
            return artistRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new ArtistNotFoundException("Could not find any artist with ID " + id);
        }
    }

    public Page<Artist> listByPage(Integer pageNum, String keyword) {
        Pageable pageable = PageRequest.of(pageNum - 1, ARTISTS_PER_PAGE);
        if(keyword != null) {
            log.info("Keyword is:" + keyword);
            return artistRepository.findAll(keyword, pageable);
        }
        return artistRepository.findAll(pageable);

    }

    public List<Artist> listArtists() {
        return artistRepository.findListArtists();
    }

    public long getTotalArtists() {
        return artistRepository.count();
    }





}
