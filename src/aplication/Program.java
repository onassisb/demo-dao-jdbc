package aplication;

import model.dao.DaoFactory;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args) {
        System.out.println("=== TEST 1: Seller findById ===");
        var sellerDao = DaoFactory.createSellerDao();
        var seller0 = sellerDao.findById(3);
        System.out.println(seller0);

        System.out.println("=== TEST 2: Seller for Department findById ===");
        var sellers = sellerDao.findByDepartment(1);
        for (Seller seller : sellers) System.out.println(seller);

        System.out.println("=== TEST 3: Seller findAll ===");
        sellers = sellerDao.findAll();
        for (Seller seller : sellers) System.out.println(seller);

        System.out.println("=== TEST 4: Seller Insert ===");
        var seller = new Seller(null, "Jonh Smith", "jsmith@gmail.com", new Date(), 4000.00, seller0.getDepartament());
        sellerDao.insert(seller);
        sellers = sellerDao.findAll();
        for (Seller obj : sellers) System.out.println(obj);

        System.out.println("=== TEST 5: Seller Update ===");
        seller.setName("Jonh Smith Atualizado");
        seller.setBaseSalary(5000.00);
        sellerDao.update(seller);
        sellers = sellerDao.findAll();
        for (Seller obj : sellers) System.out.println(obj);

        System.out.println("=== TEST 6: Seller Delete ===");
        sellerDao.delete(seller.getId());
        sellers = sellerDao.findAll();
        for (Seller obj : sellers) System.out.println(obj);

        System.out.println("=== TEST 7: Department FindById ===");
        var departmentDao = DaoFactory.createDepartmentDao();
        System.out.println(departmentDao.findById(2));

        System.out.println("=== TEST 8: Department FindAll ===");
        var listaDepartments = departmentDao.findAll();
        for (Department department: listaDepartments) System.out.println(department);

        System.out.println("=== TEST 9: Department Insert ===");
        var department = new Department(null, "D1");
        departmentDao.insert(department);
        listaDepartments = departmentDao.findAll();
        for (Department obj: listaDepartments) System.out.println(obj);

        System.out.println("=== TEST 10: Department update ===");
        department.setName("TESTE");
        departmentDao.update(department);
        listaDepartments = departmentDao.findAll();
        for (Department obj: listaDepartments) System.out.println(obj);

        System.out.println("=== TEST 11: Department Delete ===");
        departmentDao.delete(department.getId());
        listaDepartments = departmentDao.findAll();
        for (Department obj: listaDepartments) System.out.println(obj);


    }
}
