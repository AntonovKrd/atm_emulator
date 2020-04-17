package krd.antonov;

import krd.antonov.exceptions.BanknoteException;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TestBanknoteStorage {
    private static final Logger log = Logger.getLogger(TestBanknoteStorage.class);
    private static BanknoteStorage banknoteStorage;

    @BeforeEach
    public void init() {
        try {
            banknoteStorage = new BanknoteStorage(5, 5, 5, 5, 5, 5, 5);
        } catch (BanknoteException e) {
            log.error("Error create BanknoteStorage", e);
        }
    }

    @Test
    public void checkCorrectInitBanknoteStorage() {
        assertThrows(BanknoteException.class, () -> {
            BanknoteStorage banknoteStorage = new BanknoteStorage(-1, 5, 5, 5, 5, 5, 5);
        });
    }

    @Test
    public void getDollars() {
        HashMap<BanknotesDenomination, Integer> dollars = new HashMap<>();
        dollars.put(BanknotesDenomination.ONE, 2);
        assertEquals(dollars, banknoteStorage.getDollars(BanknotesDenomination.ONE, 2));
        dollars.put(BanknotesDenomination.ONE, 1);
        assertEquals(dollars, banknoteStorage.getDollars(BanknotesDenomination.ONE, 1));
        dollars.put(BanknotesDenomination.ONE, 5);
        assertNotEquals(dollars, banknoteStorage.getDollars(BanknotesDenomination.ONE, 5));
        assertNotEquals(dollars, banknoteStorage.getDollars(BanknotesDenomination.EMPTY, 10));
    }

    @Test
    public void getSumDollars() {
        long sumDollars = 5 + (2 * 5) + (5 * 5) + (10 * 5) + (20 * 5) + (50 * 5) + (100 * 5);
        assertEquals(sumDollars, banknoteStorage.getSumDollars());
    }

    @Test
    public void insertDollars() {
        HashMap<BanknotesDenomination, Integer> dollars = new HashMap<>();
        dollars.put(BanknotesDenomination.FIFTY, 7);
        banknoteStorage.insertDollars(BanknotesDenomination.FIFTY, 2);
        banknoteStorage.insertDollars(BanknotesDenomination.EMPTY, 10);
        assertEquals(dollars, banknoteStorage.getDollars(BanknotesDenomination.FIFTY, 7));
    }
}
