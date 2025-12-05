package com.wimukthi.store.services;

import com.wimukthi.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    public void showRelatedEntities(){
        var user = userRepository.findById(1L).orElseThrow();
        System.out.println(user.getEmail());

    }


}
