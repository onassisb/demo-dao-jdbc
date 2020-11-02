package aplication;

import model.dao.DaoFactory;
import model.entities.Seller;

public class Program {
    public static void main(String[] args) {
        System.out.println("=== TEST 1: Seller findById ===");
        var sellerDao = DaoFactory.createSellerDao();
        System.out.println(sellerDao.findById(3));

        System.out.println("=== TEST 2: Seller for Department findById ===");
        var sellers = sellerDao.findByDepartment(1);

        for (Seller seller : sellers) System.out.println(seller);
    }
}
