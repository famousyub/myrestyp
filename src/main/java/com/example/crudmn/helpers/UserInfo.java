package com.example.crudmn.helpers;

import java.util.List;

public class UserInfo {
    public UserInfo(List<String> username) {
        super();
        this.username = username;
    }

    public UserInfo() {

    }

    //	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long Id;
//	@ElementCollection(targetClass=String.class)
    private List<String> username;

    @Override
    public String toString() {
        return "UserInfo{" +
                "username=" + username +
                '}';
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }
}
