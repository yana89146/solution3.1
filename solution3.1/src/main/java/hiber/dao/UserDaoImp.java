package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserWithCar(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery
              ("from User u where u.car.model =:model and u.car.series =:series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.getSingleResult();
   }

   @Override
   public List<User> listUserWithCar() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User u where u.car.id != null ");
      return query.getResultList();
   }

}
