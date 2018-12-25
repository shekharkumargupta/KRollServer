package com.kroll.service;

import com.kroll.domain.ProfileImage;

public interface ProfileImageService {

    public ProfileImage create(ProfileImage image);

    public ProfileImage update(ProfileImage image);

    public ProfileImage findByLoginId(String loginId);
}
