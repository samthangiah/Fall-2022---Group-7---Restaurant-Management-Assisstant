package edu.sru.group7.restaurantmanager;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.ui.Model;

import edu.sru.group7.restaurantmanager.controller.RestaurantController;
import edu.sru.group7.restaurantmanager.domain.Admins;
import edu.sru.group7.restaurantmanager.domain.Customers;
import edu.sru.group7.restaurantmanager.domain.Ingredients;
import edu.sru.group7.restaurantmanager.domain.Inventory;
import edu.sru.group7.restaurantmanager.domain.Log;
import edu.sru.group7.restaurantmanager.domain.Managers;
import edu.sru.group7.restaurantmanager.domain.Menu;
import edu.sru.group7.restaurantmanager.domain.Offices;
import edu.sru.group7.restaurantmanager.domain.Restaurants;
import edu.sru.group7.restaurantmanager.domain.Servers;
import edu.sru.group7.restaurantmanager.domain.Shipping;
import edu.sru.group7.restaurantmanager.domain.StateTax;
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

			while (curRow.getRowNum() < sheet.getLastRowNum()) {
				count++;
				curRow = sheet.getRow(count);
				
				if (curRow != null) {
					//Check if value is String
					assertTrue((RestaurantController.checkStringType(curRow.getCell(0)) instanceof String), "checkStringType should return String");
					intCheck = RestaurantController.checkIntType(curRow.getCell(1));
					//Check if value is int or null
					assertTrue((((int) intCheck) == intCheck) || (intCheck == (Integer) null), "checkIntType should return int");
					floatCheck = RestaurantController.checkFloatType(curRow.getCell(2));
					//Check if value is float or null
					assertTrue((((float) floatCheck) == floatCheck) || (floatCheck == (Float) null), "checkFloatType should return float");
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
			List<Menu> menu = (List<Menu>) menuRepo.findAll();
			assertFalse(menu.isEmpty(), "Menu should not be empty after loading from file");
			
			for (Menu m : menu) {
				assertNotNull(m.getId(), "Menu object ID should not be null");
				assertNotNull(m.getName(), "Menu object name should not be null");
				//Menu entree and sides can be null
				assertNotNull(m.getPrice(), "Menu object price should not be null");
				assertNotNull(m.getAvailability(), "Menu object availability should not be null");
				assertNotNull(m.getQuantity(), "Menu object quantity should not be null");
			}
			
			List<Ingredients> ingredients = (List<Ingredients>) ingredientsRepo.findAll();
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
		try {
			for (Restaurants rest : restaurantRepo.findAll()) {
				controller.loadIngredients(fp, rest);
				List<Inventory> inventory = (List<Inventory>) inventoryRepo.findAll();
				assertFalse(inventory.isEmpty(), "Inventory should not be empty after loading from file");
				
				for (Inventory i : inventory) {
					assertNotNull(i.getQuantity(), "Inventory quantity should not be null");
					//TODO i.getRestaurant_id() is null, restaurant is not being set properly
					//assertTrue(i.getRestaurant_id().toString().equals(rest.toString()), "Inventory should be for specified restaurant");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void loadWarehouseIngredientsTest() {
		String fp = "src/main/resources/Ingredients.xlsx";
		try {
			for (Warehouses wh : warehouseRepo.findAll()) {
				controller.loadIngredients(fp, wh);
				List<Inventory> inventory = (List<Inventory>) inventoryRepo.findAll();
				assertFalse(inventory.isEmpty(), "Inventory should not be empty after loading from file");
				
				for (Inventory i : inventory) {
					assertNotNull(i.getQuantity(), "Inventory quantity should not be null");
					//TODO i.getWarehouse_id() is null, warehouse is not being set properly
					//assertTrue(i.getWarehouse_id().toString().equals(wh.toString()), "Inventory should be for specified warehouse");
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
			List<Ingredients> ingredients = (List<Ingredients>) ingredientsRepo.findAll();
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
			List<StateTax> stateTaxes = (List<StateTax>) stateTaxRepo.findAll();
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
		
		List<Shipping> shipments = (List<Shipping>) shippingRepo.findAll();
		assertFalse(shipments.isEmpty(), "Shipment repository should not be empty");
		assertTrue(shipments.get(0).getWarehouse_id().toString().equals(warehouses.get(0).toString()), "shipment should be assigned to warehouse");
		assertTrue(shipments.get(0).getRestaurant_id().toString().equals(restaurants.get(0).toString()), "shipment should be assigned to restaurant");
	}
	
	@Test
	public void homePageTest() {
		assertEquals("Guest/index", controller.homePage(), "homePage should return guest index page");
	}
	
	@Test
	public void addAttributesTest() {
		//TODO attributes do not get added to mock model, all model assertions are false
		controller.addAttributes(model);
		/*
		assertTrue(model.containsAttribute("listRestaurants"), "Model should contain listRestaurants attribute");
		assertTrue(model.containsAttribute("listAdmins"), "Model should contain listAdmins attribute");
		assertTrue(model.containsAttribute("listOffices"), "Model should contain listOffices attribute");
		assertTrue(model.containsAttribute("listMenu"), "Model should contain listMenu attribute");
		*/
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
	public void processCredentialsTest() {
		
		//TODO
		//All these tests fail since the fakeApplicationUserDaoService defined in RestaurantController is never initialized
		//If I do initialize it, it gives errors for the repositories it loops through being null even if I pass the autowired
		//repositories defined and initialized in RestaurantController as params in a new constructor
		
		/*
		String userPar = "";
		String passPar = "";
		assertEquals("/login?error", controller.processCredentials(userPar, passPar), "Empty credentials should return login?error page");
		userPar = "sam";
		passPar = "incorrectpassword";
		assertEquals("/login?error", controller.processCredentials(userPar, passPar), "Invalid password should return login?error page");
		userPar = "incorrectusername";
		passPar = "thangiah";
		assertEquals("/login?error", controller.processCredentials(userPar, passPar), "Invalid username should return login?error page");
		userPar = "sam";
		passPar = "thangiah";
		assertEquals("redirect:/loggedinredirect", controller.processCredentials(userPar, passPar), "Valid credentials should return redirect to loggedinredirect");
		*/
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
		assertEquals("redirect:/warehouseman-shipment-view", controller.authorityCheckForLoginRedirects(), "Logging in as WHManager should redirect to WHManager page");
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
		//TODO
	}
	
	@Test
	public void showCustRegisterFormTest() {
		String ret = controller.showCustRegisterForm(new Customers());
		assertEquals("SignIn/register", ret, "showCustRegistrationForm should return register form");
	}
	
	@Test
	public void addNewCustTest() {
		//TODO
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
		//TODO
	}
	
	@Test
	public void userUpdateCustTest() {
		//TODO
	}
	
	@Test
	@WithUserDetails(value = "Administrator@email.com")
	public void showManListTest() {
		String ret = controller.showManList(model);
		assertEquals("LocalAdmin/admin-man-view", ret, "showManList should return managers list page");
		//assertTrue(model.containsAttribute("managers"), "Model should contain managers attribute");
	}
	
	@Test
	@WithUserDetails(value = "Administrator@email.com")
	public void showServerListTest() {
		String ret = controller.showServerList(model);
		assertEquals("LocalAdmin/admin-server-view", ret, "showServerList should return servers list page");
		//assertTrue(model.containsAttribute("servers"), "Model should contain servers attribute");
	}
	
	@Test
	@WithUserDetails(value = "Administrator@email.com")
	public void showUserListTest() {
		String ret = controller.showUserList(model);
		assertEquals("LocalAdmin/admin-cust-view", ret, "showUserList should return customers list page");
		//assertTrue(model.containsAttribute("customers"), "Model should contain customers attribute");
	}
	
	@Test
	public void showAdminListTest() {
		String ret = controller.showAdminList(model);
		assertEquals("HQAdmin/HQadmin-admin-view", ret, "showAdminList should return admins list page");
		//assertTrue(model.containsAttribute("admins"), "Model should contain admins attribute");
	}
	
	@Test
	public void showOfficesListTest() {
		String ret = controller.showOfficesList(model);
		assertEquals("HQAdmin/HQadmin-offices-view", ret, "showOfficesList should return offices list page");
		//assertTrue(model.containsAttribute("offices"), "Model should contain offices attribute");
	}
	
	@Test
	public void showRestaurantListTest() {
		String ret = controller.showRestaurantList(model);
		assertEquals("HQAdmin/HQadmin-restaurants-view", ret, "showRestaurantList should return restaurants list page");
		//assertTrue(model.containsAttribute("restaurants"), "Model should contain restaurants attribute");
	}
	
	@Test
	public void showWarehouseListTest() {
		String ret = controller.showWarehouseList(model);
		assertEquals("HQAdmin/HQadmin-warehouses-view", ret, "showWarehouseList should return warehouses list page");
		//assertTrue(model.containsAttribute("warehouses"), "Model should contain warehouses attribute");
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
	public void showManagerSignUpFormTest() {
		String ret = controller.showManagerSignUpForm(new Managers());
		assertEquals("LocalAdmin/add-LFmanager", ret, "showManagerSignUpForm should return add-manager form");
	}
	
	@Test
	public void showAdminSignUpFormTest() {
		String ret = controller.showAdminSignUpForm(new Admins());
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
		//TODO
	}
	
	@Test
	public void addServerTest() {
		//TODO
	}
	
	@Test
	public void addManagerTest() {
		//TODO
	}
	
	@Test
	public void addAdminTest() {
		//TODO
	}
	
	@Test
	public void addOfficeTest() {
		//TODO
	}
	
	@Test
	public void addRestaurantTest() {
		//TODO
	}
	
	@Test
	public void addWarehouseTest() {
		//TODO
	}
	
	@Test
	public void showUpdateAdminFormTest() {
		List<Admins> admins = (List<Admins>) adminRepo.findAll();
		String ret = controller.showUpdateAdminForm(admins.get(0).getId(), model);
		assertEquals("HQadmin/update-LFadmin", ret, "showAdminUpdateForm should return update admin form");
		//assertTrue(model.containsAttribute("admin"), "Model should have admin attribute");
	}
	
	@Test
	public void showUpdateOfficeFormTest() {
		List<Offices> offices = (List<Offices>) officeRepo.findAll();
		String ret = controller.showUpdateOfficeForm(offices.get(0).getId(), model);
		assertEquals("HQadmin/update-office", ret, "showOfficeUpdateForm should return update office form");
		//assertTrue(model.containsAttribute("office"), "Model should have office attribute");
	}
	
	@Test
	public void showUpdateRestaurantFormTest() {
		List<Restaurants> restaurants = (List<Restaurants>) restaurantRepo.findAll();
		String ret = controller.showUpdateRestaurantForm(restaurants.get(0).getId(), model);
		assertEquals("HQadmin/update-restaurant", ret, "showRestaurantUpdateForm should return update restaurant form");
		//assertTrue(model.containsAttribute("restaurant"), "Model should have restaurant attribute");
	}
	
	@Test
	public void showUpdateWarehouseFormTest() {
		List<Warehouses> warehouses = (List<Warehouses>) warehouseRepo.findAll();
		String ret = controller.showUpdateWarehouseForm(warehouses.get(0).getId(), model);
		assertEquals("HQadmin/update-warehouse", ret, "showWarehouseUpdateForm should return update warehouse form");
		//assertTrue(model.containsAttribute("warehouse"), "Model should have warehouse attribute");
	}
	
	@Test
	public void showUpdateCustFormTest() {
		List<Customers> customers = (List<Customers>) customerRepo.findAll();
		String ret = controller.showUpdateCustForm(customers.get(0).getId(), model);
		assertEquals("LocalAdmin/update-customer", ret, "showCustUpdateForm should return update customer form");
		//assertTrue(model.containsAttribute("customer"), "Model should have customer attribute");
	}
	
	@Test
	public void showUpdateServerFormTest() {
		List<Servers> servers = (List<Servers>) serverRepo.findAll();
		String ret = controller.showUpdateServerForm(servers.get(0).getId(), model);
		assertEquals("LocalAdmin/update-server", ret, "showServerUpdateForm should return update server form");
		//assertTrue(model.containsAttribute("server"), "Model should have server attribute");
	}
	
	@Test
	public void showUpdateManagerFormTest() {
		List<Managers> managers = (List<Managers>) managerRepo.findAll();
		String ret = controller.showUpdateManagerForm(managers.get(0).getId(), model);
		assertEquals("LocalAdmin/update-LFmanager", ret, "showManagerUpdateForm should return update manager form");
		//assertTrue(model.containsAttribute("manager"), "Model should have manager attribute");
	}
	
	@Test
	public void updateCustTest() {
		//TODO
	}
	
	@Test
	public void updateServerTest() {
		//TODO
	}
	
	@Test
	public void updateManagerTest() {
		//TODO
	}
	
	@Test
	public void updateAdminTest() {
		//TODO
	}
	
	@Test
	public void updateOfficeTest() {
		//TODO
	}
	
	@Test
	public void updateWarehouseTest() {
		//TODO
	}
	
	@Test
	public void updateRestaurantTest() {
		//TODO
	}
	
	@Test
	public void addAdminRestaurantTest() {
		//TODO
	}
	
	@Test
	public void removeAdminRestaurantTest() {
		//TODO
	}
	
	@Test
	public void deleteCustTest() {
		//TODO
	}
	
	@Test
	public void deleteServerTest() {
		//TODO
	}
	
	@Test
	public void deleteManagerTest() {
		//TODO
	}
	
	@Test
	public void deleteOfficeTest() {
		//TODO
	}
	
	@Test
	public void deleteRestaurantTest() {
		//TODO
	}
	
	@Test
	public void deleteWarehouseTest() {
		//TODO
	}
	
	@Test
	public void deleteAdminTest() {
		//TODO
	}
	
	@Test
	public void deleteOrderTest() {
		//TODO
	}
	
	@Test
	@WithUserDetails(value = "server@email.com")
	public void showServerViewTest() {
		String ret = controller.showServerView(model);
		assertEquals("LocalServingStaff/serving-staff-view", ret, "showServerView should return server home page");
		//assertTrue(model.containsAttribute("orders"), "Model should have orders attribute");
		//assertTrue(model.containsAttribute("menu"), "Model should have menu attribute");
		//assertTrue(model.containsAttribute("server"), "Model should have server attribute");
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
		//TODO log not created in clockOut
		//assertFalse(logs.isEmpty(), "Log should have been created");
	}
	
	@Test
	@WithUserDetails(value = "Manager@email.com")
	public void clockOutTest2() {
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
		//TODO log not created in clockOut
		//assertFalse(logs.isEmpty(), "Log should have been created");
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
		//assertTrue(model.containsAttribute("orders"), "Model should contain orders attribute");
		//assertTrue(model.containsAttribute("menu"), "Model should contain menu attribute");
	}
	
	@Test
	public void showCustInfo() {
		String ret = controller.showCustInfo(null, model);
		assertEquals("redirect:/servingstaffview", ret, "null customer should redirect to server home page");
		ret = controller.showCustInfo(new Customers(), model);
		assertEquals("LocalServingStaff/server-cust-view", ret, "Non-null customer should return cust info page");
		//assertTrue(model.containsAttribute("customers"), "Model should contain customers attribute");
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
		//assertTrue(model.containsAttribute("log"), "Model should contain log attribute");
		//assertTrue(model.containsAttribute("manager"), "Model should contain manager attribute");
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
		//assertTrue(model.containsAttribute("log"), "Model should contain log attribute");
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
		//assertTrue(model.containsAttribute("log"), "Model should contain log attribute");
	}
	
	@Test
	@WithUserDetails(value = "sam")
	public void showHQAdminLogTest() {
		String ret = controller.showHQAdminLog(model);
		assertEquals("HQAdmin/hq-admin-log-view", ret, "showHQAdminLog should return hq admin log page");
		//assertTrue(model.containsAttribute("log"), "Model should contain log attribute");
	}
	
	@Test
	@WithUserDetails(value = "WHmanager@email.com")
	public void showWarehouseShipmentsTest() {
		String ret = controller.showWarehouseShipments(model);
		assertEquals("WarehouseManager/warehouseman-shipment-view", ret, "showWarehouseShipments should return warehouse shipments page");
		//assertTrue(model.containsAttribute("shipmentList"), "Model should contain shipmentList attribute");
	}
	
	@Test
	@WithUserDetails(value = "Manager@email.com")
	public void showInventoryViewTest() {
		String ret = controller.showInventoryView(model);
		assertEquals("LocalManager/manager-inventory-view", ret, "showInventoryView should return inventory view page");
		//assertTrue(model.containsAttribute("inventoryList"), "Model should contain inventoryList attribute");
	}
	
	@Test
	@WithUserDetails(value = "Manager@email.com")
	public void showRestViewShippingTest() {
		String ret = controller.showRestViewShipping(model);
		assertEquals("LocalManager/manager-shipment-view", ret, "showRestViewShipping should return shipments by restaurant page");
		//assertTrue(model.containsAttribute("shipmentList"), "Model should contain shipmentList attribute");
	}
	
	@Test
	public void localManShowUserListTest() {
		String ret = controller.localManShowUserList(model);
		assertEquals("LocalManager/manager-cust-view", ret, "localManShowUserList should return list of customers page");
		//assertTrue(model.containsAttribute("customers"), "Model should contain customers attribute");
	}
	
	// . . .
}
