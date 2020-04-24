package krd.antonov;

import krd.antonov.exceptions.BanknoteException;
import krd.antonov.utility.Utility;

import java.util.Scanner;

public class View {
    public static void main(String[] args) throws BanknoteException {
        BanknoteStorage storage = new BanknoteStorage(50, 50, 50, 50, 50, 50, 50);
        System.out.println("Virtual dollar storage is open!");
        printOperations();
        launchWorkWithStorage(storage);
    }

    public static void launchWorkWithStorage(BanknoteStorage storage) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("input command : ");
            String command = in.nextLine();
            commandProcessing(command, storage);
        }
    }

    public static void commandProcessing(String command, BanknoteStorage storage) {
        String prefix = command.trim().substring(0, command.indexOf(" "));
        switch (prefix) {
            case ("getAll"):
                System.out.println(Utility.convertMapDollarsToString(storage.getAllDollars()));
        }
    }

    public static void printOperations() {
        System.out.println("Available operations:");
        System.out.println("'getAll' - get all the money");
        System.out.println("'getSum' - print sum money in storage");
        System.out.println("'insert %denomination% - %number of bills%' - insert money in storage");
        System.out.println("'getDollars %denomination% - %number of bills%' - issuing money in equal denominations");
        System.out.println("'getDollars %sum%' - issuing money with the minimum available number of bills");
        System.out.println("Denominations : '1','2','5','10','20','50','100'");
        System.out.println("'list' - print available operations");
    }


}
