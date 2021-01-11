package com.ipartek.formacion.supermercado.controller;

import com.ipartek.formacion.supermercado.accesodatos.Dao;
import com.ipartek.formacion.supermercado.accesodatos.DaoUser;
import com.ipartek.formacion.supermercado.accesodatos.ProductDAOMySql;
import com.ipartek.formacion.supermercado.accesodatos.UserDAOMySql;
import com.ipartek.formacion.supermercado.modelo.Product;

public class Configuracion {

	public static DaoUser daoUser = UserDAOMySql.getInstance();
	public static Dao<Product> daoProduct = ProductDAOMySql.getInstance();

}
