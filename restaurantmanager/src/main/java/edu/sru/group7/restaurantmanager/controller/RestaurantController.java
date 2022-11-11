package edu.sru.group7.restaurantmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import edu.sru.group7.restaurantmanager.authentication.ApplicationUser;
import edu.sru.group7.restaurantmanager.authentication.FakeApplicationUserDaoService;
import edu.sru.group7.restaurantmanager.domain.Admins;
import edu.sru.group7.restaurantmanager.domain.CartItems;
import edu.sru.group7.restaurantmanager.domain.Customers;
import edu.sru.group7.restaurantmanager.domain.Ingredients;
import edu.sru.group7.restaurantmanager.domain.Inventory;
import edu.sru.group7.restaurantmanager.domain.Offices;
import edu.sru.group7.restaurantmanager.domain.Restaurants;
import edu.sru.group7.restaurantmanager.domain.Warehouses;
import edu.sru.group7.restaurantmanager.domain.Managers;
import edu.sru.group7.restaurantmanager.domain.Servers;
import edu.sru.group7.restaurantmanager.domain.Orders;
import edu.sru.group7.restaurantmanager.domain.PaymentDetails_Form;
import edu.sru.group7.restaurantmanager.domain.Menu;
import edu.sru.group7.restaurantmanager.domain.Log;
import edu.sru.group7.restaurantmanager.billing.PaymentDetails;
import edu.sru.group7.restaurantmanager.repository.AdminRepository;
import edu.sru.group7.restaurantmanager.repository.CartItemsRepository;
import edu.sru.group7.restaurantmanager.repository.CustomerRepository;
import edu.sru.group7.restaurantmanager.repository.IngredientsRepository;
import edu.sru.group7.restaurantmanager.repository.InventoryRepository;
import edu.sru.group7.restaurantmanager.repository.OfficeRepository;
import edu.sru.group7.restaurantmanager.repository.RestaurantRepository;
import edu.sru.group7.restaurantmanager.repository.WarehouseRepository;
import edu.sru.group7.restaurantmanager.security.ApplicationUserRole;
import edu.sru.group7.restaurantmanager.repository.ManagerRepository;
import edu.sru.group7.restaurantmanager.repository.ServerRepository;
import edu.sru.group7.restaurantmanager.repository.OrderRepository;
import edu.sru.group7.restaurantmanager.repository.PaymentDetailsRepository;
import edu.sru.group7.restaurantmanager.repository.MenuRepository;
import edu.sru.group7.restaurantmanager.repository.LogRepository;
import edu.sru.group7.restaurantmanager.billing.PaymentDetailsController;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.*;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Controller
/**
 * Restaurant Controller class for the entire Restaurant Management Assistant
 */
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

	@Autowired
	private InventoryRepository inventoryRepo;

	@Autowired
	private PaymentDetailsRepository paymentDetailsRepo;

	@Autowired
	private IngredientsRepository ingredientsRepo;

	@Autowired
	private CartItemsRepository cartItemsRepo;

	private final String menuFP = "src/main/resources/Menu.xlsx";

	private final String ingredientFP = "src/main/resources/Ingredients.xlsx";

	private FakeApplicationUserDaoService fakeApplicationUserDaoService;

	// create an UserRepository instance - instantiation (new) is done by Spring
	public RestaurantController(IngredientsRepository ingredientsRepo, RestaurantRepository restaurantRepo,
			WarehouseRepository warehouseRepo, CartItemsRepository cartItemsRepo, InventoryRepository inventoryRepo,
			OfficeRepository officeRepo, CustomerRepository customerRepo, ManagerRepository managerRepo,
			ServerRepository serverRepo, OrderRepository orderRepo, MenuRepository menuRepo, LogRepository logRepo) {
		this.ingredientsRepo = ingredientsRepo;
		this.restaurantRepo = restaurantRepo;
		this.warehouseRepo = warehouseRepo;
		this.cartItemsRepo = cartItemsRepo;
		this.inventoryRepo = inventoryRepo;
		this.customerRepo = customerRepo;
		this.managerRepo = managerRepo;
		this.serverRepo = serverRepo;
		this.officeRepo = officeRepo;
		this.orderRepo = orderRepo;
		this.menuRepo = menuRepo;
		this.logRepo = logRepo;
		isLoggedIn = false;
	}
    
    /*
     * @return Current HttpSession object to be used for guest users
     */
    public HttpSession getCurrentSession() {
    	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    	HttpSession session = request.getSession();
    	return session;
    }

	public boolean GetIsLoggedIn() {
		return isLoggedIn;
	}

	public void SetIsLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	static String checkStringType(XSSFCell testCell) {
		if (testCell.getCellType() == CellType.NUMERIC) {
			return Integer.toString((int) testCell.getNumericCellValue());
		}
		if (testCell.getCellType() == CellType.BLANK) {
			return null;
		}
		return testCell.getStringCellValue();
	}

	static int checkIntType(XSSFCell testCell) {
		if (testCell.getCellType() == CellType.STRING) {
			return Integer.parseInt(testCell.getStringCellValue());
		}
		if (testCell.getCellType() == CellType.BLANK) {
			return (Integer) null;
		}
		return (int) testCell.getNumericCellValue();
	}

	static float checkFloatType(XSSFCell testCell) {
		if (testCell.getCellType() == CellType.STRING) {
			return Integer.parseInt(testCell.getStringCellValue());
		}
		if (testCell.getCellType() == CellType.BLANK) {
			return (Float) null;
		}
		return (float) testCell.getNumericCellValue();
	}

	/**
	 * @throws IOException Loads Menu from excel sheet and adds into Menu POJO
	 */
	public void loadMenu() throws IOException {
		// TODO Auto-generated method stub

		/*
		 * File test=new File("check.txt"); if (test.createNewFile()) {
		 * System.out.println("File created: " + test.getName()); }
		 */

		FileInputStream thisxls;
		XSSFWorkbook wb;
		XSSFSheet sheet;
		XSSFRow curRow;

		thisxls = new FileInputStream("src/main/resources/Menu.xlsx");
		wb = new XSSFWorkbook(thisxls);
		sheet = wb.getSheetAt(0);

		Ingredients ingredient;

		int count = 0;

		curRow = sheet.getRow(count);

		Menu menus = new Menu();

		while (curRow.getRowNum() < sheet.getLastRowNum()) {
			count++;
			curRow = sheet.getRow(count);
			menus.setId(checkIntType(curRow.getCell(0)));
			System.out.println("Got ID");
			menus.setName(checkStringType(curRow.getCell(1)));
			System.out.println("Got Name");
			menus.setEntree(checkStringType(curRow.getCell(2)));
			System.out.println("Got Entree");
			menus.setSides(checkStringType(curRow.getCell(3)));
			System.out.println("Got Sides");
			menus.setPrice(checkFloatType(curRow.getCell(4)));
			System.out.println("Got Price");
			menus.setAvailability(true);
			System.out.println("Got Availability");
			menus.setQuantity(checkIntType(curRow.getCell(6)));
			System.out.println("Got Quantity");
			menuRepo.save(menus);

			try {
				ingredient = ingredientsRepo.findById((long) count)
						.orElseThrow(() -> new IllegalArgumentException("Invalid Ingredient Id:"));
				ingredient.setMenu(menus);
				ingredientsRepo.save(ingredient);
			} catch (Exception e) {
			}
		}
		wb.close();
	}

	/**
	 * @param filepath Ingredients excel filepath
	 * @param id
	 * @throws IOException Loads all Ingredients for each menu Items and adds to
	 *                     Restaurant inventory
	 */
	public void loadIngredients(String filepath, Restaurants id) throws IOException {

		FileInputStream thisxls;
		XSSFWorkbook wb;
		XSSFSheet sheet;
		XSSFRow curRow;

		thisxls = new FileInputStream(filepath);
		wb = new XSSFWorkbook(thisxls);
		sheet = wb.getSheetAt(0);

		int count = 0;
		curRow = sheet.getRow(count);

		while (curRow.getRowNum() < sheet.getLastRowNum()) {
			count++;
			curRow = sheet.getRow(count);
			Inventory inventory = new Inventory();
			inventory.setIngredient(checkStringType(curRow.getCell(1)));
			System.out.println("Got Ingredient");
			inventory.setQuantity(checkIntType(curRow.getCell(2)));
			System.out.println("Got Quantity");
			inventory.setRestaurant_id(id);

			inventoryRepo.save(inventory);

		}
		wb.close();
	}

	/**
	 * @param filepath Ingredients excel filepath
	 * @param id
	 * @throws IOException Loads all Ingredients for each menu Items and adds to
	 *                     Warehouse inventory
	 */
	public void loadIngredients(String filepath, Warehouses id) throws IOException {

		FileInputStream thisxls;
		XSSFWorkbook wb;
		XSSFSheet sheet;
		XSSFRow curRow;

		thisxls = new FileInputStream(filepath);
		wb = new XSSFWorkbook(thisxls);
		sheet = wb.getSheetAt(0);

		int count = 0;
		curRow = sheet.getRow(count);

		while (curRow.getRowNum() < sheet.getLastRowNum()) {
			count++;
			Inventory inventory = new Inventory();
			curRow = sheet.getRow(count);
			inventory.setIngredient(checkStringType(curRow.getCell(1)));
			System.out.println("Got Ingredient");
			inventory.setQuantity(checkIntType(curRow.getCell(2)));
			System.out.println("Got Quantity");
			inventory.setWarehouse_id(id);

			inventoryRepo.save(inventory);

		}

		wb.close();
	}

	/**
	 * @throws IOException Default Ingredients loader
	 */
	public void loadIngredients() throws IOException {
		FileInputStream thisxls;
		XSSFWorkbook wb;
		XSSFSheet sheet;
		XSSFRow curRow;

		thisxls = new FileInputStream("src/main/resources/MenuIngredients.xlsx");
		wb = new XSSFWorkbook(thisxls);
		sheet = wb.getSheetAt(0);

		int i = 0;

		curRow = sheet.getRow(i);

		while (curRow.getRowNum() < sheet.getLastRowNum()) {
			i++;
			int j = 2;
			Vector ingredientsList = new Vector();
			Ingredients ingredient = new Ingredients();
			curRow = sheet.getRow(i);
			ingredient.setId(checkIntType(curRow.getCell(0)));
			System.out.println("Got ID");
			while (curRow.getLastCellNum() > j) {
				String tempIngredient = checkStringType(curRow.getCell(j));
				ingredientsList.add(tempIngredient);
				System.out.println("Got Ingredient");
				j++;
			}
			ingredient.setIngredient(ingredientsList);

			ingredientsRepo.save(ingredient);

		}
		wb.close();
	}

	@PostConstruct
	public void loadData() {

		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "\n");
		System.out.println("STARTING TO CREATE THE DATABASE" + "\n" + "\n");

		customerRepo.deleteAll();
		adminRepo.deleteAll();
		managerRepo.deleteAll();
		serverRepo.deleteAll();
		officeRepo.deleteAll();
		restaurantRepo.deleteAll();
		warehouseRepo.deleteAll();
		orderRepo.deleteAll();
		menuRepo.deleteAll();

		Customers guest = new Customers("Guest", "", "Guest", "", 0, false, 0, 0);
		guest.setId(-1);
		customerRepo.save(guest);

		Offices office = new Offices("100 Central Loop", "16057", "Slippery Rock", "PA");

		Warehouses warehouse = new Warehouses("150 Branchton Road", "16057", "Slippery Rock", "PA");

		List<Admins> listadmins = new ArrayList<>();

		officeRepo.save(office);

		warehouseRepo.save(warehouse);

		Admins admin = new Admins("Darth", "Vader", "Administrator@email.com", "pass", office);

		Admins admin2 = new Admins("Kylo", "Ren", "Administrator2@email.com", "pass", office);

		listadmins.add(admin);
		listadmins.add(admin2);

		adminRepo.save(admin);
		adminRepo.save(admin2);

		Restaurants restaurant = new Restaurants("100 Arrowhead Drive", "16057", "Slippery Rock", "PA", admin2);

		Restaurants restaurant2 = new Restaurants("1 Vineyard Circle", "16057", "Slippery Rock", "PA", admin);

		restaurantRepo.save(restaurant);
		restaurantRepo.save(restaurant2);

		Offices office2 = new Offices("1620 East Maiden", "16057", "Slippery Rock", "PA", listadmins);

		Managers manager = new Managers("Anakin", "Skywalker", "Manager@email.com", "pass", restaurant); // restaurant
																											// is 2

		Managers manager2 = new Managers("Luke", "Skywalker", "Manager2@email.com", "pass", restaurant2);

		Servers server = new Servers("Obi-wan", "Kenobi", "server@email.com", "pass", restaurant);

		Servers server2 = new Servers("Baby", "Yoda", "server2@email.com", "pass", restaurant2);

		managerRepo.save(manager);
		managerRepo.save(manager2);
		serverRepo.save(server);
		serverRepo.save(server2);

		officeRepo.save(office2);

		try {
			loadIngredients();
			loadMenu();
			loadIngredients(ingredientFP, warehouse);
			loadIngredients(ingredientFP, restaurant);
			loadIngredients(ingredientFP, restaurant2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Customer objects for hardcoded logins
		Customers samThangiah = new Customers("sam", "thangiah", "sam", "thangiah", 0, false, 0, 1);

		Customers hqManager = new Customers("hq", "manager", "hqmanager@email.com", "pass", 0, false, 0, 1);

		customerRepo.save(samThangiah);
		customerRepo.save(hqManager);

		Orders order = new Orders();
		Customers cust = new Customers();

		cust.setEmail("customer@email.com");
		cust.setFirstName("Test");
		cust.setLastName("Customer");
		cust.setLocation((int) restaurant2.getId());
		cust.setPassword("password");
		cust.setRewardsMember(true);
		cust.setRewardsAvailable(10);

		customerRepo.save(cust);

		Set item = new HashSet();

		item.add(menuRepo.findById((long) 1).get());
		item.add(menuRepo.findById((long) 2).get());
		item.add(menuRepo.findById((long) 3).get());

		order.setDate(date.format(LocalDateTime.now()));
		order.setPrice(0.00F);
		order.setCustomer_id(cust);
		order.setItems(item);
		order.setInstructions("instructions");
		order.setRestaurant(restaurant2);
		order.setStatus("Completed");

		orderRepo.save(order);

		CartItems cartItems = new CartItems();
		cartItems.setMenu_id(menuRepo.findById((long) 1).get());
		cartItems.setCustomer_id(cust);
		cartItems.setQuantity(10);

		CartItems cartItems2 = new CartItems();
		cartItems2.setMenu_id(menuRepo.findById((long) 2).get());
		cartItems2.setCustomer_id(cust);
		cartItems2.setQuantity(10);

		CartItems cartItems3 = new CartItems();
		cartItems3.setMenu_id(menuRepo.findById((long) 3).get());
		cartItems3.setCustomer_id(cust);
		cartItems3.setQuantity(10);

		cartItemsRepo.save(cartItems);
		cartItemsRepo.save(cartItems2);
		cartItemsRepo.save(cartItems3);

		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------");
		System.out.println("DATABASE CREATED" + "\n" + "\n");

	}

	// index page
	/**
	 * @return index page
	 */
	@RequestMapping({ "/" })
	public String homePage() {
		return "Guest/index";
	}

	@ModelAttribute
	public void addAttributes(Model model) {
		List<Restaurants> listrestaurants = (List<Restaurants>) restaurantRepo.findAll();
		model.addAttribute("listRestaurants", listrestaurants);
		List<Admins> listadmins = (List<Admins>) adminRepo.findAll();
		model.addAttribute("listAdmins", listadmins);
		List<Offices> listoffices = (List<Offices>) officeRepo.findAll();
		model.addAttribute("listOffices", listoffices);
		List<Menu> listmenu = (List<Menu>) menuRepo.findAll();
		// Only display menu items that are available
		List<Menu> availablemenu = new ArrayList<Menu>();
		for (Menu m : listmenu) {
			if (m.getAvailability() == true) {
				availablemenu.add(m);
			}
		}
		model.addAttribute("listMenu", availablemenu);
	}

	/**
	 * 403 Error page
	 */
	@GetMapping("/403")
	public String error403() {
		return "SignIn/403";
	}

	// Do we use this?
	@RequestMapping({ "/signin" })
	public String signIn() {
		SetIsLoggedIn(true);
		return "redirect:/loggedinhome";
	}

	// Do we use this?
	@RequestMapping({ "/employeelogin" })
	public String tempEmployeeLoginPage() {
		SetIsLoggedIn(true);

		return "redirect:/temploginpage";
	}

	// Do we use this?
	@GetMapping("/loggedinhome")
	public String loggedIn() {
		return "Customer/loggedinhome";
	}

	// Manual credential processing to allow user registration
	@PostMapping("/processcredentials")
	public String processCredentials(String usernameParameter, String passwordParameter) {
		Optional<ApplicationUser> user = fakeApplicationUserDaoService
				.selectApplicationUserByUsername(usernameParameter);
		if (user == null) {
			return "/login?error";
		}
		try {
			ApplicationUser credentials = user.get();
			if (credentials.getPassword() == fakeApplicationUserDaoService.encode(passwordParameter)) {
				return "/temploginpage";
			}
		} catch (Exception e) {
		}
		return "/login?error";
	}

	@RequestMapping({ "/templogout" })
	public String logout() {
		SetIsLoggedIn(false);
		// Clears authentication and invalidates HTTPsession through
		// ApplicationSecurityConfig
		return "redirect:/temploginpage";
	}

	public Customers getLoggedInUser() {
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
				.startsWith("anonymousUser")) {
			return null;
		} else {
			ApplicationUser user = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			for (Customers i : customerRepo.findAll()) {
				if (i.getEmail().equals(user.getUsername())) {
					return i;
				}
			}
			for (Servers i : serverRepo.findAll()) {
				if (i.getEmail().equals(user.getUsername())) {
					Customers c = new Customers(i.getFirstName(), i.getLastName(), i.getEmail(), i.getPassword(), 0,
							false, 0, (int) i.getRestaurant().getId());
					c.setId(i.getId());
					return c;
				}
			}
			for (Managers i : managerRepo.findAll()) {
				if (i.getEmail().equals(user.getUsername())) {
					Customers c = new Customers(i.getFirstName(), i.getLastName(), i.getEmail(), i.getPassword(), 0,
							false, 0, (int) i.getRestaurant().getId());
					c.setId(i.getId());
					return c;
				}
			}
			for (Admins i : adminRepo.findAll()) {
				if (i.getEmail().equals(user.getUsername())) {
					Customers c = new Customers(i.getFirstName(), i.getLastName(), i.getEmail(), i.getPassword(), 0,
							false, 0, (int) i.getOffice().getId());
					c.setId(i.getId());
					return c;
				}
			}

			return null;
		}
	}

	/**
	 * @return menupage. Customers view of the menu
	 */
	@RequestMapping({ "/showmenu" })
	public String showMenu() {
		if (getLoggedInUser() == null) {
			return "Guest/guestmenu";
		}
		return "Customer/menupage";
	}

	@GetMapping("/temploginpage")
	public String staffLoginPage() {
		ApplicationUser user = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// Redirect user to staff page of highest authority
		if (user.getAuthorities().toString().contains("ROLE_HQADMIN")) {
			return "redirect:/hqlogadminview";
		}
		if (user.getAuthorities().toString().contains("ROLE_HQMANAGER")) {
			return "redirect:/hqlogview";
		}
		if (user.getAuthorities().toString().contains("ROLE_ADMIN")) {
			return "redirect:/logadminview";
		}
		if (user.getAuthorities().toString().contains("ROLE_MANAGER")) {
			return "redirect:/logview";
		}
		if (user.getAuthorities().toString().contains("ROLE_SERVER")) {
			return "redirect:/servingstaffview";
		}
		if (user.getAuthorities().toString().contains("ROLE_CUSTOMER")) {
			return "redirect:/loggedinhome";
		}
		return "SignIn/temploginpage";
	}

	/**
	 * @param model
	 * @return update-password. Passes current user to password change page
	 */
	@GetMapping("/changeuserpass")
	public String showUpdatePassForm(Model model) {
		Customers customer = getLoggedInUser();
		if (customer == null) {
			return "redirect:/";
		}

		model.addAttribute("customer", customer);
		return "Customer/update-password";
	}

	/**
	 * @param id       Customer Id
	 * @param customer Customer POJO
	 * @param result
	 * @param model
	 * @return loggedinhome. Updates user password and saves to Repository
	 */
	@PostMapping("/updateuserpass/{id}")
	public String updatePass(@PathVariable("id") long id, @Validated Customers customer, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			customer.setId(id);
			return "Customer/update-password";
		}

		Log log = new Log();
		log.setDate(date.format(LocalDateTime.now()));
		log.setTime(time.format(LocalDateTime.now()));
		log.setLocation(getUserLocation());
		log.setUserId(getUserUID());
		log.setAction("Customer update password");
		log.setActionId(customer.getId());
		logRepo.save(log);

		customerRepo.save(customer);
		return "redirect:/loggedinhome";
	}

	/**
	 * @param customer
	 * @return register. Shows Customer registration page.
	 */
	@RequestMapping({ "/custregistrationpage" })
	public String showCustRegisterForm(Customers customer) {
		return "SignIn/register";
	}

	/**
	 * @param customers
	 * @param result
	 * @param model
	 * @return login. Saves user entered customer information to new Customer entry.
	 */
	@RequestMapping({ "/addregisteredcustomer" })
	public String addNewCust(@Validated Customers customers, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "SignIn/register";
		}

		Log log = new Log();
		log.setDate(date.format(LocalDateTime.now()));
		log.setTime(time.format(LocalDateTime.now()));
		if (GetIsLoggedIn()) {
			log.setLocation(getUserLocation());
			log.setUserId(getUserUID());
		} else {
			log.setLocation(customers.getLocation());
			log.setUserId(-1);
		}
		log.setAction("Create new customer account");
		log.setActionId(customers.getId());
		logRepo.save(log);

		customerRepo.save(customers);
		return "redirect:/login";
	}

	/**
	 * @return contact. Shows contact us page.
	 */
	@RequestMapping("/contact")
	public String contactPage() {
		if (getLoggedInUser() == null) {
			return "Guest/guestcontact";
		}
		return "Customer/contact";
	}

	/**
	 * @param model
	 * @return custviewinfo. Shows current logged in users profile information.
	 */
	@RequestMapping("/custviewinfo")
	public String infoPage(Model model) {
		Customers user = getLoggedInUser();
		if (user == null) {
			return "redirect:/";
		}
		model.addAttribute("customers", user);
		return "Customer/custviewinfo";
	}

	/**
	 * @param model
	 * @return cart. Shows the current cart of the logged in user. Grabs each
	 *         menuItem price and adds to finalPrice to display total price. Creates
	 *         New order instance to pass as a model attribute.
	 */
	@RequestMapping("/Customer-cart-view")
	public String viewCart(Model model) {
		float finalPrice = 0;

		List<CartItems> cartItems = cartItemsRepo.findByCustomer(getLoggedInUser());

		model.addAttribute("listCart", cartItems);
		Iterator<CartItems> it = cartItems.iterator();
		while (it.hasNext()) {
			CartItems cartItem = it.next();

			// abcdefg
			finalPrice = finalPrice + (cartItem.getMenu_id().getPrice() * cartItem.getQuantity());
		}
		String roundOff = String.format("%.2f", finalPrice);
		String displayTotal = "$" + roundOff;
		model.addAttribute("totalprice", displayTotal);

		Orders order = new Orders();
		model.addAttribute("Order", order);
		return "Customer/cart";
	}

	/**
	 * @param id    Customer ID
	 * @param model
	 * @return editprofile. Shows edit profile page for current logged in user.
	 */
	@GetMapping("/editcustomer/{id}")
	public String userShowUpdateCustForm(@PathVariable("id") long id, Model model) {
		Customers customer = customerRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));

		model.addAttribute("customer", customer);
		return "Customer/editprofile";
	}

	/**
	 * @param id
	 * @param customer
	 * @param result
	 * @param model
	 * @return loggedinhome. Saves the user entered information from profile edit.
	 */
	@PostMapping("/usercustomerupdate/{id}")
	public String userUpdateCust(@PathVariable("id") long id, @Validated Customers customer, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			customer.setId(id);
			return "Customer/editprofile";
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
		return "redirect:/loggedinhome";
	}

	/**
	 * @param model
	 * @return admin-man-view. Shows all current managers from the restaurants that
	 *         the currently logged in Local Admin is assigned to.
	 */
	@RequestMapping({ "/admin-man-view" })
	public String showManList(Model model) {
		Admins admin = adminRepo.findById(getUserUID())
				.orElseThrow(() -> new IllegalArgumentException("Invalid admin Id:" + getUserUID()));
		Restaurants restaurant = restaurantRepo.findByAdmin(admin.getId());
		model.addAttribute("managers", managerRepo.findByRestaurant(restaurant.getId()));
		return "LocalAdmin/admin-man-view";
	}

	/**
	 * @param model
	 * @return admin-server-view. Shows all current servers from the restaurants
	 *         that the currently logged in Local Admin is assigned to.
	 */
	@RequestMapping({ "/admin-server-view" })
	public String showServerList(Model model) {
		Admins admin = adminRepo.findById(getUserUID())
				.orElseThrow(() -> new IllegalArgumentException("Invalid admin Id:" + getUserUID()));
		Restaurants restaurant = restaurantRepo.findByAdmin(admin.getId());

		model.addAttribute("servers", serverRepo.findServerLocation(restaurant.getId()));
		return "LocalAdmin/admin-server-view";
	}

	/**
	 * @param model
	 * @return admin-cust-view. Shows all customers to Local Admin.
	 */
	@RequestMapping({ "/admin-cust-view" })
	public String showUserList(Model model) {
		model.addAttribute("customers", customerRepo.findAll());
		return "LocalAdmin/admin-cust-view";
	}

	/**
	 * @param model
	 * @return HQadmin-admin-view. Shows all current Local Admins to HQ Admin
	 */
	@RequestMapping({ "/HQadmin-admin-view" })
	public String showAdminList(Model model) {
		model.addAttribute("admins", adminRepo.findAll());
		return "HQAdmin/HQadmin-admin-view";
	}

	/**
	 * @param model
	 * @return HQadmin-offices-view. Shows all offices to HQ Admin
	 */
	@RequestMapping({ "/HQadmin-offices-view" })
	public String showOfficesList(Model model) {
		model.addAttribute("offices", officeRepo.findAll());
		return "HQAdmin/HQadmin-offices-view";
	}

	/**
	 * @param model
	 * @return HQadmin-restaurants-view. Shows all restaurants to HQ Admin
	 */
	@RequestMapping({ "/HQadmin-restaurants-view" })
	public String showRestaurantList(Model model) {
		model.addAttribute("restaurants", restaurantRepo.findAll());
		return "HQAdmin/HQadmin-restaurants-view";
	}

	/**
	 * @param model
	 * @return HQadmin-warehouses-view. Shows all warehouses to HQ Admin
	 */
	@RequestMapping({ "/HQadmin-warehouses-view" })
	public String showWarehouseList(Model model) {
		model.addAttribute("warehouses", warehouseRepo.findAll());
		return "HQAdmin/HQadmin-warehouses-view";
	}

	/**
	 * @param customer
	 * @return add-customer. Shows add customer page to Local Admin
	 */
	@RequestMapping({ "/custsignup" })
	public String showCustSignUpForm(Customers customer) {
		return "LocalAdmin/add-customer";
	}

	/**
	 * @param server
	 * @return add-server. Shows add server page to Local Admin
	 */
	@RequestMapping({ "/serversignup" })
	public String showServerSignUpForm(Servers server) {
		return "LocalAdmin/add-server";
	}

	/**
	 * @param manager
	 * @return add-LFmanager. Shows add Local Manager page to Local Admin
	 */
	@RequestMapping({ "/mansignup" })
	public String showManagerSignUpForm(Managers manager) {
		return "LocalAdmin/add-LFmanager";
	}

	/**
	 * @param admin
	 * @return add-LFadmin. Shows add Local Admin page to HQ Admin
	 */
	@RequestMapping({ "/adminsignup" })
	public String showAdminSignUpForm(Admins admin) {
		return "HQAdmin/add-LFadmin";
	}

	/**
	 * @param office
	 * @return add-office. Shows add office page to HQ Admin
	 */
	@RequestMapping({ "/officesignup" })
	public String showOfficeSignUpForm(Offices office) {
		return "HQAdmin/add-office";
	}

	/**
	 * @param restaurant
	 * @return add-restaurant. HQ administrators add restaurant page
	 */
	@RequestMapping({ "/restaurantsignup" })
	public String showRestaurantSignUpForm(Restaurants restaurant) {
		return "HQAdmin/add-restaurant";
	}

	/**
	 * @param warehouse
	 * @return add-warehouse. HQ administrators add warehouse page
	 */
	@RequestMapping({ "/warehousesignup" })
	public String showWarehouseSignUpForm(Warehouses warehouse) {
		return "HQAdmin/add-warehouse";
	}

	/**
	 * @param customers
	 * @param result
	 * @param model
	 * @return admin-cust-view. Saves entered customer information from Local Admin.
	 *         Verifies that email does not already exist in Customers table.
	 */
	@RequestMapping({ "/addcustomer" })
	public String addCust(@Validated Customers customers, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "LocalAdmin/add-customer";
		}

		try {
			Log log = new Log();
			log.setDate(date.format(LocalDateTime.now()));
			log.setTime(time.format(LocalDateTime.now()));
			if (GetIsLoggedIn()) {
				log.setLocation(getUserLocation());
				log.setUserId(getUserUID());
			} else {
				log.setLocation(customers.getLocation());
				log.setUserId(-1);
			}
			log.setAction("Create new customer account");
			log.setActionId(customers.getId());
			logRepo.save(log);

			customerRepo.save(customers);
		} catch (Exception e) {
			result.rejectValue("email", null, "There is already an account registered with the same email");
			return "LocalAdmin/add-customer";
		}

		return "redirect:/admin-cust-view";
	}

	/**
	 * @param server
	 * @param result
	 * @param model
	 * @return admin-server-view. Saves entered server information from Local Admin.
	 *         Verifies email does not already exist in Servers table.
	 */
	@RequestMapping({ "/addserver" })
	public String addServer(@Validated Servers server, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "LocalAdmin/add-server";
		}

		try {
			Log log = new Log();
			log.setDate(date.format(LocalDateTime.now()));
			log.setTime(time.format(LocalDateTime.now()));
			log.setLocation(getUserLocation());
			log.setUserId(getUserUID());
			log.setAction("Create new server account");
			log.setActionId(server.getId());
			logRepo.save(log);

			serverRepo.save(server);

		} catch (Exception e) {
			result.rejectValue("email", null, "There is already an account registered with the same email");
			return "LocalAdmin/add-server";
		}
		return "redirect:/admin-server-view";
	}

	/**
	 * @param manager
	 * @param result
	 * @param model
	 * @return add-LFmanager. Saves entered manager information from Local Admin.
	 *         Verifies email does not already exist in Managers table.
	 */
	@RequestMapping({ "/addmanager" })
	public String addManager(@Validated Managers manager, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "LocalAdmin/add-LFmanager";
		}

		try {
			Log log = new Log();
			log.setDate(date.format(LocalDateTime.now()));
			log.setTime(time.format(LocalDateTime.now()));
			log.setLocation(getUserLocation());
			log.setUserId(getUserUID());
			log.setAction("Create new manager account");
			log.setActionId(manager.getId());
			logRepo.save(log);

			managerRepo.save(manager);

		} catch (Exception e) {
			result.rejectValue("email", null, "There is already an account registered with the same email");
			return "HQAdmin/add-LFmanager";
		}
		return "redirect:/admin-man-view";
	}

	/**
	 * @param admin
	 * @param result
	 * @param model
	 * @return HQAdmin-admin-view. Saves entered Local Admin information from HQ
	 *         Admin. Verifies email does not already exist in Admins table. Updates
	 *         restaurant to saved Admin.
	 */
	@RequestMapping({ "/addadmin" })
	public String addAdmin(@Validated Admins admin, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "HQAdmin/add-LFadmin";
		}

		try {
			adminRepo.save(admin);
			addAdminRestaurant(admin);
			Log log = new Log();
			log.setDate(date.format(LocalDateTime.now()));
			log.setTime(time.format(LocalDateTime.now()));
			log.setLocation(1 /* HQ */);
			log.setUserId(getUserUID());
			log.setAction("Create new admin account");
			log.setActionId(admin.getId());
			logRepo.save(log);
		} catch (Exception e) {
			result.rejectValue("email", null, "There is already an account registered with the same email");
			return "HQAdmin/add-LFadmin";
		}

		return "redirect:/HQadmin-admin-view";
	}

	/**
	 * @param office
	 * @param result
	 * @param model
	 * @return HQAdmin-offices-view. Saves entered office information from HQ Admin.
	 */
	@RequestMapping({ "/addoffice" })
	public String addOffice(@Validated Offices office, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "HQAdmin/add-office";
		}

		Log log = new Log();
		log.setDate(date.format(LocalDateTime.now()));
		log.setTime(time.format(LocalDateTime.now()));
		log.setLocation(1 /* HQ */);
		log.setUserId(getUserUID());
		log.setAction("Add new office");
		log.setActionId(office.getId());
		logRepo.save(log);

		officeRepo.save(office);
		return "redirect:/HQadmin-offices-view";
	}

	/**
	 * @param restaurant
	 * @param result
	 * @param model
	 * @return HQadmin-restaurants-view. Saves entered restaurant information from
	 *         HQ Admin. Loads a default inventory for New Restaurants location.
	 */
	@RequestMapping({ "/addrestaurant" })
	public String addRestaurant(@Validated Restaurants restaurant, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "HQAdmin/add-restaurant";
		}

		Log log = new Log();
		log.setDate(date.format(LocalDateTime.now()));
		log.setTime(time.format(LocalDateTime.now()));
		log.setLocation(1 /* HQ */);
		log.setUserId(getUserUID());
		log.setAction("Add new restaurant");
		log.setActionId(restaurant.getId());
		logRepo.save(log);
		restaurant.setSales(0);

		restaurantRepo.save(restaurant);
		try {
			loadIngredients(ingredientFP, restaurant);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/HQadmin-restaurants-view";
	}

	/**
	 * @param warehouse
	 * @param result
	 * @param model
	 * @return HQadmin-warehouses-view. Saves entered warehouse information from HQ
	 *         Admin. Loads a default inventory for New Warehouses location.
	 */
	@RequestMapping({ "/addwarehouse" })
	public String addWarehouse(@Validated Warehouses warehouse, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "HQAdmin/add-warehouse";
		}

		Log log = new Log();
		log.setDate(date.format(LocalDateTime.now()));
		log.setTime(time.format(LocalDateTime.now()));
		log.setLocation(1 /* HQ */);
		log.setUserId(getUserUID());
		log.setAction("Add new warehouse");
		log.setActionId(warehouse.getId());
		logRepo.save(log);

		warehouseRepo.save(warehouse);
		try {
			loadIngredients(ingredientFP, warehouse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/HQadmin-warehouses-view";
	}

	/**
	 * @param id
	 * @param model
	 * @return update-LFadmin. Shows update admin page to HQ admin.
	 */
	@GetMapping("/HQadminadminedit/{id}")
	public String showUpdateAdminForm(@PathVariable("id") long id, Model model) {
		Admins admin = adminRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid admin Id:" + id));

		model.addAttribute("admin", admin);
		return "HQadmin/update-LFadmin";
	}

	/**
	 * @param id
	 * @param model
	 * @return update-office. Shows update office page to HQ admin.
	 */
	@GetMapping("/HQadminofficeedit/{id}")
	public String showUpdateOfficeForm(@PathVariable("id") long id, Model model) {
		Offices office = officeRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid office Id:" + id));

		model.addAttribute("office", office);
		return "HQadmin/update-office";
	}

	/**
	 * @param id
	 * @param model
	 * @return update-restaurant. Shows update restaurant page to HQ admin.
	 */
	@GetMapping("/HQadminrestaurantedit/{id}")
	public String showUpdateRestaurantForm(@PathVariable("id") long id, Model model) {
		Restaurants restaurant = restaurantRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid restaurant Id:" + id));

		model.addAttribute("restaurant", restaurant);
		return "HQadmin/update-restaurant";
	}

	/**
	 * @param id
	 * @param model
	 * @return update-warehouse. Shows update warehouse page to HQ admin.
	 */
	@GetMapping("/HQadminwarehouseedit/{id}")
	public String showUpdateWarehouseForm(@PathVariable("id") long id, Model model) {
		Warehouses warehouse = warehouseRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid warehouse Id:" + id));

		model.addAttribute("warehouse", warehouse);
		return "HQadmin/update-warehouse";
	}

	/**
	 * @param id
	 * @param model
	 * @return update-customer. Shows update customer page to Local Admin.
	 */
	// Mapping for the /edit/user URL to edit a user
	@GetMapping("/localadmincustedit/{id}")
	public String showUpdateCustForm(@PathVariable("id") long id, Model model) {
		Customers customer = customerRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));

		model.addAttribute("customer", customer);
		return "LocalAdmin/update-customer";
	}

	/**
	 * @param id
	 * @param model
	 * @return update-server. Shows update server page to Local Admin.
	 */
	@GetMapping("/localadminserveredit/{id}")
	public String showUpdateServerForm(@PathVariable("id") long id, Model model) {
		Servers server = serverRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid server Id:" + id));

		model.addAttribute("server", server);
		return "LocalAdmin/update-server";
	}

	/**
	 * @param id
	 * @param model
	 * @return update-LFmanager. Shows update Local Manager page to Local Admin.
	 */
	@GetMapping("/localadminmanedit/{id}")
	public String showUpdateManagerForm(@PathVariable("id") long id, Model model) {
		Managers manager = managerRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid manager Id:" + id));

		model.addAttribute("manager", manager);
		return "LocalAdmin/update-LFmanager";
	}

	/**
	 * @param id
	 * @param customer
	 * @param result
	 * @param model
	 * @return admin-cust-view. Saves updated customer information entered by Local
	 *         Admin. Verifies that email information entered does not exist in
	 *         Customers table.
	 */
	@PostMapping("/localadmincustupdate/{id}")
	public String updateCust(@PathVariable("id") long id, @Validated Customers customer, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			customer.setId(id);
			return "LocalAdmin/update-customer";
		}

		try {
			Log log = new Log();
			log.setDate(date.format(LocalDateTime.now()));
			log.setTime(time.format(LocalDateTime.now()));
			log.setLocation(getUserLocation());
			log.setUserId(getUserUID());
			log.setAction("Update customer account");
			log.setActionId(customer.getId());
			logRepo.save(log);

			customerRepo.save(customer);
		} catch (Exception e) {
			result.rejectValue("email", null, "There is already an account registered with the same email");
			return "LocalAdmin/update-customer";
		}
		return "redirect:/admin-cust-view";
	}

	/**
	 * @param id
	 * @param server
	 * @param result
	 * @param model
	 * @return admin-server-view. Saves updated server information entered by Local
	 *         Admin. Verifies that email information entered does not exist in
	 *         Servers table.
	 */
	@PostMapping("/localadminserverupdate/{id}")
	public String updateServer(@PathVariable("id") long id, @Validated Servers server, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			server.setId(id);
			return "LocalAdmin/update-server";
		}

		try {
			Log log = new Log();
			log.setDate(date.format(LocalDateTime.now()));
			log.setTime(time.format(LocalDateTime.now()));
			log.setLocation(getUserLocation());
			log.setUserId(getUserUID());
			log.setAction("Update server account");
			log.setActionId(server.getId());
			logRepo.save(log);

			serverRepo.save(server);
		} catch (Exception e) {
			result.rejectValue("email", null, "There is already an account registered with the same email");
			return "LocalAdmin/update-server";
		}
		return "redirect:/admin-server-view";
	}

	/**
	 * @param id
	 * @param manager
	 * @param result
	 * @param model
	 * @return admin-manager-view. Saves updated Local Manager information entered
	 *         by Local Admin. Verifies that email information entered does not
	 *         exist in Managers table.
	 */
	@PostMapping("/localadminmanupdate/{id}")
	public String updateManager(@PathVariable("id") long id, @Validated Managers manager, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			manager.setId(id);
			return "LocalAdmin/update-LFmanager";
		}

		try {
			Log log = new Log();
			log.setDate(date.format(LocalDateTime.now()));
			log.setTime(time.format(LocalDateTime.now()));
			log.setLocation(getUserLocation());
			log.setUserId(getUserUID());
			log.setAction("Update manager account");
			log.setActionId(manager.getId());
			logRepo.save(log);

			managerRepo.save(manager);
		} catch (Exception e) {
			result.rejectValue("email", null, "There is already an account registered with the same email");
			return "LocalAdmin/update-LFmanager";
		}
		return "redirect:/admin-man-view";
	}

	/**
	 * @param id
	 * @param admin
	 * @param result
	 * @param model
	 * @return HQadmin-admin-view. Saves updated Local admin information entered by
	 *         HQ Admin. Verifies that email information entered does not exist in
	 *         Admins table.
	 */
	@PostMapping("/HQadminadminupdate/{id}")
	public String updateAdmin(@PathVariable("id") long id, @Validated Admins admin, BindingResult result, Model model) {
		if (result.hasErrors()) {
			admin.setId(id);
			return "HQAdmin/update-LFadmin";
		}

		try {
			Log log = new Log();
			log.setDate(date.format(LocalDateTime.now()));
			log.setTime(time.format(LocalDateTime.now()));
			log.setLocation(getUserLocation());
			log.setUserId(getUserUID());
			log.setAction("Update admin account");
			log.setActionId(admin.getId());
			logRepo.save(log);

			adminRepo.save(admin);
		} catch (Exception e) {
			result.rejectValue("email", null, "There is already an account registered with the same email");
			return "HQAdmin/add-LFadmin";
		}
		return "redirect:/HQadmin-admin-view";
	}

	/**
	 * @param id
	 * @param office
	 * @param result
	 * @param model
	 * @return HQadmin-offices-view. Saves updated office information entered by HQ
	 *         Admin.
	 */
	@PostMapping("/HQadminofficeupdate/{id}")
	public String updateOffice(@PathVariable("id") long id, @Validated Offices office, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			office.setId(id);
			return "HQAdmin/update-office";
		}

		Log log = new Log();
		log.setDate(date.format(LocalDateTime.now()));
		log.setTime(time.format(LocalDateTime.now()));
		log.setLocation(1 /* HQ */);
		log.setUserId(getUserUID());
		log.setAction("Update office information");
		log.setActionId(office.getId());
		logRepo.save(log);

		officeRepo.save(office);
		return "redirect:/HQadmin-offices-view";
	}

	/**
	 * @param id
	 * @param warehouse
	 * @param result
	 * @param model
	 * @return HQadmin-warehouses-view. Saves updated warehouse information entered
	 *         by HQ Admin.
	 */
	@PostMapping("/HQadminwarehouseupdate/{id}")
	public String updateWarehouse(@PathVariable("id") long id, @Validated Warehouses warehouse, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			warehouse.setId(id);
			return "HQAdmin/update-warehouse";
		}

		Log log = new Log();
		log.setDate(date.format(LocalDateTime.now()));
		log.setTime(time.format(LocalDateTime.now()));
		log.setLocation(1 /* HQ */);
		log.setUserId(getUserUID());
		log.setAction("Update warehouse information");
		log.setActionId(warehouse.getId());
		logRepo.save(log);

		warehouseRepo.save(warehouse);
		return "redirect:/HQadmin-warehouses-view";
	}

	/**
	 * @param id
	 * @param restaurant
	 * @param result
	 * @param model
	 * @return HQadmin-restaurants-view. Saves updated restaurant information
	 *         entered by HQ Admin.
	 */
	@PostMapping("/HQadminrestaurantupdate/{id}")
	public String updateRestaurant(@PathVariable("id") long id, @Validated Restaurants restaurant, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			restaurant.setId(id);
			return "HQAdmin/update-restaurant";
		}

		Log log = new Log();
		log.setDate(date.format(LocalDateTime.now()));
		log.setTime(time.format(LocalDateTime.now()));
		log.setLocation(1 /* HQ */);
		log.setUserId(getUserUID());
		log.setAction("Update restaurant information");
		log.setActionId(restaurant.getId());
		logRepo.save(log);

		restaurantRepo.save(restaurant);
		return "redirect:/HQadmin-restaurants-view";
	}

	/**
	 * @param admin. Grabs all restaurants the admin has been assigned and updates
	 *               their admin FK.
	 */
	public void addAdminRestaurant(Admins admin) {

		int count = 0;
		List<Restaurants> restaurants = admin.getRestaurant();
		Iterator<Restaurants> iterator = restaurants.iterator();

		while (iterator.hasNext()) {
			Restaurants restaurant = restaurants.get(count);
			restaurant.setAdmin(admin);
			restaurantRepo.save(restaurant);
			count++;
			iterator.next();
		}

	}

	/**
	 * @param admin. Removes admin assigned to restaurant so the restaurant does not
	 *               get deleted when the admin does.
	 */
	public void removeAdminRestaurant(Admins admin) {
		int count = 0;
		List<Restaurants> restaurants = admin.getRestaurant();
		Iterator<Restaurants> iterator = restaurants.iterator();

		while (iterator.hasNext()) {
			Restaurants restaurant = restaurants.get(count);
			restaurant.setAdmin(null);
			restaurantRepo.save(restaurant);
			count++;
			iterator.next();
		}

		admin.setRestaurant(null);
		adminRepo.save(admin);
	}

	/**
	 * @param id    Customer
	 * @param model
	 * @return admin-cust-view. Deletes customer from Customers table.
	 */
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

	/**
	 * @param id    Server
	 * @param model
	 * @return admin-server-view. Deletes server from Servers table.
	 */
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

	/**
	 * @param id    Manager
	 * @param model
	 * @return admin-man-view. Deletes manager from Managers table.
	 */
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

	/**
	 * @param id    Office
	 * @param model
	 * @return HQadmin-offices-view. Deletes office from Offices table.
	 */
	@GetMapping("/HQadminofficedelete/{id}")
	public String deleteOffice(@PathVariable("id") long id, Model model) {
		Offices office = officeRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid office Id:" + id));

		Log log = new Log();
		log.setDate(date.format(LocalDateTime.now()));
		log.setTime(time.format(LocalDateTime.now()));
		log.setLocation(1 /* HQ */);
		log.setUserId(getUserUID());
		log.setAction("Delete office");
		log.setActionId(office.getId());
		logRepo.save(log);

		officeRepo.delete(office);
		return "redirect:/HQadmin-offices-view";
	}

	/**
	 * @param id    Restaurant
	 * @param model
	 * @return HQadmin-restaurants-view. Deletes restaurant from Restaurants table.
	 */
	@GetMapping("/HQadminrestaurantdelete/{id}")
	public String deleteRestaurant(@PathVariable("id") long id, Model model) {
		Restaurants restaurant = restaurantRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid restaurant Id:" + id));

		Log log = new Log();
		log.setDate(date.format(LocalDateTime.now()));
		log.setTime(time.format(LocalDateTime.now()));
		log.setLocation(1 /* HQ */);
		log.setUserId(getUserUID());
		log.setAction("Delete restaurant");
		log.setActionId(restaurant.getId());
		logRepo.save(log);

		restaurantRepo.delete(restaurant);
		return "redirect:/HQadmin-restaurants-view";
	}

	/**
	 * @param id    Warehouse
	 * @param model
	 * @return HQadmin-warehouses-view. Deletes warehouse from Warehouses table.
	 */
	@GetMapping("/HQadminwarehousedelete/{id}")
	public String deleteWarehouse(@PathVariable("id") long id, Model model) {
		Warehouses warehouse = warehouseRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid warehouse Id:" + id));

		Log log = new Log();
		log.setDate(date.format(LocalDateTime.now()));
		log.setTime(time.format(LocalDateTime.now()));
		log.setLocation(1 /* HQ */);
		log.setUserId(getUserUID());
		log.setAction("Delete warehouse");
		log.setActionId(warehouse.getId());
		logRepo.save(log);

		warehouseRepo.delete(warehouse);
		return "redirect:/HQadmin-warehouses-view";
	}

	/**
	 * @param id    Admin
	 * @param model
	 * @return HQadmin-admin-view. Deletes Local Admin from Admins table. 
	 * Removes assigned restaurant before deleting admin to avoid deleting restaurant as well. 
	 */
	@GetMapping("/HQadminadmindelete/{id}")
	public String deleteAdmin(@PathVariable("id") long id, Model model) {
		Admins admin = adminRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid admin Id:" + id));

		Log log = new Log();
		log.setDate(date.format(LocalDateTime.now()));
		log.setTime(time.format(LocalDateTime.now()));
		log.setLocation(1 /* HQ */);
		log.setUserId(getUserUID());
		log.setAction("Delete admin account");
		log.setActionId(admin.getId());
		logRepo.save(log);

		removeAdminRestaurant(admin);

		adminRepo.delete(admin);
		return "redirect:/HQadmin-admin-view";
	}

	/**
	 * @param id	Order
	 * @param model
	 * @return servingstaffview. Updates selected order status to "Completed" from "Paid".
	 */
	@GetMapping("/deleteorder/{id}")
	public String deleteOrder(@PathVariable("id") long id, Model model) {
		Orders order = orderRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));

		Log log = new Log();
		log.setDate(date.format(LocalDateTime.now()));
		log.setTime(time.format(LocalDateTime.now()));
		log.setLocation(getUserLocation());
		log.setUserId(getUserUID());
		log.setAction("Complete order");
		log.setActionId(order.getId());
		logRepo.save(log);
		order.setStatus("Completed");

		orderRepo.save(order);
		return "redirect:/servingstaffview";
	}

	//Do we use this
	@GetMapping("/servingstaffview")
	public String showServerView(Model model) {
		model.addAttribute("orders", orderRepo.findOrdersByLocation(getUserLocation()));
		model.addAttribute("menu", menuRepo.findAll());
		// TO-DO make output of orders more neat
		return "LocalServingStaff/serving-staff-view";
	}

	//Do we use this
	@GetMapping("/servingstaffviewview")
	public String showLocalManServerView(Model model) {
		// get all orders paid
		model.addAttribute("orders", orderRepo.findAll());
		model.addAttribute("menu", menuRepo.findAll());
		return "LocalManager/manager-server-view-view";
	}

	// I wrote this unintuitively but the pathvariable "id" is supposed to be the
	// customer's email since
	// order.customer is a String and you can't just do customerRepo.findByEmail()
	// or anything like that, it
	// only works with id so I just loop through the customers for one with a
	// matching email to get their id
	@GetMapping("/serverviewcustinfo/{id}")
	public String showCustInfo(@PathVariable("id") Customers orderCust, Model model) {
		Customers customer = customerRepo.findByEmail(orderCust.getEmail());
		if (customer == null) {
			return "redirect:/servingstaffview";
		}
		else {
			model.addAttribute("customers", customer);
		}
				
		return "LocalServingStaff/server-cust-view";
	}

	// For guest order "view customer info and rewards" to not throw 404
	@GetMapping("/serverviewcustinfo/")
	public String blankCustInfo() {
		return "redirect:/servingstaffview";
	}

	@GetMapping("/updatemenuitem/{id}")
	public String showUpdateMenuItemForm(@PathVariable("id") long id, Model model) {
		Menu item = menuRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid menu Id: " + id));

		model.addAttribute("item", item);
		return "LocalServingStaff/update-menu-item";
	}

	@PostMapping("/updatemenuitem/{id}")
	public String updateMenuItem(@PathVariable("id") long id, @Validated Menu item, BindingResult result, Model model) {
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

	@GetMapping("/logview")
	public String showLog(Model model) {
		Customers cust = getLoggedInUser();
		if (cust == null) {
			return "redirect:/";
		}
		Iterable<Log> fullLog = logRepo.findAll();
		List<Log> localLog = new ArrayList<Log>();
		for (Log i : fullLog) {
			if (i.getLocation() == cust.getLocation()) {
				localLog.add(i);
			}
		}
		model.addAttribute("log", (Iterable<Log>) localLog);
		return "LocalManager/log-view";
	}

	@GetMapping("/logadminview")
	public String showAdminLog(Model model) {
		Customers cust = getLoggedInUser();
		if (cust == null) {
			return "redirect:/";
		}
		Iterable<Log> fullLog = logRepo.findAll();
		List<Log> localLog = new ArrayList<Log>();
		for (Log i : fullLog) {
			if (i.getLocation() == cust.getLocation()) {
				localLog.add(i);
			}
		}
		model.addAttribute("log", (Iterable<Log>) localLog);
		return "LocalAdmin/log-admin-view";
	}

	@GetMapping("/hqlogview")
	public String showHQLog(Model model) {
		model.addAttribute("log", logRepo.findAll());
		return "HQManager/hq-log-view";
	}

	@GetMapping("/hqlogadminview")
	public String showHQAdminLog(Model model) {
		model.addAttribute("log", logRepo.findAll());
		return "HQAdmin/hq-admin-log-view";
	}

	// local manager home page
	@RequestMapping({ "/local-manager-view" })
	public String showManagerPage() {
		return "LocalManager/local-manager-view";
	}

	@RequestMapping({ "/manager-inventory-view" })
	public String showInventoryView(Model model) {
		Managers manager = managerRepo.findById(getUserUID())
				.orElseThrow(() -> new IllegalArgumentException("Invalid admin Id:" + getUserUID()));

		model.addAttribute("inventoryList", inventoryRepo.findInventoryRestaurant(manager.getRestaurant().getId()));

		return "LocalManager/manager-inventory-view";
	}

	@RequestMapping({ "/local-manager-view-view" })
	public String showHQManManagerPage() {
		return "HQManager/local-manager-view-view";
	}

	@RequestMapping({ "/manager-cust-view" })
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

	@GetMapping("/managerinventoryedit/{id}")
	public String localManShowUpdateInventoryForm(@PathVariable("id") long id, Model model) {
		Inventory inventory = inventoryRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid inventory Id:" + id));

		model.addAttribute("inventory", inventory);
		return "LocalManager/update-inventory";
	}

	@PostMapping("/managerinventoryupdate/{id}")
	public String localManUpdateInventory(@PathVariable("id") long id, @Validated Inventory inventory,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			inventory.setId(id);
			return "LocalManager/update-inventory";
		}

		Log log = new Log();
		log.setDate(date.format(LocalDateTime.now()));
		log.setTime(time.format(LocalDateTime.now()));
		log.setLocation(getUserLocation());
		log.setUserId(getUserUID());
		log.setAction("Update Inventory");
		log.setActionId(inventory.getId());
		logRepo.save(log);

		inventoryRepo.save(inventory);
		return "redirect:/manager-inventory-view";
	}

	@PostMapping("/localmanagercustupdate/{id}")
	public String localManUpdateCust(@PathVariable("id") long id, @Validated Customers customer, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			customer.setId(id);
			return "LocalManager/update-customer";
		}

		try {
			Log log = new Log();
			log.setDate(date.format(LocalDateTime.now()));
			log.setTime(time.format(LocalDateTime.now()));
			log.setLocation(getUserLocation());
			log.setUserId(getUserUID());
			log.setAction("Update customer account");
			log.setActionId(customer.getId());
			logRepo.save(log);

			customerRepo.save(customer);
		} catch (Exception e) {
			result.rejectValue("email", null, "There is already an account registered with the same email");
			return "HQAdmin/add-LFadmin";
		}
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

	@RequestMapping({ "/manager-menu-view" })
	public String localManShowMenu(Model model) {
		model.addAttribute("menu", menuRepo.findAll());
		return "LocalManager/manager-menu-view";
	}

	@RequestMapping({ "/localmanageraddmenu" })
	public String showMenuAddForm(Menu menu) {
		return "LocalManager/add-menu-item";
	}

	@RequestMapping({ "/addmenuitem" })
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
		Menu item = menuRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid menu Id: " + id));

		model.addAttribute("item", item);
		return "LocalManager/update-menu";
	}

	@PostMapping("/localmanagerupdatemenu/{id}")
	public String LocalManUpdateMenu(@PathVariable("id") long id, @Validated Menu item, BindingResult result,
			Model model) {
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
		Menu item = menuRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Menu Id:" + id));

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

	@RequestMapping({ "/manager-server-view" })
	public String localManShowServers(Model model) {
		model.addAttribute("servers", serverRepo.findServerLocation(getUserLocation()));
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
	public String localManUpdateServer(@PathVariable("id") long id, @Validated Servers server, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			server.setId(id);
			return "LocalManager/update-server";
		}

		try {
			Log log = new Log();
			log.setDate(date.format(LocalDateTime.now()));
			log.setTime(time.format(LocalDateTime.now()));
			log.setLocation(getUserLocation());
			log.setUserId(getUserUID());
			log.setAction("Update server account");
			log.setActionId(server.getId());
			logRepo.save(log);

			serverRepo.save(server);
		} catch (Exception e) {
			result.rejectValue("email", null, "There is already an account registered with the same email");
			return "HQAdmin/add-LFadmin";
		}
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

	// HQ Manager home page
	@RequestMapping({ "/HQ-manager-view" })
	public String showHQManagerPage() {
		return "HQManager/HQ-manager-view";
	}

	@RequestMapping({ "/HQmanager-managers-view" })
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
	public String hqManUpdateManager(@PathVariable("id") long id, @Validated Managers manager, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			manager.setId(id);
			return "HQManager/update-LFmanager";
		}

		Log log = new Log();
		log.setDate(date.format(LocalDateTime.now()));
		log.setTime(time.format(LocalDateTime.now()));
		log.setLocation(1 /* HQ */);
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
		log.setLocation(1 /* HQ */);
		log.setUserId(getUserUID());
		log.setAction("Delete manager account");
		log.setActionId(manager.getId());
		logRepo.save(log);

		managerRepo.delete(manager);
		return "redirect:/HQmanager-managers-view";
	}

	@RequestMapping({ "/HQmanageraddmanager" })
	public String showLFManagerAddForm(Managers manager) {
		return "HQManager/add-LFmanager";
	}

	@RequestMapping({ "/addlfmanager" })
	public String addLFManager(@Validated Managers manager, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "HQManager/add-LFmanager";
		}

		Log log = new Log();
		log.setDate(date.format(LocalDateTime.now()));
		log.setTime(time.format(LocalDateTime.now()));
		log.setLocation(1 /* HQ */);
		log.setUserId(getUserUID());
		log.setAction("Create new LF manager");
		log.setActionId(manager.getId());
		logRepo.save(log);

		managerRepo.save(manager);
		return "redirect:/HQmanager-managers-view";
	}

	@RequestMapping({ "/HQmanager-location-view" })
	public String showHQManagerLocationPage() {
		return "HQManager/HQManager-locations-view";
	}

	@RequestMapping({ "/HQmanager-restaurants-view" })
	public String hqManShowRestaurants(Model model) {
		model.addAttribute("restaurants", restaurantRepo.findAll());
		return "HQManager/HQManager-restaurants-view";
	}

	@RequestMapping({ "/HQmanager-offices-view" })
	public String hqManShowOffices(Model model) {
		model.addAttribute("offices", officeRepo.findAll());
		return "HQManager/HQManager-offices-view";
	}

	@RequestMapping({ "/HQmanager-warehouses-view" })
	public String hqManShowWarehouses(Model model) {
		model.addAttribute("warehouses", warehouseRepo.findAll());
		return "HQManager/HQManager-warehouses-view";
	}
		
		@RequestMapping({"/Customer-ordertype-view"})
		public String showOrderType(Model model){
			final Orders order = new Orders();
			model.addAttribute("Order", order);
			
			if (getLoggedInUser() != null) {
				return "Customer/orderpage";
			}
			return "Guest/order-as-guest";
		}
		
		@RequestMapping({"/ordersuccessful"})
		public String showOrderSuccess(){
			if (getLoggedInUser() != null) {
				return "Customer/ordersuccessful";
			}
			return "Guest/guestordersuccess";
		}
		
		@RequestMapping("/pay")
		public String showPaymentPage(Model model) {
			PaymentDetails_Form payForm = new PaymentDetails_Form();
			model.addAttribute("PaymentDetails_Form", payForm);
			if (getLoggedInUser() != null) {
				return "Customer/pay";
			}
			return "Guest/guestpay";
		}
		
		@RequestMapping("/processpayment")
		public String processPayment(@Validated PaymentDetails_Form form, BindingResult result, Model model) {
			if (result.hasErrors()) {
				return "redirect:/pay";
			}
			PaymentDetails details = new PaymentDetails();
			details.buildFromForm(form);
			Orders order;
			
			if (getLoggedInUser() != null) {
				order = orderRepo.findByCustomerIdUnpaid(getUserUID());
			} else {
				order = (Orders) getCurrentSession().getAttribute("order");
			}
			
			removeFromInventory(order);
			order.setStatus("Paid");
			orderRepo.save(order);
			deleteCartItems();
			
			//add payment gateway such as stripe to handle payment processing
			//if (payment can be processed) {
				paymentDetailsRepo.delete(details);
				return "redirect:/ordersuccessful";
			//}
			//else {
			//	paymentDetailsRepo.delete(details);
			//	return "redirect:/pay";
			//}
		}
		
    /**
	  * @param model
	  * @return Customer Cart page
	  */
		@RequestMapping("/Customer-cart-view")
		public String viewCart(Model model) {
			float finalPrice = 0;
			List<CartItems> cartItems;
			
			if (getLoggedInUser() != null) {
				cartItems = cartItemsRepo.findByCustomer(getLoggedInUser());
			}
			else {
				//Guest users cart is handled through HttpSession instead of Customer account
				if (getCurrentSession().getAttribute("cartItems") != null) {
					//cartItems attribute is set in createNewOrder() and custAddToOrder() if getLoggedInUser() is null
					cartItems = (List<CartItems>) getCurrentSession().getAttribute("cartItems");
				} else {
					cartItems = new ArrayList<CartItems>();
				}
			}

			model.addAttribute("listCart",cartItems);
			Iterator<CartItems> it = cartItems.iterator();
			while(it.hasNext()) {
				CartItems cartItem = it.next();
				finalPrice = finalPrice + (cartItem.getMenu_id().getPrice() * cartItem.getQuantity());
			}
			String roundOff = String.format("%.2f", finalPrice);
			String displayTotal = "$" + roundOff;
			model.addAttribute("totalprice", displayTotal);
			
			Orders order = new Orders();
			model.addAttribute("Order", order);
			
			if (getLoggedInUser() != null) {
				return "Customer/cart";
			}
			return "Guest/cart";
		}
		
		private void addToSales(Orders order) {
			Restaurants restaurant = order.getRestaurant();
			restaurant.setSales(restaurant.getSales() + order.getPrice());
			restaurantRepo.save(restaurant);
			
		}
    
		@GetMapping("/editcart/{id}")
		public String deleteCartItem(@PathVariable("id") long id, Model model) {
			CartItems item = cartItemsRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid cartItems Id:" + id));
						
			if (getLoggedInUser() == null) {
				//Manually remove specified item from guest cart HttpSession attribute so it doesnt stay visible on the page
				List<CartItems> cartItems = (List<CartItems>) getCurrentSession().getAttribute("cartItems");
				List<CartItems> newCart = new ArrayList<CartItems>();
								
				for (CartItems i : cartItems) {
					//Find menu item from cart items
					if (!(i.getMenu_id().toString().equals(item.getMenu_id().toString()))) {
						newCart.add(i);
					}
				}
				getCurrentSession().setAttribute("cartItems", newCart);
			}
			cartItemsRepo.delete(item);
			
			return "redirect:/Customer-cart-view";
		}
		
		public void deleteCartItems() {
			Customers customer = getLoggedInUser();
			List<CartItems> cartItems;
			if (customer != null) {
				cartItems = cartItemsRepo.findByCustomer(customer);
			}
			else {
				//cartItems either retrieved from HttpSession attribute or set to empty list if no cart items were set
				if (getCurrentSession().getAttribute("cartItems") != null) {
					cartItems = (List<CartItems>) getCurrentSession().getAttribute("cartItems");
					getCurrentSession().removeAttribute("cartItems");
				} else {
					cartItems = new ArrayList<CartItems>();
				}
			}
			
			cartItemsRepo.deleteAll(cartItems);
		}
		
		@GetMapping({"/addtoorder/{id}"})
		public String custAddToOrder(@PathVariable("id") long id, @Validated CartItems cartItem, BindingResult result, Model model) {
			if (result.hasErrors()) {
				return "redirect:/Customer-ordertype-view";
			}
			
			if (getLoggedInUser() != null) {
				cartItem = cartItemsRepo.findByCustMenuId(id, getUserUID());
				if (cartItem == null) {
					//Begin new order if customer has no items in cart
					cartItem = createNewOrder(id, cartItem);
				} else {
					//Add to item quantity if customer already has said item in their cart
					cartItem.setQuantity(cartItem.getQuantity() + 1);
				}
			} else {
				//Dealing with guest cart information
				//Retrieve cart information from HttpSession attribute instead of Customer account
				if (getCurrentSession().getAttribute("cartItems") == null) {
					cartItem = createNewOrder(id, cartItem);
				} else {
					//Manually appending to cart necessary
					List<CartItems> items = (List<CartItems>) getCurrentSession().getAttribute("cartItems");
					Menu menu = menuRepo.findById(id)
							.orElseThrow(() -> new IllegalArgumentException("Invalid menu Id:" + id));
					boolean flag = false;
					//Looping through current cart items to check if guest already has at least 1 of the same item in their cart
					for (CartItems i : items) {
						if (i.getMenu_id().toString().equals(menu.toString())) {
							i.setQuantity(i.getQuantity() + 1);
							flag = true;
						}
					}
					if (flag == false) {
						cartItem = new CartItems(menu, getGuestCust(), 1);
						items.add(cartItem);
					}
					//cartItems attribute is set to new appended list
					getCurrentSession().setAttribute("cartItems", items);
				}
			}
			
			//cartItemsRepo is saved regardless of logged in status for deleteCartItem() to work
			try {
				cartItemsRepo.save(cartItem);
			} catch(Exception e) {
				e.printStackTrace();
				return "redirect:/Customer-ordertype-view";
			}
			
			return "redirect:/Customer-ordertype-view";
		}
		
		private CartItems createNewOrder(long id, CartItems cartItem) {
			Menu menu = menuRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid menu Id:" + id));
			cartItem = new CartItems();
			
			cartItem.setMenu_id(menu);
			cartItem.setQuantity(1);
			if (menu.getId() == (long) -1) {
				cartItem.setQuantity(null);
			}

			if (getLoggedInUser() != null) {
				cartItem.setCustomer_id(getLoggedInUser());
			} else {
				cartItem.setCustomer_id(getGuestCust());
				List<CartItems> items = new ArrayList<CartItems>();
				items.add(cartItem);
				//Create HttpSession attribute of cartItems for guest customers
				getCurrentSession().setAttribute("cartItems", items);
			}
			
			return cartItem;
		}
		
		@RequestMapping({"/addNewOrder"})
		public String custAddOrder(Orders order) {
			if (orderRepo.findByCustomerIdUnpaid(getUserUID()) != null) {
				return "redirect:/pay";
			}
			float finalPrice = 0;
			List<CartItems> cartItems;
			Customers user = getLoggedInUser();
			
			if (user != null) {
				cartItems = cartItemsRepo.findByCustomer(user);
				order.setCustomer_id(user);
			} else {
				if (getCurrentSession().getAttribute("cartItems") != null) {
					cartItems = (List<CartItems>) getCurrentSession().getAttribute("cartItems");
				} else {
					cartItems = new ArrayList<CartItems>();
				}
				order.setCustomer_id(getGuestCust());
			}
			
			Iterator<CartItems> cartIt = cartItems.iterator();
			Set<Menu> menuItems = new HashSet<Menu>();
			
			while(cartIt.hasNext()) {
				CartItems cartItem = cartIt.next();
				menuItems.add(cartItem.getMenu_id());
				finalPrice = finalPrice + (cartItem.getMenu_id().getPrice() * cartItem.getQuantity());
			}
			
			order.setDate(date.format(LocalDateTime.now()));
			order.setItems(menuItems);
			order.setPrice(finalPrice);
			order.setStatus("Pending Payment");
			orderRepo.save(order);
			
			//Rewards can only be checked after finalPrice is set
			if (user != null) {
				if (user.getRewardsMember() == true) {
					System.out.println("--------------------------------------------------------------------------------------------------");
					System.out.println(order.getPrice());
					//For every $10 spent per order, cust is awarded with 1 rewards point
					int rewards = (int) order.getPrice() / 10;
					System.out.println("rewards earned: " + rewards);
					rewards += user.getRewardsAvailable();
					
					user.setRewardsAvailable(rewards);
					customerRepo.save(user);
				}
			} else {
				//Add order attribute for payment processing to access for guests
				getCurrentSession().setAttribute("order", order);
			}
			return "redirect:/pay";
		}
		
		/**@PostMapping({"/addorder"})
		public String custAddOrder(@Validated Orders order, BindingResult result, Model model) {
			if (result.hasErrors()) {
				return "redirect:/Customer-ordertype-view";
			}
      
			if (getLoggedInUser() != null) {
				order.setCustomer_id(getLoggedInUser());
			}
			else {
				order.setCustomer_id(getGuestCust());
			}
		}
		return "redirect:/pay";
	}

	public void removeFromInventory(Orders order) {
		Set<Menu> items = order.getItems();
		Iterator<Menu> it = items.iterator();

		// finds restaurant corresponding to order
		Restaurants restaurant = new Restaurants();
		restaurant.setId(order.getRestaurant().getId());

		// find inventory corresponding to restaurant
		List<Inventory> inventoryList = inventoryRepo.findInventoryRestaurant(restaurant.getId());

		// iterate over all menu ID's for order
		while (it.hasNext()) {
			Menu menu = it.next();
			// find ingredients for menu item and add it to an array and then create an
			// iterator for array
			Ingredients menuIngredients = ingredientsRepo.findByMenuItem(menu.getId());
			try {
				Vector ingredientList = menuIngredients.getIngredient();
				Iterator ingredientIT = ingredientList.iterator();
				// iterate over each ingredient for a menu item
				while (ingredientIT.hasNext()) {
					String ingredient = ingredientIT.next().toString();
					// Create inventoryiterator so it resets per new ingredient to top of list
					Iterator<Inventory> inventoryIT = inventoryList.iterator();
					// iterate over each inventory item to compare current ingredient to selected
					// ingredient in Repo
					while (inventoryIT.hasNext()) {
						Inventory inventory = inventoryIT.next();
						System.out.println(
								"--------------------------------------------------------------------------------------------------");
						System.out.println(inventory.getIngredient() + " get ingredient");
						System.out.println(ingredient + " ingredient");
						if (inventory.getIngredient().compareTo(ingredient) == 0) {
							System.out.println(inventory.getIngredient() + " is equal to " + ingredient);
							inventory.setQuantity(inventory.getQuantity() - 1);
							inventoryRepo.save(inventory);
							break;
						}
					}
				}
			} catch (Exception e) {
				System.out.println("No ingredients for Menu Item");
			}
			order.setPrice(totalPrice);
			orderRepo.save(order);
			
			//Award rewards points to signed in rewards customers
			Customers orderCustomer = getLoggedInUser();
			if (orderCustomer != null) {
				if (orderCustomer.getRewardsMember() == true) {
					System.out.println("--------------------------------------------------------------------------------------------------");
					System.out.println(order.getPrice());
					//For every $10 spent per order, cust is awarded with 1 rewards point
					int rewards = (int) order.getPrice() / 10;
					System.out.println("rewards earned: " + rewards);
					rewards += orderCustomer.getRewardsAvailable();
					
					orderCustomer.setRewardsAvailable(rewards);
					customerRepo.save(orderCustomer);
				}
			}
			
      		return "redirect:/pay";
		}
		*/
		
		@RequestMapping({"/redeem"})
		public String redeemRewards() {
			Customers user = getLoggedInUser();
			if (user == null) {
				return "redirect:/Customer-cart-view";
			}
			if (user.getRewardsMember() == true) {
				int rewards = user.getRewardsAvailable();
				//Redeem 5 rewards points at a time
				if (rewards >= 5) {
					user.setRewardsAvailable(rewards - 5);
					
					List<CartItems> items = cartItemsRepo.findByCustomer(user);
					float price = 0.00F;
					for (CartItems i : items) {
						price += i.getMenu_id().getPrice() * i.getQuantity();
					}
					//Discount is 10% of order, rounded to 2 decimal places
					float discountPrice = Math.round((price / 10) * 100.0) / 100.0F;
					
					Menu discount = new Menu();
					discount.setId(-1);
					discount.setName("Rewards discount");
					discount.setAvailability(false);
					//Price set to negative so that it is subtracted
					discount.setPrice(0 - discountPrice);
					menuRepo.save(discount);
					cartItemsRepo.save(new CartItems(discount, user, 1));

				}
				// Discount is 10% of order, rounded to 2 decimal places
				float discountPrice = Math.round((price / 10) * 100.0) / 100.0F;

				Menu discount = new Menu();
				discount.setId(-1);
				discount.setName("Rewards discount");
				discount.setAvailability(false);
				// Price set to negative so that it is subtracted
				discount.setPrice(0 - discountPrice);
				menuRepo.save(discount);

				cartItemsRepo.save(new CartItems(discount, getLoggedInUser(), 1));
			}
		}

		return "redirect:/Customer-cart-view";
	}
	
	/*
	 * @return Customer object for hardcoded guest customer of id -1
	 */
	public Customers getGuestCust() {
		//I would just do this instead of looping through the findAll() but it doesnt like the Optional<> type
		//order.setCustomer_id(customerRepo.findById((long) -1));
		for (Customers c : customerRepo.findAll()) {
			//Guest user -1
			if (c.getId() == (long) -1) {
				return c;
			}
		}
		return null;
	}
	
    public int getUserLocation() {

		Customers user = getLoggedInUser();
		if (user == null) {
			return -1;
		}
		return user.getLocation();
	}

	public long getUserUID() {
		Customers user = getLoggedInUser();
		if (user == null) {
			return (long) -1;
		}
		return user.getId();
	}

}
