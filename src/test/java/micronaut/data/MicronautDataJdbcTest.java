package micronaut.data;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.StreamSupport;

@MicronautTest
public class MicronautDataJdbcTest {

    @Inject
    EmbeddedApplication application;

    @Inject
    UserRepository userRepository;
    @Inject
    UserPageableRepository userPageableRepository;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @BeforeAll
    static void init(UserRepository userRepository){
        userRepository.saveAll(Arrays.asList(
                new User("sumit", "sumit@gmail", "sumit", null, null),
                new User("sumitoma", "sumit@gmail", "sumit", null, null)
        ));
    }

    @Test
    void testCount(){
       Assertions.assertEquals(2, userRepository.count());
    }

    @Test
    void testFindById(){
       Optional<User> user = userRepository.findById("sumit");
       if(user.isPresent()){
           Assertions.assertEquals("sumit", user.get().getId());
       } else {
           Assertions.fail("User does not exist");
       }

    }

    @Test
    void testExistsById(){
        Assertions.assertTrue(userRepository.existsById("sumit"));
    }

    @Test
    void testFindByFirstName(){
        AttributesDTO user = userRepository.findByFirstName("sumit");
        Assertions.assertNotNull(user);
        Assertions.assertEquals("sumit", user.getFirstName());
        Assertions.assertEquals(User.Status.ACTIVE, user.getStatus());
    }

    @Test
    void testFindAll(){
        Iterable<User> userIterable = userRepository.findAll();
        Assertions.assertNotNull(userIterable);
        long totalCount = StreamSupport.stream(userIterable.spliterator(), false).count();
        Assertions.assertEquals(2, totalCount);
    }


    //Pageable repository test
    @Test
    void testFindPage(){
        Page<User> user = userPageableRepository.findAll(Pageable.from(0, 2));

        Assertions.assertNotNull(user);
        Assertions.assertEquals(2, user.getContent().size());
        Assertions.assertEquals(1, user.getTotalPages());
        Assertions.assertEquals(2, user.getTotalSize());

    }


}
