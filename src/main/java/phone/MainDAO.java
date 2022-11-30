package phone;

public class MainDAO {

	public static void main(String[] args) {

		PhoneDAO phoneDAO = new PhoneDAO();

		Phone samsung = new Phone(1, "Samsung", "S21", "111111");

		System.out.println(phoneDAO.create(samsung));
		System.out.println(phoneDAO.create(new Phone(2, "Iphone", "11", "222222")));
		System.out.println(phoneDAO.create(new Phone(3, "Xiaomi", "Mi 9 SE", "333333")));
		System.out.println(phoneDAO.create(new Phone(4, "Xiaomi", "Mi A1", "444444")));
		System.out.println(phoneDAO.findAll());
		System.out.println(phoneDAO.findEntityById(1));
		System.out.println(phoneDAO.delete(1));
		System.out.println(phoneDAO.update(samsung));

	}
}
