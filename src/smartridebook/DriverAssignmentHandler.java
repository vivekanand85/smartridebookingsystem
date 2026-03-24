package smartridebook;

public abstract class DriverAssignmentHandler {

	protected DriverAssignmentHandler nextHandler;
	
	public void setNext(DriverAssignmentHandler nexHandler) {
		this.nextHandler=nexHandler;
	}
	public abstract void handle(RideRequest request);
}


 class AvailabilityCheckHandler extends DriverAssignmentHandler {
    @Override
    public void handle(RideRequest request) {
        System.out.println("1. Checking driver availability for: " + request.getRideType());
        
        // If drivers are available, pass to the next step
        if (nextHandler != null) {
            nextHandler.handle(request);
        }
    }
}
 class DistanceValidationHandler extends DriverAssignmentHandler {
    @Override
    public void handle(RideRequest request) {
        System.out.println("2. Validating ride distance from " + request.getPickupLocation() + " to " + request.getDropLocation());
        
        // If distance is valid, pass to the next step
        if (nextHandler != null) {
            nextHandler.handle(request);
        }
    }
}

 class DriverAllocationHandler extends DriverAssignmentHandler {
    @Override
    public void handle(RideRequest request) {
        System.out.println("3. Assigning the nearest available driver...");
        // This is the final step in this chain, so it doesn't call nextHandler
    }
}
