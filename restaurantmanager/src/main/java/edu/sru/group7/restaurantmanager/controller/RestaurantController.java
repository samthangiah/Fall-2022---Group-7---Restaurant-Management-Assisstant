package edu.sru.group7.restaurantmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.sru.group7.restaurantmanager.domain.Admins;
import edu.sru.group7.restaurantmanager.domain.Customers;
import edu.sru.group7.restaurantmanager.domain.Offices;
import edu.sru.group7.restaurantmanager.domain.Restaurants;
import edu.sru.group7.restaurantmanager.domain.Warehouses;
import edu.sru.group7.restaurantmanager.domain.Managers;
import edu.sru.group7.restaurantmanager.domain.Servers;
import edu.sru.group7.restaurantmanager.domain.Orders;
import edu.sru.group7.restaurantmanager.domain.Menu;
import edu.sru.group7.restaurantmanager.domain.Log;
import edu.sru.group7.restaurantmanager.repository.AdminRepository;
import edu.sru.group7.restaurantmanager.repository.CustomerRepository;
import edu.sru.group7.restaurantmanager.repository.OfficeRepository;
import edu.sru.group7.restaurantmanager.repository.RestaurantRepository;
import edu.sru.group7.restaurantmanager.repository.WarehouseRepository;
import edu.sru.group7.restaurantmanager.repository.ManagerRepository;
import edu.sru.group7.restaurantmanager.repository.ServerRepository;
import edu.sru.group7.restaurantmanager.repository.OrderRepository;
import edu.sru.group7.restaurantmanager.repository.MenuRepository;
import edu.sru.group7.restaurantmanager.repository.LogRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class RestaurantController {
    
    DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    private boolean isLoggedIn;
    
	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private ManagerRepository managerRepo;

	@Autowired
	private ServerRepository serverRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private MenuRepository menuRepo;
	
	@Autowired
    private LogRepository logRepo;
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired 
	private OfficeRepository officeRepo;
	
	@Autowired 
	private WarehouseRepository warehouseRepo;
	
	@Autowired 
	private RestaurantRepository restaurantRepo;

	//create an UserRepository instance - instantiation (new) is done by Spring
    public RestaurantController(RestaurantRepository restaurantRepo,
    							WarehouseRepository warehouseRepo,
    							OfficeRepository officeRepo,
    							CustomerRepository customerRepo, 
    							ManagerRepository managerRepo,
    							ServerRepository serverRepo, 
    							OrderRepository orderRepo,
    							MenuRepository menuRepo,
    							LogRepository logRepo) {
    	this.restaurantRepo = restaurantRepo;
    	this.warehouseRepo = warehouseRepo;
		this.customerRepo = customerRepo;
		this.managerRepo = managerRepo;
		this.serverRepo = serverRepo;
		this.officeRepo = officeRepo;
		this.orderRepo = orderRepo;
		this.menuRepo = menuRepo;
		this.logRepo = logRepo;
		isLoggedIn = false;
	}
    
    public boolean GetIsLoggedIn() {
    	return isLoggedIn;
    }
    
    public void SetIsLoggedIn(boolean isLoggedIn) {
    	this.isLoggedIn = isLoggedIn;
    }
    
    //index page
    @RequestMapping({"/"})
    public String homePage() {
    	
    	//For testing purposes, delete later
    	customerRepo.deleteAll();
    	adminRepo.deleteAll();
    	managerRepo.deleteAll();
    	serverRepo.deleteAll();
    	officeRepo.deleteAll();
    	restaurantRepo.deleteAll();
    	warehouseRepo.deleteAll();
    	
    	Admins admin = new Admins("Darth",
    			"Vader",
    			"Administrator@email.com",
    			"pass",
    			1); //office is 1
    	Managers manager = new Managers("Anakin",
    			"Skywalker",
    			"Manager@email.com",
    			"pass",
    			2); //restaurant is 2
    	Managers manager2 = new Managers("Luke",
    			"Skywalker",
    			"Manager2@email.com",
    			"pass",
    			2);
    	Servers server = new Servers("Obi-wan",
    			"Kenobi",
    			"server@email.com",
    			"pass",
    			2); // restaurant is 2
    	Servers server2 = new Servers("Baby",
    			"Yoda",
    			"server2@email.com",
    			"pass",
    			2); // restaurant is 2
    	
    	//String address, String zipcode, String city
    	
    	Offices office = new Offices("100 Central Loop",
    			"16057",
    			"Slippery Rock",
    			"PA");
    	
    	Restaurants restaurant = new Restaurants("100 Arrowhead Drive",
    			"16057",
    			"Slippery Rock",
    			"PA");
    	
    	Warehouses warehouse = new Warehouses("150 Branchton Road",
    			"16057",
    			"Slippery Rock",
    			"PA");
    	
    	adminRepo.save(admin);
    	managerRepo.save(manager);
    	managerRepo.save(manager2);
    	serverRepo.save(server);
    	serverRepo.save(server2);
    	officeRepo.save(office);
    	restaurantRepo.save(restaurant);
    	warehouseRepo.save(warehouse);
    	
    	addSampleOrder();
    	
    	return "index";
    }
    
    //temporary login page
    @RequestMapping({"/templogin"})
    public String tempLoginPage() {
    	SetIsLoggedIn(true);
    	return "tempcustlogin";
    }
    
    //temporary login page
    @RequestMapping({"/tempemployeelogin"})
    public String tempEmployeeLoginPage() {
    	SetIsLoggedIn(true);
    	return "temploginpage";
    }
    
    //temporary logout function
    @RequestMapping({"/templogout"})
    public String tempLogout() {
    	SetIsLoggedIn(false);
    	return "index";
    }
    
    @RequestMapping({"/employeesignin"})
    public String employeeSignIn() {
    	return "employeesignin";
    }
    
    //HQ admin home page
    @RequestMapping({"/HQ-admin-view"})
    public String showHQAdminPage() {
    	return "HQAdmin/HQ-admin-view";
    }
    
    //HQ admin all 3 locations view
    @RequestMapping({"/HQadmin-locations-view"})
    public String showLocationsPage() {
    	return "HQAdmin/HQadmin-locations-view";
    }
    
    //local admin home page
    @RequestMapping({"/local-admin-view"})
    public String showAdminPage() {
    	return "LocalAdmin/local-admin-view";
    }
    
    @RequestMapping({"/order-placement/cust-order"})
    public String showCustOrderPage() {
    	return"cust-order";
    }

    //local admin manager view
    @RequestMapping({"/admin-man-view"})
    public String showManList(Model model) {
    	model.addAttribute("managers", managerRepo.findAll());
    	return "LocalAdmin/admin-man-view";
    }
    
    //local admin server view
    @RequestMapping({"/admin-server-view"})
    public String showServerList(Model model) {
    	model.addAttribute("servers", serverRepo.findAll());
    	return "LocalAdmin/admin-server-view";
    }
    
    //local admin customer view
    @RequestMapping({"/admin-cust-view"})
    public String showUserList(Model model) {
        model.addAttribute("customers", customerRepo.findAll());
        return "LocalAdmin/admin-cust-view";
    }
    
    //HQ admin local admins view
    @RequestMapping({"/HQadmin-admin-view"})
    public String showAdminList(Model model) {
        model.addAttribute("admins", adminRepo.findAll());
        return "HQAdmin/HQadmin-admin-view";
    }
    
    //HQ admin offices view
    @RequestMapping({"/HQadmin-offices-view"})
    public String showOfficesList(Model model) {
        model.addAttribute("offices", officeRepo.findAll());
        return "HQAdmin/HQadmin-offices-view";
    }
    
    //HQ admin restaurants view
    @RequestMapping({"/HQadmin-restaurants-view"})
    public String showRestaurantList(Model model) {
        model.addAttribute("restaurants", restaurantRepo.findAll());
        return "HQAdmin/HQadmin-restaurants-view";
    }
    
    //HQ admin warehouses view
    @RequestMapping({"/HQadmin-warehouses-view"})
    public String showWarehouseList(Model model) {
        model.addAttribute("warehouses", warehouseRepo.findAll());
        return "HQAdmin/HQadmin-warehouses-view";
    }

    //add customer view
  	@RequestMapping({"/custsignup"})
      public String showCustSignUpForm(Customers customer) {
          return "LocalAdmin/add-customer";
      }
  	
  	//add server view
  	@RequestMapping({"/serversignup"})
    public String showServerSignUpForm(Servers server) {
        return "LocalAdmin/add-server";
    }
  	
  	//add manager view
  	@RequestMapping({"/mansignup"})
    public String showManagerSignUpForm(Managers manager) {
        return "LocalAdmin/add-LFmanager";
    }
  	
  	//add local admin view
  	@RequestMapping({"/adminsignup"})
    public String showAdminSignUpForm(Admins admin) {
        return "HQAdmin/add-LFadmin";
    }
  	
  	@RequestMapping({"/officesignup"})
    public String showOfficeSignUpForm(Offices office) {
        return "HQAdmin/add-office";
    }
  	
  	@RequestMapping({"/restaurantsignup"})
    public String showRestaurantSignUpForm(Restaurants restaurant) {
        return "HQAdmin/add-restaurant";
    }
  	
  	@RequestMapping({"/warehousesignup"})
    public String showWarehouseSignUpForm(Warehouses warehouse) {
        return "HQAdmin/add-warehouse";
    }
  	
    //Mapping for the /signup URL - to add a user
    @RequestMapping({"/addcustomer"})
    public String addCust(@Validated Customers customers, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "LocalAdmin/add-customer";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    if (GetIsLoggedIn()) {
	    	log.setLocation(getUserLocation());
		    log.setUserId(getUserUID());
	    }
	    else {
		    log.setLocation(customers.getLocation());
		    log.setUserId(-1);
	    }
	    log.setAction("Create new customer account");
	    log.setActionId(customers.getId());
	    logRepo.save(log);
        
        customerRepo.save(customers);
        return "redirect:/admin-cust-view";
    }

    @RequestMapping({"/addserver"})
    public String addServer(@Validated Servers server, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "LocalAdmin/add-server";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(getUserLocation());
		log.setUserId(getUserUID());
	    log.setAction("Create new server account");
	    log.setActionId(server.getId());
	    logRepo.save(log);

        serverRepo.save(server);
        return "redirect:/admin-server-view";
    }

    @RequestMapping({"/addmanager"})
    public String addManager(@Validated Managers manager, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "LocalAdmin/add-LFmanager";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(getUserLocation());
		log.setUserId(getUserUID());
	    log.setAction("Create new manager account");
	    log.setActionId(manager.getId());
	    logRepo.save(log);

        managerRepo.save(manager);
        return "redirect:/admin-man-view";
    }
    
    @RequestMapping({"/addadmin"})
    public String addAdmin(@Validated Admins admin, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "HQAdmin/add-LFadmin";
        }
        
        try {
        adminRepo.save(admin);
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(1 /*HQ*/);
		log.setUserId(getUserUID());
	    log.setAction("Create new admin account");
	    log.setActionId(admin.getId());
	    logRepo.save(log);
        }
        catch (Exception e){
        	result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        	return "HQAdmin/add-LFadmin";
        }
        
        return "redirect:/HQadmin-admin-view";
    }
    
    @RequestMapping({"/addoffice"})
    public String addOffice(@Validated Offices office, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "HQAdmin/add-office";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(1 /*HQ*/);
	    log.setUserId(getUserUID());
	    log.setAction("Add new office");
	    log.setActionId(office.getId());
	    logRepo.save(log);

        officeRepo.save(office);
        return "redirect:/HQadmin-offices-view";
    }
    
    @RequestMapping({"/addrestaurant"})
    public String addRestaurant(@Validated Restaurants restaurant, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "HQAdmin/add-restaurant";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(1 /*HQ*/);
	    log.setUserId(getUserUID());
	    log.setAction("Add new restaurant");
	    log.setActionId(restaurant.getId());
	    logRepo.save(log);

        restaurantRepo.save(restaurant);
        return "redirect:/HQadmin-restaurants-view";
    }
    
    @RequestMapping({"/addwarehouse"})
    public String addWarehouse(@Validated Warehouses warehouse, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "HQAdmin/add-warehouse";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(1 /*HQ*/);
	    log.setUserId(getUserUID());
	    log.setAction("Add new warehouse");
	    log.setActionId(warehouse.getId());
	    logRepo.save(log);

        warehouseRepo.save(warehouse);
        return "redirect:/HQadmin-warehouses-view";
    }
    
    @GetMapping("/HQadminadminedit/{id}")
    public String showUpdateAdminForm(@PathVariable("id") long id, Model model) {
        Admins admin = adminRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid admin Id:" + id));

        model.addAttribute("admin", admin);
        return "HQadmin/update-LFadmin";
    }
    
    @GetMapping("/HQadminofficeedit/{id}")
    public String showUpdateOfficeForm(@PathVariable("id") long id, Model model) {
        Offices office = officeRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid office Id:" + id));

        model.addAttribute("office", office);
        return "HQadmin/update-office";
    }
    
    @GetMapping("/HQadminrestaurantedit/{id}")
    public String showUpdateRestaurantForm(@PathVariable("id") long id, Model model) {
        Restaurants restaurant = restaurantRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant Id:" + id));

        model.addAttribute("restaurant", restaurant);
        return "HQadmin/update-restaurant";
    }
    
    @GetMapping("/HQadminwarehouseedit/{id}")
    public String showUpdateWarehouseForm(@PathVariable("id") long id, Model model) {
        Warehouses warehouse = warehouseRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid warehouse Id:" + id));

        model.addAttribute("warehouse", warehouse);
        return "HQadmin/update-warehouse";
    }

    //Mapping for the /edit/user URL to edit a user 
    @GetMapping("/localadmincustedit/{id}")
    public String showUpdateCustForm(@PathVariable("id") long id, Model model) {
        Customers customer = customerRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));

        model.addAttribute("customer", customer);
        return "LocalAdmin/update-customer";
    }

    @GetMapping("/localadminserveredit/{id}")
    public String showUpdateServerForm(@PathVariable("id") long id, Model model) {
        Servers server = serverRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid server Id:" + id));

        model.addAttribute("server", server);
        return "LocalAdmin/update-server";
    }

    @GetMapping("/localadminmanedit/{id}")
    public String showUpdateManagerForm(@PathVariable("id") long id, Model model) {
        Managers manager = managerRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid manager Id:" + id));

        model.addAttribute("manager", manager);
        return "LocalAdmin/update-LFmanager";
    }

    //Mapping for the /update/id URL to update a user 
    @PostMapping("/localadmincustupdate/{id}")
    public String updateCust(@PathVariable("id") long id, @Validated Customers customer, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            customer.setId(id);
            return "LocalAdmin/update-customer";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(getUserLocation());
	    log.setUserId(getUserUID());
	    log.setAction("Update customer account");
	    log.setActionId(customer.getId());
	    logRepo.save(log);
        
        customerRepo.save(customer);
        return "redirect:/admin-cust-view";
    }

    @PostMapping("/localadminserverupdate/{id}")
    public String updateServer(@PathVariable("id") long id, @Validated Servers server, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            server.setId(id);
            return "LocalAdmin/update-server";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(getUserLocation());
	    log.setUserId(getUserUID());
	    log.setAction("Update server account");
	    log.setActionId(server.getId());
	    logRepo.save(log);

        serverRepo.save(server);
        return "redirect:/admin-server-view";
    }

    @PostMapping("/localadminmanupdate/{id}")
    public String updateManager(@PathVariable("id") long id, @Validated Managers manager, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            manager.setId(id);
            return "LocalAdmin/update-LFmanager";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(getUserLocation());
	    log.setUserId(getUserUID());
	    log.setAction("Update manager account");
	    log.setActionId(manager.getId());
	    logRepo.save(log);

        managerRepo.save(manager);
        return "redirect:/admin-man-view";
    }
    
    @PostMapping("/HQadminadminupdate/{id}")
    public String updateAdmin(@PathVariable("id") long id, @Validated Admins admin, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            admin.setId(id);
            return "HQAdmin/update-LFadmin";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(getUserLocation());
	    log.setUserId(getUserUID());
	    log.setAction("Update admin account");
	    log.setActionId(admin.getId());
	    logRepo.save(log);

        adminRepo.save(admin);
        return "redirect:/HQadmin-admin-view";
    }
    
    @PostMapping("/HQadminofficeupdate/{id}")
    public String updateOffice(@PathVariable("id") long id, @Validated Offices office, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            office.setId(id);
            return "HQAdmin/update-office";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(1 /*HQ*/);
	    log.setUserId(getUserUID());
	    log.setAction("Update office information");
	    log.setActionId(office.getId());
	    logRepo.save(log);

        officeRepo.save(office);
        return "redirect:/HQadmin-offices-view";
    }
    
    @PostMapping("/HQadminwarehouseupdate/{id}")
    public String updateWarehouse(@PathVariable("id") long id, @Validated Warehouses warehouse, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
        	warehouse.setId(id);
            return "HQAdmin/update-warehouse";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(1 /*HQ*/);
	    log.setUserId(getUserUID());
	    log.setAction("Update warehouse information");
	    log.setActionId(warehouse.getId());
	    logRepo.save(log);

        warehouseRepo.save(warehouse);
        return "redirect:/HQadmin-warehouses-view";
    }
    
    @PostMapping("/HQadminrestaurantupdate/{id}")
    public String updateRestaurant(@PathVariable("id") long id, @Validated Restaurants restaurant, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
        	restaurant.setId(id);
            return "HQAdmin/update-restaurant";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(1 /*HQ*/);
	    log.setUserId(getUserUID());
	    log.setAction("Update restaurant information");
	    log.setActionId(restaurant.getId());
	    logRepo.save(log);

        restaurantRepo.save(restaurant);
        return "redirect:/HQadmin-restaurants-view";
    }

    //Mapping for the /delete/id URL to delete a user     
    @GetMapping("/localadmincustdelete/{id}")
    public String deleteCust(@PathVariable("id") long id, Model model) {
        Customers customer = customerRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(getUserLocation());
	    log.setUserId(getUserUID());
	    log.setAction("Delete customer account");
	    log.setActionId(customer.getId());
	    logRepo.save(log);
        
        customerRepo.delete(customer);
        return "redirect:/admin-cust-view";
    }

    @GetMapping("/localadminserverdelete/{id}")
    public String deleteServer(@PathVariable("id") long id, Model model) {
        Servers server = serverRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid server Id:" + id));
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(getUserLocation());
	    log.setUserId(getUserUID());
	    log.setAction("Delete server account");
	    log.setActionId(server.getId());
	    logRepo.save(log);
        
        serverRepo.delete(server);
        return "redirect:/admin-server-view";
    }

    @GetMapping("/localadminmandelete/{id}")
    public String deleteManager(@PathVariable("id") long id, Model model) {
        Managers manager = managerRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid manager Id:" + id));
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(getUserLocation());
	    log.setUserId(getUserUID());
	    log.setAction("Delete manager account");
	    log.setActionId(manager.getId());
	    logRepo.save(log);
        
        managerRepo.delete(manager);
        return "redirect:/admin-man-view";
    }
    
    @GetMapping("/HQadminofficedelete/{id}")
    public String deleteOffice(@PathVariable("id") long id, Model model) {
    	Offices office = officeRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid office Id:" + id));
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(1 /*HQ*/);
        log.setUserId(getUserUID());
	    log.setAction("Delete office");
	    log.setActionId(office.getId());
	    logRepo.save(log);
        
        officeRepo.delete(office);
        return "redirect:/HQadmin-offices-view";
    }
    
    @GetMapping("/HQadminrestaurantdelete/{id}")
    public String deleteRestaurant(@PathVariable("id") long id, Model model) {
    	Restaurants restaurant = restaurantRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant Id:" + id));
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(1 /*HQ*/);
	    log.setUserId(getUserUID());
	    log.setAction("Delete restaurant");
	    log.setActionId(restaurant.getId());
	    logRepo.save(log);
        
    	restaurantRepo.delete(restaurant);
        return "redirect:/HQadmin-restaurants-view";
    }
    
    @GetMapping("/HQadminwarehousedelete/{id}")
    public String deleteWarehouse(@PathVariable("id") long id, Model model) {
    	Warehouses warehouse = warehouseRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid warehouse Id:" + id));
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(1 /*HQ*/);
	    log.setUserId(getUserUID());
	    log.setAction("Delete warehouse");
	    log.setActionId(warehouse.getId());
	    logRepo.save(log);
        
    	warehouseRepo.delete(warehouse);
        return "redirect:/HQadmin-warehouses-view";
    }
    
    @GetMapping("/HQadminadmindelete/{id}")
    public String deleteAdmin(@PathVariable("id") long id, Model model) {
    	Admins admin = adminRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid admin Id:" + id));
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(1 /*HQ*/);
	    log.setUserId(getUserUID());
	    log.setAction("Delete admin account");
	    log.setActionId(admin.getId());
	    logRepo.save(log);
        
    	adminRepo.delete(admin);
        return "redirect:/HQadmin-admin-view";
    }
    
    @GetMapping("/deleteorder/{id}")
    public String deleteOrder(@PathVariable("id") long id, Model model) {
    	Orders order = orderRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(getUserLocation());
	    log.setUserId(getUserUID());
	    log.setAction("Delete order");
	    log.setActionId(order.getId());
	    logRepo.save(log);
        
    	orderRepo.delete(order);
        return "redirect:/servingstaffview";
    }
    
    //For testing purposes
    @GetMapping("/add-sample-order")
    public String addSampleOrder() {
    	orderRepo.deleteAll();
    	menuRepo.deleteAll();
    	
    	Orders order = new Orders();
    	Menu item = new Menu();
    	Customers cust = new Customers();
    	
    	order.setDate(date.format(LocalDateTime.now()));
    	order.setPrice(0.00F);
    	order.setCustomer("customer@email.com");
    	order.setItems("items");
    	order.setInstructions("instructions");
    	order.setLocation(0);
    	
    	item.setName("name");
    	item.setEntree("entree");
    	item.setSides("sides");
    	item.setPrice(0.00F);
    	item.setAvailability(true);
    	item.setQuantity(1);
    	
    	cust.setEmail("customer@email.com");
    	cust.setId(5);
    	cust.setFirstName("Test");
    	cust.setLastName("Customer");
    	cust.setLocation(2);
    	cust.setPassword("password");
    	cust.setRewardsAvailable(10);
    	
    	orderRepo.save(order);
    	menuRepo.save(item);
    	customerRepo.save(cust);
    	
    	addCustLocationsForSimulatedLogin();
    	
    	return "employeesignin";
    }
    
    //For testing purposes
    public void addCustLocationsForSimulatedLogin() {
    	customerRepo.deleteAll();
    	
    	Customers hqUser = new Customers();
    	hqUser.setId(1);
    	hqUser.setEmail("hquser@email.com");
    	hqUser.setFirstName("John");
    	hqUser.setLastName("Doe");
    	hqUser.setPassword("pass");
    	hqUser.setLocation(1);
    	customerRepo.save(hqUser);
    	
    	Customers restaurant1User = new Customers();
    	restaurant1User.setId(2);
    	restaurant1User.setEmail("restaurantuser@email.com");
    	restaurant1User.setFirstName("rest");
    	restaurant1User.setLastName("Customer");
    	restaurant1User.setPassword("pass");
    	restaurant1User.setLocation(2);
    	customerRepo.save(restaurant1User);
    	
    	Customers office1User = new Customers();
    	office1User.setId(3);
    	office1User.setEmail("officeuser@email.com");
    	office1User.setFirstName("office");
    	office1User.setLastName("Customer");
    	office1User.setPassword("pass");
    	office1User.setLocation(3);
    	customerRepo.save(office1User);
    	
    	Customers warehouse1User = new Customers();
    	warehouse1User.setId(4);
    	warehouse1User.setEmail("warehouseuser@email.com");
    	warehouse1User.setFirstName("warehouse");
    	warehouse1User.setLastName("Customer");
    	warehouse1User.setPassword("pass");
    	warehouse1User.setLocation(4);
    	customerRepo.save(warehouse1User);
    }
    
    @GetMapping("/servingstaffview")
    public String showServerView(Model model) {
        model.addAttribute("orders", orderRepo.findAll());
        model.addAttribute("menu", menuRepo.findAll());
        return "LocalServingStaff/serving-staff-view";
    }
    
    //I wrote this unintuitively but the pathvariable "id" is supposed to be the customer's email since 
    //order.customer is a String and you can't just do customerRepo.findByEmail() or anything like that, it
    //only works with id so I just loop through the customers for one with a matching email to get their id
    @GetMapping("/serverviewcustinfo/{id}")
    public String showCustInfo(@PathVariable("id") String orderCust, Model model) {
    	Iterable<Customers> custs = customerRepo.findAll();
    	List<Customers> temp = new ArrayList<Customers>();
    	boolean flag = false;
    	for (Customers i : custs) {
    		if (i.getEmail().equals(orderCust)) {
    			temp.add(i);
    			flag = true;
    		}
    	}
    	if (!flag) {
    		return "redirect:/servingstaffview";
    	}
    	else {
    		model.addAttribute("customers", temp);
    	}
        return "LocalServingStaff/server-cust-view";
    }
    
    @GetMapping("/updatemenuitem/{id}")
    public String showUpdateMenuItemForm(@PathVariable("id") long id, Model model) {
        Menu item = menuRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid menu Id: " + id));
        
        model.addAttribute("item", item);
        return "LocalServingStaff/update-menu-item";
    }
    
    @PostMapping("/updatemenuitem/{id}")
    public String updateMenuItem(@PathVariable("id") long id, @Validated Menu item, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            item.setId(id);
            return "LocalServingStaff/update-menu-item";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(getUserLocation());
	    log.setUserId(getUserUID());
	    log.setAction("Update menu item");
	    log.setActionId(item.getId());
	    logRepo.save(log);
        
        menuRepo.save(item);
        return "redirect:/servingstaffview";
    }
    
    @GetMapping("/logview/{id}") //get userid currently logged in from customer table
    public String showLog(@PathVariable("id") long id, Model model) {
	    Customers cust = customerRepo.findById(id)
	    	.orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
	    Iterable<Log> fullLog = logRepo.findAll();
	    if (cust.getLocation() == 1 /*HQ Location*/ ) {
		    model.addAttribute("log", fullLog);
	    }
	    else {
		    List<Log> localLog = new ArrayList<Log>();
		    for (Log i : fullLog) {
			    if (i.getLocation() == cust.getLocation()) {
				    localLog.add(i);
			    }
		    }
		    model.addAttribute("log", (Iterable<Log>) localLog);
	    }
	    return "log-view";
    }
    
    //local manager home page
    @RequestMapping({"/local-manager-view"})
    public String showManagerPage() {
    	return "LocalManager/local-manager-view";
    }
    
    @RequestMapping({"/manager-cust-view"})
    public String localManShowUserList(Model model) {
        model.addAttribute("customers", customerRepo.findAll());
        return "LocalManager/manager-cust-view";
    }
    
    @GetMapping("/localmanagercustedit/{id}")
    public String localManShowUpdateCustForm(@PathVariable("id") long id, Model model) {
        Customers customer = customerRepo.findById(id)
        		.orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        
        model.addAttribute("customer", customer);
        return "LocalManager/update-customer";
    }
    
    @PostMapping("/localmanagercustupdate/{id}")
    public String localManUpdateCust(@PathVariable("id") long id, @Validated Customers customer, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            customer.setId(id);
            return "LocalManager/update-customer";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(getUserLocation());
	    log.setUserId(getUserUID());
	    log.setAction("Update customer account");
	    log.setActionId(customer.getId());
	    logRepo.save(log);
        
        customerRepo.save(customer);
        return "redirect:/manager-cust-view";
    }
    
    @GetMapping("/localmanagercustdelete/{id}")
    public String localManDeleteCust(@PathVariable("id") long id, Model model) {
        Customers customer = customerRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(getUserLocation());
	    log.setUserId(getUserUID());
	    log.setAction("Delete customer account");
	    log.setActionId(customer.getId());
	    logRepo.save(log);
        
        customerRepo.delete(customer);
        return "redirect:/manager-cust-view";
    }
    
    @RequestMapping({"/manager-menu-view"})
    public String localManShowMenu(Model model) {
        model.addAttribute("menu", menuRepo.findAll());
        return "LocalManager/manager-menu-view";
    }
    
    @RequestMapping({"/localmanageraddmenu"})
    public String showMenuAddForm(Menu menu) {
        return "LocalManager/add-menu-item";
    }
  	
    @RequestMapping({"/addmenuitem"})
    public String addMenuItem(@Validated Menu menu, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "LocalManager/add-menu-item";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
		log.setLocation(getUserLocation());
		log.setUserId(getUserUID());
	    log.setAction("Create new menu item");
	    log.setActionId(menu.getId());
	    logRepo.save(log);
        
        menuRepo.save(menu);
        return "redirect:/manager-menu-view";
    }
    
    @GetMapping("/localmanagereditmenu/{id}")
    public String showLocalManUpdateMenuItemForm(@PathVariable("id") long id, Model model) {
        Menu item = menuRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid menu Id: " + id));
        
        model.addAttribute("item", item);
        return "LocalManager/update-menu";
    }
    
    @PostMapping("/localmanagerupdatemenu/{id}")
    public String LocalManUpdateMenu(@PathVariable("id") long id, @Validated Menu item, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            item.setId(id);
            return "LocalManager/update-menu";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(getUserLocation());
	    log.setUserId(getUserUID());
	    log.setAction("Update menu item");
	    log.setActionId(item.getId());
	    logRepo.save(log);
        
        menuRepo.save(item);
        return "redirect:/manager-menu-view";
    }
    
    @GetMapping("/localmanagerdeletemenu/{id}")
    public String deleteMenuItem(@PathVariable("id") long id, Model model) {
        Menu item = menuRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid Menu Id:" + id));
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(getUserLocation());
	    log.setUserId(getUserUID());
	    log.setAction("Delete menu item");
	    log.setActionId(item.getId());
	    logRepo.save(log);
        
        menuRepo.delete(item);
        return "redirect:/manager-menu-view";
    }
    
    @RequestMapping({"/manager-server-view"})
    public String localManShowServers(Model model) {
        model.addAttribute("servers", serverRepo.findAll());
        return "LocalManager/manager-server-view";
    }
    
    @GetMapping("/localmanagerserveredit/{id}")
    public String showLocalManUpdateServerForm(@PathVariable("id") long id, Model model) {
        Servers server = serverRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid server Id:" + id));

        model.addAttribute("server", server);
        return "LocalManager/update-server";
    }

    @PostMapping("/localmanagerserverupdate/{id}")
    public String localManUpdateServer(@PathVariable("id") long id, @Validated Servers server, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            server.setId(id);
            return "LocalManager/update-server";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(getUserLocation());
	    log.setUserId(getUserUID());
	    log.setAction("Update server account");
	    log.setActionId(server.getId());
	    logRepo.save(log);

        serverRepo.save(server);
        return "redirect:/manager-server-view";
    }
    
    @GetMapping("/localmanagerserverdelete/{id}")
    public String localManDeleteServer(@PathVariable("id") long id, Model model) {
        Servers server = serverRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid server Id:" + id));
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(getUserLocation());
	    log.setUserId(getUserUID());
	    log.setAction("Delete server account");
	    log.setActionId(server.getId());
	    logRepo.save(log);
        
        serverRepo.delete(server);
        return "redirect:/manager-server-view";
    }
    
    //HQ Manager home page
    @RequestMapping({"/HQ-manager-view"})
    public String showHQManagerPage() {
    	return "HQManager/HQ-manager-view";
    }
    
    @RequestMapping({"/HQmanager-managers-view"})
    public String hqManShowManagers(Model model) {
        model.addAttribute("managers", managerRepo.findAll());
        return "HQManager/HQManager-managers-view";
    }
    
    @GetMapping("/HQmanagermanedit/{id}")
    public String showHQManUpdateManagerForm(@PathVariable("id") long id, Model model) {
        Managers manager = managerRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid manager Id:" + id));

        model.addAttribute("manager", manager);
        return "HQManager/update-LFmanager";
    }
    
    @PostMapping("/hqmanagermanupdate/{id}")
    public String hqManUpdateManager(@PathVariable("id") long id, @Validated Managers manager, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            manager.setId(id);
            return "HQManager/update-LFmanager";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(1 /*HQ*/);
	    log.setUserId(getUserUID());
	    log.setAction("Update manager account");
	    log.setActionId(manager.getId());
	    logRepo.save(log);

        managerRepo.save(manager);
        return "redirect:/HQmanager-managers-view";
    }
    
    @GetMapping("/HQmanagermandelete/{id}")
    public String hqManDeleteManager(@PathVariable("id") long id, Model model) {
        Managers manager = managerRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid manager Id:" + id));
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
	    log.setLocation(1 /*HQ*/);
	    log.setUserId(getUserUID());
	    log.setAction("Delete manager account");
	    log.setActionId(manager.getId());
	    logRepo.save(log);
        
        managerRepo.delete(manager);
        return "redirect:/HQmanager-managers-view";
    }
    
    @RequestMapping({"/HQmanageraddmanager"})
    public String showLFManagerAddForm(Managers manager) {
        return "HQManager/add-LFmanager";
    }
  	
    @RequestMapping({"/addlfmanager"})
    public String addLFManager(@Validated Managers manager, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "HQManager/add-LFmanager";
        }
        
        Log log = new Log();
	    log.setDate(date.format(LocalDateTime.now()));
	    log.setTime(time.format(LocalDateTime.now()));
		log.setLocation(1 /*HQ*/);
		log.setUserId(getUserUID());
	    log.setAction("Create new LF manager");
	    log.setActionId(manager.getId());
	    logRepo.save(log);
        
        managerRepo.save(manager);
        return "redirect:/HQmanager-managers-view";
    }
    
    @RequestMapping({"/HQmanager-location-view"})
    public String showHQManagerLocationPage() {
    	return "HQManager/HQManager-locations-view";
    }
    
    @RequestMapping({"/HQmanager-restaurants-view"})
    public String hqManShowRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantRepo.findAll());
        return "HQManager/HQManager-restaurants-view";
    }
    
    @RequestMapping({"/HQmanager-offices-view"})
    public String hqManShowOffices(Model model) {
        model.addAttribute("offices", officeRepo.findAll());
        return "HQManager/HQManager-offices-view";
    }
    
    @RequestMapping({"/HQmanager-warehouses-view"})
    public String hqManShowWarehouses(Model model) {
        model.addAttribute("warehouses", warehouseRepo.findAll());
        return "HQManager/HQManager-warehouses-view";
    }
    
    //TODO
    public int getUserLocation() {
    	return 0; //get user location from customer table once login implemented
    }
    
    //TODO
    public long getUserUID() {
    	return 0; //get userid from customer table once login implemented
    }
    
}