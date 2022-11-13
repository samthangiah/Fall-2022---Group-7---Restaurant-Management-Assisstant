package edu.sru.group7.restaurantmanager.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum ApplicationUserRole {
	HQADMIN(Sets.newHashSet(ApplicationUserPermission.HQADMIN_READ,
							ApplicationUserPermission.HQADMIN_WRITE,
							ApplicationUserPermission.ADMIN_READ,
							ApplicationUserPermission.ADMIN_WRITE,
							ApplicationUserPermission.HQMANAGER_READ,
							ApplicationUserPermission.HQMANAGER_WRITE,
							ApplicationUserPermission.MANAGER_READ,
							ApplicationUserPermission.MANAGER_WRITE,
							ApplicationUserPermission.SERVER_READ,
							ApplicationUserPermission.SERVER_WRITE,
							ApplicationUserPermission.CUSTOMER_WRITE,
							ApplicationUserPermission.CUSTOMER_READ)),
	ADMIN(Sets.newHashSet(ApplicationUserPermission.ADMIN_READ,
							ApplicationUserPermission.ADMIN_WRITE,
							ApplicationUserPermission.MANAGER_READ,
							ApplicationUserPermission.MANAGER_WRITE,
							ApplicationUserPermission.SERVER_READ,
							ApplicationUserPermission.SERVER_WRITE,
							ApplicationUserPermission.CUSTOMER_WRITE,
							ApplicationUserPermission.CUSTOMER_READ)),
	HQMANAGER(Sets.newHashSet(ApplicationUserPermission.HQMANAGER_READ,
							ApplicationUserPermission.HQMANAGER_WRITE,
							ApplicationUserPermission.MANAGER_READ,
							ApplicationUserPermission.MANAGER_WRITE,
							ApplicationUserPermission.SERVER_READ,
							ApplicationUserPermission.SERVER_WRITE,
							ApplicationUserPermission.CUSTOMER_WRITE,
							ApplicationUserPermission.CUSTOMER_READ)),
	MANAGER(Sets.newHashSet(ApplicationUserPermission.MANAGER_READ,
							ApplicationUserPermission.MANAGER_WRITE,
							ApplicationUserPermission.SERVER_READ,
							ApplicationUserPermission.SERVER_WRITE,
							ApplicationUserPermission.CUSTOMER_WRITE,
							ApplicationUserPermission.CUSTOMER_READ)),
	SERVER(Sets.newHashSet(ApplicationUserPermission.SERVER_READ,
							ApplicationUserPermission.SERVER_WRITE,
							ApplicationUserPermission.CUSTOMER_WRITE,
							ApplicationUserPermission.CUSTOMER_READ)),
	CUSTOMER(Sets.newHashSet(ApplicationUserPermission.CUSTOMER_WRITE,
							ApplicationUserPermission.CUSTOMER_READ)), 
	WAREHOUSEMANAGER(Sets.newHashSet(ApplicationUserPermission.WAREHOUSEMANAGER_WRITE,
							ApplicationUserPermission.WAREHOUSEMANAGER_READ));
	
	private final Set<ApplicationUserPermission> permissions;

	private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}
	
	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}

	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
					.collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}
	
}
