package com.lcuraca.tecsup.notesapp.repositories;
import com.lcuraca.tecsup.notesapp.models.User;
import com.orm.SugarRecord;

import java.util.List;

public class UserRepository {
    public static List<User> list(){
        List<User> users = SugarRecord.listAll(User.class);
        return users;
    }

    public static User login(String username, String password) {
        List<User> users = SugarRecord.listAll(User.class);
        for (User user : users) {
            if (user.getUser().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static User read(long id){
        User user = SugarRecord.findById(User.class, id);
        return user;
    }

    public static void create(String username,String fullname, String email, String password){
        User user = new User(username,fullname, email, password);
        SugarRecord.save(user);
    }

    public static void update(String username, String fullname, String email, String password, long id){
        User user = SugarRecord.findById(User.class, id);
        user.setUser(username);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);
        SugarRecord.save(user);
    }

    public static void delete(Long id){
        User user = SugarRecord.findById(User.class, id);
        SugarRecord.delete(user);
    }
}
