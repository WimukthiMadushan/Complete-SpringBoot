package com.wimukthi.store;

import com.wimukthi.store.entities.Address;
import com.wimukthi.store.entities.Profile;
import com.wimukthi.store.entities.User;
import com.wimukthi.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        var services = context.getBean(UserService.class);
        services.showRelatedEntities();

        /*
        var user = User.builder()
                .name("Wimukthi")
                .email("email@gmail.com")
                .password("Wimukthi")
                .build();

        var profile = Profile.builder()
                        .bio("bio")
                                .build();
        user.setProfile(profile);
        profile.setUser(user);


        System.out.println(user);

         */

    }


}
