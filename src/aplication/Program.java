package aplication;

import model.entities.Departament;
import model.entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args) {
        var obj = new Departament(1, "Books");
        var objDep = new Seller(1, "Bob", "bob@gmail.com", new Date(), 3000.00, obj);

        System.out.println(obj);
        System.out.println(objDep);
    }
}
