package edu.sru.group7.restaurantmanager.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum ApplicationUserRole {
	ADMIN(Sets.newHashSet(
						ApplicationUserPermission.CUSTOMER_WRITE,
						ApplicationUserPermission.CUSTOMER_READ)),
	CUSTOMER(Sets.newHashSet()),
	HQADMIN(Sets.newHashSet(ApplicationUserPermission.ADMIN_READ,
			ApplicationUserPermission.ADMIN_WRITE,
			ApplicationUserPermission.CUSTOMER_WRITE,
			ApplicationUserPermission.CUSTOMER_READ)),
	HQMANAGER(Sets.newHashSet()),
	SERVER(Sets.newHashSet());
	
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
