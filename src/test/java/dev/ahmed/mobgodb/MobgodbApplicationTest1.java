package dev.ahmed.mobgodb;

import com.github.javafaker.Faker;
import com.mongodb.client.result.UpdateResult;
import dev.ahmed.mobgodb.entity.User;
import dev.ahmed.mobgodb.repsitory.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.regex.Pattern;

@SpringBootTest
class MobgodbApplicationTest1 {
    @Autowired
    private UserRepository userRepository;

    //add
    @Test
    public void createUser() {
        User user = new User();
        user.setAge(20);
        user.setName("Ahmed");
        user.setEmail("3332200@yahoo.com");
        User user1 = userRepository.save(user);
    }

    //findall
    @Test
    public void findUser() {
        List<User> userList = userRepository.findAll();
        System.out.println(userList);
    }

    //find by id
    @Test
    public void getById() {
        User user = userRepository.findById("5ffbfe8197f24a07007bd6ce").get();
        System.out.println(user);
    }

    //finduserlist
    @Test
    public void findUserList() {
        User user = new User();
        user.setName("张三");
        user.setAge(20);
        Example<User> userExample = Example.of(user);
        List<User> userList = userRepository.findAll(userExample);
        System.out.println(userList);
    }

    //find with like
    @Test
    public void findUsersLikeName() {
        //
        ExampleMatcher matcher = ExampleMatcher.matching() //object creation
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        User user = new User();
        user.setName("ahmed");
        Example<User> userExample = Example.of(user, matcher);
        List<User> userList = userRepository.findAll(userExample);
        System.out.println(userList);
    }

    //find paging
    @Test
    public void findUsersPage() {
        Sort sort = Sort.by(Sort.Direction.DESC, "age");
        // 0 is fistpage
        Pageable pageable = PageRequest.of(0, 10, sort);
        //
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        User user = new User();
        user.setName("ahmed");
        Example<User> userExample = Example.of(user, matcher);
        //创建实例
        Example<User> example = Example.of(user, matcher);
        Page<User> pages = userRepository.findAll(example, pageable);
        System.out.println(pages);
    }

    //update
    @Test
    public void updateUser() {
        User user = userRepository.findById("5ffbfe8197f24a07007bd6ce").get();
        user.setName("ahmed-");
        user.setAge(25);
        user.setEmail("883220990@qq.com");
        User save = userRepository.save(user);
        System.out.println(save);
    }

    //delete
    @Test
    public void delete() {
        userRepository.deleteById("5ffbfe8197f24a07007bd6ce");
    }

}
