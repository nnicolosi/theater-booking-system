# Theater Booking System
A simple Spring Boot application written in Kotlin

I initially created this application as part of an online course for building Spring Boot applications with Kotlin.  Once I was done with the course, however, I went back and refactored the application to make the patterns more consistent and to achieve a little more polish (filling in some user experience gaps).

The application is a contrived system allowing users to book seats in a theater for a variety of upcoming performances.  Here are some of the features:

- Initiate a one-time bootstrapping of data (seats and performances)
- Check seat availability for a given performance
- Book a seat for a given performance
- View a list of performances
- Add a new performance
- View a report showing all bookings
- View a report showing all premium seat bookings

The application is configured to use an H2 database.  The application.properties file can be updated to use this database in a file-based or in-memory configuration.  With the file-based configuration, updates are persisted between application restarts.  With the in-memory configuration, the database is recreated with each application restart.