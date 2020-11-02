package aplication;

import model.dao.DaoFactory;

public class Program {
    public static void main(String[] args) {
        var sellerDao = DaoFactory.createSellerDao();
        System.out.println(sellerDao.findById(3));
    }
}
