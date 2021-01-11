package com.ipartek.formacion.supermercado.accesodatos;

import com.ipartek.formacion.supermercado.modelo.User;

public interface DaoUser extends Dao<User> {

	User getByEmail(String email);

}
