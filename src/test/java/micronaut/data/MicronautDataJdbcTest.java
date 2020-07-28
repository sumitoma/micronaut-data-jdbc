package micronaut.data;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Optional;

@MicronautTest
public class MicronautDataJdbcTest {

    @Inject
    EmbeddedApplication application;

    @Inject
    UserRepository userRepository;

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
    void testFindById(){
       Optional<User> user = userRepository.findById("sumit");
       if(user.isPresent()){
           Assertions.assertEquals("sumit", user.get().getId());
       } else {
           Assertions.fail("User does not exist");
       }

    }

    @Test
    void testFindByFirstName(){
        AttributesDTO user = userRepository.findByFirstName("sumit");
        Assertions.assertNotNull(user);
        Assertions.assertEquals("sumit", user.getFirstName());
        Assertions.assertEquals(User.Status.ACTIVE, user.getStatus());
    }

}
