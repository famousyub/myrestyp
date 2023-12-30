package com.example.crudmn.entity.customInterface;

import com.example.crudmn.entity.Artist;
import com.example.crudmn.entity.Category;

import java.util.Date;
import java.util.Set;

public interface SongNotUri {

    Integer getId();

    String getName();

    String getIntroduction();

    Date getCreateTime();

    Date getUpdateTime();

    String getPhoto();

    String getLyric();

    boolean isEnabled();

    Set<Artist> getArtists();

    Set<Category> getCategories();
}
