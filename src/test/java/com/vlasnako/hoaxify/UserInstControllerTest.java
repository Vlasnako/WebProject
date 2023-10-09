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
import org.springframework.test.web.reactive.server.WebTestClient;

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
        ResponseEntity<Object> response = postSignup(API_1_0_USERS, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
//    @Test
//    public void delete_whenUserIsValid() {
//        UserInst userInst = createValidUser();
//        testRestTemplate.delete(API_1_0_USERS, userInst, Object.class);
//        assertThat(userRepository.count()).isEqualTo(0L);
//    }
    public <T> ResponseEntity <T> postSignup(Object request, Class <T> response) {
        return testRestTemplate.postForEntity(API_1_0_USERS, request, response);
    }
    @Test
    public void postUser_whenUserHasNullUsername_receiveBadRequest() {
        UserInst user = createValidUser();
        user.setUsername(null);
        ResponseEntity<Object> response = postSignup(user, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    public void postUser_whenUserHasNullDisplayName_receiveBadRequest() {
        UserInst user = createValidUser();
        user.setDisplayName(null);
        ResponseEntity<Object> response = postSignup(user, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    public void postUser_whenUserHasNullPassword_receiveBadRequest() {
        UserInst user = createValidUser();
        user.setPassword(null);
        ResponseEntity<Object> response = postSignup(user, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    public void postUser_whenUserIsValid_receiveSuccessMessage() {
        UserInst userr = createValidUser();
        ResponseEntity<GenericResponce> response = postSignup(API_1_0_USERS, GenericResponce.class);
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
