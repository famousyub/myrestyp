package com.example.crudmn.models.psn;

public class DoubledObjectEntity {

    private String id1;
    private String id2;

    public String getId1() {
        return id1;
    }

    public DoubledObjectEntity(String id1, String id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    public DoubledObjectEntity() {
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }
}
