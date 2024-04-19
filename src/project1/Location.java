package project1;

public class Location implements Comparable<Location> {
	private String location = "noLocation";

	private LinkedList<Martyr> martyrList;

	public Location(String location) {
		super();
		martyrList = new LinkedList<>();
		setLocation(location);
	}

	public void insertSorted(Martyr martyr) {
		if (martyr != null)
			martyrList.insertSorted(martyr);
	}

	@Override
	public String toString() {
		return "Location [location=" + location + "]";
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		if (location != null)
			this.location = location;

	}

	public LinkedList<Martyr> getMartyrList() {
		return martyrList;
	}

	public void setMartyrList(LinkedList<Martyr> martyrList) {
		this.martyrList = martyrList;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Location)) {
			return false;
		}

		Location other = (Location) obj;
		return location.equals(other.getLocation());
	}

	@Override
	public int compareTo(Location o) {
		if (o != null)
			return location.compareTo(o.location);
		return 0;
	}

}
