package com.example.crudmn.repository;

import com.example.crudmn.entity.Song;
import com.example.crudmn.entity.customInterface.SongIdName;
import com.example.crudmn.entity.customInterface.SongNotUri;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends PagingAndSortingRepository<Song, Integer> {
    @Query("UPDATE Song s SET s.enabled = ?2 WHERE s.id = ?1")
    @Modifying
    public void updateEnabledStatus(Integer id, boolean enabled);

    @Query("SELECT s FROM Song s WHERE s.name LIKE %?1% OR s.introduction LIKE %?1%")
    public Page<SongNotUri> findAll(@Param("keyword") String keyword, Pageable pageable);


    public Song findByName(String name);


    public Long countById(Integer id);


    @Query("SELECT s FROM Song s JOIN s.artists a JOIN s.categories c WHERE  (s.id NOT IN :list) AND " +
            "(a.id IN :listIdArtist OR  c.id IN :listIdCategory)")
    public List<SongNotUri> findSongSuggested(List<Integer> listIdArtist, List<Integer> listIdCategory, List<Integer> list, Pageable pageable);

    @Query("SELECT s FROM Song s")
    public List<SongIdName> findListSongsId();
}
