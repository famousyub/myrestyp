package com.example.crudmn.service;


import com.example.crudmn.mapper.UserCryptoMapper;
import com.example.crudmn.models.UserCryptoType;
import com.example.crudmn.repository.UserCryptoTypeRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.List;

@Service
public class UserCryptoService {

    @Autowired
    private GridFsTemplate template;

    @Autowired
    private GridFsOperations operations;

    @Autowired
    private UserCryptoTypeRepository userCryptoTypeRepository;


    private  byte[] myfile;

    Object fileId_ ;
    public boolean uplader(MultipartFile upload) throws IOException{
;
        if(upload.getSize()<0){
            return false;
        }

        DBObject metadata = new BasicDBObject();

        myfile = upload.getBytes();
        metadata.put("fileSize", upload.getSize());

        Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(), metadata);
        this.fileId_ = fileID;

        return  true;
    }

    public  String addUserCrypto(UserCryptoMapper userCryptoMapper, String userId, byte[] file)
    {
        if(myfile.equals(null) ){
            return  "something wrong";
        }

        UserCryptoType userCryptoType = new UserCryptoType();

        userCryptoType.setDisplayName(userCryptoMapper.getDisplayName());
        userCryptoType.setBirthday(userCryptoMapper.getBirthday());

        userCryptoType.setFile(file);

        userCryptoType.setUserId(userId);

     userCryptoTypeRepository.save(userCryptoType);

        return  "uploaded";
    }



    public  UserCryptoType getUser(String id){


        UserCryptoType userCryptoType = userCryptoTypeRepository.findById(id).get();

        return  userCryptoType;
    }


    public List<UserCryptoType> getAllCryptoUser(){
        List<UserCryptoType> userCryptoTypes = userCryptoTypeRepository.findAll();

        return  userCryptoTypes;
    }
    public String addFile(MultipartFile upload) throws IOException {

        DBObject metadata = new BasicDBObject();
        metadata.put("fileSize", upload.getSize());

        Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(), metadata);

        return fileID.toString();
    }


    public UserCryptoType downloadFile(String id) throws IOException {

        GridFSFile gridFSFile = template.findOne( new Query(Criteria.where("_id").is(id)) );

        UserCryptoType loadFile = new UserCryptoType();

        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            loadFile.setFilename( gridFSFile.getFilename() );

            loadFile.setFileType( gridFSFile.getMetadata().get("_contentType").toString() );

            loadFile.setFileSize( gridFSFile.getMetadata().get("fileSize").toString() );

            loadFile.setFile( IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()) );
        }

        return loadFile;
    }


}
