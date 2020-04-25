package krd.antonov.view;

import krd.antonov.storage.BanknoteStorage;
import krd.antonov.storage.BanknotesDenomination;
import krd.antonov.storage.exceptions.BanknoteException;
import krd.antonov.utility.Utility;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandProcessor {

    private static final Logger log = Logger.getLogger(CommandProcessor.class);
    private BanknoteStorage storage;

    public CommandProcessor(BanknoteStorage storage) {
        this.storage = storage;
        System.out.println("Virtual dollar storage is open!");
        System.out.println("Current balance : " + Utility.convertMapDollarsToString(storage.getAllDollars()));
    }

    public void launchWorkWithStorage() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("input command : ");
            String command = in.nextLine();
            commandProcessing(command);
        }
    }

    public void commandProcessing(String command) {
        String prefix = command.trim().substring(0, command.indexOf(" "));
        switch (prefix) {
            case ("balance"):
                System.out.println("Current balance : " + Utility.convertMapDollarsToString(storage.getAllDollars()));
                break;
            case ("getSum"):
                System.out.println(storage.getSumDollars() + " dollars");
                break;
            case ("insert"):
                List<Integer> params = Utility.getNumbersFromString(command);
                try {
                    if (params.size() != 2)
                        System.out.println("Error reading parameters. Check the command correctly.");
                    else {
                        storage.insertDollars(Arrays.stream(BanknotesDenomination.values())
                                .filter(denomination -> denomination.getValue() == params.get(0))
                                .findFirst().get(), params.get(1));
                        System.out.println("Success insert dollars");
                    }
                } catch (BanknoteException e) {
                    System.out.println(e.getMessage());
                    log.error(e);
                }
        }
    }

    public void printOperations() {
        System.out.println("Available operations:");
        System.out.println("'balance' - print current balance");
        System.out.println("'getSum' - print sum money in storage");
        System.out.println("'insert %denomination% - %number of bills%' - insert money in storage");
        System.out.println("'getDollars %denomination% - %number of bills%' - issuing money in equal denominations");
        System.out.println("'getDollars %sum%' - issuing money with the minimum available number of bills");
        System.out.println("Denominations : 1, 2, 5, 10, 20, 50, 100");
        System.out.println("'list' - print available operations");
    }

}
