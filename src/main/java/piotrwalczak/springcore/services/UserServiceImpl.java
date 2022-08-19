package piotrwalczak.springcore.services;

import piotrwalczak.springcore.dao.UserDao;
import piotrwalczak.springcore.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public User getUserById(long userId) {
        return userDao.get(userId).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getByEmail(email).orElse(null);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userDao.getByName(name, pageSize, pageNum);
    }

    @Override
    public User createUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.save(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userDao.delete(userId);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
