package krd.antonov;

import krd.antonov.exceptions.BanknoteException;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BanknoteStorage {
    private final static Logger log = Logger.getLogger(BanknoteStorage.class);
    private final Map<BanknotesDenomination, Integer> dollarsMap;
    private final Integer[] denominations = {1, 2, 5, 10, 20, 50, 100};

    public BanknoteStorage(int oneDollarsCount, int twoDollarsCount, int fiveDollarsCount, int tenDollarsCount,
                           int twentyDollarsCount, int fiftyDollarsCount, int oneHundredDollarsCount) throws BanknoteException {
        this.dollarsMap = new HashMap<>();
        this.dollarsMap.put(BanknotesDenomination.ONE, oneDollarsCount);
        this.dollarsMap.put(BanknotesDenomination.TWO, twoDollarsCount);
        this.dollarsMap.put(BanknotesDenomination.FIVE, fiveDollarsCount);
        this.dollarsMap.put(BanknotesDenomination.TEN, tenDollarsCount);
        this.dollarsMap.put(BanknotesDenomination.TWENTY, twentyDollarsCount);
        this.dollarsMap.put(BanknotesDenomination.FIFTY, fiftyDollarsCount);
        this.dollarsMap.put(BanknotesDenomination.ONE_HUNDRED, oneHundredDollarsCount);
        checkDollarsMap();
        log.info("BanknoteStorage is created");
    }

    private void checkDollarsMap() throws BanknoteException {
        if (dollarsMap.entrySet().stream().anyMatch(e -> e.getValue() < 0)) {
            throw new BanknoteException("Incorrect counts banknotes");
        }
    }

    public HashMap<BanknotesDenomination, Integer> getDollars(BanknotesDenomination denomination, int count) {
        HashMap<BanknotesDenomination, Integer> dollars = new HashMap<>();
        if (denomination.equals(BanknotesDenomination.EMPTY) || dollarsMap.get(denomination) == 0
                || dollarsMap.get(denomination) - count < 0) {
            dollars.put(BanknotesDenomination.EMPTY, 0);
            log.error("Not enough bills in the banknote storage");
        } else {
            dollarsMap.put(denomination, dollarsMap.get(denomination) - count);
            dollars.put(denomination, count);
            log.info(count + " banknote's " + denomination.name() + " dollar('s) given out");
        }
        return dollars;
    }

    public void insertDollars(BanknotesDenomination denomination, int count) {
        if (denomination != BanknotesDenomination.EMPTY) {
            dollarsMap.put(denomination, dollarsMap.get(denomination) + count);
            log.info(count + " banknote's " + denomination.name() + " dollar('s)  inserted");
        } else log.error("bill collector is empty");
    }

    public long getSumDollars() {
        AtomicInteger multiplier = new AtomicInteger();
        log.info("the amount of the banknote storage is displayed");
        return dollarsMap.values().stream().mapToLong(integer -> integer * denominations[multiplier.getAndIncrement()]).sum();
    }
}
