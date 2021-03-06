package club.dhbxs.service.impl;

import club.dhbxs.bean.User;
import club.dhbxs.dao.UserDao;
import club.dhbxs.dao.impl.UserDaoImpl;
import club.dhbxs.service.UserSignInService;
import club.dhbxs.service.UserSignUpService;


/**
 * @ClassName UserSignInServiceImpl
 * @Description
 * @Author 17235
 * @Date 2020/6/18 上午 8:29
 * @Version 1.0
 **/
public class UserSignInServiceImpl implements UserSignInService {
    @Override
    public User verify(User u){
        UserDao user = new UserDaoImpl();
        User u1 = user.queryUserByName(u.getUserName());
        if (u1 != null){
            if(u.getUserName().equals(u1.getUserName())){
                if (u.getUserPassword().equals(u1.getUserPassword())) {
                    return u1;
                }
            }
        }
        return null;
    }

    @Override
    public User verifyAdmin(User u) {
        if (verify(u) != null) {
            UserDao user = new UserDaoImpl();
            User u1 = user.queryUserByName(u.getUserName());
            if (u1.getUserLevel() != null){
                if (u1.getUserLevel().equals("admin")) {
                    return u1;
                }
            }
        }
        return null;
    }


}