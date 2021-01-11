package com.ipartek.formacion.supermercado.modelo;

import java.time.LocalDate;

public class Client {

	private Long id;
	private String firstName, lastName, cif;
	private LocalDate birthDate;

	private String errorId, errorFirstName, errorLastName, errorCif, errorBirthDate;

	public Client() {
		super();
	}

	public Client(String id, String firstName, String lastName, String cif, String birthDate) {
		super();
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setCif(cif);
		setBirthDate(birthDate);
	}

	public Client(Long id, String firstName, String lastName, String cif, LocalDate birthDate) {
		super();
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setCif(cif);
		setBirthDate(birthDate);
	}

	public void setId(String id) {
		try {
			setId(id != null && id.trim().length() > 0 ? Long.parseLong(id) : null);
		} catch (NumberFormatException e) {
			setErrorId("El id debe ser numérico");
		}
	}

	public void setBirthDate(String birthDate) {
		try {
			setBirthDate(birthDate != null && birthDate.trim().length() > 0 ? LocalDate.parse(birthDate) : null);
		} catch (Exception e) {
			setErrorBirthDate(
					"No se ha introducido un formato de fecha correcto (AÑO-MES-DÍA, 4 dígitos - 2 dígitos - 2 dígitos)");
		}
	}

	public boolean isCorrecto() {
		return errorId == null && errorFirstName == null && errorLastName == null && errorCif == null
				&& errorBirthDate == null;
	}

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	public String getErrorFirstName() {
		return errorFirstName;
	}

	public void setErrorFirstName(String errorFirstName) {
		this.errorFirstName = errorFirstName;
	}

	public String getErrorLastName() {
		return errorLastName;
	}

	public void setErrorLastName(String errorLastName) {
		this.errorLastName = errorLastName;
	}

	public String getErrorCif() {
		return errorCif;
	}

	public void setErrorCif(String errorCif) {
		this.errorCif = errorCif;
	}

	public String getErrorBirthDate() {
		return errorBirthDate;
	}

	public void setErrorBirthDate(String errorBirthDate) {
		this.errorBirthDate = errorBirthDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if (id != null && id < 1) {
			setErrorId("El id debe ser mayor que 0");
		} else {
			setErrorId(null);
		}
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName == null || !firstName.matches("\\p{Lu}\\p{Ll}{2}[ \\p{Ll}]*")) {
			setErrorFirstName("El nombre es obligatorio, debe tener al menos 3 caracteres y comenzar por mayúscula");
		} else {
			setErrorFirstName(null);
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		lastName = lastName == null || lastName.trim().length() == 0 ? null : lastName;

		if (lastName != null && !lastName.matches("\\p{Lu}\\p{Ll}{2}[ \\p{Ll}]*")) {
			setErrorLastName(
					"Los apellidos no son obligatorios, pero deben tener al menos 3 letras y comenzar por mayúscula");
		} else {
			setErrorLastName(null);
		}
		this.lastName = lastName;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		if (cif == null || !cif.matches("[ABCDEFGHJPQRSUVNW]\\d{8}|[XYZ]\\d{7}[A-Z]|\\d{8}[A-Z]")) {
			cif = null;
			setErrorCif("El cif es obligatorio y debe tener los siguientes formatos: A12345678, X12345567B, 12345678B");
		} else {
			setErrorCif(null);
		}
		this.cif = cif;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		if (birthDate != null && birthDate.isAfter(LocalDate.now().minusYears(18))) {
			setErrorBirthDate("Debes ser mayor de edad o has introducido una fecha futura");
		} else {
			setErrorBirthDate(null);
		}
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", cif=" + cif
				+ ", birthDate=" + birthDate + ", errorId=" + errorId + ", errorFirstName=" + errorFirstName
				+ ", errorLastName=" + errorLastName + ", errorCif=" + errorCif + ", errorBirthDate=" + errorBirthDate
				+ ", isCorrecto()=" + isCorrecto() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Client other = (Client) obj;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (cif == null) {
			if (other.cif != null)
				return false;
		} else if (!cif.equals(other.cif))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
