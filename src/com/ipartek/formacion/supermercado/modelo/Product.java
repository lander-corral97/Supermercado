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

	public Product(Long id, String name, String description, String imageUrl, BigDecimal price, Integer discount,
			String unitMeasuring, BigDecimal unitPerMeasuring, Integer quantity) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.price = price;
		this.discount = discount;
		this.unitMeasuring = unitMeasuring;
		this.unitPerMeasuring = unitPerMeasuring;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
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
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
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
		this.unitPerMeasuring = unitPerMeasuring;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
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
