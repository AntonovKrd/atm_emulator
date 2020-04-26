package krd.antonov.utility;

import krd.antonov.storage.BanknotesDenomination;

import java.util.*;
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

    /**
     * @param resource - string with numbers
     * @return List<Integer> params from string. If any param is greater than Integer.Max return Empty List
     */
    public static List<Integer> getNumbersFromString(String resource) {
        Pattern pattern = Pattern.compile("[-]?[0-9]+");
        Matcher matcher = pattern.matcher(resource);
        List<Integer> numbers = new ArrayList<>();
        while (matcher.find())
            try {
                numbers.add(Integer.parseInt(matcher.group()));
            } catch (NumberFormatException exception) {
                return new ArrayList<>();
            }
        return numbers;
    }

    public static boolean isDenominationCorrect(int denomination) {
        return Arrays.stream(BanknotesDenomination.values()).anyMatch(banknoteDenomination -> banknoteDenomination.getValue() == denomination);
    }

    public static BanknotesDenomination getDenominationByValue(int value) {
        return Arrays.stream(BanknotesDenomination.values()).filter(banknoteDenomination -> banknoteDenomination.getValue() == value).findFirst().get();
    }

    public static String getCommandKey(String command) {
        String[] matches = command.split(" ");
        return matches[0];
    }
}
