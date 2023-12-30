package com.example.crudmn.service;


import com.example.crudmn.entity.Config;
import com.example.crudmn.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ConfigService {

    @Autowired
    private ConfigRepository configRepository;


    public Config add(Config config){
        return configRepository.save(config);
    }
}
