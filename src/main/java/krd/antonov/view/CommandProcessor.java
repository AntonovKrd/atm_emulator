package krd.antonov.view;

import krd.antonov.storage.BanknoteStorage;
import krd.antonov.storage.exceptions.BanknoteException;
import krd.antonov.utility.Utility;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CommandProcessor {

    private static final Logger log = Logger.getLogger(CommandProcessor.class);
    private static final String errorInput = "Bad command. Check the correctness of the command and parameters.";
    private final BanknoteStorage storage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandProcessor processor = (CommandProcessor) o;
        return Objects.equals(storage, processor.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storage);
    }

    public CommandProcessor(BanknoteStorage storage) {
        this.storage = storage;
        System.out.println("Virtual dollar storage is open!");
        System.out.println("Current balance : " + Utility.convertMapDollarsToString(storage.getAllDollars()));
    }

    private String processingCommandInsert(String command) {
        List<Integer> params = Utility.getNumbersFromString(command);
        String result = errorInput;
        if (params.size() == 2 && Utility.isDenominationCorrect(params.get(0))) {
            try {
                storage.insertDollars(Utility.getDenominationByValue(params.get(0)), params.get(1));
                result = "insert success";
            } catch (BanknoteException e) {
                log.error(e);
                result = e.getMessage();
            }
        }
        return result;
    }

    private String processingCommandGetDollars(String command) {
        List<Integer> params = Utility.getNumbersFromString(command);
        String result = errorInput;
        if (params.size() == 2 && Utility.isDenominationCorrect(params.get(0))) {
            try {
                result = Utility.convertMapDollarsToString(storage.getDollars(Utility.getDenominationByValue(params.get(0)), params.get(1))) + " given out";
            } catch (BanknoteException e) {
                log.error(e);
                result = e.getMessage();
            }
        }
        if (params.size() == 1) {
            try {
                result = Utility.convertMapDollarsToString(storage.getMinBillsDollars(params.get(0))) + " given out";
            } catch (BanknoteException e) {
                log.error(e);
                result = e.getMessage();
            }
        }
        return result;
    }

    public void launchWorkWithStorage() {
        Scanner in = new Scanner(System.in);
        printOperations();
        while (true) {
            System.out.print("input command : ");
            String command = in.nextLine();
            commandProcessing(command);
        }
    }

    public void commandProcessing(String command) {
        String key = Utility.getCommandKey(command);
        switch (key) {
            case ("balance"):
                System.out.println("Current balance : " + Utility.convertMapDollarsToString(storage.getAllDollars()));
                break;
            case ("getSum"):
                System.out.println(storage.getSumDollars() + " dollars");
                break;
            case ("insert"):
                System.out.println(processingCommandInsert(command));
                break;
            case ("getDollars"):
                System.out.println(processingCommandGetDollars(command));
                break;
            case ("list"):
                printOperations();
                break;
            case ("exit"):
                System.exit(0);
                break;
            default:
                System.out.println(errorInput);
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
        System.out.println("Maximum number of bills in each cell - " + Integer.MAX_VALUE);
        System.out.println("'list' - print available operations");
        System.out.println("'exit' - close program");
    }


}
