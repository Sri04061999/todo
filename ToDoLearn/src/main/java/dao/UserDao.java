package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import dto.Task;
import dto.User;

public class UserDao {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dev");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	public void saveUser(User user) {
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
	}

	public User fetchByEmail(String email) {
		List<User> list = entityManager.createQuery("select x from User x where email=?1").setParameter(1, email)
				.getResultList();
		if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	public void addTask(Task task) {
		entityTransaction.begin();
		entityManager.persist(task);
		entityTransaction.commit();
	}

	public List<Task> fetchAllTask() {
		return entityManager.createQuery("select x from Task x ").getResultList();
	}

	public void update(User user) {
		entityTransaction.begin();
		entityManager.merge(user);
		entityTransaction.commit();
	}
	public Task fetchTask(int id) {
		return entityManager.find(Task.class, id);
	}

	public void removeTask(Task task) {
		entityTransaction.begin();
		entityManager.remove(task);
		entityTransaction.commit();
	}
}
