package org.example.java.tutorial.java21.patternmatching;

import lombok.AllArgsConstructor;
import lombok.Getter;

interface OnlineCourse {
    String getName();
}

@AllArgsConstructor
@Getter
final class PluralSightCourse implements OnlineCourse {

    private final String name;
    private final int courseMinutes;
}

@AllArgsConstructor
@Getter
final class YoutubeCourse implements OnlineCourse {

    private final String name;
    private final int courseSeconds;
}
