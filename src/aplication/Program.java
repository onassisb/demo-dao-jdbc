package aplication;

import model.dao.DaoFactory;
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
        //sellerDao.insert(seller);
        System.out.println(seller);
    }
}
