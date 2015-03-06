package com.aol.assist.model;

public class Product {

	private String friendlyName;
	private String sku;
	private String clientId;

	public Product(String clientId, String sku, String friendlyName) {
		this.clientId = clientId;
		this.sku = sku;
		this.friendlyName = friendlyName;
	}

	public String getSku() {
		return sku;
	}

	public String getClientId() {
		return clientId;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

}
