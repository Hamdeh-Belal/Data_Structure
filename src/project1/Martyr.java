package project1;

import java.util.Date;
import java.util.Objects;

public class Martyr implements Comparable<Martyr> {
	private String name = "noName";
	private int age = -1;
	private Date dateOfDeath = new Date();
	private char gender = 'M';

	public Martyr(String name, int age, Date dateOfDeath, char gender) {
		setName(name);
		setAge(age);
		setDateOfDeath(dateOfDeath);
		setGender(gender);

	}

	public Martyr(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null)
			this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (age >= 0)
			this.age = age;
	}

	public Date getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(Date dateOfDeath) {
		if (dateOfDeath != null)
			this.dateOfDeath = dateOfDeath;
		else
			this.dateOfDeath = new Date();
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		if (gender == 'm' || gender == 'M' || gender == 'f' || gender == 'F') {
			if (gender == 'm')
				gender = 'M';
			else if (gender == 'f')
				gender = 'F';
			this.gender = gender;
		}
	}

	@Override
	public int compareTo(Martyr o) {
		if (o != null)
			return this.dateOfDeath.compareTo(o.getDateOfDeath());
		return 0;
	}

	@Override
	public String toString() {
		return "Martyr [name=" + name + ", age=" + age + ", dateOfDeath=" + dateOfDeath + ", gender=" + gender + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Martyr))
			return false;
		Martyr other = (Martyr) obj;
		return name.equals(other.name);
	}

}
