# Online Store REST API
#### Video Demo:  <https://www.youtube.com/watch?v=v1Wanq8Tqfc>
#### Description:

This project is a backend REST API for an online store, built with Java and Spring Boot framework. The goal was to design and implement an e-commerce backend that handles purchases: seeing products organized by category, placing an order, adding items and making a payment. The API follows REST conventions and returns JSON responses, making it easy to consume from any frontend or HTTP client.

The application is structured around a layered architecture, separating concerns into 4 main layers: entities (the domain model), repositories (data access), services (business logic), and resources (HTTP controllers).


## Project Files

### Entities

User.java - 
User's name, email, phone number, and password. Users are linked to orders they have placed.

Product.java - 
Each product has a name, description, price, and an image URL. Products are associated with one or more categories.

Category.java - 
Product categories like "Furniture" or "Decoration". A product can belong to many categories, and a category can contain multiple products, so a many-to-many relationship managed through a join table.

Order.java - 
Records the time of the order, it's client, the set of items and the payment. The getTotal() method calculates the order's total price by summing the subtotal of all its items.

OrderItem.java - 
Junction entity between Order and Product. The relationship between orders and products has extra data (quantity and price at the time of purchase) so a dedicated entity was needed. It uses a composite primary key via an embedded class. The price is stored at the moment the item is added, so a future product price change does not affect past orders. A getSubTotal() method returns quantity x price.

OrderItemPK.java - 
Embeddable composite key class that holds references to Order and Product. It's the standard JPA approach for modeling a many-to-many relationship that carries their own attributes.

Payment.java - 
It stores the moment of payment and is linked one-to-one with an Order. Payment is cascaded from the order, meaning saving an order with a payment set will also persist the payment automatically.

### Repositories

Each entity has a corresponding repository interface that extends JpaRepository. This provides all standard CRUD operations (save, find, delete, list) out of the box without requiring any implementation code.

### Services

UserService.java - 
Handles logic for users: list, find by ID, inserting, update, and delete. Delete and update catch JPA exceptions and rethrow them as custom application exceptions, so that the controller layer can return clean HTTP error responses.

CategoryService.java - 
Provides read operations for categories (list all, find by ID). Categories are not meant to be created or modified through the API during normal usage, so only retrieval is exposed.

ProductService.java - Handles retrieval of products (list all, find by ID). Like categories, product management was intentionally kept read-only on the API surface.

OrderService.java - It handles creating a new order (setting the current time and linking the user), add items to an order, list, find by ID, and deletE.

CheckoutService.java - Intended to handle the payment flow for a given order. This service is a placeholder representing where payment processing logic (such as calling an external payment gateway) would be integrated in a production system.

### Resources (Controllers)

UserResource.java - 
GET /users, GET /users/{id}, POST /users, PUT /users/{id}, DELETE /users/{id}.

CategoryResource.java - 
GET /categories, GET /categories/{id}, POST /categories, PUT /categories/{id}, DELETE /categories/{id}.

ProductResource.java - 
GET /products, GET /products/{id}, POST /products, PUT /products/{id}, DELETE /products/{id}.

OrderResource.java - 
GET /orders, GET /orders/{id}, POST /orders, POST /orders/{id}/items, DELETE /orders/{id}.

CheckoutResource.java - 
POST /checkout/{orderId}. Returns a confirmation message, serving as the endpoint where a payment integration would be wired in.

### Exception Handling

ResourceNotFoundException.java - A custom unchecked exception thrown when a requested resource isn't found in the database.

DatabaseException.java - 
A custom unchecked exception thrown when a database operation fails due to a constraint violation, like deleting a user who has associated orders.

ResourceExceptionHandler.java - 
A @ControllerAdvice class that intercepts both custom exceptions and maps them to HTTP responses.

StandardError.java - 
Structure of the error responses, including the request path that triggered the error.

### Configuration

TestConfig.java - 
A Spring @Configuration class active only under the test profile. It implements CommandLineRunner to seed the in-memory H2 database with sample data on startup. This made it easy to test all endpoints without needing to manually insert data each time.


## Database

For the database setup, I chose to use an H2 in-memory database for the test profile and PostgreSQL for production. The two profiles are separated cleanly through Spring's @Profile annotation and application-test.properties / application-dev.properties files.
