package project2;

import java.util.Objects;

/**
 * @author Belal
 *
 */
public class Car implements Comparable<Car> {
	private String model;
	private int year;
	private String color;
	private double price;

	/**
	 * @param model
	 * @param year
	 * @param color
	 * @param price
	 */
	public Car(String model, int year, String color, double price) {
		super();
		setModel(model);
		setYear(year);
		setColor(color);
		setPrice(String.valueOf(price));
	}

	/**
	 * @param model
	 * @param year
	 * @param color
	 * @param price
	 */
	public Car(String model, String year, String color, String price) {
		super();
		setModel(model);
		setYear(year);
		setColor(color);
		setPrice(price);
	}

	/**
	 * @param model
	 * @param year
	 * @param color
	 * @param price
	 */
	public void UpdateAll(String model, String year, String color, String price) {
		setModel(model);
		setYear(year);
		setColor(color);
		setPrice(price);
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		if (model != null)
			this.model = model.trim();
		else
			this.model = "NoModel";
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		if (year > 0)
			this.year = year;
		else
			this.year = 1900;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		if (year != null || year != "")
			this.year = Integer.parseInt(year.trim());
		else
			this.year = 1900;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		if (color != null)
			this.color = color.trim();
		else
			this.color = "NoColor";
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		if (price > 0.0)
			this.price = price;
		else
			this.price = 1000;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		if (price == "" || price == null)
			price = "1K";
		price = price.trim();
		double extractedNumber = Double.parseDouble(price.substring(0, price.length() - 1));

		char suffix = price.charAt(price.length() - 1);
		double multiplier = 1.0;

		if (suffix == 'K' || suffix == 'k') {
			multiplier = 1000.0;
		} else if (suffix == 'M' || suffix == 'm') {
			multiplier = 1000000.0;
		}

		this.price = (double) (extractedNumber * multiplier);
//		System.out.println(this.price);
	}

	public String getPriceString() {
		String[] multiValue = new String[] { "", "K", "M", "B", "T" };
		int multivalueIndex = 0;
		double tempPrice = price;

		while (tempPrice >= 1000.0 && multivalueIndex < multiValue.length - 1) {
			tempPrice /= 1000.0;
			multivalueIndex++;
		}

		return (String.format("%.0f%s", tempPrice, multiValue[multivalueIndex])).trim();
	}

	@Override
	public int compareTo(Car o) {
		if (o != null) {
			int modelI = this.model.compareTo(o.model);
			if (modelI == 0) {
				modelI += Integer.compare(this.year, o.year);
				if (modelI == 0) {
					modelI += this.color.compareTo(o.getColor());
					if (modelI == 0) {
						modelI += Double.compare(this.price, o.price);
					}
				}
			}
			return (-1) * modelI;
		}
		return 0;

	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (!(obj instanceof Car))
//			return false;
//		Car other = (Car) obj;
//
//		return (this.model.equals(other.model) && this.price == other.getPrice() && this.year == other.getYear()
//				&& this.color.equals(other.getColor()));
//	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Car other))
			return false;
		return Objects.equals(color, other.color) && Objects.equals(model, other.model)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && year == other.year;
	}

	@Override
	public String toString() {
		return getModel() + ", " + getYear() + ", " + getColor() + ", " + getPriceString();
	}

}
