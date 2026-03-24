package smartridebook;

public class AppConfig {

	private static volatile AppConfig instancec;
	
	private double surgeMultiplier =2.0;
	private double platformFee=5.0;
	
	private AppConfig() {
		
	}
	
	
	public static AppConfig getInstance() {
		if(instancec==null) {
			synchronized (AppConfig.class) {
				if(instancec==null) {
					instancec=new AppConfig();
				}
			}
		}
		return instancec;
	}
	
	public double getSurgeMultiplier() { return surgeMultiplier; }
    public double getPlatformFee() { return platformFee; }
}

