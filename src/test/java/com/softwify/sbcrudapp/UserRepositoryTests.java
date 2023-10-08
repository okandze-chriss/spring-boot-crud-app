package com.softwify.sbcrudapp;

import com.softwify.sbcrudapp.dao.IUserRepository;
import com.softwify.sbcrudapp.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired private  IUserRepository repo;

    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("danyoko@gmail.com");
        user.setPassword("CUSTOMER");
        user.setLastName("OKO");
        user.setFirstName("Dany");

        User savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAll(){
        Iterable<User> userList = repo.findAll();
        Assertions.assertThat(userList).hasSizeGreaterThan(0);

        for (User user : userList)
        {
            System.out.println(user);
        }
    }

    @Test
    public  void testUpdate(){
        Integer userId  = 2;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.orElseThrow();
        user.setFirstName("BEBE");
        repo.save(user);
        User updateUser = repo.findById(userId).orElseThrow();
        Assertions.assertThat(updateUser.getPassword()).isEqualTo("CUSTOMER");
    }

    @Test
    public void testGet(){
        Integer userId = 2;
        Optional<User> user = repo.findById(userId);
        Assertions.assertThat(user).isPresent();
        System.out.println(user.get());
    }

    @Test
    public void testDelete(){
        Integer userId = 2;
        repo.deleteById(userId);

        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }


}
