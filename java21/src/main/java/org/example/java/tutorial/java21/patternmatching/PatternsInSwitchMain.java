package org.example.java.tutorial.java21.patternmatching;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

@Slf4j
public class PatternsInSwitchMain {

    public static void main(String[] args) {

        Stream<OnlineCourse> courses = getCourses();

        int totalDuration = courses.mapToInt(PatternsInSwitchMain::extractDuration).sum();
        log.info("Total duration of all courses: {}", totalDuration);

        courses = getCourses();
        totalDuration = courses.mapToInt(PatternsInSwitchMain::extractDurationPatterns).sum();
        log.info("Total duration of all courses: {}", totalDuration);
    }

    public static int extractDuration(OnlineCourse course) {

        if (course instanceof PluralSightCourse pluralSightCourse) {
            return pluralSightCourse.getCourseMinutes();
        } else if (course instanceof YoutubeCourse youtubeCourse) {
            return youtubeCourse.getCourseSeconds() / 60;
        }
        throw new IllegalArgumentException("Unknown course type");
    }

    public static int extractDurationPatterns(OnlineCourse course) {

        return switch (course) {
            case PluralSightCourse pc -> pc.getCourseMinutes();
            case YoutubeCourse yc -> yc.getCourseSeconds() / 60;
            case null -> 0;
            // before, the switch handles only primitives types so the null was not necessary
            // now the switch can handle null values, so we need to add a null case otherwise it will throw a NullPointerException
            default -> throw new IllegalArgumentException("Unknown course type");
            // the default will not be mandatory if the OnlineCourse interface is sealed
        };
    }

    static Stream<OnlineCourse> getCourses() {
        return Stream.of(
                new PluralSightCourse("Java Fundamentals", 185),
                new YoutubeCourse("Java Tricks", 1200)
        );
    }
}
