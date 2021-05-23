package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.Util;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {


    Session session = null;
    Transaction transaction = null;
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE `users`.`users` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastname` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT(3) NOT NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB\n" +
                "DEFAULT CHARACTER SET = utf8";

        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {

        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void dropUsersTable() {
        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            transaction.commit();
        } catch (Exception e) {

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {

        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
        } catch (Exception e) {

        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            result = session.createQuery("from user").list();
            transaction.commit();
        } catch (Exception e) {

        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("delete from users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
