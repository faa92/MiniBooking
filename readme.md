# MiniBooking

MiniBooking is a diploma project for managing apartment rentals.
The project consists of 4 entities: Landlord, Tenant, RentalAd, and Response.

## Functionality

### For Landlords:

- Create, delete, and update rental ads
- View all their own rental ads
- Confirm apartment bookings

### For Tenants:

- Get a list of rental ads from all landlords
- Get a specific rental ad by title
- Send a response to a rental ad to book an apartment

Java project using Spring Boot, JPA, and Spring Security.

## Technologies

- Spring Boot
- JPA
- Spring Security
- Postgres DB
- Flyway for DB schema versioning
- Three-layer architecture: repositories, services, controllers
- JPA or Spring Data JPA Repository
- Spring Web:
    - REST API
    - Swagger UI
    - Stateless authentication
- Docker support

## Installation

1. Clone the repository: git clone https://github.com/faa92/MiniBooking
2. Configure the database connection properties.
3. Build: mvn clean install
4. Run: java -jar target/mini-booking.jar

## Usage

- REST API endpoints
- Swagger UI for API documentation
- Stateless authentication mechanism

## Acknowledgements

Recognize individuals, resources, or sources of inspiration.

## Docker

Docker support for containerization.