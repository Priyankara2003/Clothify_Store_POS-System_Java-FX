package service.custom;

import service.SuperService;

public interface UserDetailsService extends SuperService {
    String isExistsUser(String email, String pw);
}
