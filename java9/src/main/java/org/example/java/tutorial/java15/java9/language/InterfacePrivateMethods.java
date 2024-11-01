package org.example.java.tutorial.java15.java9.language;

public class InterfacePrivateMethods {

	interface PricedObject8{

		double getPrice();

		// default methods has been introduced in java 8 so you'll not break all your code with added methods. You can choose to use or not these added methods (like stream in collection)
		default double getPriceWithTax() {
			return getPrice() * 1.21;
		}

		// an anti-pattern has been detected here which is the duplication of code - getPrice() * 1.21
		default double getOfferPrice(double discount) {
			return getPrice() * 1.21 * discount;
		}
	}

	interface PricedObject9{

		double getPrice();

		default double getPriceWithTax() {
			return getTaxedPriceInternal();
		}

		default double getOfferPrice(double discount) {
			return getTaxedPriceInternal() * discount;
		}

		// java 9 resolves this issue by introducing the private interface methods
		// they are only accessible by code in the body of this interface
		private double getTaxedPriceInternal(){
			return getPrice() * 1.21;
		}
	}
}
