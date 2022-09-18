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

@Controller
public class RestaurantController {
    
    DateTimeFormatter date = DateTimeFormatter.ofPattern("ISO_LOCAL_DATE");
    DateTimeFormatter time = DateTimeFormatter.ofPattern("ISO_LOCAL_TIME");
    
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
	}
    
    //index page
    @RequestMapping({"/"})
    public String homePage() {
    	return "index";
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
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    else {
		    log.location = customers.location;
		    log.userId = "Guest";
	    }
	    log.action = "Create new customer account";
	    log.actionId = customers.id;
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
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    else {
		    log.location = server.location;
		    log.userId = "Guest";
	    }
	    log.action = "Create new server account";
	    log.actionId = server.id;
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
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    else {
		    log.location = manager.location;
		    log.userId = "Guest";
	    }
	    log.action = "Create new manager account";
	    log.actionId = manager.id;
	    logRepo.save(log);

        managerRepo.save(manager);
        return "redirect:/admin-man-view";
    }
    
    @RequestMapping({"/addadmin"})
    public String addAdmin(@Validated Admins admin, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "HQAdmin/add-LFadmin";
        }
        
        Log log = new Log();
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    else {
		    log.location = admin.location;
		    log.userId = "Guest";
	    }
	    log.action = "Create new admin account";
	    log.actionId = admin.id;
	    logRepo.save(log);

        adminRepo.save(admin);
        return "redirect:/HQadmin-admin-view";
    }
    
    @RequestMapping({"/addoffice"})
    public String addOffice(@Validated Offices office, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "HQAdmin/add-office";
        }
        
        Log log = new Log();
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    log.action = "Add new office";
	    log.actionId = office.id;
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
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    log.action = "Add new restaurant";
	    log.actionId = restaurant.id;
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
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    log.action = "Add new warehouse";
	    log.actionId = warehouse.id;
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
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    log.action = "Update customer account";
	    log.actionId = customer.id;
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
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    log.action = "Update server account";
	    log.actionId = server.id;
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
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    log.action = "Update manager account";
	    log.actionId = manager.id;
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
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    log.action = "Update admin account";
	    log.actionId = admin.id;
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
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    log.action = "Update office information";
	    log.actionId = office.id;
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
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    log.action = "Update warehouse information";
	    log.actionId = warehouse.id;
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
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    log.action = "Update restaurant information";
	    log.actionId = restaurant.id;
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
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    log.action = "Delete customer account";
	    log.actionId = customer.id;
	    logRepo.save(log);
        
        customerRepo.delete(customer);
        return "redirect:/admin-cust-view";
    }

    @GetMapping("/localadminserverdelete/{id}")
    public String deleteServer(@PathVariable("id") long id, Model model) {
        Servers server = serverRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid server Id:" + id));
        
        Log log = new Log();
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    log.action = "Delete server account";
	    log.actionId = server.id;
	    logRepo.save(log);
        
        serverRepo.delete(server);
        return "redirect:/admin-server-view";
    }

    @GetMapping("/localadminmandelete/{id}")
    public String deleteManager(@PathVariable("id") long id, Model model) {
        Managers manager = managerRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid manager Id:" + id));
        
        Log log = new Log();
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    log.action = "Delete manager account";
	    log.actionId = manager.id;
	    logRepo.save(log);
        
        managerRepo.delete(manager);
        return "redirect:/admin-man-view";
    }
    
    @GetMapping("/HQadminofficedelete/{id}")
    public String deleteOffice(@PathVariable("id") long id, Model model) {
    	Offices office = officeRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid office Id:" + id));
        
        Log log = new Log();
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    log.action = "Delete office";
	    log.actionId = office.id;
	    logRepo.save(log);
        
        officeRepo.delete(office);
        return "redirect:/HQadmin-offices-view";
    }
    
    @GetMapping("/HQadminrestaurantdelete/{id}")
    public String deleteRestaurant(@PathVariable("id") long id, Model model) {
    	Restaurants restaurant = restaurantRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant Id:" + id));
        
        Log log = new Log();
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    log.action = "Delete restaurant";
	    log.actionId = restaurant.id;
	    logRepo.save(log);
        
    	restaurantRepo.delete(restaurant);
        return "redirect:/HQadmin-restaurants-view";
    }
    
    @GetMapping("/HQadminwarehousedelete/{id}")
    public String deleteWarehouse(@PathVariable("id") long id, Model model) {
    	Warehouses warehouse = warehouseRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid warehouse Id:" + id));
        
        Log log = new Log();
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    log.action = "Delete warehouse";
	    log.actionId = warehouse.id;
	    logRepo.save(log);
        
    	warehouseRepo.delete(warehouse);
        return "redirect:/HQadmin-warehouses-view";
    }
    
    @GetMapping("/HQadminadmindelete/{id}")
    public String deleteAdmin(@PathVariable("id") long id, Model model) {
    	Admins admin = adminRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid admin Id:" + id));
        
        Log log = new Log();
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    log.action = "Delete admin account";
	    log.actionId = admin.id;
	    logRepo.save(log);
        
    	adminRepo.delete(admin);
        return "redirect:/HQadmin-admin-view";
    }
    
    @GetMapping("/servingstaffview")
    public String showServerView(Model model) {
        model.addAttribute("orders", orderRepo.findAll());
        model.addAttribute("menu", menuRepo.findAll());
        return "serving-staff-view";
    }
    
    @GetMapping("/serverviewcustinfo/{id}")
    public String showCustInfo(@PathVariable("id") long id, Model model) {
        model.addAttribute("customers", customerRepo.findById(id));
        return "server-cust-view";
    }
    
    @GetMapping("/updatemenuitem/{id}")
    public String showUpdateMenuItemForm(@PathVariable("id") long id, Model model) {
        Menu item = menuRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid menu Id: " + id));
        
        model.addAttribute("item", item);
        return "update-menu-item";
    }
    
    @PostMapping("/updatemenuitem/{id}")
    public String updateMenuItem(@PathVariable("id") long id, @Validated Menu item, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            item.SetId(id);
            return "update-menu-item";
        }
        
        Log log = new Log();
	    log.date = date.format(LocalDateTime.now());
	    log.time = time.format(LocalDateTime.now());
	    if (0==0 /*check if user is logged in once login is implemented*/ ) {
		    log.location = ""; //get user location from customer table
		    log.userid = ""; //get userid from customer table
	    }
	    log.action = "Update menu item";
	    log.actionId = item.id;
	    logRepo.save(log);
        
        menuRepo.save(item);
        return "redirect:/serving-staff-view";
    }
    
    @GetMapping("/logview/{id}") //get userid currently logged in from customer table
    public String showLog(@PathVariable("id") long id, Model model) {
	    Customers cust = customerRepo.findById(id);
	    List<Log> fullLog = logRepo.findAll();
	    if (cust.location == "HQ") {
		    model.addAttribute("log", fullLog);
	    }
	    else {
		    List<Log> localLog = new ArrayList<Log>();
		    for (Log i : fullLog) {
			    if (i.location == cust.location) {
				    localLog.add(i);
			    }
		    }
		    model.addAttribute("log", localLog);
	    }
	    return "log-view";
    }
    
}