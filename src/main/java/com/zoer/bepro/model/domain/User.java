package com.zoer.bepro.model.domain;

import com.zoer.bepro.model.dao.Identified;

/**
 * Created by zoer on 27.12.16.
 */
public class User implements Identified<Integer> {


    Integer iduser;
    String email;
    String nickname;
    String password;
    Integer profileId;
    Profile profile;

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    @Override
    public Integer getId() {
        return iduser;
    }

    public void setId(Integer iduser) {
        this.iduser = iduser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    @Override
    public int compareTo(Identified<Integer> integerIdentified) {
        return this.getId()-integerIdentified.getId();
    }
}
