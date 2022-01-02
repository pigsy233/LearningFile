package spring5.dao;

import org.springframework.stereotype.Repository;

import javax.inject.Scope;

@Repository(value = "userDaoImpl")
public class UserDaoImpl implements UserDao{

    @Override
    public void add() {
        System.out.println("UserDaoImpl activated");
    }
}
