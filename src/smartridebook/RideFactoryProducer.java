package smartridebook;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

interface Ride{
	double calculateFare(double distance);
}
class BikeRide implements Ride{

	public double calculateFare(double distance) {
		return 20.0 + (distance * 5.0);
	}
}
class SedanRide implements Ride{

	public double calculateFare(double distance) {
		return 50.0 + (distance * 12.0);
	}
}
class SUVRide implements Ride{

	public double calculateFare(double distance) {
		return 80.0 + (distance * 18.0);
	}
}
interface RideFactory{
	Ride createRide();
}
class BikeRideFactory implements RideFactory{
	
	public Ride createRide() {
		return new BikeRide();
	}
}
class SedanRideFactory implements RideFactory{
	public Ride createRide() {
		return new SedanRide();
	}
}
class SUVRideFactory implements RideFactory{
	public Ride createRide() {
		return new SUVRide();
	}
}
public class RideFactoryProducer {

	private static final Map<String,RideFactory> factoryRegistry=new ConcurrentHashMap<>();
	
	static {
		factoryRegistry.put("BIKE", new BikeRideFactory());
		factoryRegistry.put("SEDAN", new SedanRideFactory());
		factoryRegistry.put("SUV", new SUVRideFactory());
	}
	
	public static void register(String type,RideFactory factory) {
		factoryRegistry.put(type, factory);
	}
	
	public static RideFactory getFactoy(String type) {
		RideFactory factory=factoryRegistry.get(type.toUpperCase());
		if(factory==null) {
			throw new IllegalArgumentException("Invalid ride type requested: " + type);
		}
		return factory;
	}
	
}
