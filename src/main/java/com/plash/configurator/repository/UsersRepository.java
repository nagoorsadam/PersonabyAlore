package com.plash.configurator.repository;

import com.plash.configurator.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

    Users findByUseridAndCompanyidAndDeleted(Long userid, Long companyid, Integer deleted);
    Users findByUseridAndDeleted(Long userid, Integer deleted);
}
