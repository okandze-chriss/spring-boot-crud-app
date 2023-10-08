package com.softwify.sbcrudapp.dao;

import com.softwify.sbcrudapp.models.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Integer> {
    public Long countById(Integer id);
}
