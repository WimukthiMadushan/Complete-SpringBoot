package com.wimukthi.store;

import com.wimukthi.store.entities.Address;
import com.wimukthi.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        // SpringApplication.run(StoreApplication.class, args);

        var user = User.builder()
                .name("Wimukthi")
                .email("email@gmail.com")
                .password("Wimukthi")
                .build();

        var address = Address.builder()
                .street("Street")
                .city("City")
                .state("State")
                .zip("Zip")
                .build();

        user.addAddress(address);
        System.out.println(user);
    }


}
