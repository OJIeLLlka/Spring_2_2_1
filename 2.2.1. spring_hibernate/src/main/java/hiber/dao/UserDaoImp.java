package hiber.dao;

import hiber.config.AppConfig;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
   private final SessionFactory sessionFactory = new AppConfig().getTransactionManager().getSessionFactory();

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByModelAndSeries(String model, int series) {
      String hql = "from User u WHERE u.car.model = :model AND u.car.series = :series";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
      List<User> user = query.setParameter("model", model)
              .setParameter("series", series)
              .getResultList();
      return user.get(1);
   }
}
