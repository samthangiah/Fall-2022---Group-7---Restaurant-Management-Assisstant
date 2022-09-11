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

import edu.sru.group7.restaurantmanager.domain.Customers;
import edu.sru.group7.restaurantmanager.domain.Managers;
import edu.sru.group7.restaurantmanager.domain.Servers;
import edu.sru.group7.restaurantmanager.repository.CustomerRepository;
import edu.sru.group7.restaurantmanager.repository.ManagerRepository;
import edu.sru.group7.restaurantmanager.repository.ServerRepository;

@Controller
public class RestaurantController {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private ManagerRepository managerRepo;
	
	@Autowired
	private ServerRepository serverRepo;
    
	//create an UserRepository instance - instantiation (new) is done by Spring
    public RestaurantController(CustomerRepository customerRepo, 
    							ManagerRepository managerRepo,
    							ServerRepository serverRepo) {
		this.customerRepo = customerRepo;
		this.managerRepo = managerRepo;
		this.serverRepo = serverRepo;
	}
    
    @RequestMapping({"/localAdmin"})
    public String showAdminPage() {
    	return "localAdmin";
    }
    
    @RequestMapping({"/admin-man-view"})
    public String showManList(Model model) {
    	model.addAttribute("managers", managerRepo.findAll());
    	return "admin-man-view";
    }
    
    @RequestMapping({"/admin-server-view"})
    public String showServerList(Model model) {
    	model.addAttribute("servers", serverRepo.findAll());
    	return "admin-server-view";
    }
    
    @RequestMapping({"/admin-cust-view"})
    public String showUserList(Model model) {
        model.addAttribute("customers", customerRepo.findAll());
        return "admin-cust-view";
    }
    
  //Mapping for the /signup URL - calls the add-user HTML, to add a user
  	@RequestMapping({"/custsignup"})
      public String showCustSignUpForm(Customers customer) {
          return "add-customer";
      }
  	
  	@RequestMapping({"/serversignup"})
    public String showServerSignUpForm(Servers server) {
        return "add-server";
    }
  	
  	@RequestMapping({"/mansignup"})
    public String showManagerSignUpForm(Managers manager) {
        return "add-LFmanager";
    }
  	
  	
  //Mapping for the /signup URL - to add a user
    @RequestMapping({"/addcustomer"})
    public String addCust(@Validated Customers customers, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-customer";
        }
        
        customerRepo.save(customers);
        return "redirect:/admin-cust-view";
    }
    
    @RequestMapping({"/addserver"})
    public String addServer(@Validated Servers server, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-server";
        }
        
        serverRepo.save(server);
        return "redirect:/admin-server-view";
    }
    
    @RequestMapping({"/addmanager"})
    public String addManager(@Validated Managers manager, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-LFmanager";
        }
        
        managerRepo.save(manager);
        return "redirect:/admin-man-view";
    }
    
  //Mapping for the /edit/user URL to edit a user 
    @GetMapping("/localadmincustedit/{id}")
    public String showUpdateCustForm(@PathVariable("id") long id, Model model) {
        Customers customer = customerRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        
        model.addAttribute("customer", customer);
        return "update-customer";
    }
    
    @GetMapping("/localadminserveredit/{id}")
    public String showUpdateServerForm(@PathVariable("id") long id, Model model) {
        Servers server = serverRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid server Id:" + id));
        
        model.addAttribute("server", server);
        return "update-server";
    }
    
    @GetMapping("/localadminmanedit/{id}")
    public String showUpdateManagerForm(@PathVariable("id") long id, Model model) {
        Managers manager = managerRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid manager Id:" + id));
        
        model.addAttribute("manager", manager);
        return "update-LFmanager";
    }
    
    //Mapping for the /update/id URL to update a user 
    @PostMapping("/localadmincustupdate/{id}")
    public String updateCust(@PathVariable("id") long id, @Validated Customers customer, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            customer.setId(id);
            return "update-customer";
        }
            
        customerRepo.save(customer);
        return "redirect:/admin-cust-view";
    }
    
    @PostMapping("/localadminserverupdate/{id}")
    public String updateServer(@PathVariable("id") long id, @Validated Servers server, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            server.setId(id);
            return "update-server";
        }
            
        serverRepo.save(server);
        return "redirect:/admin-server-view";
    }
    
    @PostMapping("/localadminmanupdate/{id}")
    public String updateManager(@PathVariable("id") long id, @Validated Managers manager, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            manager.setId(id);
            return "update-LFmanager";
        }
            
        managerRepo.save(manager);
        return "redirect:/admin-man-view";
    }
    
    //Mapping for the /delete/id URL to delete a user     
    @GetMapping("/localadmincustdelete/{id}")
    public String deleteCust(@PathVariable("id") long id, Model model) {
        Customers customer = customerRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        customerRepo.delete(customer);
        return "redirect:/admin-cust-view";
    }
    
    @GetMapping("/localadminserverdelete/{id}")
    public String deleteServer(@PathVariable("id") long id, Model model) {
        Servers server = serverRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid server Id:" + id));
        serverRepo.delete(server);
        return "redirect:/admin-server-view";
    }
    
    @GetMapping("/localadminmandelete/{id}")
    public String deleteManager(@PathVariable("id") long id, Model model) {
        Managers manager = managerRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid manager Id:" + id));
        managerRepo.delete(manager);
        return "redirect:/admin-man-view";
    }
    
    
	
}
