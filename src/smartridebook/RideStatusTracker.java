package smartridebook;

import java.util.*;

interface Observer{
	void update(String status);
}

 class RiderNotificationService implements Observer {
    @Override
    public void update(String status) {
        System.out.println("Notify Rider: Ride is now " + status);
    }
}

 class DriverNotificationService implements Observer {
    @Override
    public void update(String status) {
        System.out.println("Notify Driver: New ride request - " + status); 
    }
}

 class AdminNotificationService implements Observer {
    @Override
    public void update(String status) {
        System.out.println("Admin Log: System status changed to " + status); 
    }
}
public class RideStatusTracker {
 
	private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String status) {
        for (Observer obs : observers) {
            obs.update(status);
        }
    }
}
