package com.wimukthi.store.repositories;

import com.wimukthi.store.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
