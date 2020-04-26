package krd.antonov;

import krd.antonov.storage.BanknoteStorage;
import krd.antonov.storage.BanknotesDenomination;
import krd.antonov.storage.exceptions.BanknoteException;
import krd.antonov.utility.Utility;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestUtility {

    @Test
    void testConvertMapDollarsToString() throws BanknoteException {
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
        numbers.clear();
        assertEquals(numbers, Utility.getNumbersFromString("getDollars   100 -     " + Integer.MAX_VALUE + 1));
        assertEquals(numbers, Utility.getNumbersFromString("getDollars   " + Integer.MAX_VALUE + 1 + " -     7642"));
    }

    @Test
    void testIsDenominationCorrect() {
        assertTrue(Utility.isDenominationCorrect(0));
        assertTrue(Utility.isDenominationCorrect(1));
        assertTrue(Utility.isDenominationCorrect(2));
        assertTrue(Utility.isDenominationCorrect(5));
        assertTrue(Utility.isDenominationCorrect(10));
        assertTrue(Utility.isDenominationCorrect(20));
        assertTrue(Utility.isDenominationCorrect(50));
        assertTrue(Utility.isDenominationCorrect(100));
        assertFalse(Utility.isDenominationCorrect(99));
        assertFalse(Utility.isDenominationCorrect(3));
        assertFalse(Utility.isDenominationCorrect(6));
        assertFalse(Utility.isDenominationCorrect(9));
        assertFalse(Utility.isDenominationCorrect(101));
    }

    @Test
    void testDenominationByValue() {
        assertEquals(BanknotesDenomination.ONE, Utility.getDenominationByValue(1));
        assertEquals(BanknotesDenomination.TWO, Utility.getDenominationByValue(2));
        assertEquals(BanknotesDenomination.FIVE, Utility.getDenominationByValue(5));
        assertEquals(BanknotesDenomination.TEN, Utility.getDenominationByValue(10));
        assertEquals(BanknotesDenomination.TWENTY, Utility.getDenominationByValue(20));
        assertEquals(BanknotesDenomination.FIFTY, Utility.getDenominationByValue(50));
        assertEquals(BanknotesDenomination.ONE_HUNDRED, Utility.getDenominationByValue(100));
        assertEquals(BanknotesDenomination.EMPTY, Utility.getDenominationByValue(0));
    }

    @Test
    void TestGetCommandKey() {
        assertEquals("getSum", Utility.getCommandKey("getSum - print sum money in storage"));
        assertEquals("insert", Utility.getCommandKey("insert %denomination% - %number of bills%' - insert money in storage"));
        assertEquals("getDollars", Utility.getCommandKey("getDollars %denomination% - %number of bills% - issuing money in equal denominations"));
        assertEquals("list", Utility.getCommandKey("list - print available operations"));
        assertEquals("exit", Utility.getCommandKey("exit - close program"));
    }
}
