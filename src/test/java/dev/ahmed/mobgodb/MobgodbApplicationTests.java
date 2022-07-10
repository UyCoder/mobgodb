package dev.ahmed.mobgodb;

import com.github.javafaker.Faker;
import dev.ahmed.mobgodb.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.regex.Pattern;

@SpringBootTest
class MobgodbApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    Faker faker = new Faker();

    // create data test
    @Test
    void create() {
        User user = new User();
        user.setName("Ahmed-" + faker.name().firstName());
        user.setAge(18 + (int) (Math.random() * 63));
        user.setEmail("ahmedbughra@gmail.com");
        User user1 = mongoTemplate.insert(user);
        System.out.println(user1);
    }

    // findall data test
    @Test
    void findAll() {
        List<User> all = mongoTemplate.findAll(User.class);
        System.out.println(all);
    }

    // findbyid data test
    @Test
    void findById() {
        User user = mongoTemplate.findById("62cb174fd8e5a82b9876edf0", User.class);
        System.out.println(user);
    }

    // find user list test
    @Test
    void findUserList() {
        // name=test and age=25
        Query query = new Query(
                Criteria.where("name").is("Ahmed").and("age").is(25));
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
    }

    // find like user list test
    @Test
    void findLikeUserList() {
        // name like ahmed and age=25
        Query query = new Query(
                Criteria.where("name").regex("^Ahmed"));
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
    }

    // second way to find like user list test
    @Test
    void findLikeUserList2() {
        // name like ahmed
        String name = "hme";
        String regex = String.format("%s%s%s", "^.*", name, ".*$");
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Query query = new Query(
                Criteria.where("name").regex(pattern));
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
    }

    // third way to find like user list test
    @Test
    void findLikeUserList3() {
        // name like ahmed
        String regex = "hme";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Query query = new Query(
                Criteria.where("name").regex(pattern));
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
    }

    // page user list test
    @Test
    void pageUserList() {
        int pageNo = 1;
        int pageSize = 3;
        String name = "Ahmed";

        String regix = String.format("%s%s%s", "^.*", name, ".*$");
        Pattern pattern = Pattern.compile(regix, Pattern.CASE_INSENSITIVE);
        Query query = new Query(
                Criteria.where("name").regex(pattern));

        // page
        // search count
        long count = mongoTemplate.count(query, User.class);
        // page search
        List<User> users1 = mongoTemplate.find(
                query.skip((pageNo - 1) * pageSize).limit(pageSize), User.class);
        System.out.println(count);
        System.out.println(users1);
    }
}
