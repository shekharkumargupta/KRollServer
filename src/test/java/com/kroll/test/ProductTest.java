package com.kroll.test;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;

import com.kroll.dao.exceptions.DBException;
import com.kroll.domain.Company;
import com.kroll.domain.Product;
import com.kroll.domain.ProductImage;
import com.kroll.util.HibernateUtil;

public class ProductTest {

	// private static final CompanyDAO companyDAO = new CompanyDAOImpl();
	// private static final ProductDAO productDAO = new ProductDAOImpl();
	// private static final LoginDAO loginDAO = new LoginDAOImpl();

	public static Company createCompany(String name, String address, String contactName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Company company = new Company();
		company.setName(name);
		company.setAddress(address);
		company.setContactName(contactName);
		session.save(company);
		System.out.println(company);
		session.getTransaction().commit();
		session.close();
		return company;
	}

	public static void createProduct(Company company) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Product indian = new Product("India", false, 0, true, true, company);
		ProductImage indianImage = new ProductImage(indian.getName());
		session.save(indianImage);
		indian.addImage(indianImage);
		
		Product chinese = new Product("Chinese", false, 0, true, true, company);
		ProductImage chineseImage = new ProductImage(chinese.getName());
		session.save(chineseImage);
		chinese.addImage(chineseImage);

		Product paratha = new Product("Paratha", false, 20, false, false, company);
		ProductImage parathaImage = new ProductImage(paratha.getName());
		session.save(parathaImage);
		paratha.addImage(parathaImage);
		
		Product thali = new Product("Thali", false, 20, false, false, company);
		ProductImage thaliImage = new ProductImage(thali.getName());
		session.save(thaliImage);
		thali.addImage(thaliImage);
		
		indian.addItem(paratha);
		indian.addItem(thali);
		session.save(paratha);
		session.save(thali);

		Product noodles = new Product("Noodles", false, 20, false, false, company);
		ProductImage noodlesImage = new ProductImage(noodles.getName());
		session.save(noodlesImage);
		noodles.addImage(noodlesImage);
		
		Product seafoods = new Product("Sea Food", false, 20, false, false, company);
		ProductImage seafoodsImage = new ProductImage(seafoods.getName());
		session.save(seafoodsImage);
		seafoods.addImage(seafoodsImage);
		
		chinese.addItem(noodles);
		chinese.addItem(seafoods);
		session.save(noodles);
		session.save(seafoods);

		Product aaluParatha = new Product("Aalu Paratha", true, 20, false, false, company);
		ProductImage aaluParathaImage = new ProductImage(aaluParatha.getName());
		session.save(aaluParathaImage);
		aaluParatha.addImage(aaluParathaImage);
		
		Product paneerParatha = new Product("Paneer Paratha", true, 20, false, false, company);
		ProductImage paneerParahtaImage = new ProductImage(paratha.getName()); 
		session.save(paneerParahtaImage);
		paneerParatha.addImage(paneerParahtaImage);
		
		Product dailyThali = new Product("Daily Thali", true, 20, false, false, company);
		ProductImage dailyThaliImage = new ProductImage(dailyThali.getName());
		session.save(dailyThaliImage);
		dailyThali.addImage(dailyThaliImage);
		
		Product maharajaThali = new Product("Maharaja Thali", true, 20, false, false, company);
		ProductImage maharajaThaliImage = new ProductImage(maharajaThali.getName());
		session.save(maharajaThaliImage);
		maharajaThali.addImage(maharajaThaliImage);
		
		paratha.addItem(aaluParatha);
		paratha.addItem(paneerParatha);
		thali.addItem(dailyThali);
		thali.addItem(maharajaThali);
		session.save(aaluParatha);
		session.save(paneerParatha);
		session.save(dailyThali);
		session.save(maharajaThali);

		Product vegNoodles = new Product("Veg Noodles", true, 20, false, false, company);
		ProductImage vegNoodlesImage = new ProductImage(vegNoodles.getName());
		session.save(vegNoodlesImage);
		vegNoodles.addImage(vegNoodlesImage);
		
		Product nonVegNoodles = new Product("Non Veg Noodles", true, 20, false, false, company);
		ProductImage nonVegNoodlesImage = new ProductImage(nonVegNoodles.getName());
		session.save(nonVegNoodlesImage);
		nonVegNoodles.addImage(nonVegNoodlesImage);
		
		Product fish = new Product("Fish", true, 20, false, false, company);
		ProductImage fishImage = new ProductImage(fish.getName());
		session.save(fishImage);
		fish.addImage(fishImage);
		
		Product wineFish = new Product("Wine Fish", true, 20, false, false, company);
		ProductImage wineFishImage = new ProductImage(wineFish.getName());
		session.save(wineFishImage);
		wineFish.addImage(wineFishImage);
		
		noodles.addItem(vegNoodles);
		noodles.addItem(nonVegNoodles);
		seafoods.addItem(fish);
		seafoods.addItem(wineFish);
		session.save(vegNoodles);
		session.save(nonVegNoodles);
		session.save(fish);
		session.save(wineFish);

		session.save(indian);
		session.save(chinese);
		session.getTransaction().commit();
		session.close();
	}

	public static void createKRollProduct(Company company) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Product roll = new Product("Roll", true, 20, false, true, company);
		ProductImage rollImage = new ProductImage(roll.getName());
		session.save(rollImage);
		roll.addImage(rollImage);
		roll.setDescription(
				"Roll is a street-food dish originating from Kolkata, India. In its original form, it is a skewer-roasted kebab wrapped in a paratha bread");
		
		Product omlett = new Product("Omlett", true, 30, false, true, company);
		ProductImage omlettImage = new ProductImage(omlett.getName());
		session.save(omlettImage);
		omlett.addImage(omlettImage);
		omlett.setDescription("In cuisine, an omelette or omelet is a dish made from beaten eggs quickly fried");
		
		Product paratha = new Product("Paratha", true, 35, false, true, company);
		ProductImage parathaImage = new ProductImage(paratha.getName());
		session.save(parathaImage);
		paratha.addImage(parathaImage);
		paratha.setDescription(
				"A paratha is a flatbread that originated in the Indian subcontinent. Paratha is an amalgamation of the words parat and atta.");
		
		Product thali = new Product("Thali", true, 60, false, true, company);
		ProductImage thaliImage = new ProductImage(thali.getName());
		session.save(thaliImage);
		thali.addImage(thaliImage);
		thali.setDescription(
				"meal made up of a selection of various dishes. It simply means a round platter used to serve food.");
		
		Product chickenChowmin = new Product("Chicken Chowmin", true, 45, false, true, company);
		ProductImage chickenChowminImage = new ProductImage(chickenChowmin.getName());
		session.save(chickenChowminImage);
		chickenChowmin.addImage(chickenChowminImage);
		chickenChowmin.setDescription(
				"Noodles are a staple food in many cultures made from unleavened dough which is stretched.");
		// Product(name, isItem, price, master, available, company) {

		session.save(roll);
		session.save(omlett);
		session.save(paratha);
		session.save(thali);
		session.save(chickenChowmin);

		Product veg = new Product("Veg", false, 0, true, true, company);
		ProductImage vegImage = new ProductImage(veg.getName());
		session.save(vegImage);
		veg.addImage(vegImage);
		
		Product nonVeg = new Product("Non Veg", false, 0, true, true, company);
		ProductImage nonVegImage = new ProductImage(nonVeg.getName());
		session.save(nonVegImage);
		nonVeg.addImage(nonVegImage);

		veg.addItem(paratha);
		veg.addItem(thali);

		nonVeg.addItem(roll);
		nonVeg.addItem(omlett);
		nonVeg.addItem(chickenChowmin);

		session.save(veg);
		session.save(nonVeg);
		
		rollImage.setProductId(roll.getId());
		omlettImage.setProductId(omlett.getId());
		parathaImage.setProductId(paratha.getId());
		thaliImage.setProductId(thali.getId());
		chickenChowminImage.setProductId(chickenChowmin.getId());
		vegImage.setProductId(veg.getId());
		nonVegImage.setProductId(nonVeg.getId());
		
		session.update(rollImage);
		session.update(omlettImage);
		session.update(parathaImage);
		session.update(thaliImage);
		session.update(chickenChowminImage);
		session.update(vegImage);
		session.update(nonVegImage);
		
		
		session.getTransaction().commit();
		session.close();
	}

	public static void findAllCompanies() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Query query = session.createQuery("Select c from Company c");
		@SuppressWarnings("unchecked")
		Collection<Company> companies = query.list();
		for (Company company : companies) {
			System.out.println(company);
			findAllMasterProducts(company.getId());
		}
		session.getTransaction().commit();
		session.close();
	}

	public static Collection<Product> findAllProducts() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Query query = session.createQuery("Select p from Product p");
		@SuppressWarnings("unchecked")
		Collection<Product> products = query.list();
		for (Product product : products) {
			if (!product.isItem()) {
				System.out.println(product.getName() + "\t" + product.isMaster());
			} else {
				System.out.println("\t>" + product.getName() + "\t" + product.isMaster());
			}
		}
		session.getTransaction().commit();
		session.close();
		return products;
	}

	public static Collection<Product> findAllMasterProducts(long companyId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		System.out.println("Company: " + companyId);

		Query query = session
				.createQuery("Select p from Product p where" + " p.item =? and p.master = ? and p.company.id = ?")
				.setParameter(0, false).setParameter(1, true).setParameter(2, companyId);

		@SuppressWarnings("unchecked")
		Collection<Product> products = query.list();
		for (Product product : products) {
			System.out.println("\t>" + product.getName() + "\t" + product.isMaster());
		}
		session.getTransaction().commit();
		session.close();
		return products;
	}

	public static void createCompanyAndProducts() throws DBException {
		Company company = createCompany("KRoll", "Malviya Nagar", "Pankaj Kumar");
		createKRollProduct(company);
		findAllMasterProducts(company.getId());
	}

	public static void main(String args[]) throws DBException {
		createCompanyAndProducts();
	}
}