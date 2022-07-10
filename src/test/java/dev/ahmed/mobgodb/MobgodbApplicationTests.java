package dev.ahmed.mobgodb;

import com.github.javafaker.Faker;
import dev.ahmed.mobgodb.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootTest
class MobgodbApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    Faker faker = new Faker();

    // create data test
    @Test
    void create() {
        User user = new User();
        user.setName("Ahmed-"+faker.name().firstName());
        user.setAge(18 + (int)(Math.random() * 63));
        user.setEmail("ahmedbughra@gmail.com");
        User user1 = mongoTemplate.insert(user);
        System.out.println(user1);
    }

    // findall data test
    @Test
    void findAll() {
        List<User> all  = mongoTemplate.findAll(User.class);
        System.out.println(all);
    }

}
