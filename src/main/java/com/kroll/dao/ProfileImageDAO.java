package com.kroll.dao;

import com.kroll.domain.ProfileImage;

public interface ProfileImageDAO {

    public ProfileImage create(ProfileImage image);

    public ProfileImage update(ProfileImage image);

    public ProfileImage findByLoginId(String loginId);
}
