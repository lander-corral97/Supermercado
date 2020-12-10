package com.ipartek.formacion.supermercado.accesodatos;

import java.math.BigDecimal;
import java.util.TreeMap;

import org.apache.commons.lang3.SerializationUtils;

import com.ipartek.formacion.supermercado.modelo.Product;

public class ProductDAOTreeMap implements Dao<Product> {

	private static TreeMap<Long, Product> products = new TreeMap<>();

	static {
		products.put(1L, new Product(1L, "Beefeater", "Botella de ginebra", "http://placeimg.com/640/480/tech",
				new BigDecimal("12.95"), 20, "Litro", new BigDecimal("18.50"), 1));
		products.put(2L,
				new Product(2L, "Beefeater 'Light'", "Botella de ginebra (light)", "http://placeimg.com/640/480/tech?2",
						new BigDecimal("7.90"), null, "Litro", new BigDecimal("18.50"), 1));

		for (Long id = 3L; id <= 12L; id++) {
			products.put(id,
					new Product(id, "Producto " + id, "Descripción " + id, "http://placeimg.com/640/480/tech?" + id,
							new BigDecimal(11 * id), id.intValue(), "Unidad " + id, new BigDecimal(10 * id),
							id.intValue()));
		}
	}

	private ProductDAOTreeMap() {
	}

	private static final ProductDAOTreeMap INSTANCE = new ProductDAOTreeMap();

	public static ProductDAOTreeMap getInstance() {
		return INSTANCE;
	}

	@Override
	public Iterable<Product> getAll() {
		return products.values();
	}

	@Override
	public Product get(Long id) {
		return SerializationUtils.clone(products.get(id));
	}

	@Override
	public void insert(Product prod) {
		Long id = products.size() == 0 ? 1L : products.lastKey() + 1L;
		prod.setId(id);
		products.put(id, prod);
	}

	@Override
	public void update(Product prod) {
		products.put(prod.getId(), prod);
	}

	@Override
	public void delete(Long id) {
		products.remove(id);
	}

}
