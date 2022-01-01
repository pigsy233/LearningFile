package creature.service;

import creature.dao.UserDao;

public class AnimalService {

    UserDao userDao ;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(){
        System.out.println("service add............");
        userDao.update();
    }
}
