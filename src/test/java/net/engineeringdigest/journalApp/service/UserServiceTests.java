package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Disabled
    @Test
//    this can also be parameterised test
    public void testFindByUserName(){
        assertNotNull(userRepository.findByUserName("chetan"));

    }
    @Disabled  // due to some errors of getting Null pointer exception go check for vipul entries
    @Test
    public void testFindByUserEntries(){
        User user = userRepository.findByUserName("vipul");
        assertTrue(!user.getJournalEntries().isEmpty());


    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
           " 3,3,6"
    })

    public void test(int a, int b, int expected){
        assertEquals(expected, a+b, "failed for: " + expected);
    }

    @Disabled  // due to some errors of storing same user again
    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.saveNewUser(user));
    }



//    @BeforeAll
//    @BeforeEach
//    @AfterEach
//    @AfterAll

}
