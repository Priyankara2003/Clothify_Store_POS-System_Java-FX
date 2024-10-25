package repository.custom;

import entity.UserDetailsEntity;
import repository.CrudRepository;

public interface UserDetaislDao extends CrudRepository<UserDetailsEntity> {
    String isExistsUser(String email, String pw);
}
