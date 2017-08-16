package com.lxhf.frame.db.operation;

import android.content.Context;

import com.lxhf.frame.bean.Baby;
import com.lxhf.frame.bean.User;
import com.lxhf.frame.db.greendao.BabyDao;
import com.lxhf.frame.db.greendao.DaoMaster;
import com.lxhf.frame.db.greendao.DaoSession;
import com.lxhf.frame.db.greendao.UserDao;
import com.lxhf.frame.utils.T;

import java.util.List;

/**
 * GreenDao操作数据库
 * Created by Jamais on 17/6/27.
 * E-mail : liutl@hfvast.com
 */
public class DbManager {
    private static DbManager dbManager = null;


    public static DbManager getIntance() {
        synchronized (DbManager.class) {
            if (dbManager == null) {
                dbManager = new DbManager();
            }
        }
        return dbManager;
    }
    //********************************************获取实体类数据操作的DAO******************************

    /**
     * 操作数据库初始化,UserDao对象用来操作User数据库。
     *
     * @param mContext
     * @param dbName   表名
     * @author Jamais
     * @created at 17/6/28 下午4:36
     */
    private UserDao getUserDao(Context mContext, String dbName) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(mContext.getApplicationContext(), dbName, null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        return userDao;
    }

    /**
     * 操作数据库初始化,BabyDao对象用来操作Baby数据库。
     *
     * @param mContext
     * @param dbName   表名
     * @author Jamais
     * @created at 17/6/28 下午4:36
     */
    private BabyDao getBabyDao(Context mContext, String dbName) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(mContext.getApplicationContext(), dbName, null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        BabyDao babyDao = daoSession.getBabyDao();
        return babyDao;
    }
    //********************************************添加数据*******************************************

    /**
     * 插入user数据
     *
     * @author Jamais
     * @created at 17/6/28 下午4:48
     */
    public void insert(Context mContext, String dbName, User user) {
        UserDao userDao = getUserDao(mContext, dbName);
        if (userDao != null) {
            userDao.insert(user);
        }
    }

    /**
     * 插入baby数据
     *
     * @author Jamais
     * @created at 17/6/28 下午4:48
     */
    public void insert(Context mContext, String dbName, Baby baby) {
        BabyDao babyDao = getBabyDao(mContext, dbName);
        if (babyDao != null) {
            babyDao.insert(baby);
        }
    }
    //********************************************删除数据*******************************************

    /**
     * 根据id删除一条数据
     *
     * @author Jamais
     * @created at 17/6/28 下午5:05
     */
    public void deleteUserById(Context mContext, String dbName, int id) {
        UserDao userDao = getUserDao(mContext, dbName);
        if (userDao != null) {
            User user = userDao.queryBuilder().where(UserDao.Properties.Id.eq(16)).build().unique();
            if (user == null) {
                T.toastAtBottomS(mContext, "用户不存在");
            } else {
                userDao.deleteByKey(user.getId());
            }
        }
    }

    /**
     * 根据age删除一组数据
     *
     * @author Jamais
     * @created at 17/6/28 下午5:05
     */
    public void deleteUserByAge(Context mContext, String dbName, int age) {
        UserDao userDao = getUserDao(mContext, dbName);
        if (userDao != null) {
            List<User> userList = userDao.queryBuilder().where(UserDao.Properties.Age.eq(10)).build().list();
            for (User user : userList) {
                userDao.delete(user);
            }
        }
    }

    //********************************************修改数据*******************************************

    /**
     * 修改名字为name冰洁年龄是age的用户的数据
     *
     * @author Jamais
     * @created at 17/6/28 下午5:12
     */
    public void updateUserByAgeAndName(Context mContext, String dbName, int oldAge, String oldName, int newAge, String newName) {
        UserDao userDao = getUserDao(mContext, dbName);
        if (userDao != null) {
            User user = userDao.queryBuilder()
                    .where(UserDao.Properties.Age.eq(oldAge), UserDao.Properties.Name.eq(oldName)).build().unique();
            if (user == null) {
                T.toastAtBottomS(mContext, "用户不存在!");
            } else {
                user.setName(newName);
                user.setAge(newAge);
                userDao.update(user);
            }
        }
    }
    //********************************************查询数据*******************************************

    /**
     * 根据年龄查询User表
     *
     * @author Jamais
     * @created at 17/6/28 下午5:18
     */
    public List<User> queryUserByAge(Context mContext, String dbName, int age) {
        List<User> userList = null;
        UserDao userDao = getUserDao(mContext, dbName);
        if (userDao != null) {
            userList = userDao.queryBuilder().where(UserDao.Properties.Age.eq(age)).build().list();
        }
        return userList;
    }
}
