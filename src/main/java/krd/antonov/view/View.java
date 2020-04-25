package krd.antonov.view;

import krd.antonov.storage.BanknoteStorage;
import krd.antonov.storage.exceptions.BanknoteException;

public class View {

    public static void main(String[] args) throws BanknoteException {
        BanknoteStorage storage = new BanknoteStorage(50, 50, 50, 50, 50, 50, 50);
        CommandProcessor processor = new CommandProcessor(storage);
        processor.launchWorkWithStorage();
    }




}
