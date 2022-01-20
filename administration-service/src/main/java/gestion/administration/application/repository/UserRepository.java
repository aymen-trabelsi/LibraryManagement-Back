package gestion.administration.application.repository;

import gestion.administration.application.dataTO.UserDTO;
import gestion.administration.application.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
}
