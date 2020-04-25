package krd.antonov.utility;

import krd.antonov.storage.BanknotesDenomination;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Utility {

    public static String convertMapDollarsToString(Map<BanknotesDenomination, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(BanknotesDenomination::getValue).reversed()))
                .map(entry -> entry.getKey().getValue() + " dollar(s)  - " + entry.getValue() + " bill(s)")
                .collect(Collectors.joining(", "));
    }

    public static List<Integer> getNumbersFromString(String resource) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(resource);
        List<Integer> numbers = new ArrayList<>();
        while (matcher.find()) numbers.add(Integer.parseInt(matcher.group()));
        return numbers;
    }

}
