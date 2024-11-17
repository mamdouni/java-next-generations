package org.example.java.tutorial.java21;

import lombok.extern.slf4j.Slf4j;

import static java.lang.StringTemplate.RAW;
import static java.util.FormatProcessor.FMT;

@Slf4j
public class StringTemplatesExample {

    public static void main(String[] args) {

        /*
        String templates are introduced to the Java programming 21 ecosystem with the following goals in mind:
        - simplify the process of expressing Strings with values that can be compiled at run time
        - enhanced readability of String compositions, overcome the verbosity associated with StringBuilder and StringBuffer classes
        - overcome the security issues of the String interpolation techniques that other programming languages allow, trading off a small amount of inconvenience
        - allow Java libraries to define custom formatting syntax of the resulting String literal
        This is a preview feature in Java 21; therefore, we’d have to enable preview mode.

         source: https://www.baeldung.com/java-21-string-templates
         */

        String name = "Alice";
        String greeting = STR."Hello, \{name}!";
        log.info(greeting); // "Hello, Alice!"

        log.info(interpolationUsingSTRProcessor("cold", "10", "Celsius"));

        log.info(interpolationOfJSONBlock("hot", "30", "Fahrenheit"));

        log.info(interpolationWithExpressions());

        // Another Java-provided processor is the FMT Template Processor. It adds the support of understanding the formatters that are provided to the processor,
        // which format the data according to the formatting style provided.
        // check the import static java.util.FormatProcessor.FMT
        log.info(interpolationOfJSONBlockWithFMT("windy", 25.5789f, "Celsius"));

        // how it works
        // First, an instance of a template processor, StringTemplate.Processor<R, E>, is obtained by evaluating the left of the dot. In our case, it’s the STR template processor.
        //Next, we obtain an instance of a template, StringTemplate, by evaluating to the right of the dot:
        StringTemplate template = RAW."Hello, |\{name}| - Welcome, |\{name}|";
        log.info(template.toString());

        // RAW is the standard template processor that produces an unprocessed StringTemplate type object.
        //Finally, we pass the StringTemplate str instance to the process() method of the processor (which in our case is STR):
        log.info(STR.process(template));
    }

    static String interpolationUsingSTRProcessor(String feelsLike, String temperature, String unit) {
        return STR
                . "Today's weather is \{ feelsLike }, with a temperature of \{ temperature } degrees \{ unit }" ;
    }

    static String interpolationOfJSONBlock(String feelsLike, String temperature, String unit) {
        return STR
                . """
                {
                  "feelsLike": "\{ feelsLike }",
                  "temperature": "\{ temperature }",
                  "unit": "\{ unit }"
                }
                """ ;
    }

    static String interpolationWithExpressions() {

         class DummyValues {
             static String getFeelsLike() {
                 return "warm";
             }
            static String getUnit() {
                return "Celsius";
            }
            static String getTemperature() {
                return "20";
            }
        }

        return STR
                . "Today's weather is \{ DummyValues.getFeelsLike() }, with a temperature of \{ DummyValues.getTemperature() } degrees \{ DummyValues.getUnit() }";
    }

    static String interpolationOfJSONBlockWithFMT(String feelsLike, float temperature, String unit) {
        return FMT
                . """
      {
        "feelsLike": "%1s\{ feelsLike }",
        "temperature": "%2.2f\{ temperature }",
        "unit": "%.3s\{ unit }"
      }
      """ ;
    }
}
