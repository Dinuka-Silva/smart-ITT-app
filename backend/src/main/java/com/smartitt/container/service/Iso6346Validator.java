package com.smartitt.container.service;

import java.util.Map;

public class Iso6346Validator {

    private static final Map<Character, Integer> CHAR_VALUES = Map.ofEntries(
            Map.entry('A', 10), Map.entry('B', 12), Map.entry('C', 13), Map.entry('D', 14),
            Map.entry('E', 15), Map.entry('F', 16), Map.entry('G', 17), Map.entry('H', 18),
            Map.entry('I', 19), Map.entry('J', 20), Map.entry('K', 21), Map.entry('L', 23),
            Map.entry('M', 24), Map.entry('N', 25), Map.entry('O', 26), Map.entry('P', 27),
            Map.entry('Q', 28), Map.entry('R', 29), Map.entry('S', 30), Map.entry('T', 31),
            Map.entry('U', 32), Map.entry('V', 34), Map.entry('W', 35), Map.entry('X', 36),
            Map.entry('Y', 37), Map.entry('Z', 38)
    );

    public static String normalize(String containerNumber) {
        if (containerNumber == null) return "";
        return containerNumber.replaceAll("[\\s-]", "").toUpperCase().trim();
    }

    public static boolean isValidFormat(String containerNumber) {
        String clean = normalize(containerNumber);
        return clean.matches("^[A-Z]{4}\\d{7}$");
    }

    public static int calculateCheckDigit(String containerNumber) {
        String clean = normalize(containerNumber);
        if (clean.length() < 10) return -1;

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            char ch = clean.charAt(i);
            int value;
            if (Character.isLetter(ch)) {
                value = CHAR_VALUES.getOrDefault(ch, 0);
            } else if (Character.isDigit(ch)) {
                value = Character.getNumericValue(ch);
            } else {
                return -1;
            }
            int weight = (int) Math.pow(2, i);
            sum += value * weight;
        }

        int remainder = sum % 11;
        return remainder % 10;
    }

    public static boolean isValidCheckDigit(String containerNumber) {
        if (!isValidFormat(containerNumber)) return false;
        String clean = normalize(containerNumber);
        int expected = calculateCheckDigit(clean);
        int actual = Character.getNumericValue(clean.charAt(10));
        return expected == actual;
    }
}
