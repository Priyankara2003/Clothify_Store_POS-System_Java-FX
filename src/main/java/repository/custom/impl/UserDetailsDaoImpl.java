package repository.custom.impl;

import org.hibernate.Session;
import repository.custom.UserDetaislDao;
import util.HibernateUtil;

import java.util.List;

public class UserDetailsDaoImpl implements UserDetaislDao {
    @Override
    public boolean save(entity.UserDetailsEntity entity) {
        return false;
    }

    @Override
    public boolean update(entity.UserDetailsEntity entity) {
        return false;
    }

    @Override
    public List<entity.UserDetailsEntity> findAll() {
        return List.of();
    }

    @Override
    public boolean delete(int Id) {
        return false;
    }

    @Override
    public String isExistsUser(String email, String pw) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        String sqlQuery = "SELECT userRole FROM users WHERE userName = \""+email+"\" AND password = \""+pw+"\"";
        List<String> resultList = session.createNativeQuery(sqlQuery, String.class).getResultList();
        if (!resultList.isEmpty()){
            return resultList.getFirst();
        }
        return null;
    }
}
