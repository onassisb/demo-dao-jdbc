package aplication;

import model.dao.DaoFactory;

public class Program {
    public static void main(String[] args) {
        System.out.println("=== TEST 1: Seller findById ===");
        var sellerDao = DaoFactory.createSellerDao();
        System.out.println(sellerDao.findById(3));
    }
}
