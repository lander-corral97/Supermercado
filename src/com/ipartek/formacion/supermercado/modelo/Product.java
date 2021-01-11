package com.ipartek.formacion.supermercado.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {

	private static final long serialVersionUID = 3161433044871932737L;
	private Long id;
	private String name;
	private String description;
	private String imageUrl;
	private BigDecimal price;
	private Integer discount;
	private String unitMeasuring;
	private BigDecimal unitPerMeasuring;
	private Integer quantity;

	private boolean correcto = true;

	private String errorId;
	private String errorName;
	private String errorDescription;
	private String errorImageUrl;
	private String errorPrice;
	private String errorDiscount;
	private String errorUnitMeasuring;
	private String errorUnitPerMeasuring;
	private String errorQuantity;

	public Product(String id, String name, String description, String imageUrl, String price, String discount,
			String unitMeasuring, String unitPerMeasuring, String quantity) {
		setId(id);
		setName(name);
		setDescription(description);
		setImageUrl(imageUrl);
		setPrice(price);
		setDiscount(discount);
		setUnitMeasuring(unitMeasuring);
		setUnitPerMeasuring(unitPerMeasuring);
		setQuantity(quantity);
	}

	private void setQuantity(String quantity) {
		try {
			setQuantity(Integer.parseInt(quantity));
		} catch (NumberFormatException e) {
			setErrorQuantity("El precio de la cantidad debe ser un número");
		}
	}

	private void setUnitPerMeasuring(String unitPerMeasuring) {
		try {
			setUnitPerMeasuring(new BigDecimal(unitPerMeasuring));
		} catch (Exception e) {
			setErrorUnitPerMeasuring("El precio / medida debe ser un número");
		}
	}

	private void setDiscount(String discount) {
		try {
			setDiscount(Integer.parseInt(discount));
		} catch (NumberFormatException e) {
			setErrorDiscount("El descuento debe ser un número entero");
		}
	}

	private void setPrice(String price) {
		try {
			setPrice(new BigDecimal(price));
		} catch (Exception e) {
			setErrorPrice("El precio no tiene un formato correcto");
		}
	}

	private void setId(String id) {
		try {
			setId(id.trim().length() != 0 ? Long.parseLong(id) : null);
		} catch (NumberFormatException e) {
			setErrorId("El id debe ser numérico");
		}
	}

	public Product(Long id, String name, String description, String imageUrl, BigDecimal price, Integer discount,
			String unitMeasuring, BigDecimal unitPerMeasuring, Integer quantity) {
		super();
		setId(id);
		setName(name);
		setDescription(description);
		setImageUrl(imageUrl);
		setPrice(price);
		setDiscount(discount);
		setUnitMeasuring(unitMeasuring);
		setUnitPerMeasuring(unitPerMeasuring);
		setQuantity(quantity);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if (id != null && id <= 0) {
			setErrorId("No se admiten ids inferiores o iguales a 0");
		}
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.trim().length() < 3 || name.matches("[A-Z][a-z]*")) {
			setErrorName("Debe introducir un nombre con sólo letras y mayúscula la primera. Mínimo 3 caracteres");
		}
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		if (price == null || price.compareTo(new BigDecimal("10")) < 0) {
			setErrorPrice("Debe rellenarse y ser mayor que 0");
		}
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		if (discount != null && (discount < 0 || discount > 100)) {
			setErrorDiscount("El descuento debe estar comprendido entre 0 y 100");
		}
		this.discount = discount;
	}

	public String getUnitMeasuring() {
		return unitMeasuring;
	}

	public void setUnitMeasuring(String unitMeasuring) {
		this.unitMeasuring = unitMeasuring;
	}

	public BigDecimal getUnitPerMeasuring() {
		return unitPerMeasuring;
	}

	public void setUnitPerMeasuring(BigDecimal unitPerMeasuring) {
		if (unitPerMeasuring != null && unitPerMeasuring.compareTo(BigDecimal.ZERO) < 0) {
			setErrorUnitPerMeasuring("El precio / medida debe ser mayor que 0");
		}
		this.unitPerMeasuring = unitPerMeasuring;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		if (quantity == null || quantity < 0) {
			setErrorQuantity("La cantidad debe ser mayor que 0");
		}
		this.quantity = quantity;
	}

	public BigDecimal getDiscountedPrice() {
		if (this.discount == null || this.discount == 0) {
			return this.price;
		}
		if (this.discount == 100) {
			return BigDecimal.ZERO;
		}
		return this.price.multiply(new BigDecimal(100 - this.discount)).divide(new BigDecimal(100));
	}

	public boolean isCorrecto() {
		return correcto;
	}

	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		correcto = false;
		this.errorId = errorId;
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		correcto = false;
		this.errorName = errorName;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		correcto = false;
		this.errorDescription = errorDescription;
	}

	public String getErrorImageUrl() {
		return errorImageUrl;
	}

	public void setErrorImageUrl(String errorImageUrl) {
		correcto = false;
		this.errorImageUrl = errorImageUrl;
	}

	public String getErrorPrice() {
		return errorPrice;
	}

	public void setErrorPrice(String errorPrice) {
		correcto = false;
		this.errorPrice = errorPrice;
	}

	public String getErrorDiscount() {
		return errorDiscount;
	}

	public void setErrorDiscount(String errorDiscount) {
		correcto = false;
		this.errorDiscount = errorDiscount;
	}

	public String getErrorUnitMeasuring() {
		return errorUnitMeasuring;
	}

	public void setErrorUnitMeasuring(String errorUnitMeasuring) {
		correcto = false;
		this.errorUnitMeasuring = errorUnitMeasuring;
	}

	public String getErrorUnitPerMeasuring() {
		return errorUnitPerMeasuring;
	}

	public void setErrorUnitPerMeasuring(String errorUnitPerMeasuring) {
		correcto = false;
		this.errorUnitPerMeasuring = errorUnitPerMeasuring;
	}

	public String getErrorQuantity() {
		return errorQuantity;
	}

	public void setErrorQuantity(String errorQuantity) {
		correcto = false;
		this.errorQuantity = errorQuantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((unitMeasuring == null) ? 0 : unitMeasuring.hashCode());
		result = prime * result + ((unitPerMeasuring == null) ? 0 : unitPerMeasuring.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (unitMeasuring == null) {
			if (other.unitMeasuring != null)
				return false;
		} else if (!unitMeasuring.equals(other.unitMeasuring))
			return false;
		if (unitPerMeasuring == null) {
			if (other.unitPerMeasuring != null)
				return false;
		} else if (!unitPerMeasuring.equals(other.unitPerMeasuring))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", imageUrl=" + imageUrl
				+ ", price=" + price + ", discount=" + discount + ", unitMeasuring=" + unitMeasuring
				+ ", unitPerMeasuring=" + unitPerMeasuring + ", quantity=" + quantity + "]";
	}

}
