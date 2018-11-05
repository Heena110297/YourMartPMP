package com.nagarro.YourMartPMPAdminPanel.customException;

public class SellerNotFoundException extends RuntimeException {

	public SellerNotFoundException() {
	}

	public SellerNotFoundException(String arg0) {
		super(arg0);
	}

	public SellerNotFoundException(Throwable arg0) {
		super(arg0);
	}

	public SellerNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public SellerNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
