package com.example.crudmn.service;

import com.example.crudmn.dto.CommentDto;
import com.example.crudmn.dto.TagDto;

import java.util.List;

import java.util.List;

import java.util.List;

public interface TagService {
    TagDto addCategory(TagDto categoryDto);

    TagDto getCategory(Long categoryId);

    List<TagDto> getAllCategories();

    TagDto updateCategory(TagDto categoryDto, Long categoryId);

    void deleteCategory(Long categoryId);
}