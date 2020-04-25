package krd.antonov;

import krd.antonov.storage.BanknoteStorage;
import krd.antonov.storage.BanknotesDenomination;
import krd.antonov.storage.exceptions.BanknoteException;
import krd.antonov.utility.Utility;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtility {

    @Test
    public void testConvertMapDollarsToString() throws BanknoteException {
        BanknoteStorage banknoteStorage = new BanknoteStorage(5, 5, 5, 5, 5, 5, 5);
        String mapString = "50 dollar(s)  - 4 bill(s)";
        assertEquals(mapString, Utility.convertMapDollarsToString(banknoteStorage.getDollars(BanknotesDenomination.FIFTY, 4)));
        mapString = "50 dollar(s)  - 1 bill(s), 20 dollar(s)  - 2 bill(s), 5 dollar(s)  - 1 bill(s), 2 dollar(s)  - 2 bill(s)";
        assertEquals(mapString, Utility.convertMapDollarsToString(banknoteStorage.getMinBillsDollars(99)));
    }

    @Test
    void testGetNumbersFromString() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(54);
        numbers.add(4);
        assertEquals(numbers, Utility.getNumbersFromString("insert 54 - 4 of bills"));
        numbers.clear();
        numbers.add(100);
        numbers.add(7642);
        assertEquals(numbers, Utility.getNumbersFromString("getDollars   100 -     7642"));
    }
}
