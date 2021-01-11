package com.ipartek.formacion.supermercado.accesodatos;

import java.util.TreeMap;

import com.ipartek.formacion.supermercado.modelo.User;

public class UserDAOTreeMap implements DaoUser {

	private static TreeMap<Long, User> users = new TreeMap<>();

	static {
		users.put(1L, new User(1L, "lander.corral@gmail.com", "1234"));
		users.put(2L, new User(2L, "rogrigo@gmail.com", "asdf"));
	}

	private UserDAOTreeMap() {

	}

	private static final UserDAOTreeMap INSTANCE = new UserDAOTreeMap();

	public static UserDAOTreeMap getInstance() {
		return INSTANCE;
	}

	@Override
	public Iterable<User> getAll() {
		return users.values();
	}

	@Override
	public User get(Long id) {
		return users.get(id);
	}

	@Override
	public void insert(User user) {
		Long id = users.size() == 0 ? 1L : users.lastKey() + 1L;
		user.setId(id);
		users.put(id, user);
	}

	@Override
	public void update(User user) {
		users.put(user.getId(), user);
	}

	@Override
	public void delete(Long id) {
		users.remove(id);
	}

	@Override
	public User getByEmail(String email) {
		for (User user : users.values()) {
			if (email.equals(user.getEmail())) {
				return user;
			}
		}
		return null;
	}

}
