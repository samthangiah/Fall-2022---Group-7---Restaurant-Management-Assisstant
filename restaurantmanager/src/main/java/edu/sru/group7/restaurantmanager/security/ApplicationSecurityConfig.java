
package edu.sru.group7.restaurantmanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; 
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter; 
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.sru.group7.restaurantmanager.authentication.ApplicationUserService;

@SuppressWarnings("deprecation")

@Configuration

@EnableWebSecurity public class ApplicationSecurityConfig extends
WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private final ApplicationUserService applicationUserService;

	@Autowired public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService)
	{ this.passwordEncoder = passwordEncoder;
	this.applicationUserService = applicationUserService; }

	//Directs user to login page upon 403 permissions error automatically
	protected void configure(HttpSecurity http) throws Exception { 
		http
			.authorizeRequests() 
			/* Pages accessible to any authority including guest users */
			.antMatchers("/", "/index", "/css/*", "/js/*", "/img/*", "/assets/*", "/403", "/login", "/custsignin",
					"/processcredentials", "/custregistrationpage", "/addregisteredcustomer", "/add-sample-order", 
					"/showmenu", "/contact", "/Customer-ordertype-view", "/ordersuccessful", "/pay", "/processpayment",
					"/processpaypal", "/addorder", "/redeem", "/addNewOrder", "/addtoorder/*", "/editcart/*",
					"/Customer-cart-view", "/ordereditcart/*").permitAll()
			
			/* Pages accessible to any logged in user */
			.antMatchers("/loggedinhome", "/showlogout", "/changeuserpass", "/updateuserpass/*", "/custviewinfo",
					"/editcustomer/*", "/usercustomerupdate/*", "/rewardsinfo", "/custorderinfo").hasAnyRole(ApplicationUserRole.CUSTOMER.name(),
					ApplicationUserRole.ADMIN.name(), ApplicationUserRole.HQADMIN.name(), ApplicationUserRole.MANAGER.name(),
					ApplicationUserRole.HQMANAGER.name(), ApplicationUserRole.SERVER.name())
			
			/* Pages accessible to only users with the HQADMIN role */
			.antMatchers( "/HQadmin-locations-view", "/HQadmin-admin-view", "/HQadmin-offices-view",
					"/HQadmin-restaurants-view", "/HQadmin-warehouses-view", "/adminsignup", "/officesignup",
					"/restaurantsignup", "/warehousesignup", "/addadmin", "/addoffice", "/addrestaurant",
					"/addwarehouse", "/HQadminadminedit/*", "/HQadminofficeedit/*", "/HQadminrestaurantedit/*",
					"/HQadminwarehouseedit/*", "/HQadminadminupdate/*", "/HQadminofficeupdate/*", "/HQadminwarehouseupdate/*",
					"/HQadminrestaurantupdate/*", "/HQadminofficedelete/*", "/HQadminrestaurantdelete/*",
					"/HQadminwarehousedelete/*", "/HQadminadmindelete/*", "/hqlogadminview").hasAnyRole(ApplicationUserRole.HQADMIN.name())
			
			/* Pages accessible to only users with the ADMIN or HQADMIN role */
			.antMatchers("/order-placement/cust-order", "/admin-man-view", "/admin-server-view",
					"/admin-cust-view", "/custsignup", "/serversignup", "/mansignup", "/addcustomer",
					"/addserver", "/addmanager", "/localadmincustedit/*", "/localadminserveredit/*",
					"/localadminmanedit/*", "/localadmincustupdate/*", "/localadminserverupdate/*",
					"/localadminmanupdate/*", "/localadmincustdelete/*", "/localadminserverdelete/*",
					"/localadminmandelete/*", "/logadminview").hasAnyRole(ApplicationUserRole.ADMIN.name(), 
							ApplicationUserRole.HQADMIN.name())
			
			/* Pages accessible to any staff users */
			.antMatchers("/deleteorder/*", "/servingstaffview", "/serverviewcustinfo/*", "/updatemenuitem/*",
					"/clockin", "/clockout").hasAnyRole(ApplicationUserRole.SERVER.name(), ApplicationUserRole.ADMIN.name(), 
					ApplicationUserRole.MANAGER.name(), ApplicationUserRole.HQADMIN.name(), ApplicationUserRole.HQMANAGER.name())
			
			/* Pages accessible to only Manager or Admin users */
			.antMatchers("/servingstaffviewview", "/logview", "/manager-cust-view",
					"/localmanagercustedit/*", "/localmanagercustupdate/*", "/localmanagercustdelete/*",
					"/manager-menu-view", "/manager-server-view","/localmanagerserveredit/*", "/localmanagerserverupdate/*", 
					"/localmanagerserverdelete/*", "/manager-inventory-view", "/managerinventoryupdate/*", "/managerinventoryedit/*", 
					"/update-inventory").hasAnyRole(ApplicationUserRole.MANAGER.name(),
							ApplicationUserRole.HQMANAGER.name(), ApplicationUserRole.ADMIN.name(), ApplicationUserRole.HQADMIN.name())
			
			/* Pages accessible to only HQ staff */
			.antMatchers("/hqlogview", "/HQmanager-managers-view",
					"/HQmanagermanedit/*", "/HQmanagermanupdate/*", "/HQmanagermandelete/*", "/HQmanageraddmanager",
					"/addlfmanager", "/HQmanager-restaurants-view", "/HQmanager-offices-view", 
					"/HQmanager-warehouses-view").hasAnyRole(ApplicationUserRole.HQMANAGER.name(), ApplicationUserRole.HQADMIN.name())
			
			/* Pages accessible to only users with WAREHOUSEMANAGER role */
			.antMatchers("/warehouseman-shipment-view", "/warehouseman-log-view", "/warehouseman-employees-view", 
					"warehouseman-inventory-view").hasAnyRole(ApplicationUserRole.WAREHOUSEMANAGER.name())
			
			.anyRequest() 
			.authenticated() 
			.and() 
			.formLogin()
			.successForwardUrl("/loggedinredirect")
			.and()
			.logout()
				.logoutSuccessUrl("/showlogout")
				.clearAuthentication(true)
				.invalidateHttpSession(true);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(applicationUserService);
		return provider;
		
	}

}