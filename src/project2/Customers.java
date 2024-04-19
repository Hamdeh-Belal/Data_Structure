/**
 * 
 */
package project2;

/**
 * @author Belal
 *
 */
public class Customers implements Comparable<Customers> {

	private String custName;
	private String custMobile;
	private String brandName;
	private String model;
	private int year;
	private String color;
	private double price;;
	private String orderDate;
	private String orderStatus;

	/**
	 * @param custName
	 * @param custMobile
	 * @param brandName
	 * @param model
	 * @param year
	 * @param color
	 * @param price
	 * @param orderDate
	 * @param orderStatus
	 */
	public Customers(String custName, String custMobile, String brandName, String model, int year, String color,
			double price, String orderDate, String orderStatus) {
		super();
		setCustName(custName);
		setCustMobile(custMobile);
		setBrandName(brandName);
		setModel(model);
		setYear(year);
		setColor(color);
		setPrice(String.valueOf(price));
		setOrderDate(orderDate);
		setOrderStatus(orderStatus);
	}

	/**
	 * @param custName
	 * @param custMobile
	 * @param brandName
	 * @param model
	 * @param year
	 * @param color
	 * @param price
	 * @param orderDate
	 * @param orderStatus
	 */
	public Customers(String custName, String custMobile, String brandName, String model, String year, String color,
			String price, String orderDate, String orderStatus) {
		super();
		setCustName(custName);
		setCustMobile(custMobile);
		setBrandName(brandName);
		setModel(model);
		setYear(year);
		setColor(color);
		setPrice(price);
		setOrderDate(orderDate);
		setOrderStatus(orderStatus);
	}

	/**
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
		if (custName != null)
			this.custName = custName.trim();
		else
			this.custName = "NoName";
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
	}

	/**
	 * @return the custMobile
	 */
	public String getCustMobile() {
		return custMobile;
	}

	/**
	 * @param custMobile the custMobile to set
	 */
	public void setCustMobile(String custMobile) {
		if (custMobile != null)
			this.custMobile = custMobile.trim();
		else
			this.custMobile = "0590000000";
	}

	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		if (brandName != null)
			this.brandName = brandName.trim();
		else
			this.brandName = "NoBrandName";

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
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		if (orderDate != null)
			this.orderDate = orderDate.trim();
		else
			this.orderDate = "1/1/1900";
	}

	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		if (orderStatus != null)
			this.orderStatus = orderStatus.trim();
		else
			this.orderStatus = "InProcess";

	}

	@Override
	public int compareTo(Customers o) {
		return custName.compareTo(o.getCustName());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customers other = (Customers) obj;

		return custName.equals(other.getCustName());
	}

	@Override
	public String toString() {
		return " "+getCustName() + ", " + getCustMobile() + ", " + getBrandName() + ", " + getModel() + ", " + getYear()
				+ ", " + getColor() + ", " + getPriceString() + ", " + getOrderDate() + ", " + getOrderStatus();
	}

	public Car getCar() {
		return (new Car(model, year, color, price));
	}

	public String getPriceString() {
		String[] multiValue = new String[] { "", "K", "M", "B", "T" };
		int multivalueIndex = 0;
		double pricetemp = price;
		while (pricetemp >= 1000.0 && multivalueIndex < multiValue.length - 1) {
			pricetemp /= 1000.0;
			multivalueIndex++;
		}

		return (String.format("%.0f%s", pricetemp, multiValue[multivalueIndex])).trim();
	}

}
