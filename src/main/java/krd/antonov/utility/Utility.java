package krd.antonov.utility;

import krd.antonov.BanknotesDenomination;

import java.util.Map;
import java.util.stream.Collectors;

public class Utility {

    public static String convertMapDollarsToString(Map<BanknotesDenomination, Integer> map) {
        return map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
    }

}
