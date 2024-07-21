package org.example.java.tutorial.java12;

import java.text.CompactNumberFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsageCompactNumberFormat {

    public static void main(String[] args) {

        // Compact Number Format
        // Compact Number Format is a new feature in Java 12 that is used to format a number in a compact
        NumberFormat numberFormat = NumberFormat.getCompactNumberInstance();
        numberFormat.setMaximumFractionDigits(3);

        printTheCompactNumberFormat(numberFormat);

        // using a Locale
        numberFormat = NumberFormat.getCompactNumberInstance(Locale.GERMAN, NumberFormat.Style.SHORT);
        numberFormat.setMaximumFractionDigits(3);

        printTheCompactNumberFormat(numberFormat);

        // and you can even create your own compact number format
        final String[] compactPatterns
                = {"", "", "", "0k", "00k", "000k", "0m", "00m", "000m",
                "0b", "00b", "000b", "0t", "00t", "000t"};

        final DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.GERMANY);

        final CompactNumberFormat customCompactNumberFormat
                = new CompactNumberFormat( decimalFormat.toPattern(),
                decimalFormat.getDecimalFormatSymbols(),  compactPatterns);

        printTheCompactNumberFormat(customCompactNumberFormat);
    }

    private static void printTheCompactNumberFormat(NumberFormat numberFormat) {
        log.info(
                numberFormat.format(1000) + "\n" +
                numberFormat.format(1567) + "\n" +
                numberFormat.format(1_000_000) + "\n" +
                numberFormat.format(1_000_000_000) + "\n"
        );
    }

}
