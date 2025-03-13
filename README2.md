# Hexagonal Architecture Template (Spring Boot)

## Overview

This project serves as a **template** for building Spring Boot applications following the **Hexagonal Architecture (Ports & Adapters pattern)**. It ensures a **clean separation** of concerns, improves **testability**, and provides **flexibility** in switching out technologies.

This template is structured to be:

- **Framework-agnostic at its core**: Business logic is independent of external frameworks.
- **Modular and maintainable**: Clear separation between core domain, application logic, and infrastructure.
- **Optimized for GraalVM**: Allows native image compilation for faster startup times.

## Project Structure

The following breakdown explains the key components of this template:

```plaintext
src/main/java/com/example/
├── application/           # Application layer (Use cases & service interfaces)
│   ├── port/             # Defines input & output ports
│   ├── in/               # Input ports (Use cases exposed to the outside world)
│   │   └── api/          # Interfaces consumed by driving adapters
│   ├── out/              # Output ports (Interfaces for external dependencies)
│   │   ├── persistence/  # Repository interfaces for persistence
│   │   └── external/     # Interfaces for external services (e.g., APIs)
│   └── service/          # Service layer (business logic orchestrators)
│       └── impl/         # Implementations of input ports using domain logic
├── domain/               # Pure business logic (Entities, Aggregates, etc.)
│   ├── model/            # Business models
│   ├── exception/        # Domain-specific exceptions
├── infrastructure/        # Technical implementation details
│   ├── adapter/          # Adapters (Bridges between domain & external world)
│   │   ├── in/           # Inbound adapters (Controllers, Listeners, etc.)
│   │   │   ├── web/      # REST API layer
│   │   │   │   ├── controller/ # API controllers
│   │   │   │   ├── dto/  # Data Transfer Objects
│   │   │   │   └── mapper/ # DTO ↔ Domain mappers
│   │   │   └── event/    # Event-based adapters (e.g., Kafka consumers)
│   │   ├── out/          # Outbound adapters (External services, DB, etc.)
│   │   │   ├── persistence/  # Persistence layer implementation
│   │   │   │   ├── entity/   # JPA entities
│   │   │   │   ├── repository/ # Repository implementations
│   │   │   │   └── mapper/    # Entity ↔ Domain mappers
│   │   │   └── external/      # External API clients
│   └── config/               # Spring Boot configuration classes
```

## Architectural Breakdown

### 1. **Domain Layer (**``**)**

This layer contains **pure business logic** and is completely independent of frameworks.

- `` → Business entities (core objects that represent the domain)
- `` → Custom exceptions related to the domain

### 2. **Application Layer (**``**)**

This is where business rules and application logic reside.

- `` → Defines input ports (use cases) consumed by controllers and event handlers.
- `` → Defines output ports that interact with external dependencies (database, APIs, etc.).
- `` → Implements use cases by orchestrating domain logic.

### 3. **Infrastructure Layer (**``**)**

This layer bridges the application with external technologies.

- **Adapters (**``**)**
    - **Inbound (**``**)** → Implements input ports via controllers (e.g., REST, GraphQL, Event listeners).
    - **Outbound (**``**)** → Implements output ports via persistence (JPA, MongoDB) and external services (REST clients, Kafka producers).
- `` → Centralized Spring Boot configurations (e.g., database, security, API clients).

## Technologies & Dependencies

This project is built with:

- **Spring Boot** (Core framework)
- **Maven** (Dependency management)
- **Spring WebFlux** (For reactive programming, if needed)
- **Spring Data JPA** (For persistence)
- **Spring Security** (For authentication & authorization)

## Running the Project

```sh
mvn clean package
java -jar target/myproject.jar
```


## Conclusion

This template provides a solid **Hexagonal Architecture** foundation for future services. It ensures that business logic remains **isolated and testable**, while allowing infrastructure and frameworks to be **easily swapped or extended**.

🚀 **Next Steps**: Implement business logic, define APIs, and integrate with external systems!



1. Naming Conventions and Consistency
   The naming of certain directories and classes seems consistent, but make sure all names are as clear as possible to avoid ambiguity. For example:
   application/port/: It would be clearer if the directory name reflects the nature of the ports more directly, like application/adapter/ (to emphasize the distinction between inbound/outbound) or application/contract/ for clearer contract boundaries. But the current structure is acceptable.
   in/ and out/: These are pretty intuitive, but ensure the terminology used internally (e.g., "driven" or "driving" adapters) is also clear across the team to avoid confusion when working with multiple adapters.
2. Separation of Concerns
   The architecture seems to adhere well to Hexagonal principles, with a clean separation between application, domain, and infrastructure layers. Here are some points to think about:
   domain/model/: This directory is the heart of the system and should hold business models. Ensure it’s not diluted with any application-specific logic, and it remains purely business-focused.
   infrastructure/adapter/in/web/: It's good to see that adapters are properly separated by inbound and outbound. One possible addition could be in/controller/ and in/service/, depending on your design. But it seems fine to leave things as-is for now.
3. Service Layer Placement
   The service/impl/ directory under application/ is where implementation logic should reside. While this works well for organizing services, ensure that the domain logic (business rules) remains in the domain layer, keeping the service layer strictly for coordinating use cases.
   One suggestion: Ensure that impl/ doesn’t end up carrying business logic. The goal should be to keep the service layer thin, focused mostly on orchestration.
4. Persistence Layer
   infrastructure/adapter/out/persistence/: You are using a clean separation for persistence, which is good. Consider adding repository/impl/ for actual JPA repository implementations if you expect more than one type of persistence approach (e.g., NoSQL).
   JPA entities: Ensure your JPA entities remain "dumb" and only hold data. Any business logic should go into the domain/model/, leaving the entity to just act as a POJO.
5. External APIs and Clients
   infrastructure/adapter/out/external/: It's great that you're separating external APIs into their own adapter layer. Be sure to avoid mixing external service logic with core business logic in this layer.
6. Exception Handling
   domain/exception/: This directory is for domain-specific exceptions. However, the way exceptions are handled between layers should be consistent. Ensure exceptions thrown by the domain are properly translated to something suitable for the service or infrastructure layer (e.g., validation errors, business rule violations).
   If you're using custom exceptions, it may help to categorize them based on the layer or purpose (e.g., validation, persistence, integration).
7. Configuration Layer
   infrastructure/config/: Configuration classes should be segregated by their responsibility. For instance, database configurations, security configurations, and message broker configurations could all be in their respective classes. If things start growing, consider grouping configurations under specific subdirectories for better clarity.
8. DTO and Mapper Organization
   web/dto/ and web/mapper/: The DTOs and mappers are placed under the web directory, which is good for the presentation layer. But if the number of mappers grows, you may want to consider having a separate mapper directory under infrastructure/ for other non-UI mappers, especially if you have mappers for transforming external service responses or persistence entities.
9. Event Handling
   infrastructure/adapter/in/event/: If your application is event-driven, ensure that there is a clear distinction between different event types (e.g., domain events vs. application events). You might also want to separate event listeners and publishers to avoid clutter.
10. Testing Layer
    spring-boot-starter-test: You already have the necessary testing dependencies. Make sure your tests align with the hexagonal approach by following the ports and adapters strategy in your test suite. Keep unit tests isolated from the external layers (like databases or external APIs), and use mocks/stubs for outbound adapters.



Potential Enhancements or Considerations:
Integration Tests: Consider placing integration tests for ports/adapters under a different package (e.g., src/test/integration/) to make it clear these tests will span across layers.
Use of Annotations: If your application grows, consider using custom annotations for your layers, like @Service, @Port, @Adapter to enforce clear boundaries and improve readability.
Event-Driven Approach: If you anticipate scaling to a more event-driven architecture, consider making event-related adapters a more prominent part of the structure, separating domain and application events.