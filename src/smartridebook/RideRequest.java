package smartridebook;

public class RideRequest {

	private final String pickupLocation;
    private final String dropLocation;
    private final String rideType;
    
    private final boolean acRequested;
    private final boolean musicRequested;
    private final int luggageCount;
    private final boolean wheelchairAssistance;
    private RideRequest(Builder builder) {
        this.pickupLocation = builder.pickupLocation;
        this.dropLocation = builder.dropLocation;
        this.rideType = builder.rideType;
        this.acRequested = builder.acRequested;
        this.musicRequested = builder.musicRequested;
        this.luggageCount = builder.luggageCount;
        this.wheelchairAssistance = builder.wheelchairAssistance;
    }
    
    public String getPickupLocation() { return pickupLocation; }
    public String getRideType() { return rideType; }
    public String getDropLocation() {return dropLocation;}
    
    public boolean getacRequested() { return acRequested; }
    public boolean getmusicRequested() { return musicRequested; }
    public int getLuggageCount() {return luggageCount;}
    public boolean getwheelcnthairAssistance() {return wheelchairAssistance;}
    
    public static class Builder{
    	private String pickupLocation = "";
        private String dropLocation = "";
        private String rideType = "";
        
        private boolean acRequested = false;
        private boolean musicRequested=false;
        private int luggageCount=0;
        private boolean wheelchairAssistance=false;
        
        public Builder pickup(String pickupLocation) {
        	this.pickupLocation=pickupLocation;
        	return this;
        }
        public Builder drop(String dropLocation) {
            this.dropLocation = dropLocation;
            return this;
        }

        public Builder rideType(String rideType) {
            this.rideType = rideType;
            return this;
        }
        
        public Builder addPreferenceAC(boolean acRequested) {
            this.acRequested = acRequested;
            return this;
        }

        public Builder music(boolean musicRequested) {
            this.musicRequested = musicRequested;
            return this;
        }
        public Builder wheelchairAccess(boolean wheelchairAssistance) {
            this.wheelchairAssistance = wheelchairAssistance;
            return this;
        }
        public Builder luggageCount(int luggageCount) {
            this.luggageCount = luggageCount;
            return this;
        }
        
        public RideRequest build() {

            if(pickupLocation == null || pickupLocation.isEmpty()) {
                throw new IllegalStateException("Pickup location required");
            }

            if(dropLocation == null || dropLocation.isEmpty()) {
                throw new IllegalStateException("Drop location required");
            }

            if(rideType == null || rideType.isEmpty()) {
                throw new IllegalStateException("Ride type required");
            }
        	return new RideRequest(this);
        }
        
    }
}
