/**
 * 
 */
package project2;

/**
 * @author Belal
 *
 */
public class Brand implements Comparable<Brand> {

	private String brand;
	private LinkedList<Car> carList;

	/**
	 * @param brand
	 * @param carList
	 */
	public Brand(String brand) {
		super();
		setBrand(brand);
		this.carList = new LinkedList<>();
//		setCarList(new LinkedList<>());
	}

	public void insertSorted(Car car) {
		if (car != null)
			carList.insertSorted(car);
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		if (brand != null)
			this.brand = brand;
		else
			this.brand = "NoBrandName";
	}

	/**
	 * @return the carList
	 */
	public LinkedList<Car> getCarList() {
		return carList;
	}

	/**
	 * @param carList the carList to set
	 */
	public void setCarList(LinkedList<Car> carList) {
		if (carList != null)
			this.carList = carList;
		else
			this.carList = new LinkedList<>();
	}

	@Override
	public int compareTo(Brand o) {
		if (o != null)
			return this.brand.compareTo(o.brand);
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brand other = (Brand) obj;

		return brand.equals(other.brand);
	}

	@Override
	public String toString() {
		return "Brand [brand=" + brand + "]";
	}

}
