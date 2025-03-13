# Hexagonal Architecture Spring Boot EX

A Spring Boot template implementing the Hexagonal Architecture pattern (also known as Ports and Adapters).

## Overview

This project structure follows the hexagonal architecture pattern, organized into three main layers:

```
src/main/java/com/example/
├── application/
├── domain/
└── infrastructure/
```


## Architecture Breakdown

### Application Layer

The application layer contains ports (interfaces) that define how the outside world interacts with the core domain, along with the service implementations that coordinate domain operations.

```
application/
├── port/
│   ├── in/               # Input ports (use cases the application offers)
│   │   └── api/          # Interfaces consumed by driving adapters
│   └── out/              # Output ports (services the application needs)
│       ├── persistence/  # Repository interfaces
│       └── external/     # External service interfaces
└── service/
    └── impl/             # Implementations of input ports using domain logic
```

### Domain Layer

The domain layer contains core business entities and logic, free from infrastructure concerns.

```
domain/
├── model/                # Business entities
├── exception/            # Domain-specific exceptions
```

### Infrastructure Layer

The infrastructure layer contains adapter implementations that connect the application to external systems.

```
infrastructure/
├── adapter/
│   ├── in/               # Inbound (driving) adapters
│   │   ├── web/          # REST API implementation
│   │   │   ├── controller/
│   │   │   ├── dto/      # Data transfer objects
│   │   │   └── mapper/   # DTOs to domain mappers
│   │   └── event/        # Event-based adapters
│   └── out/              # Outbound (driven) adapters
│       ├── persistence/  # Database implementations
│       │   ├── entity/   # JPA entities
│       │   ├── repository/
│       │   └── mapper/   # Entity to domain mappers
│       └── external/     # External API clients
└── config/               # Configuration classes
```

## Key Principles

1. **Dependency Rule**: Dependencies always point inward (infrastructure → application → domain)

2. **Port Interfaces**: All interactions cross boundaries through well-defined ports
    - Input ports: Use cases offered by the application
    - Output ports: External services required by the application

3. **Adapter Implementations**: Concrete implementations of the ports
    - Inbound adapters: Implement or use input ports (e.g., REST controllers)
    - Outbound adapters: Implement output ports (e.g., database repositories)

4. **Core Isolation**: Domain logic remains isolated from external concerns, making it easier to test and less vulnerable to external changes

## Benefits

- Clear separation of concerns
- Components can be developed and tested in isolation
- Business logic is protected from external changes
- Adapters can be swapped without affecting the core
- Improved testability with cleaner unit tests

## Technologies

- Java 21 with GraalVM
- Spring Boot 3.2.x
- Maven for dependency management
- Spring Data JPA for persistence
- H2 Database for development
- Lombok for reducing boilerplate
- MapStruct for object mapping

## Getting Started

1. Clone this repository
2. Build with Maven: `mvn clean install`
3. Run the application: `mvn spring-boot:run`

## Testing

The project includes test directories organized according to the hexagonal architecture:

```
src/test/java/com/example/
├── application/           # Tests for application services
├── domain/                # Unit tests for domain logic
├── infrastructure/        # Tests for adapters
│   ├── adapter/
│   │   ├── in/
│   │   └── out/
└── architecture/          # Architecture tests
```

```
src/main/java/com/example/
├── application/
│   ├── port/
│   ├── in/               # Input ports (use cases the application offers)
│   │   └── api/          # Interfaces consumed by driving adapters
│   └── out/              # Output ports (services the application needs)
│       ├── persistence/  # Repository interfaces
│       └── external/     # External service interfaces
│    └── service/
│        └── impl/             # Implementations of input ports using domain logic
├── domain/
│    ├── model/                # Business entities
│    ├── exception/            # Domain-specific exceptions
├── infrastructure/
    ├── adapter/
    │   ├── in/               # Inbound (driving) adapters
    │   │   ├── web/          # REST API implementation
    │   │   │   ├── controller/
    │   │   │   ├── dto/      # Data transfer objects
    │   │   │   └── mapper/   # DTOs to domain mappers
    │   │   └── event/        # Event-based adapters
    │   └── out/              # Outbound (driven) adapters
    │       ├── persistence/  # Database implementations
    │       │   ├── entity/   # JPA entities
    │       │   ├── repository/
    │       │   └── mapper/   # Entity to domain mappers
    │       └── external/     # External API clients
    └── config/               # Configuration classes
```
