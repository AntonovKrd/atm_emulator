package krd.antonov;

import krd.antonov.exceptions.BanknoteException;

public class View {
    public static void main(String[] args) throws BanknoteException {
        BanknoteStorage storage = new BanknoteStorage(50, 50, 50, 50, 50, 50, 50);
        System.out.println("Virtual dollar storage is open!");
        printOperations();
    }


    public static void printOperations() {
        System.out.println("Available operations:");
        System.out.println("'getAll' - get all the money");
        System.out.println("'getSum' - print sum money in storage");
        System.out.println("'insert %denomination% - %number of bills%' - insert money in storage");
        System.out.println("'getDollars %denomination% - %number of bills%' - issuing money in equal denominations");
        System.out.println("'getDollars %sum%' - issuing money with the minimum available number of bills");
        System.out.println("Denominations : '1','2','5','10','20','50','100'");
    }

}
