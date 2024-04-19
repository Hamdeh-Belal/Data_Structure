package project0;

import java.util.Objects;

public class Name implements Comparable<Name> {
	private String name;
	private int age;
	private String location;
	private String birthday;
	private char gender = 'M';
	private String note;

	public Name(String name, int age, String location, String birthday, char gender, String note) {
		super();
		setName(name);
		setAge(age);
		setLocation(location);
		setBirthday(birthday);
		setGender(gender);
		setNote(note);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		if (gender == 'M' || gender == 'F' || gender == 'm' || gender == 'f')
			this.gender = gender;
		else
			System.out.println("error !!");

	}

	@Override
	public int compareTo(Name o) {

		return 0;
	}

	@Override
	public String toString() {
		return "Name [name=" + name + ", age=" + age + ", location=" + location + ", birthday=" + birthday + ", gender="
				+ gender + ", note=" + note + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Name other = (Name) obj;
		return age == other.age && Objects.equals(birthday, other.birthday) && gender == other.gender
				&& Objects.equals(location, other.location) && Objects.equals(name, other.name)
				&& Objects.equals(note, other.note);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (age > 0)
			this.age = age;
		else
			System.out.println("the age is not true !! ");
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		if (location != null)
			this.location = location;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		if (birthday != null)
			this.birthday = birthday;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		if (note != null)
			this.note = note;
	}

}