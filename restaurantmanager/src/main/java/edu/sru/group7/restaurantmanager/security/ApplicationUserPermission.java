package edu.sru.group7.restaurantmanager.security;

public enum ApplicationUserPermission {

	CUSTOMER_READ("customers:read"),
	CUSTOMER_WRITE("customers:write"),
	ADMIN_READ("admins:read"),
	ADMIN_WRITE("admins:read");
	
	private final String permission;
	
	ApplicationUserPermission(String permission) {
		this.permission = permission;
	}
	
	public String getPermission() {
		return permission;
	}
	
	
}
