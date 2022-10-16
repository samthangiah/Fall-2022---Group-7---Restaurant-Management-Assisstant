package edu.sru.group7.restaurantmanager.security;

public enum ApplicationUserPermission {

	CUSTOMER_READ("customers:read"),
	CUSTOMER_WRITE("customers:write"),
	ADMIN_READ("admins:read"),
	ADMIN_WRITE("admins:write"),
	MANAGER_READ("managers:read"),
	MANAGER_WRITE("managers:write"),
	SERVER_READ("servers:read"),
	SERVER_WRITE("servers:write"),
	HQMANAGER_READ("hqmanagers:read"),
	HQMANAGER_WRITE("hqmanagers:write"),
	HQADMIN_READ("hqadmins:read"),
	HQADMIN_WRITE("hqadmins:write");
	
	private final String permission;
	
	ApplicationUserPermission(String permission) {
		this.permission = permission;
	}
	
	public String getPermission() {
		return permission;
	}
	
	
}
