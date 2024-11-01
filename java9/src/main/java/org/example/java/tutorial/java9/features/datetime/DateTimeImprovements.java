package org.example.java.tutorial.java9.features.datetime;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class DateTimeImprovements {

	public static void main(String[] args) {

		/*
			Clock.systemUTC
			Obtains a clock that returns the current instant using the best available system clock, converting to date and time using the UTC time-zone.
			This clock, rather than systemDefaultZone(), should be used when you need the current instant without the date or time.
			This clock is based on the best available system clock. This may use System.currentTimeMillis(), or a higher resolution clock if one is available.
		 */
		Clock.systemUTC();

		// dividedBy
		// Returns number of whole times a specified Duration occurs within this Duration.
		System.out.printf(
				"The number of hours between 2 days is : %d",
				Duration
				.of(2, ChronoUnit.DAYS)
				.dividedBy(Duration.of(1, ChronoUnit.HOURS))
		);
		System.out.printf("%n	-------------------------%n");

		// truncatedTo
		// Returns a copy of this Duration truncated to the specified unit.
		System.out.printf(
				"Turn 1 day to hours gives us : %s%n",
				Duration
						.of(1, ChronoUnit.DAYS)
						.truncatedTo(ChronoUnit.HOURS)
		);
		System.out.printf("	-------------------------%n");

		// datesUntil
		// public Stream<LocalDate> datesUntil(LocalDate endExclusive)
		// Returns a sequential ordered stream of dates. The returned stream starts from this date (inclusive) and goes to endExclusive (exclusive) by an incremental step of 1 day.
		LocalDate.now()
				.datesUntil(LocalDate.now().plus(1, ChronoUnit.DAYS).plus(Period.ofDays(1)))
				.forEach(System.out::println);
		System.out.printf("	-------------------------%n");

		// show the number of leap year starting from my birthday
		System.out.print(
		LocalDate.of(1991, 6, 7)
				.datesUntil(LocalDate.now(), Period.ofYears(1))
				.map(date -> Year.of(date.getYear()))
				.filter(Year::isLeap)
				.count()
		);

	}
}
