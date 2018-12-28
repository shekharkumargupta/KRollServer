package com.kroll.dao;

import com.kroll.domain.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileImageDAO extends JpaRepository<ProfileImage, Long> {

    public ProfileImage findByPerson(Long personId);
}
