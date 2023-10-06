package com.vlasnako.hoaxify;

import com.vlasnako.hoaxify.user_package.UserInst;
import com.vlasnako.hoaxify.user_package.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserInstControllerTest {
    public static final String API_1_0_USERS = "/api/1.0/users";
    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    UserRepository userRepository;
    @Before
    public void cleanup() {
        userRepository.deleteAll();
    }
    @Test
    public void postUser_whenUserIsValid_receiveOk() {
        UserInst userr = createValidUser();
        ResponseEntity<Object> response = testRestTemplate.postForEntity(API_1_0_USERS, userr, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void postUser_whenUserIsValid_receiveSuccessMessage() {
        UserInst userr = createValidUser();
        ResponseEntity<GenericResponce> response = testRestTemplate.postForEntity(API_1_0_USERS, userr, GenericResponce.class);
        assertThat(response.getBody().getMessage()).isNotNull();
    }
    private UserInst createValidUser() {
        UserInst userInst = new UserInst();
        userInst.setUsername("test-user");
        userInst.setDisplayName("test-display");
        userInst.setPassword("P4ssword");
        return userInst;
    }
    @Test
    public void postUser_whenUserIsValid_userSavedToDatabase() {
        UserInst userr = createValidUser();
        testRestTemplate.postForEntity(API_1_0_USERS, userr, Object.class);
        assertThat(userRepository.count()).isEqualTo(1);
    }
    @Test
    public void when_UserIsValid_passwordIsHashedInDatabase() {
        UserInst userr = createValidUser();
        testRestTemplate.postForEntity(API_1_0_USERS, userr, Object.class);
        List<UserInst> users = userRepository.findAll();
        UserInst userInDB = users.get(0);
        assertThat(userInDB.getPassword()).isNotEqualTo(userr.getPassword());
    }

}
