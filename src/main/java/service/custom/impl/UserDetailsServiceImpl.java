package service.custom.impl;

import repository.DaoFactory;
import repository.custom.UserDetaislDao;
import service.custom.UserDetailsService;
import util.DaoType;

public class UserDetailsServiceImpl implements UserDetailsService {


    @Override
    public String isExistsUser(String email, String pw) {
        UserDetaislDao userDetaislDao = DaoFactory.getInstance().getDaoType(DaoType.UserDetails);
        return userDetaislDao.isExistsUser(email,pw);
    }
}
