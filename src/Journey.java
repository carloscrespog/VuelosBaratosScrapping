/**
 * 
 */




/**
 * @author CarlosCrespog
 *
 */
public class Journey {

	public Journey(String departureTime, String arrivalTime, String duration,
			String origin, String destination, int fee, String company) {
		
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.duration = duration;
		this.origin = origin;
		this.destination = destination;
		this.fee = fee;
		this.company = company;
	}
	public Journey() {
		
		this.departureTime = "foo";
		this.arrivalTime = "foo";
		this.duration = "foo";
		this.origin = "foo";
		this.destination = "foo";
		this.fee = 0;
		this.company = "foo";
	}

	/** The departure time of the journey */
	private String departureTime;
	
	/** The arrival time of the journey */
	private String arrivalTime;
	
	/** 
	 * The duration of the journey. This is not simply the difference of the 
	 * departure and arrival time because of timezone considerations.
	 */
	private String duration;
	
	/** The origin */
	private String origin;

	/** The destination */
	private String destination;
	
	/** The fee map that contains the different available fee */
	private int fee;
	
	private String company;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getOrigin() {
		return origin;
	}

	/**
	 * @return the departureTime
	 */
	public String getDepartureTime() {
		return departureTime;
	}

	/**
	 * @param departureTime the departureTime to set
	 */
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * @return the arrivalTime
	 */
	public String getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the oringin
	 */
	public String getOringin() {
		return origin;
	}

	/**
	 * @param oringin the oringin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * @return the fees
	 */
	public int getFee() {
		return fee;
	}

	/**
	 * @param fees the fees to set
	 */
	public void setFee(int fee) {
		this.fee = fee;
	}
	
	/** Textual representation of the journey. Use for debuging purposes inly.*/
	public String toString() {
		
		String toString = "From: ";
		toString = toString.concat(origin);
		toString = toString.concat(" (");
		toString = toString.concat(departureTime);
		toString = toString.concat(") to: ");
		toString = toString.concat(destination);
		toString = toString.concat(" (");
		toString = toString.concat(arrivalTime);
		toString = toString.concat(") in ");
		toString = toString.concat(duration);
		toString = toString.concat(" for ");
		Integer feei=new Integer(fee);
		//if(feei != null)
			toString = toString.concat(feei.toString());
			toString = toString.concat(" euros ");
		//else
		//	toString += null;
		toString=toString.concat(" with ");
		toString=toString.concat(company);
		
		return toString;
	}
	
}
