package smartridebook;

interface PaymentGateway{
	void pay(double amount);
}

class UpiGateWay implements PaymentGateway{
	public void pay(double amount) {
		System.out.println("Processing UPI Payment of: " + amount);
	}
}
class CryptoPaymentGateway implements PaymentGateway{
	public void pay(double amount) {
		System.out.println("Processing CryptoPaymentGateway Payment of: " + amount);
	}
}

abstract class PaymentHandler{
 protected PaymentHandler nextHandler;
 public void setNext(PaymentHandler nextHandler) {
	 this.nextHandler=nextHandler;
 }
 public abstract void handle(double amount);
}

class FareCalculationHandler extends PaymentHandler{
	 public void handle(double amount) {
		 System.out.println("Calculating final fare including surge and taxes...");
		 if(nextHandler!=null) nextHandler.handle(amount);
 	 }
}
class CouponValidationHandler extends PaymentHandler{
	 public void handle(double amount) {
		 System.out.println("Validating and applying coupons...");
		 double finalAmount=amount-50;
		 if(nextHandler!=null) nextHandler.handle(finalAmount);
	 }
}

public class PaymentProcesser extends PaymentHandler {
	PaymentGateway gateway;
	
	public PaymentProcesser(PaymentGateway gateway) {
		this.gateway=gateway;
	}
	 public void handle(double amount) {
		 System.out.println("Executing final payment...");
		 gateway.pay(amount);
		 if(nextHandler!=null) nextHandler.handle(amount);
 	 }
}
