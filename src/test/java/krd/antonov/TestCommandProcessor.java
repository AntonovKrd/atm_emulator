package krd.antonov;

import krd.antonov.storage.BanknoteStorage;
import krd.antonov.storage.exceptions.BanknoteException;
import krd.antonov.view.CommandProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCommandProcessor {
    private static BanknoteStorage storage;

    @BeforeEach
    void init() throws BanknoteException {
        storage = new BanknoteStorage(10, 10, 10, 10, 10, 10, 10);
    }

    @Test
    void testCreateCommandProcessor() {
        assertEquals(new CommandProcessor(storage), new CommandProcessor(storage));
    }
}