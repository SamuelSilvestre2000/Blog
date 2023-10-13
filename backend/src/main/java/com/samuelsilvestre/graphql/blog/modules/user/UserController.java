package com.samuelsilvestre.graphql.blog.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.samuelsilvestre.graphql.blog.modules.post.PostEntity;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // @MutationMapping
    // public UserEntity addUser(@Argument("username") String username, 
    //                           @Argument("email") String email, 
    //                           @Argument("password") String password) {
    //     UserEntity user = new UserEntity();
    //     user.setUsername(username);
    //     user.setEmail(email);
    //     user.setPassword(password); // Remember to hash the password before saving
    //     return userRepository.save(user);
    // }

    @MutationMapping()
    UserEntity addUser(@Argument ("user") UserInput user){
        return this.userRepository.save(new UserEntity(user.username, user.password, user.email));
    }

    @QueryMapping
    public Iterable<UserEntity> users() {
        return this.userRepository.findAll();
    }

    record UserInput(String username, String password, String email) {

    }
}
