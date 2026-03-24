package smartridebook;

public class RideBookingFacade {
	public void bookRide(RideRequest request) {
		System.out.println("=== Starting Ride Booking Process ===");
		
		RideFactory factory=RideFactoryProducer.getFactoy(request.getRideType());
		Ride ride=factory.createRide();
		
		DriverAssignmentHandler availability=new AvailabilityCheckHandler();
		DriverAssignmentHandler distance=new DistanceValidationHandler();
		DriverAssignmentHandler allocation=new DriverAllocationHandler();
		
		availability.setNext(distance);
		distance.setNext(allocation);
		
		availability.handle(request);
		
		
		double surge=AppConfig.getInstance().getSurgeMultiplier();
		double baseFare=ride.calculateFare(10);
		
		double totalFare=baseFare*surge;
		
		PaymentGateway userPreferenceGateway = new UpiGateWay();
		PaymentHandler processor=new PaymentProcesser(userPreferenceGateway);
		processor.handle(totalFare);
		
		RideStatusTracker tracker = new RideStatusTracker();
	
        tracker.addObserver(new RiderNotificationService());
        tracker.addObserver(new DriverNotificationService());
        
        tracker.notifyObservers("ACCEPTED");
        System.out.println("=== CAPTAIN ARRIVE ON WAY ===");
	}
	public static void main(String[] args) {
		RideRequest request=new RideRequest.Builder()
				.pickup("jigni")
				.drop("hcltech")
				.rideType("SUV")
				.build();
		
		RideBookingFacade bookingFacade=new RideBookingFacade();
		bookingFacade.bookRide(request);
	}
}
