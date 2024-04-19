package project0;

import java.util.Objects;

public class Martyr implements Comparable<Martyr> {
	private String name = "";
	private String age = "";
	private String location = "";
	private String dateOfDeath = "";
	private String gender = "";
	private String note = "";

	public Martyr(String name, String age, String location, String dateOfDeath, String gender, String note) {
		super();
		setName(name);
		setAge(age);
		setLocation(location);
		setDateOfDeath(dateOfDeath);
		setGender(gender);
		setNote(note);
	}

	public Martyr(String line) {
		String[] data = line.split(",");

		for (int i = 0; i < 6; i++)
			setName(data[0]);
		setAge(data[1]);
		setLocation(data[2]);
		setDateOfDeath(data[3]);
		setGender(data[4]);
		String complement = "";
		for (int i = 5; i < data.length; i++)
			complement += data[i];
		setNote(complement);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(String birthday) {
		this.dateOfDeath = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, dateOfDeath, gender, location, name, note);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Martyr other = (Martyr) obj;
		return Objects.equals(age, other.age) && Objects.equals(dateOfDeath, other.dateOfDeath)
				&& gender == other.gender && Objects.equals(location, other.location)
				&& Objects.equals(name, other.name) && Objects.equals(note, other.note);
	}

	@Override
	public String toString() {
		return "Martyr [name=" + name + ", age=" + age + ", location=" + location + ", birthday=" + dateOfDeath
				+ ", gender=" + gender + ", note=" + note + "]";
	}

	@Override
	public int compareTo(Martyr o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
