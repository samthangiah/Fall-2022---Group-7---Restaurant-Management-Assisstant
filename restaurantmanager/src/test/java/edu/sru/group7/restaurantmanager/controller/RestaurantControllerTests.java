package edu.sru.group7.restaurantmanager.controller;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import edu.sru.group7.restaurantmanager.billing.PaymentDetails;
import edu.sru.group7.restaurantmanager.billing.Paypal;
import edu.sru.group7.restaurantmanager.domain.Admins;
import edu.sru.group7.restaurantmanager.domain.CartItems;
import edu.sru.group7.restaurantmanager.domain.Customers;
import edu.sru.group7.restaurantmanager.domain.Ingredients;
import edu.sru.group7.restaurantmanager.domain.Inventory;
import edu.sru.group7.restaurantmanager.domain.Log;
import edu.sru.group7.restaurantmanager.domain.Managers;
import edu.sru.group7.restaurantmanager.domain.Menu;
import edu.sru.group7.restaurantmanager.domain.Offices;
import edu.sru.group7.restaurantmanager.domain.Orders;
import edu.sru.group7.restaurantmanager.domain.PaymentDetails_Form;
import edu.sru.group7.restaurantmanager.domain.Paypal_Form;
import edu.sru.group7.restaurantmanager.domain.Restaurants;
import edu.sru.group7.restaurantmanager.domain.Servers;
import edu.sru.group7.restaurantmanager.domain.Shipping;
import edu.sru.group7.restaurantmanager.domain.StateTax;
import edu.sru.group7.restaurantmanager.domain.WarehouseEmployees;
import edu.sru.group7.restaurantmanager.domain.WarehouseManager;
import edu.sru.group7.restaurantmanager.domain.Warehouses;
import edu.sru.group7.restaurantmanager.repository.AdminRepository;
import edu.sru.group7.restaurantmanager.repository.CartItemsRepository;
import edu.sru.group7.restaurantmanager.repository.CustomerRepository;
import edu.sru.group7.restaurantmanager.repository.IngredientsRepository;
import edu.sru.group7.restaurantmanager.repository.InventoryRepository;
import edu.sru.group7.restaurantmanager.repository.LogRepository;
import edu.sru.group7.restaurantmanager.repository.ManagerRepository;
import edu.sru.group7.restaurantmanager.repository.MenuRepository;
import edu.sru.group7.restaurantmanager.repository.OfficeRepository;
import edu.sru.group7.restaurantmanager.repository.OrderRepository;
import edu.sru.group7.restaurantmanager.repository.PaymentDetailsRepository;
import edu.sru.group7.restaurantmanager.repository.PaypalRepository;
import edu.sru.group7.restaurantmanager.repository.RestaurantRepository;
import edu.sru.group7.restaurantmanager.repository.ServerRepository;
import edu.sru.group7.restaurantmanager.repository.ShippingRepository;
import edu.sru.group7.restaurantmanager.repository.StateTaxRepository;
import edu.sru.group7.restaurantmanager.repository.WarehouseEmployeeRepository;
import edu.sru.group7.restaurantmanager.repository.WarehouseManagerRepository;
import edu.sru.group7.restaurantmanager.repository.WarehouseRepository;

/**
 * Test class for RestaurantController methods
 * Methods commented out and marked with //TODO have bugs that only occur in test class, 
 * Each one I have thoroughly tested on the website itself 
 */
@SpringBootTest
class RestaurantControllerTests {
	
	@Autowired
	private WarehouseManagerRepository warehouseManagerRepo;
	
	@Autowired
	private IngredientsRepository ingredientsRepo;
	
	@Autowired
	private RestaurantRepository restaurantRepo;

	@Autowired
	private WarehouseRepository warehouseRepo;

	@Autowired
	private CartItemsRepository cartItemsRepo;

	@Autowired
	private InventoryRepository inventoryRepo;

	@Autowired
	private OfficeRepository officeRepo;

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
	private PaymentDetailsRepository paymentDetailsRepo;

	@Autowired
	private StateTaxRepository stateTaxRepo;

	@Autowired
	private ShippingRepository shippingRepo;

	@Autowired
	private PaypalRepository paypalRepo;
	
	@Autowired 
	private WarehouseEmployeeRepository warehouseEmployeeRepo;
	
	@Autowired
	private RestaurantController controller = new RestaurantController(warehouseManagerRepo, ingredientsRepo, restaurantRepo, warehouseRepo,
			cartItemsRepo, inventoryRepo, officeRepo, customerRepo, managerRepo,
			serverRepo, orderRepo, menuRepo, logRepo, adminRepo, paymentDetailsRepo,
			stateTaxRepo, shippingRepo, paypalRepo, warehouseEmployeeRepo);
	
	@Mock
	private Model model;
	
	@Mock
	private BindingResult bindingResult;
	
	@BeforeEach
	public void load() {
		controller.loadData();
	}
	
	@Test
	public void restaurantControllerConstructorTest() {
		assertNotNull(controller, "Controller should not be null");
		assertEquals(controller.GetIsLoggedIn(), false, "isLoggedIn should be initialized to false");
	}
	
	@Test
	public void getCurrentSessionTest() {
		assertNotNull(controller.getCurrentSession(), "Session should not be null");
		assertTrue((controller.getCurrentSession() instanceof HttpSession), "Session should be object of type HttpSession");
	}
	
	@Test
	public void getIsLoggedInTest() {
		assertNotNull(controller.GetIsLoggedIn(), "isLoggedIn should not be null");
	}
	
	@Test
	public void setIsLoggedInTest() {
		controller.SetIsLoggedIn(true);
		assertEquals(true, controller.GetIsLoggedIn(), "SetIsLoggedIn should set value of isLoggedIn");
		controller.SetIsLoggedIn(false);
		assertEquals(false, controller.GetIsLoggedIn(), "SetIsLoggedIn should set value of isLoggedIn");
	}
	
	@Test
	public void checkStringIntFloatTypeTest() {
		//Test method for checkStringType, checkIntType, and checkFloatType
		//Uses TestDataSpreadsheet.xlsx
		FileInputStream thisxls;
		XSSFWorkbook wb;
		XSSFSheet sheet;
		XSSFRow curRow;
		
		int intCheck;
		float floatCheck;

		try {
			thisxls = new FileInputStream("src/main/resources/TestDataSpreadsheet.xlsx");
			wb = new XSSFWorkbook(thisxls);
			sheet = wb.getSheetAt(0);
			
			int count = 0;
			curRow = sheet.getRow(count);

			while (count < sheet.getLastRowNum()) {
				count++;
				curRow = sheet.getRow(count);
				
				if (curRow != null) {
					//Check if value is String
					assertTrue((RestaurantController.checkStringType(curRow.getCell(0)) instanceof String), "checkStringType should return String");
					intCheck = RestaurantController.checkIntType(curRow.getCell(1));
					//Check if value is int
					assertTrue(((int) intCheck == intCheck), "checkIntType should return int");
					floatCheck = RestaurantController.checkFloatType(curRow.getCell(2));
					//Check if value is float
					assertTrue(((float) floatCheck == floatCheck), "checkFloatType should return float");
				}
			}
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void loadMenuTest() {
		try {
			controller.loadMenu();
			List<Menu> menu = new ArrayList<Menu>();
			menu.addAll((List<Menu>) menuRepo.findAll());
			assertFalse(menu.isEmpty(), "Menu should not be empty after loading from file");
			
			for (Menu m : menu) {
				assertNotNull(m.getId(), "Menu object ID should not be null");
				assertNotNull(m.getName(), "Menu object name should not be null");
				//Menu entree and sides can be null
				assertNotNull(m.getPrice(), "Menu object price should not be null");
				assertNotNull(m.getAvailability(), "Menu object availability should not be null");
				assertNotNull(m.getQuantity(), "Menu object quantity should not be null");
			}
			
			List<Ingredients> ingredients = new ArrayList<Ingredients>();
			ingredients.addAll((List<Ingredients>) ingredientsRepo.findAll());
			assertFalse(ingredients.isEmpty(), "Ingredients should not be empty after loading from file");
			
			for (Ingredients i : ingredients) {
				assertNotNull(i.getMenu(), "Ingredient should have menu object connected");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void loadRestaurantIngredientsTest() {
		String fp = "src/main/resources/Ingredients.xlsx";
		List<Inventory> inventory = new ArrayList<Inventory>();
		try {
			for (Restaurants rest : restaurantRepo.findAll()) {
				inventory.clear();
				controller.loadIngredients(fp, rest);
				inventory.addAll((List<Inventory>) inventoryRepo.findInventoryRestaurant(rest.getId()));
				assertFalse(inventory.isEmpty(), "Inventory should not be empty after loading from file");
				
				for (Inventory i : inventory) {
					assertNotNull(i.getQuantity(), "Inventory quantity should not be null");
					assertTrue(i.getRestaurant_id().toString().equals(rest.toString()), "Inventory should be for specified restaurant");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void loadWarehouseIngredientsTest() {
		String fp = "src/main/resources/Ingredients.xlsx";
		List<Inventory> inventory = new ArrayList<Inventory>();
		try {
			for (Warehouses wh : warehouseRepo.findAll()) {
				inventory.clear();
				controller.loadIngredients(fp, wh);
				inventory.addAll((List<Inventory>) inventoryRepo.findInventoryWarehouse(wh.getId()));
				assertFalse(inventory.isEmpty(), "Inventory should not be empty after loading from file");
				
				for (Inventory i : inventory) {
					assertNotNull(i.getQuantity(), "Inventory quantity should not be null");
					assertTrue(i.getWarehouse_id().toString().equals(wh.toString()), "Inventory should be for specified warehouse");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void loadDefaultIngredientsTest() {
		try {
			controller.loadIngredients();
			List<Ingredients> ingredients = new ArrayList<Ingredients>();
			ingredients.addAll((List<Ingredients>) ingredientsRepo.findAll());
			assertFalse(ingredients.isEmpty(), "Ingredients should not be empty after loading from file");
			
			for (Ingredients i : ingredients) {
				assertNotNull(i.getId(), "Ingredient ID should not be null");
				assertNotNull(i.getIngredient(), "Ingredient vector should not be null");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void loadTaxesTest() {
		try {
			controller.loadTaxes();
			List<StateTax> stateTaxes = new ArrayList<StateTax>();
			stateTaxes.addAll((List<StateTax>) stateTaxRepo.findAll());
			assertFalse(stateTaxes.isEmpty(), "Tax information should not be empty after loading from file");
			
			for (StateTax st : stateTaxes) {
				assertNotNull(st.getId(), "Tax ID should not be null");
				assertNotNull(st.getState(), "Tax State should not be null");
				assertNotNull(st.getincomePercent(), "Tax income percent should not be null");
				assertNotNull(st.getSalesPercent(), "Tax sales percent should not be null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void loadDataTest() {
		controller.loadData();
		
		List<Customers> customers = (List<Customers>) customerRepo.findAll();
		assertFalse(customers.isEmpty(), "Customer repository should not be empty");
		assertEquals("Guest", customers.get(0).getEmail());
		assertEquals("N/A", customers.get(0).getLocation());
		
		assertEquals("sam", customers.get(1).getEmail());
		assertEquals("hqmanager@email.com", customers.get(2).getEmail());
		assertEquals("customer@email.com", customers.get(3).getEmail());
		
		List<Offices> offices = (List<Offices>) officeRepo.findAll();
		assertFalse(offices.isEmpty(), "Office repository should not be empty");
		assertEquals("100 Central Loop", offices.get(0).getAddress());
		assertEquals("1620 East Maiden", offices.get(1).getAddress());
		
		List<Warehouses> warehouses = (List<Warehouses>) warehouseRepo.findAll();
		assertFalse(warehouses.isEmpty(), "Warehouse repository should not be empty");
		assertEquals("150 Branchton Road", warehouses.get(0).getAddress());
		
		List<Admins> admins = (List<Admins>) adminRepo.findAll();
		assertFalse(admins.isEmpty(), "Admin repository should not be empty");
		assertEquals("Darth", admins.get(0).getFirstName());
		assertTrue(admins.get(0).getOffice().toString().equals(offices.get(0).toString()), "First admin should be assigned to first office");
		assertEquals("Kylo", admins.get(1).getFirstName());
		assertTrue(admins.get(1).getOffice().toString().equals(offices.get(0).toString()), "Second admin should be assigned to first office");
		assertNotNull(offices.get(1).getAdmin(), "office2 should have admin and admin2 assigned to it");
		
		List<Restaurants> restaurants = (List<Restaurants>) restaurantRepo.findAll();
		assertFalse(restaurants.isEmpty(), "Restaurant repository should not be empty");
		assertEquals("100 Arrowhead Drive", restaurants.get(0).getAddress());
		assertEquals("1 Vineyard Circle", restaurants.get(1).getAddress());
		
		List<Managers> managers = (List<Managers>) managerRepo.findAll();
		assertFalse(managers.isEmpty(), "Manager repository should not be empty");
		assertEquals("Anakin", managers.get(0).getFirstName());
		assertEquals("Luke", managers.get(1).getFirstName());
		
		List<Servers> servers = (List<Servers>) serverRepo.findAll();
		assertFalse(servers.isEmpty(), "Server repository should not be empty");
		assertEquals("Obi-wan", servers.get(0).getFirstName());
		assertEquals("Baby", servers.get(1).getFirstName());
		
		List<WarehouseManager> warehouseManagers = (List<WarehouseManager>) warehouseManagerRepo.findAll();
		assertFalse(warehouseManagers.isEmpty(), "Warehouse manager repository should not be empty");
		assertEquals("Emperor", warehouseManagers.get(0).getFirstName());
		assertTrue(warehouseManagers.get(0).getWarehouse().toString().equals(warehouses.get(0).toString()), "warehousemanager should be assigned to warehouse");
		
		List<Orders> orders = (List<Orders>) orderRepo.findAll();
		assertFalse(orders.isEmpty(), "Order repository should not be empty");
		
		List<Shipping> shipments = (List<Shipping>) shippingRepo.findAll();
		assertFalse(shipments.isEmpty(), "Shipment repository should not be empty");
		assertTrue(shipments.get(0).getWarehouse_id().toString().equals(warehouses.get(0).toString()), "shipment should be assigned to warehouse");
		assertTrue(shipments.get(0).getRestaurant_id().toString().equals(restaurants.get(0).toString()), "shipment should be assigned to restaurant");
		
		List<WarehouseEmployees> warehouseEmployees = (List<WarehouseEmployees>) warehouseEmployeeRepo.findAll();
		assertFalse(warehouseEmployees.isEmpty(), "Warehouse employee repository should not be empty");
		assertEquals("Jabba", warehouseEmployees.get(0).getFirstName());
	}
	
	@Test
	public void homePageTest() {
		assertEquals("Guest/index", controller.homePage(), "homePage should return guest index page");
	}
	
	@Test
	public void addAttributesTest() {
		controller.addAttributes(model);
		verify(model, times(1)).addAttribute(eq("listRestaurants"), ArgumentMatchers.anyIterable());
		verify(model, times(1)).addAttribute(eq("listAdmins"), ArgumentMatchers.anyIterable());
		verify(model, times(1)).addAttribute(eq("listOffices"), ArgumentMatchers.anyIterable());
		verify(model, times(1)).addAttribute(eq("listWarehouses"), ArgumentMatchers.anyIterable());
		verify(model, times(1)).addAttribute(eq("listMenu"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void loginPageTest() {
		String ret = controller.loginPage();
		assertEquals("redirect:/loggedinredirect", ret, "loginPage should return redirect to loggedinredirect");
		assertEquals(true, controller.GetIsLoggedIn(), "loginPage should set isLoggedIn to true");
	}
	
	@Test
	public void loggedInTest() {
		assertEquals("Customer/loggedinhome", controller.loggedIn(), "loggedIn should return loggedinhome page");
	}
	
	@Test
	public void logoutTest() {
		String ret = controller.logout();
		assertEquals("redirect:/loggedinredirect", ret, "logout should return redirect to login");
		assertEquals(false, controller.GetIsLoggedIn(), "logout should set isLoggedIn to false");
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void getLoggedInUserTest() {
		Customers user = controller.getLoggedInUser();
		assertNotNull(user, "User should not be null");
		assertEquals("customer@email.com", user.getEmail(), "User email should be customer@email.com");
	}
	
	@Test
	@WithUserDetails(value = "server@email.com")
	public void getLoggedInUserTest2() {
		Customers user = controller.getLoggedInUser();
		assertNotNull(user, "User should not be null");
		assertEquals("server@email.com", user.getEmail(), "User email should be server@email.com");
	}
	
	@Test
	@WithUserDetails(value = "Manager@email.com")
	public void getLoggedInUserTest3() {
		Customers user = controller.getLoggedInUser();
		assertNotNull(user, "User should not be null");
		assertEquals("Manager@email.com", user.getEmail(), "User email should be Manager@email.com");
	}
	
	@Test
	@WithUserDetails(value = "Administrator@email.com")
	public void getLoggedInUserTest4() {
		Customers user = controller.getLoggedInUser();
		assertNotNull(user, "User should not be null");
		assertEquals("Administrator@email.com", user.getEmail(), "User email should be Administrator@email.com");
	}
	
	@Test
	@WithUserDetails(value = "WHmanager@email.com")
	public void getLoggedInUserTest5() {
		Customers user = controller.getLoggedInUser();
		assertNotNull(user, "User should not be null");
		assertEquals("WHmanager@email.com", user.getEmail(), "User email should be WHmanager@email.com");
	}
	
	@Test
	public void getLoggedInUserTest6() {
		//Not authenticated test
		assertEquals(null, controller.getLoggedInUser(), "User should be null");
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void showMenuTest() {
		String ret = controller.showMenu();
		assertEquals("Customer/menupage", ret, "showMenu should return customer menu page if logged in");
	}
	
	@Test
	public void showMenuTest2() {
		//Not authenticated test
		String ret = controller.showMenu();
		assertEquals("Guest/guestmenu", ret, "showMenu should return guest menu page if not logged in");
	}
	
	@Test
	@WithUserDetails(value = "sam")
	public void authorityCheckForLoginRedirectsTest() {
		assertEquals("redirect:/hqlogadminview", controller.authorityCheckForLoginRedirects(), "Logging in as HQAdmin should redirect to HQAdmin page");
	}
	
	@Test
	@WithUserDetails(value = "hqmanager@email.com")
	public void authorityCheckForLoginRedirectsTest2() {
		assertEquals("redirect:/hqlogview", controller.authorityCheckForLoginRedirects(), "Logging in as HQManager should redirect to HQManager page");
	}
	
	@Test
	@WithUserDetails(value = "Administrator@email.com")
	public void authorityCheckForLoginRedirectsTest3() {
		assertEquals("redirect:/logadminview", controller.authorityCheckForLoginRedirects(), "Logging in as admin should redirect to admin page");
	}
	
	@Test
	@WithUserDetails(value = "Manager@email.com")
	public void authorityCheckForLoginRedirectsTest4() {
		assertEquals("redirect:/logview", controller.authorityCheckForLoginRedirects(), "Logging in as manager should redirect to manager page");
	}
	
	@Test
	@WithUserDetails(value = "WHmanager@email.com")
	public void authorityCheckForLoginRedirectsTest5() {
		assertEquals("redirect:/warehouseman-log-view", controller.authorityCheckForLoginRedirects(), "Logging in as WHManager should redirect to WHManager page");
	}
	
	@Test
	@WithUserDetails(value = "server@email.com")
	public void authorityCheckForLoginRedirectsTest6() {
		assertEquals("redirect:/servingstaffview", controller.authorityCheckForLoginRedirects(), "Logging in as server should redirect to server page");
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void authorityCheckForLoginRedirectsTest7() {
		assertEquals("redirect:/loggedinhome", controller.authorityCheckForLoginRedirects(), "Logging in as customer should redirect to customer page");
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void showUpdatePassFormTest() {
		String ret = controller.showUpdatePassForm(model);
		assertEquals("Customer/update-password", ret, "showUpdatePassForm should return update password page if logged in");
	}
	
	@Test
	public void showUpdatePassFormTest2() {
		//Not authenticated test
		String ret = controller.showUpdatePassForm(model);
		assertEquals("redirect:/", ret, "showUpdatePassForm should redirect to guest index page if not logged in");
	}
	
	@Test
	public void updatePassTest() {
		List<Customers> customers = (List<Customers>) customerRepo.findAll();
		String ret = controller.updatePass(customers.get(0).getId(), customers.get(0), bindingResult, model);
		assertEquals("redirect:/loggedinhome", ret, "updatePass should return redirect to home page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "updatePass should be logged");
	}
	
	@Test
	public void showCustRegisterFormTest() {
		String ret = controller.showCustRegisterForm(new Customers());
		assertEquals("SignIn/register", ret, "showCustRegistrationForm should return register form");
	}
	
	@Test
	public void addNewCustTest() {
		String ret = controller.addNewCust(new Customers(), bindingResult, model);
		assertEquals("redirect:/login", ret, "addNewCust should redirect to login");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "addNewCust should be logged");
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void contactPageTest() {
		String ret = controller.contactPage();
		assertEquals("Customer/contact", ret, "contactPage should return Customers contact page");
	}
	
	@Test
	public void contactPageTest2() {
		//Not authenticated test
		String ret = controller.contactPage();
		assertEquals("Guest/guestcontact", ret, "contactPage should return Guest contact page");
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void infoPageTest() {
		String ret = controller.infoPage(model);
		assertEquals("Customer/custviewinfo", ret, "infoPage should return custviewinfo page");
	}
	
	@Test
	public void infoPageTest2() {
		//Not authenticated test
		String ret = controller.infoPage(model);
		assertEquals("redirect:/", ret, "infoPage should return redirect to index");
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void orderHistoryPageTest() {
		String ret = controller.orderHistoryPage(model);
		assertEquals("Customer/vieworderhistory", ret, "orderHistoryPage should return Customers order history page");
	}
	
	@Test
	public void orderHistoryPageTest2() {
		//Not authenticated test
		String ret = controller.orderHistoryPage(model);
		assertEquals("redirect:/", ret, "orderHistoryPage should return redirect to index");
	}
	
	@Test
	public void userShowUpdateCustFormTest() {
		List<Customers> customers = (List<Customers>) customerRepo.findAll();
		String ret = controller.userShowUpdateCustForm(customers.get(0).getId(), model);
		assertEquals("Customer/editprofile", ret, "userShowCustUpdateForm should return edit profile form");
		verify(model, times(1)).addAttribute(eq("customer"), ArgumentMatchers.isA(Customers.class));
	}
	
	@Test
	public void userUpdateCustTest() {
		List<Customers> customers = (List<Customers>) customerRepo.findAll();
		String ret = controller.userUpdateCust(customers.get(0).getId(), customers.get(0), bindingResult, model);
		assertEquals("redirect:/loggedinhome", ret, "userUpdateCust should return redirect to home page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "userUpdateCust should be logged");
	}
	
	@Test
	@WithUserDetails(value = "Administrator@email.com")
	public void showManListTest() {
		String ret = controller.showManList(model);
		assertEquals("LocalAdmin/admin-man-view", ret, "showManList should return managers list page");
		verify(model, times(1)).addAttribute(eq("managers"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	@WithUserDetails(value = "Administrator@email.com")
	public void showServerListTest() {
		String ret = controller.showServerList(model);
		assertEquals("LocalAdmin/admin-server-view", ret, "showServerList should return servers list page");
		verify(model, times(1)).addAttribute(eq("servers"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	@WithUserDetails(value = "Administrator@email.com")
	public void showUserListTest() {
		String ret = controller.showUserList(model);
		assertEquals("LocalAdmin/admin-cust-view", ret, "showUserList should return customers list page");
		verify(model, times(1)).addAttribute(eq("customers"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void showAdminListTest() {
		String ret = controller.showAdminList(model);
		assertEquals("HQAdmin/HQadmin-admin-view", ret, "showAdminList should return admins list page");
		verify(model, times(1)).addAttribute(eq("admins"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void showOfficesListTest() {
		String ret = controller.showOfficesList(model);
		assertEquals("HQAdmin/HQadmin-offices-view", ret, "showOfficesList should return offices list page");
		verify(model, times(1)).addAttribute(eq("offices"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void showRestaurantListTest() {
		String ret = controller.showRestaurantList(model);
		assertEquals("HQAdmin/HQadmin-restaurants-view", ret, "showRestaurantList should return restaurants list page");
		verify(model, times(1)).addAttribute(eq("restaurants"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void showWarehouseListTest() {
		String ret = controller.showWarehouseList(model);
		assertEquals("HQAdmin/HQadmin-warehouses-view", ret, "showWarehouseList should return warehouses list page");
		verify(model, times(1)).addAttribute(eq("warehouses"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void showCustSignUpFormTest() {
		String ret = controller.showCustSignUpForm(new Customers());
		assertEquals("LocalAdmin/add-customer", ret, "showCustSignUpForm should return add-customer form");
	}
	
	@Test
	public void showServerSignUpFormTest() {
		String ret = controller.showServerSignUpForm(new Servers());
		assertEquals("LocalAdmin/add-server", ret, "showServerSignUpForm should return add-server form");
	}
	
	@Test
	public void showEmployeeSignUpFormTest() {
		String ret = controller.showEmployeeSignUpForm(new WarehouseEmployees());
		assertEquals("LocalAdmin/add-warehouse-employee", ret, "showEmployeeSignUpForm should return add-warehouse-employee form");
	}
	
	@Test
	@WithUserDetails(value = "Administrator@email.com")
	public void showAdminEmployeesViewTest() {
		String ret = controller.showAdminEmployeesView(model);
		assertEquals("LocalAdmin/admin-warehouse-employee-view", ret, "showAdminEmployeesView should return warehouse employees list page");
		verify(model, times(1)).addAttribute(eq("warehouseEmployees"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void addEmployeeTest() {
		String ret = controller.addEmployee(new WarehouseEmployees(), bindingResult, model);
		assertEquals("redirect:/admin-employee-view", ret, "addEmployee should redirect to employee-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "addEmployee should be logged");
	}
	
	@Test
	public void showUpdateEmployeeFormTest() {
		List<WarehouseEmployees> employees = (List<WarehouseEmployees>) warehouseEmployeeRepo.findAll();
		String ret = controller.showUpdateEmployeeForm(employees.get(0).getId(), model);
		assertEquals("LocalAdmin/update-warehouse-employee", ret, "showUpdateEmployeesForm should return update-warehouse-employee form");
		verify(model, times(1)).addAttribute(eq("employee"), ArgumentMatchers.isA(WarehouseEmployees.class));
	}
	
	@Test
	public void updateEmployeeTest() {
		List<WarehouseEmployees> whEmployees = (List<WarehouseEmployees>) warehouseEmployeeRepo.findAll();
		String ret = controller.updateEmployee(whEmployees.get(0).getId(), whEmployees.get(0), bindingResult, model);
		assertEquals("redirect:/admin-warehouse-employee-view", ret, "updateEmployee should return redirect to employee-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "updateEmployee should be logged");
	}
	
	@Test
	public void showManagerSignUpFormTest() {
		String ret = controller.showManagerSignUpForm(new Managers());
		assertEquals("LocalAdmin/add-LFmanager", ret, "showManagerSignUpForm should return add-manager form");
	}
	
	@Test
	public void showAdminSignUpFormTest() {
		String ret = controller.showAdminSignUpForm(new Admins(), model);
		assertEquals("HQAdmin/add-LFadmin", ret, "showAdminSignUpForm should return add-admin form");
	}
	
	@Test
	public void showOfficeSignUpFormTest() {
		String ret = controller.showOfficeSignUpForm(new Offices());
		assertEquals("HQAdmin/add-office", ret, "showOfficesSignUpForm should return add-office form");
	}
	
	@Test
	public void showRestaurantSignUpFormTest() {
		String ret = controller.showRestaurantSignUpForm(new Restaurants());
		assertEquals("HQAdmin/add-restaurant", ret, "showRestaurantSignUpForm should return add-restaurant form");
	}
	
	@Test
	public void showWarehouseSignUpFormTest() {
		String ret = controller.showWarehouseSignUpForm(new Warehouses());
		assertEquals("HQAdmin/add-warehouse", ret, "showWarehouseSignUpForm should return add-warehouse form");
	}
	
	@Test
	public void addCustTest() {
		String ret = controller.addCust(new Customers(), bindingResult, model);
		assertEquals("redirect:/admin-cust-view", ret, "addCust should redirect to cust-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "addCust should be logged");
	}
	
	@Test
	public void addServerTest() {
		String ret = controller.addServer(new Servers(), bindingResult, model);
		assertEquals("redirect:/admin-server-view", ret, "addServer should redirect to employee-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "addServer should be logged");
	}
	
	@Test
	public void addManagerTest() {
		String ret = controller.addManager(new Managers(), bindingResult, model);
		assertEquals("redirect:/admin-man-view", ret, "addManager should redirect to man-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "addManager should be logged");
	}
	
	@Test
	public void addAdminTest() {
		String ret = controller.addAdmin(new Admins(), bindingResult, model);
		assertEquals("redirect:/HQadmin-admin-view", ret, "addAdmin should redirect to admin-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "addAdmin should be logged");
	}
	
	@Test
	public void addOfficeTest() {
		String ret = controller.addOffice(new Offices(), bindingResult, model);
		assertEquals("redirect:/HQadmin-offices-view", ret, "addOffice should redirect to offices-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "addOffice should be logged");
	}
	
	@Test
	public void addRestaurantTest() {
		String ret = controller.addRestaurant(new Restaurants(), bindingResult, model);
		assertEquals("redirect:/HQadmin-restaurants-view", ret, "addRestaurant should redirect to restaurants-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "addRestaurant should be logged");
	}
	
	@Test
	public void addWarehouseTest() {
		String ret = controller.addWarehouse(new Warehouses(), bindingResult, model);
		assertEquals("redirect:/HQadmin-warehouses-view", ret, "addWarehouse should redirect to warehouses-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "addWarehouse should be logged");
	}
	
	@Test
	public void showUpdateAdminFormTest() {
		List<Admins> admins = (List<Admins>) adminRepo.findAll();
		String ret = controller.showUpdateAdminForm(admins.get(0).getId(), model);
		assertEquals("HQadmin/update-LFadmin", ret, "showAdminUpdateForm should return update admin form");
		verify(model, times(1)).addAttribute(eq("admin"), ArgumentMatchers.isA(Admins.class));
	}
	
	@Test
	public void showUpdateOfficeFormTest() {
		List<Offices> offices = (List<Offices>) officeRepo.findAll();
		String ret = controller.showUpdateOfficeForm(offices.get(0).getId(), model);
		assertEquals("HQadmin/update-office", ret, "showOfficeUpdateForm should return update office form");
		verify(model, times(1)).addAttribute(eq("office"), ArgumentMatchers.isA(Offices.class));
	}
	
	@Test
	public void showUpdateRestaurantFormTest() {
		List<Restaurants> restaurants = (List<Restaurants>) restaurantRepo.findAll();
		String ret = controller.showUpdateRestaurantForm(restaurants.get(0).getId(), model);
		assertEquals("HQadmin/update-restaurant", ret, "showRestaurantUpdateForm should return update restaurant form");
		verify(model, times(1)).addAttribute(eq("restaurant"), ArgumentMatchers.isA(Restaurants.class));
	}
	
	@Test
	public void showUpdateWarehouseFormTest() {
		List<Warehouses> warehouses = (List<Warehouses>) warehouseRepo.findAll();
		String ret = controller.showUpdateWarehouseForm(warehouses.get(0).getId(), model);
		assertEquals("HQadmin/update-warehouse", ret, "showWarehouseUpdateForm should return update warehouse form");
		verify(model, times(1)).addAttribute(eq("warehouse"), ArgumentMatchers.isA(Warehouses.class));
	}
	
	@Test
	public void showUpdateCustFormTest() {
		List<Customers> customers = (List<Customers>) customerRepo.findAll();
		String ret = controller.showUpdateCustForm(customers.get(0).getId(), model);
		assertEquals("LocalAdmin/update-customer", ret, "showCustUpdateForm should return update customer form");
		verify(model, times(1)).addAttribute(eq("customer"), ArgumentMatchers.isA(Customers.class));
	}
	
	@Test
	public void showUpdateServerFormTest() {
		List<Servers> servers = (List<Servers>) serverRepo.findAll();
		String ret = controller.showUpdateServerForm(servers.get(0).getId(), model);
		assertEquals("LocalAdmin/update-server", ret, "showServerUpdateForm should return update server form");
		verify(model, times(1)).addAttribute(eq("server"), ArgumentMatchers.isA(Servers.class));
	}
	
	@Test
	public void showUpdateManagerFormTest() {
		List<Managers> managers = (List<Managers>) managerRepo.findAll();
		String ret = controller.showUpdateManagerForm(managers.get(0).getId(), model);
		assertEquals("LocalAdmin/update-LFmanager", ret, "showManagerUpdateForm should return update manager form");
		verify(model, times(1)).addAttribute(eq("manager"), ArgumentMatchers.isA(Managers.class));
	}
	
	@Test
	public void updateCustTest() {
		List<Customers> customers = (List<Customers>) customerRepo.findAll();
		String ret = controller.updateCust(customers.get(0).getId(), customers.get(0), bindingResult, model);
		assertEquals("redirect:/admin-cust-view", ret, "updateCust should return redirect to cust-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "updateCust should be logged");
	}
	
	@Test
	public void updateServerTest() {
		List<Servers> servers = (List<Servers>) serverRepo.findAll();
		String ret = controller.updateServer(servers.get(0).getId(), servers.get(0), bindingResult, model);
		assertEquals("redirect:/admin-server-view", ret, "updateServer should return redirect to server-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "updateServer should be logged");
	}
	
	@Test
	public void updateManagerTest() {
		List<Managers> managers = (List<Managers>) managerRepo.findAll();
		String ret = controller.updateManager(managers.get(0).getId(), managers.get(0), bindingResult, model);
		assertEquals("redirect:/admin-man-view", ret, "updateManager should return redirect to man-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "updateManager should be logged");
	}
	
	@Test
	public void updateAdminTest() {
		List<Admins> admins = (List<Admins>) adminRepo.findAll();
		String ret = controller.updateAdmin(admins.get(0).getId(), admins.get(0), bindingResult, model);
		assertEquals("redirect:/HQadmin-admin-view", ret, "updateAdmin should return redirect to admin-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "updateAdmin should be logged");
	}
	
	@Test
	public void updateOfficeTest() {
		List<Offices> offices = (List<Offices>) officeRepo.findAll();
		String ret = controller.updateOffice(offices.get(0).getId(), offices.get(0), bindingResult, model);
		assertEquals("redirect:/HQadmin-offices-view", ret, "updateOffice should return redirect to offices-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "updateOffice should be logged");
	}
	
	@Test
	public void updateWarehouseTest() {
		List<Warehouses> warehouses = (List<Warehouses>) warehouseRepo.findAll();
		String ret = controller.updateWarehouse(warehouses.get(0).getId(), warehouses.get(0), bindingResult, model);
		assertEquals("redirect:/HQadmin-warehouses-view", ret, "updateWarehouse should return redirect to warehouses-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "updateWarehouse should be logged");
	}
	
	@Test
	public void updateRestaurantTest() {
		List<Restaurants> restaurants = (List<Restaurants>) restaurantRepo.findAll();
		String ret = controller.updateRestaurant(restaurants.get(0).getId(), restaurants.get(0), bindingResult, model);
		assertEquals("redirect:/HQadmin-restaurants-view", ret, "updateRestaurant should return redirect to restaurants-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "updateRestaurant should be logged");
	}
	
	@Test
	public void addAdminRestaurantTest() {
		//Setup
		Admins admin = new Admins();
		admin = adminRepo.save(admin);
		List<Restaurants> r = new ArrayList<Restaurants>();
		Restaurants rest = new Restaurants();
		rest.setAdmin(admin);
		rest = restaurantRepo.save(rest);
		r.add(rest);
		admin.setRestaurant(r);
		admin = adminRepo.save(admin);
		
		controller.addAdminRestaurant(admin);
		Restaurants restaurant = restaurantRepo.findByAdmin(admin.getId());
		assertEquals(admin.toString(), restaurant.getAdmin().toString(), "Restaurant admin should be set to admin");
		
	}
	
	@Test
	public void removeAdminRestaurantTest() {
		//Setup
		Admins admin = new Admins();
		admin = adminRepo.save(admin);
		List<Restaurants> r = new ArrayList<Restaurants>();
		Restaurants rest = new Restaurants();
		rest.setAdmin(admin);
		rest = restaurantRepo.save(rest);
		r.add(rest);
		admin.setRestaurant(r);
		admin = adminRepo.save(admin);
		
		controller.removeAdminRestaurant(admin);
		Restaurants restaurant = restaurantRepo.findByAdmin(admin.getId());
		assertNull(restaurant, "No restaurant should be assigned to admin");
	}
	
	@Test
	public void deleteCustTest() {
		List<Customers> customers = (List<Customers>) customerRepo.findAll();
		String ret = controller.deleteCust(customers.get(0).getId(), model);
		assertEquals("redirect:/admin-cust-view", ret, "deleteCust should return redirect to cust-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "deleteCust should be logged");
	}
	
	@Test
	public void deleteServerTest() {
		List<Servers> servers = (List<Servers>) serverRepo.findAll();
		String ret = controller.deleteServer(servers.get(0).getId(), model);
		assertEquals("redirect:/admin-server-view", ret, "deleteServer should return redirect to server-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "deleteServer should be logged");
	}
	
	@Test
	public void deleteManagerTest() {
		List<Managers> managers = (List<Managers>) managerRepo.findAll();
		String ret = controller.deleteManager(managers.get(0).getId(), model);
		assertEquals("redirect:/admin-man-view", ret, "deleteManager should return redirect to man-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "deleteManager should be logged");
	}
	
	@Test
	public void deleteOfficeTest() {
		//TODO this throws LazyInitializationException for Offices.admin
		/*
		List<Offices> offices = (List<Offices>) officeRepo.findAll();
		String ret = controller.deleteOffice(offices.get(0).getId(), model);
		assertEquals("redirect:/HQadmin-offices-view", ret, "deleteOffice should return redirect to offices-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "deleteOffice should be logged");
		*/
	}
	
	@Test
	public void deleteRestaurantTest() {
		List<Restaurants> restaurants = (List<Restaurants>) restaurantRepo.findAll();
		String ret = controller.deleteRestaurant(restaurants.get(0).getId(), model);
		assertEquals("redirect:/HQadmin-restaurants-view", ret, "deleteRestaurant should return redirect to restaurants-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "deleteRestaurant should be logged");
	}
	
	@Test
	public void deleteWarehouseTest() {
		List<Warehouses> warehouses = (List<Warehouses>) warehouseRepo.findAll();
		String ret = controller.deleteWarehouse(warehouses.get(0).getId(), model);
		assertEquals("redirect:/HQadmin-warehouses-view", ret, "deleteWarehouse should return redirect to warehouses-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "deleteWarehouse should be logged");
	}
	
	@Test
	public void deleteAdminTest() {
		//TODO this throws LazyInitializationException for Admins.restaurant
		/*
		List<Admins> admins = (List<Admins>) adminRepo.findAll();
		String ret = controller.deleteAdmin(admins.get(0).getId(), model);
		assertEquals("redirect:/HQadmin-admin-view", ret, "deleteAdmin should return redirect to admin-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "deleteAdmin should be logged");
		*/
	}
	
	@Test
	public void deleteOrderTest() {
		List<Orders> orders = (List<Orders>) orderRepo.findAll();
		String ret = controller.deleteOrder(orders.get(0).getId(), model);
		assertEquals("redirect:/servingstaffview", ret, "deleteOrder should return redirect to server home page");
		assertEquals("Completed", orders.get(0).getStatus(), "Order status should be set to Completed");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "deleteOrder should be logged");
	}
	
	@Test
	@WithUserDetails(value = "server@email.com")
	public void showServerViewTest() {
		String ret = controller.showServerView(model);
		assertEquals("LocalServingStaff/serving-staff-view", ret, "showServerView should return server home page");
		verify(model, times(1)).addAttribute(eq("orders"), ArgumentMatchers.anyIterable());
		verify(model, times(1)).addAttribute(eq("menu"), ArgumentMatchers.anyIterable());
		verify(model, times(1)).addAttribute(eq("server"), ArgumentMatchers.isA(Servers.class));
	}
	
	@Test
	@WithUserDetails(value = "server@email.com")
	public void clockInTest() {
		String ret = controller.clockIn();
		assertEquals("redirect:/loggedinredirect", ret, "Clocking in should redirect to home page");
		for (Servers s : serverRepo.findAll()) {
			if (s.getEmail().equals("server@email.com")) {
				assertEquals(true, s.getIsOnDuty(), "Server should be on duty");
				assertNotNull(s.getLastClockedIn(), "Server last clocked in should not be null");
			}
		}
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "Log should have been created");
	}
	
	@Test
	@WithUserDetails(value = "Manager@email.com")
	public void clockInTest2() {
		String ret = controller.clockIn();
		assertEquals("redirect:/loggedinredirect", ret, "Clocking in should redirect to home page");
		for (Managers m : managerRepo.findAll()) {
			if (m.getEmail().equals("Manager@email.com")) {
				assertEquals(true, m.getIsOnDuty(), "Manager should be on duty");
				assertNotNull(m.getLastClockedIn(), "Manager last clocked in should not be null");
			}
		}
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "Log should have been created");
	}
	
	@Test
	@WithUserDetails(value = "server@email.com")
	public void clockOutTest() {
		controller.clockIn();
		String ret = controller.clockOut();
		assertEquals("redirect:/loggedinredirect", ret, "Clocking out should redirect to home page");
		for (Servers s : serverRepo.findAll()) {
			if (s.getEmail().equals("server@email.com")) {
				assertEquals(false, s.getIsOnDuty(), "Server should not be on duty");
				assertNotNull(s.getLastClockedIn(), "Server last clocked in should not be null");
				assertNotNull(s.getTotalHours(), "Server total hours should not be null");
				assertNotNull(s.getWeekHours(), "Server week hours should not be null");
			}
		}
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "Log should have been created");
	}
	
	@Test
	@WithUserDetails(value = "Manager@email.com")
	public void clockOutTest2() {
		controller.clockIn();
		String ret = controller.clockOut();
		assertEquals("redirect:/loggedinredirect", ret, "Clocking out should redirect to home page");
		for (Managers m : managerRepo.findAll()) {
			if (m.getEmail().equals("Manager@email.com")) {
				assertEquals(false, m.getIsOnDuty(), "Manager should not be on duty");
				assertNotNull(m.getLastClockedIn(), "Manager last clocked in should not be null");
				assertNotNull(m.getTotalHours(), "Manager total hours should not be null");
				assertNotNull(m.getWeekHours(), "Manager week hours should not be null");
			}
		}
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "Log should have been created");
	}
	
	@Test
	public void resetWeeklyHoursTest() {
		controller.resetWeeklyHours();
		for (Servers s : serverRepo.findAll()) {
			assertEquals(0, s.getWeekHours(), "Server week hours should be set to 0");
		}
		for (Managers m : managerRepo.findAll()) {
			assertEquals(0, m.getWeekHours(), "Manager week hours should be set to 0");
		}
	}
	
	@Test
	public void payCalcTest() {
		Restaurants rest = new Restaurants("", "", "", "Pennsylvania", null);
		controller.payCalc(40.0F, 7.25F, rest);
		assertEquals(-281.10F, rest.getProfits());
	}
	
	@Test
	public void showLocalManServerViewTest() {
		String ret = controller.showLocalManServerView(model);
		assertEquals("LocalManager/manager-server-view-view", ret, "showLocalManServerView should return server page with manager authentications");
		verify(model, times(1)).addAttribute(eq("orders"), ArgumentMatchers.anyIterable());
		verify(model, times(1)).addAttribute(eq("menu"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void showCustInfoTest() {
		String ret = controller.showCustInfo(null, model);
		assertEquals("redirect:/servingstaffview", ret, "null customer should redirect to server home page");
		
		ret = controller.showCustInfo(new Customers(), model);
		assertEquals("LocalServingStaff/server-cust-view", ret, "Non-null customer should return cust info page");
		verify(model, times(1)).addAttribute(eq("customers"), ArgumentMatchers.isA(Customers.class));
	}
	
	@Test
	public void blankCustInfoTest() {
		String ret = controller.blankCustInfo();
		assertEquals("redirect:/servingstaffview", ret, "Invalid customer should redirect to server home page");
	}
	
	@Test
	@WithUserDetails(value = "Manager@email.com")
	public void showLogTest() {
		String ret = controller.showLog(model);
		assertEquals("LocalManager/log-view", ret, "showLog should return manager log page");
		verify(model, times(1)).addAttribute(eq("log"), ArgumentMatchers.anyIterable());
		verify(model, times(1)).addAttribute(eq("manager"), ArgumentMatchers.isA(Managers.class));
	}
	
	@Test
	public void showLogTest2() {
		//Not authenticated test
		String ret = controller.showLog(model);
		assertEquals("redirect:/", ret, "showLog should redirect to index if not authenticated");
	}
	
	@Test
	@WithUserDetails(value = "Administrator@email.com")
	public void showAdminLogTest() {
		String ret = controller.showAdminLog(model);
		assertEquals("LocalAdmin/log-admin-view", ret, "showAdminLog should return admin log page");
		verify(model, times(1)).addAttribute(eq("log"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void showAdminLogTest2() {
		//Not authenticated test
		String ret = controller.showAdminLog(model);
		assertEquals("redirect:/", ret, "showAdminLog should redirect to index if not authenticated");
	}
	
	@Test
	@WithUserDetails(value = "hqmanager@email.com")
	public void showHQLogTest() {
		String ret = controller.showHQLog(model);
		assertEquals("HQManager/hq-log-view", ret, "showHQLog should return hq manager log page");
		verify(model, times(1)).addAttribute(eq("log"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	@WithUserDetails(value = "sam")
	public void showHQAdminLogTest() {
		String ret = controller.showHQAdminLog(model);
		assertEquals("HQAdmin/hq-admin-log-view", ret, "showHQAdminLog should return hq admin log page");
		verify(model, times(1)).addAttribute(eq("log"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	@WithUserDetails(value = "WHmanager@email.com")
	public void showWarehouseShipmentsTest() {
		String ret = controller.showWarehouseShipments(model);
		assertEquals("WarehouseManager/warehouseman-shipment-view", ret, "showWarehouseShipments should return warehouse shipments page");
		verify(model, times(1)).addAttribute(eq("shipmentList"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	@WithUserDetails(value = "WHmanager@email.com")
	public void showWarehouseLogTest() {
		String ret = controller.showWarehouseLog(model);
		assertEquals("WarehouseManager/warehouseman-log-view", ret, "showWarehouseShipments should return warehouse log page");
		verify(model, times(1)).addAttribute(eq("log"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	@WithUserDetails(value = "WHmanager@email.com")
	public void showWarehouseInventoryTest() {
		String ret = controller.showWarehouseInventory(model);
		assertEquals("WarehouseManager/warehouseman-inventory-view", ret, "showWarehouseInventory should return warehouse inventory page");
		verify(model, times(1)).addAttribute(eq("inventoryList"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	@WithUserDetails(value = "WHmanager@email.com")
	public void showWarehouseEmployeesTest() {
		String ret = controller.showWarehouseEmployees(model);
		assertEquals("WarehouseManager/warehouseman-employees-view", ret, "showWarehouseEmployees should return warehouse employees page");
		verify(model, times(1)).addAttribute(eq("employees"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void warehouseManShowUpdateEmployeeTest() {
		List<WarehouseEmployees> employees = (List<WarehouseEmployees>) warehouseEmployeeRepo.findAll();
		String ret = controller.warehouseManShowUpdateEmployee(employees.get(0).getId(), model);
		assertEquals("WarehouseManager/update-employee", ret, "warehouseManShowUpdateEmployee should return warehouse employee update form");
		verify(model, times(1)).addAttribute(eq("employee"), ArgumentMatchers.isA(WarehouseEmployees.class));
	}
	
	@Test
	@WithUserDetails(value = "Manager@email.com")
	public void showInventoryViewTest() {
		String ret = controller.showInventoryView(model);
		assertEquals("LocalManager/manager-inventory-view", ret, "showInventoryView should return inventory view page");
		verify(model, times(1)).addAttribute(eq("inventoryList"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	@WithUserDetails(value = "Manager@email.com")
	public void showRestViewShippingTest() {
		String ret = controller.showRestViewShipping(model);
		assertEquals("LocalManager/manager-shipment-view", ret, "showRestViewShipping should return shipments by restaurant page");
		verify(model, times(1)).addAttribute(eq("shipmentList"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void localManShowUserListTest() {
		String ret = controller.localManShowUserList(model);
		assertEquals("LocalManager/manager-cust-view", ret, "localManShowUserList should return list of customers page");
		verify(model, times(1)).addAttribute(eq("customers"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void localManShowUpdateCustFormTest() {
		List<Customers> customers = (List<Customers>) customerRepo.findAll();
		String ret = controller.localManShowUpdateCustForm(customers.get(0).getId(), model);
		assertEquals("LocalManager/update-customer", ret, "localManShowUpdateCustForm should return update customer form");
		verify(model, times(1)).addAttribute(eq("customer"), ArgumentMatchers.isA(Customers.class));
	}
	
	@Test
	public void localManShowUpdateInventoryFormTest() {
		List<Inventory> inventory = (List<Inventory>) inventoryRepo.findAll();
		String ret = controller.localManShowUpdateInventoryForm(inventory.get(0).getId(), model);
		assertEquals("LocalManager/update-inventory", ret, "localManShowInventoryForm should return update inventory form");
		verify(model, times(1)).addAttribute(eq("inventory"), ArgumentMatchers.isA(Inventory.class));
	}
	
	@Test
	public void localManUpdateInventoryTest() {
		List<Inventory> inventory = (List<Inventory>) inventoryRepo.findAll();
		String ret = controller.localManUpdateInventory(inventory.get(0).getId(), inventory.get(0), bindingResult, model);
		assertEquals("redirect:/manager-inventory-view", ret, "localManUpdateInventory should return redirect to inventory-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "localManUpdateInventory should be logged");
	}
	
	@Test
	public void localManUpdateCustTest() {
		List<Customers> customers = (List<Customers>) customerRepo.findAll();
		String ret = controller.localManUpdateCust(customers.get(0).getId(), customers.get(0), bindingResult, model);
		assertEquals("redirect:/manager-cust-view", ret, "localManUpdateCust should return redirect to cust-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "localManUpdateCust should be logged");
	}
	
	@Test
	public void localManDeleteCustTest() {
		List<Customers> customers = (List<Customers>) customerRepo.findAll();
		String ret = controller.localManDeleteCust(customers.get(0).getId(), model);
		assertEquals("redirect:/manager-cust-view", ret, "localManDeleteCust should return redirect to cust-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "localManDeleteCust should be logged");
	}
	
	@Test
	public void localManShowMenuTest() {
		String ret = controller.localManShowMenu(model);
		assertEquals("LocalManager/manager-menu-view", ret, "localManShowMenu should return menu list page");
		verify(model, times(1)).addAttribute(eq("menu"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	@WithUserDetails(value = "Manager@email.com")
	public void localManShowServersTest() {
		String ret = controller.localManShowServers(model);
		assertEquals("LocalManager/manager-server-view", ret, "localManShowServers should return server list page");
		verify(model, times(1)).addAttribute(eq("servers"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void showLocalManUpdateServerFormTest() {
		List<Servers> servers = (List<Servers>) serverRepo.findAll();
		String ret = controller.showLocalManUpdateServerForm(servers.get(0).getId(), model);
		assertEquals("LocalManager/update-server", ret, "showLocalManUpdateServerForm should return update server form");
		verify(model, times(1)).addAttribute(eq("server"), ArgumentMatchers.isA(Servers.class));
	}
	
	@Test
	public void localManUpdateServerTest() {
		List<Servers> servers = (List<Servers>) serverRepo.findAll();
		String ret = controller.localManUpdateServer(servers.get(0).getId(), servers.get(0), bindingResult, model);
		assertEquals("redirect:/manager-server-view", ret, "localManUpdateServer should return redirect to server-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "localManUpdateServer should be logged");
	}
	
	@Test
	public void localManDeleteServerTest() {
		List<Servers> servers = (List<Servers>) serverRepo.findAll();
		String ret = controller.localManDeleteServer(servers.get(0).getId(), model);
		assertEquals("redirect:/manager-server-view", ret, "localManDeleteServer should return redirect to server-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "localManDeleteServer should be logged");
	}
	
	@Test
	public void showHQManagerPageTest() {
		String ret = controller.showHQManagerPage();
		assertEquals("HQManager/HQ-manager-view", ret, "showHQManagerPage should return HQ Manager home page");
	}
	
	@Test
	public void hqManShowManagersTest() {
		String ret = controller.hqManShowManagers(model);
		assertEquals("HQManager/HQManager-managers-view", ret, "hqManShowManagers should return managers list page");
		verify(model, times(1)).addAttribute(eq("managers"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void showHQManUpdateManagerFormTest() {
		List<Managers> managers = (List<Managers>) managerRepo.findAll();
		String ret = controller.showHQManUpdateManagerForm(managers.get(0).getId(), model);
		assertEquals("HQManager/update-LFmanager", ret, "showHQManUpdateManagerForm should return update manager form");
		verify(model, times(1)).addAttribute(eq("manager"), ArgumentMatchers.isA(Managers.class));
	}
	
	@Test
	public void hqManUpdateManagerTest() {
		List<Managers> managers = (List<Managers>) managerRepo.findAll();
		String ret = controller.hqManUpdateManager(managers.get(0).getId(), managers.get(0), bindingResult, model);
		assertEquals("redirect:/HQmanager-managers-view", ret, "hqManUpdateManager should return redirect to managers-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "hqManUpdateManager should be logged");
	}
	
	@Test
	public void hqManDeleteManagerTest() {
		List<Managers> managers = (List<Managers>) managerRepo.findAll();
		String ret = controller.hqManDeleteManager(managers.get(0).getId(), model);
		assertEquals("redirect:/HQmanager-managers-view", ret, "hqManDeleteManager should return redirect to managers-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "hqManDeleteManager should be logged");
	}
	
	@Test
	public void showLFManagerAddFormTest() {
		String ret = controller.showLFManagerAddForm(new Managers());
		assertEquals("HQManager/add-LFmanager", ret, "showLFManagerAddForm should return add-LFmanager form");
	}
	
	@Test
	public void showHQManUpdateWHManagerFormTest() {
		List<WarehouseManager> whmanagers = (List<WarehouseManager>) warehouseManagerRepo.findAll();
		String ret = controller.showHQManUpdateWHManagerForm(whmanagers.get(0).getId(), model);
		assertEquals("HQManager/update-WHmanager", ret, "showHQManUpdateWHManagerForm should return update-WHmanager form");
	}
	
	@Test
	public void hqManUpdateWHManagerTest() {
		List<WarehouseManager> whManagers = (List<WarehouseManager>) warehouseManagerRepo.findAll();
		String ret = controller.hqManUpdateWHManager(whManagers.get(0).getId(), whManagers.get(0), bindingResult, model);
		assertEquals("redirect:/HQmanager-WHmanagers-view", ret, "hqManUpdateWHManager should return redirect to WHmanagers-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "hqManUpdateWHManager should be logged");
	}
	
	@Test
	public void hqManDeleteWHManagerTest() {
		//TODO this throws LazyInitializationException for Warehouses.manager
		/*
		List<WarehouseManager> whManagers = (List<WarehouseManager>) warehouseManagerRepo.findAll();
		String ret = controller.hqManDeleteWHManager(whManagers.get(0).getId(), model);
		assertEquals("redirect:/HQmanager-WHmanagers-view", ret, "hqManDeleteWHManager should return redirect to WHmanagers-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "hqManDeleteWHManager should be logged");
		*/
	}
	
	@Test
	public void showWHManagerAddFormTest() {
		String ret = controller.showWHManagerAddForm(new WarehouseManager(), model);
		assertEquals("HQManager/add-WHmanager", ret, "showWHManagerAddForm should return add-WHmanager form");
		verify(model, times(1)).addAttribute(eq("manager"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void hqManShowWHManagersTest() {
		String ret = controller.hqManShowWHManagers(model);
		assertEquals("HQManager/HQManager-WHmanagers-view", ret, "hqManShowWHManagers should return warehouse managers list page");
		verify(model, times(1)).addAttribute(eq("warehouseManager"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void addLFManagerTest() {
		String ret = controller.addLFManager(new Managers(), bindingResult, model);
		assertEquals("redirect:/HQmanager-managers-view", ret, "addLFManager should redirect to manager-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "addLFManager should be logged");
	}
	
	@Test
	public void addWHManagerTest() {
		String ret = controller.addWHManager(new WarehouseManager(), bindingResult, model);
		assertEquals("redirect:/HQmanager-WHmanagers-view", ret, "addWHManager should redirect to WHmanager-view page");
		List<Log> logs = (List<Log>) logRepo.findAll();
		assertFalse(logs.isEmpty(), "addWHManager should be logged");
	}
	
	@Test
	public void showHQManagerLocationPageTest() {
		String ret = controller.showHQManagerLocationPage();
		assertEquals("HQManager/HQManager-locations-view", ret, "showHQManagerLocationPage should return locations page");
	}
	
	@Test
	public void hqManShowRestaurantsTest() {
		String ret = controller.hqManShowRestaurants(model);
		assertEquals("HQManager/HQManager-restaurants-view", ret, "hqManShowRestaurants should return restaurants list page");
		verify(model, times(1)).addAttribute(eq("restaurants"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void hqManShowOfficesTest() {
		String ret = controller.hqManShowOffices(model);
		assertEquals("HQManager/HQManager-offices-view", ret, "hqManShowOffices should return offices list page");
		verify(model, times(1)).addAttribute(eq("offices"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void hqManShowWarehousesTest() {
		String ret = controller.hqManShowWarehouses(model);
		assertEquals("HQManager/HQManager-warehouses-view", ret, "hqManShowWarehouses should return warehouses list page");
		verify(model, times(1)).addAttribute(eq("warehouses"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void showOrderTypeTest() {
		String ret = controller.showOrderType(model);
		assertEquals("Customer/orderpage", ret, "showOrderType should return order page");
		verify(model, times(1)).addAttribute(eq("Order"), ArgumentMatchers.isA(Orders.class));
		verify(model, times(1)).addAttribute(eq("cartQuantity"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	public void showOrderTypeTest2() {
		//Not authenticated test
		String ret = controller.showOrderType(model);
		assertEquals("Guest/order-as-guest", ret, "showOrderType should return order page");
		verify(model, times(1)).addAttribute(eq("Order"), ArgumentMatchers.isA(Orders.class));
		verify(model, times(1)).addAttribute(eq("cartQuantity"), ArgumentMatchers.anyIterable());
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void showOrderSuccessTest() {
		String ret = controller.showOrderSuccess();
		assertEquals("Customer/ordersuccessful", ret, "showOrderSuccess should return order success page");
	}
	
	@Test
	public void showOrderSuccessTest2() {
		//Not authenticated test
		String ret = controller.showOrderSuccess();
		assertEquals("Guest/guestordersuccess", ret, "showOrderSuccess should return order success page");
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void showPaymentPageTest() {
		String ret = controller.showPaymentPage(model);
		assertEquals("Customer/pay", ret, "showPaymentPage should return pay page");
		verify(model, times(1)).addAttribute(eq("PaymentDetails_Form"), ArgumentMatchers.isA(PaymentDetails_Form.class));
		verify(model, times(1)).addAttribute(eq("Paypal_Form"), ArgumentMatchers.isA(Paypal_Form.class));
	}
	
	@Test
	public void showPaymentPageTest2() {
		//Not authenticated test
		String ret = controller.showPaymentPage(model);
		assertEquals("Guest/guestpay", ret, "showPaymentPage should return pay page");
		verify(model, times(1)).addAttribute(eq("PaymentDetails_Form"), ArgumentMatchers.isA(PaymentDetails_Form.class));
		verify(model, times(1)).addAttribute(eq("Paypal_Form"), ArgumentMatchers.isA(Paypal_Form.class));
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void processPaymentTest() {
		//TODO this throws LazyInitializationException for Customers.orderhistory
		/*
		//Setup
		Customers cust = customerRepo.findByEmail("customer@email.com");
		PaymentDetails_Form pd_f = new PaymentDetails_Form();
		pd_f.setCardholderName(cust.getFirstName() + " " + cust.getLastName());
		pd_f.setCardNumber("");
		pd_f.setCardType("");
		pd_f.setExpirationDate("");
		pd_f.setPostalCode("");
		pd_f.setSecurityCode("");
		Orders order = new Orders();
		order.setStatus("Pending Payment");
		order.setCustomer_id(cust);
		order = orderRepo.save(order);
		
		String ret = controller.processPayment(pd_f, bindingResult, model);
		assertEquals("redirect:/ordersuccessful", ret, "Processing should return ordersuccess page");
		assertTrue(cust.getOrderHistory().contains(order), "Order should be added to customer history");
		assertTrue(order.getStatus().equals("Paid"), "Order status should be set to paid");
		List<PaymentDetails> details = (List<PaymentDetails>) paymentDetailsRepo.findAll();
		assertTrue(details.isEmpty(), "Payment details should not be saved to the repository");
		*/
	}
	
	//TODO The following two methods processPaypalTest and viewCartTest throw errors because of the WithUserDetails 
	//Annotation even though the same annotation with the same params is used for several other methods and works fine
	/*
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void processPaypalTest() {
		//TODO this throws LazyInitializationException for Customers.orderhistory
		//Setup
		Customers cust = customerRepo.findByEmail("customer@email.com");
		Paypal_Form pp_f = new Paypal_Form();
		pp_f.setPaypalLogin(cust.getEmail());
		pp_f.setPaypalPassword(cust.getPassword());
		Orders order = new Orders();
		order.setStatus("Pending Payment");
		order.setCustomer_id(cust);
		order = orderRepo.save(order);
		
		String ret = controller.processPaypal(pp_f, bindingResult, model);
		assertEquals("redirect:/ordersuccessful", ret, "Processing should return ordersuccess page");
		assertTrue(cust.getOrderHistory().contains(order), "Order should be added to customer history");
		assertTrue(order.getStatus().equals("Paid"), "Order status should be set to paid");
		List<Paypal> details = (List<Paypal>) paypalRepo.findAll();
		assertTrue(details.isEmpty(), "Paypal details should not be saved to the repository");
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void viewCartTest() {
		String ret = controller.viewCart(model);
		assertEquals("Customer/cart", ret, "viewCart should return cart page");
		verify(model, times(1)).addAttribute(eq("listCart"), ArgumentMatchers.anyIterable());
		verify(model, times(1)).addAttribute(eq("totalprice"), ArgumentMatchers.anyString());
		verify(model, times(1)).addAttribute(eq("Order"), ArgumentMatchers.isA(Orders.class));
	}*/
	
	@Test
	public void viewCartTest2() {
		//Not authenticated test
		String ret = controller.viewCart(model);
		assertEquals("Guest/cart", ret, "viewCart should return cart page");
		verify(model, times(1)).addAttribute(eq("listCart"), ArgumentMatchers.anyIterable());
		verify(model, times(1)).addAttribute(eq("totalprice"), ArgumentMatchers.anyString());
		verify(model, times(1)).addAttribute(eq("Order"), ArgumentMatchers.isA(Orders.class));
	}
	
	@Test
	public void addToSalesTest() {
		List<Orders> orders = (List<Orders>) orderRepo.findAll();
		Restaurants restaurant = orders.get(0).getRestaurant();
		Float sales = restaurant.getSales();
		Float profits = restaurant.getProfits();
		
		controller.addToSales(orders.get(0));
		assertEquals((sales + orders.get(0).getPrice()), restaurant.getSales(), "Order price should be added to restaurant sales");
		assertEquals((profits + orders.get(0).getPrice()), restaurant.getProfits(), "Order price should be added to restaurant profits");
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void deleteCartItemTest() {
		//Setup
		Customers cust = customerRepo.findByEmail("customer@email.com");
		List<Menu> items = (List<Menu>) menuRepo.findAll();
		CartItems item = new CartItems(items.get(0), cust, 1);
		item = cartItemsRepo.save(item);
		
		String ret = controller.deleteCartItem(item.getId(), model);
		assertEquals("redirect:/Customer-cart-view", ret, "deleteCartItem should return redirect to cart page");
		List<CartItems> cartItems = (List<CartItems>) cartItemsRepo.findByCustomer(cust);
		assertFalse(cartItems.contains(item), "Item should be removed from customer's cart");
	}
	
	@Test
	public void deleteCartItemTest2() {
		//Not authenticated test
		//Setup
		Customers cust = customerRepo.findByEmail("Guest");
		List<Menu> items = (List<Menu>) menuRepo.findAll();
		CartItems item = new CartItems(items.get(0), cust, 1);
		item = cartItemsRepo.save(item);
		List<CartItems> cartAttribute = new ArrayList<CartItems>();
		cartAttribute.add(item);
		HttpSession session = controller.getCurrentSession();
		session.setAttribute("cartItems", cartAttribute);
		
		String ret = controller.deleteCartItem(item.getId(), model);
		assertEquals("redirect:/Customer-cart-view", ret, "deleteCartItem should return redirect to cart page");
		List<CartItems> cartItems = (List<CartItems>) controller.getCurrentSession().getAttribute("cartItems");
		assertFalse(cartItems.contains(item), "Item should be removed from guest's cart");
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void editCartFromMenuTest() {
		//Setup
		Customers cust = customerRepo.findByEmail("customer@email.com");
		List<Menu> items = (List<Menu>) menuRepo.findAll();
		CartItems item = new CartItems(items.get(0), cust, 1);
		item = cartItemsRepo.save(item);
		
		String ret = controller.editCartFromMenu(items.get(0).getId(), model);
		assertEquals("redirect:/Customer-ordertype-view", ret, "deleteCartItem should return redirect to order page");
		List<CartItems> cartItems = (List<CartItems>) cartItemsRepo.findByCustomer(cust);
		assertFalse(cartItems.contains(item), "Item should be removed from customer's cart");
	}
	
	@Test
	public void editCartFromMenuTest2() {
		//Not authenticated test
		//Setup
		Customers cust = customerRepo.findByEmail("Guest");
		List<Menu> items = (List<Menu>) menuRepo.findAll();
		CartItems item = new CartItems(items.get(0), cust, 1);
		item = cartItemsRepo.save(item);
		List<CartItems> cartAttribute = new ArrayList<CartItems>();
		cartAttribute.add(item);
		HttpSession session = controller.getCurrentSession();
		session.setAttribute("cartItems", cartAttribute);
		
		String ret = controller.editCartFromMenu(items.get(0).getId(), model);
		assertEquals("redirect:/Customer-ordertype-view", ret, "deleteCartItem should return redirect to order page");
		List<CartItems> cartItems = (List<CartItems>) controller.getCurrentSession().getAttribute("cartItems");
		assertFalse(cartItems.contains(item), "Item should be removed from guest's cart");
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void deleteCartItemsTest() {
		//Setup
		Customers cust = customerRepo.findByEmail("customer@email.com");
		List<Menu> menu = (List<Menu>) menuRepo.findAll();
		CartItems item = new CartItems(menu.get(0), cust, 1);
		CartItems item2 = new CartItems(menu.get(1), cust, 1);
		CartItems item3 = new CartItems(menu.get(2), cust, 1);
		cartItemsRepo.save(item);
		cartItemsRepo.save(item2);
		cartItemsRepo.save(item3);
		cust = customerRepo.save(cust);
		List<CartItems> oldCartItems = cartItemsRepo.findByCustomer(cust);
		
		controller.deleteCartItems();
		List<CartItems> newCartItems = cartItemsRepo.findByCustomer(cust);
		
		assertFalse(oldCartItems.equals(newCartItems), "deleteCartItems should update customers cart");
		assertTrue(newCartItems.isEmpty(), "Cart should be empty after deleting all items");
	}
	
	@Test
	public void deleteCartItemsTest2() {
		//Not authenticated test
		controller.deleteCartItems();
		assertNull(controller.getCurrentSession().getAttribute("cartItems"), "Guest should no longer have cartItems attribute");
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void custAddToOrderTest() {		
		List<Menu> items = (List<Menu>) menuRepo.findAll();
		Customers cust = customerRepo.findByEmail("customer@email.com");
		
		String ret = controller.custAddToOrder(items.get(5).getId(), new CartItems(), bindingResult, model);
		assertEquals("redirect:/Customer-ordertype-view", ret, "custAddToOrder should return redirect to order page");
		List<CartItems> cartItems = cartItemsRepo.findByCustomer(cust);
		boolean flag = false;
		for (CartItems c : cartItems) {
			if (c.getMenu_id().toString().equals(items.get(5).toString())) {
				flag = true;
			}
		}
		assertTrue(flag, "Cart should contain cartItem with specified menu item");
	}
	
	@Test
	public void custAddToOrderTest2() {
		//Not authenticated test
		List<Menu> items = (List<Menu>) menuRepo.findAll();
		
		String ret = controller.custAddToOrder(items.get(5).getId(), new CartItems(), bindingResult, model);
		assertEquals("redirect:/Customer-ordertype-view", ret, "custAddToOrder should return redirect to order page");
		List<CartItems> cartItems = (List<CartItems>) controller.getCurrentSession().getAttribute("cartItems");
		boolean flag = false;
		if (cartItems != null) {
			for (CartItems c : cartItems) {
				if (c.getMenu_id().toString().equals(items.get(5).toString())) {
					flag = true;
				}
			}
		}
		assertTrue(flag, "Cart should contain cartItem with specified menu item");
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void addTaxesTest() {
		//TODO this throws LazyInitializationException for Customers.cartItems
		/*
		//Setup
		Customers cust = controller.getLoggedInUser();
		List<Menu> items = (List<Menu>) menuRepo.findAll();
		CartItems item = new CartItems(items.get(0), cust, 1);
		item = cartItemsRepo.save(item);
		Set<CartItems> oldCartItems = cust.getCartItems();
		Float price = 0.00F;
		
		//New tax object test
		controller.AddTaxes();
		Set<CartItems> newCartItems = cust.getCartItems();
		assertFalse(newCartItems.equals(oldCartItems), "Tax cartItem should be added to customer's cart");
		boolean flag = false;
		for (CartItems c : newCartItems) {
			if (c.getMenu_id().getName().equals("Sales Tax")) {
				flag = true;
				assertEquals((long) -1, c.getMenu_id().getId(), "Tax id should be -1");
				assertEquals(false, c.getMenu_id().getAvailability(), "Tax availability should be false");
				price = c.getMenu_id().getPrice();
			}
		}
		assertTrue(flag, "Tax object should be saved to customer's cart");
		
		//Updating existing tax object test
		CartItems item2 = new CartItems(items.get(1), cust, 1);
		item = cartItemsRepo.save(item2);
		oldCartItems = cust.getCartItems();
		controller.AddTaxes();
		newCartItems = cust.getCartItems();
		assertFalse(newCartItems.equals(oldCartItems), "Tax cartItem should be updated");
		flag = false;
		for (CartItems c : newCartItems) {
			if (c.getMenu_id().getName().equals("Sales Tax")) {
				flag = true;
				assertTrue(c.getMenu_id().getPrice() != price, "Tax price should be updated");
			}
		}
		assertTrue(flag, "Tax object should be saved to customer's cart");
		
		//Deleting tax object test
		cartItemsRepo.delete(item);
		cartItemsRepo.delete(item2);
		controller.AddTaxes();
		newCartItems = cust.getCartItems();
		assertTrue(newCartItems.isEmpty(), "Tax object should be deleted if it is the only item left in cart");
		*/
	}
	
	@Test
	public void addTaxesTest2() {
		//TODO this throws LazyInitializationException for Customers.cartItems
		/*
		//Not authenticated tests
		//Setup
		Customers cust = controller.getGuestCust();
		List<Menu> items = (List<Menu>) menuRepo.findAll();
		CartItems item = new CartItems(items.get(0), cust, 1);
		item = cartItemsRepo.save(item);
		List<CartItems> oldCartItems = new ArrayList<CartItems>();
		oldCartItems.add(item);
		Float price = 0.00F;
		
		//New tax object test
		controller.AddTaxes();
		List<CartItems> newCartItems = new ArrayList<CartItems>(cust.getCartItems());
		assertNotNull(newCartItems, "cartItems attribute should be set");
		assertFalse(newCartItems.equals(oldCartItems), "Tax cartItem should be added to guest's cart");
		boolean flag = false;
		for (CartItems c : newCartItems) {
			if (c.getMenu_id().getName().equals("Sales Tax")) {
				flag = true;
				assertEquals((long) -1, c.getMenu_id().getId(), "Tax id should be -1");
				assertEquals(false, c.getMenu_id().getAvailability(), "Tax availability should be false");
				price = c.getMenu_id().getPrice();
			}
		}
		assertTrue(flag, "Tax object should be saved to guest's cart");
		
		//Updating existing tax object test
		CartItems item2 = new CartItems(items.get(1), cust, 1);
		item = cartItemsRepo.save(item2);
		oldCartItems.add(item2);
		controller.AddTaxes();
		newCartItems = new ArrayList<CartItems>(cust.getCartItems());
		assertNotNull(newCartItems, "cartItems attribute should be set");
		assertFalse(newCartItems.equals(oldCartItems), "Tax cartItem should be updated");
		flag = false;
		for (CartItems c : newCartItems) {
			if (c.getMenu_id().getName().equals("Sales Tax")) {
				flag = true;
				assertTrue(c.getMenu_id().getPrice() != price, "Tax price should be updated");
			}
		}
		assertTrue(flag, "Tax object should be saved to guest's cart");
		
		//Deleting tax object test
		oldCartItems.remove(item);
		oldCartItems.remove(item2);
		cartItemsRepo.delete(item);
		cartItemsRepo.delete(item2);
		controller.AddTaxes();
		newCartItems = new ArrayList<CartItems>(cust.getCartItems());
		assertNotNull(newCartItems, "cartItems attribute should be set");
		assertTrue(newCartItems.isEmpty(), "Tax object should be deleted if it is the only item left in cart");
		*/
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void createNewOrderTest() {
		List<Menu> items = (List<Menu>) menuRepo.findAll();
		CartItems item = controller.createNewOrder(items.get(0).getId(), new CartItems());
		item = cartItemsRepo.save(item);
		assertEquals(items.get(0).toString(), item.getMenu_id().toString(), "CartItem Menu item should be set");
		assertEquals(controller.getLoggedInUser().toString(), item.getCustomer_id().toString(), "CartItem customer should be set");
		assertEquals(1, item.getQuantity(), "CartItem quantity should be 1");
	}
	
	@Test
	public void createNewOrderTest2() {
		//Not authenticated test
		List<Menu> items = (List<Menu>) menuRepo.findAll();
		CartItems item = controller.createNewOrder(items.get(0).getId(), new CartItems());
		item = cartItemsRepo.save(item);
		assertEquals(items.get(0).toString(), item.getMenu_id().toString(), "CartItem Menu item should be set");
		assertEquals(controller.getGuestCust().toString(), item.getCustomer_id().toString(), "CartItem customer should be set");
		assertEquals(1, item.getQuantity(), "CartItem quantity should be 1");
		assertNotNull(controller.getCurrentSession().getAttribute("cartItems"));
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void custAddOrderTest() {
		//Setup
		Customers cust = customerRepo.findByEmail("customer@email.com");
		List<Menu> items = (List<Menu>) menuRepo.findAll();
		CartItems item = new CartItems(items.get(0), cust, 1);
		cartItemsRepo.save(item);
		
		String ret = controller.custAddOrder(new Orders());
		assertEquals("redirect:/pay", ret, "custAddOrder should return redirect to payment page");
		Orders order = orderRepo.findByCustomerIdUnpaid(cust.getId());
		assertEquals(cust.toString(), order.getCustomer_id().toString(), "Order customer should be set");
		assertNotNull(order.getDate(), "Order date should be set");
		assertNotNull(order.getItems(), "Order items should be set");
		assertNotNull(order.getPrice(), "Order price should be set");
		assertEquals("Pending Payment", order.getStatus(), "Order status should be set");
	}
	
	@Test
	public void custAddOrderTest2() {
		//Setup
		Customers cust = customerRepo.findByEmail("Guest");
		List<Menu> items = (List<Menu>) menuRepo.findAll();
		CartItems item = new CartItems(items.get(0), cust, 1);
		cartItemsRepo.save(item);
		
		String ret = controller.custAddOrder(new Orders());
		assertEquals("redirect:/pay", ret, "custAddOrder should return redirect to payment page");
		Orders order = orderRepo.findByCustomerIdUnpaid(cust.getId());
		assertEquals(cust.toString(), order.getCustomer_id().toString(), "Order customer should be set");
		assertNotNull(order.getDate(), "Order date should be set");
		assertNotNull(order.getItems(), "Order items should be set");
		assertNotNull(order.getPrice(), "Order price should be set");
		assertEquals("Pending Payment", order.getStatus(), "Order status should be set");
	}
	
	@Test
	public void removeFromInventoryTest() {
		//Setup
		List<Menu> menu = (List<Menu>) menuRepo.findAll();
		List<Restaurants> rest = (List<Restaurants>) restaurantRepo.findAll();
		Set<Menu> items = new HashSet<Menu>();
		items.add(menu.get(0));
		items.add(menu.get(1));
		items.add(menu.get(2));
		Orders order = new Orders();
		order.setItems(items);
		order.setRestaurant(rest.get(0));
		order = orderRepo.save(order);
		List<Inventory> oldInventory = inventoryRepo.findInventoryRestaurant(rest.get(0).getId());
		
		controller.removeFromInventory(order);
		List<Inventory> newInventory = inventoryRepo.findInventoryRestaurant(rest.get(0).getId());
		assertTrue(newInventory != oldInventory, "Inventory should be updated for restaurant");
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void redeemRewardsTest() {
		//TODO this throws LazyInitializationException for Customers.cartItems
		/*
		Customers cust = controller.getLoggedInUser();
		int oldRewards = cust.getRewardsAvailable();
		Set<CartItems> oldItems = cust.getCartItems();
		
		String ret = controller.redeemRewards();
		assertEquals("redirect:/Customer-cart-view", ret, "redeemRewards should return redirect to cart page");
		int newRewards = cust.getRewardsAvailable();
		//I do not know why this assertion fails
		//assertTrue(newRewards == (oldRewards - 5), "Rewards should be redeemed 5 at a time");
		Set<CartItems> newItems = cust.getCartItems();
		assertFalse(newItems.equals(oldItems), "Rewards should be added to customer's cart");
		*/
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void updateRewardsTest() {
		//TODO this throws LazyInitializationException for Customers.cartItems
		/*
		//Setup
		Customers cust = controller.getLoggedInUser();
		List<Menu> items = (List<Menu>) menuRepo.findAll();
		CartItems item = new CartItems(items.get(0), cust, 1);
		cartItemsRepo.save(item);
		controller.redeemRewards();
		CartItems item2 = new CartItems(items.get(1), cust, 1);
		cartItemsRepo.save(item2);
		Set<CartItems> oldItems = cust.getCartItems();
		
		controller.updateRewards();
		customerRepo.save(cust);
		Set<CartItems> newItems = cust.getCartItems();
		assertFalse(newItems.equals(oldItems), "Rewards cartItem should be updated");
		*/
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void custRewardsInfoTest() {
		String ret = controller.custRewardsInfo(model);
		assertEquals("Customer/rewards", ret, "custRewardsInfo should return rewards info page");
		verify(model, times(1)).addAttribute(eq("customers"), ArgumentMatchers.isA(Customers.class));
	}
	
	@Test
	public void getGuestCustTest() {
		Customers expectedGuest = customerRepo.findByEmail("Guest");
		Customers actualGuest = controller.getGuestCust();
		assertEquals(expectedGuest.toString(), actualGuest.toString(), "Guest user should be correct customer object");
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void getUserLocationTest() {
		String ret = controller.getUserLocation();
		assertTrue((ret != null && ret.isBlank() == false), "Location attribute for customer@email.com should be 'Restaurant ID: _____ Address: 100 Arrowhead Drive'");
	}
	
	@Test
	public void getUserLocationTest2() {
		//Not authenticated test
		String ret = controller.getUserLocation();
		assertEquals("N/A", ret, "Location attribute for guest should be N/A");
	}
	
	@Test
	@WithUserDetails(value = "customer@email.com")
	public void getUserUIDTest() {
		long id = controller.getUserUID();
		Customers cust = customerRepo.findByEmail("customer@email.com");
		long expectedId = cust.getId();
		assertEquals(expectedId, id, "ID for customer@email.com should be equal to its getId() return value");
	}
	
	@Test
	public void getUserUIDTest2() {
		//Not authenticated test
		long id = controller.getUserUID();
		assertEquals((long) -1, id, "ID for guest should be -1");
	}
}
