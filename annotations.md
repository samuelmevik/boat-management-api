## Spring Annotations Overview

### `@Component`
`@Component` is a generic stereotype for any Spring-managed component. When I mark a class with this annotation, I'm telling Spring to create an instance of this class and manage it, making it available for dependency injection throughout the application.

### `@Controller`
This is a `@Component` used for web MVC controllers. In a web application, classes annotated with `@Controller` handle incoming web requests and decide what response to send back to the user, such as rendering a webpage.

### `@Service`
`@Service` is a `@Component` for service layer components. Classes marked with this annotation contain the business logic of the application—the core operations and calculations that are central to its functionality.

### `@Repository`
This annotation is a `@Component` for data access layer components. When I have a class that interacts with the database (fetching or saving data), I use `@Repository` to indicate its role. This also enables Spring to translate database exceptions into a consistent, more manageable form.

### `@RestController`
`@RestController` is a `@Controller` for RESTful web services. Classes annotated with this handle web requests and return data directly (like JSON), rather than rendering a view. This is useful for building APIs that clients can consume.

### `@Configuration`
This is a specialized `@Component` for configuration classes. By marking a class with `@Configuration`, I'm telling Spring that this class contains configuration and setup instructions, such as bean definitions and other settings that the application needs to run properly.

### `@Bean`
Used to define a bean in a Spring application. A bean is an object that is managed by the Spring container. By using `@Bean`, I can specify how to create an instance of an object that Spring will manage and supply wherever it's needed in the application.

### `@Autowired`
Used for automatic dependency injection. When I mark a constructor, method, or field with `@Autowired`, Spring will automatically provide the required dependencies. This helps reduce boilerplate code by eliminating the need to manually instantiate dependent objects.

### `@RequestMapping`
Used to map HTTP requests to handler methods in controllers. By applying this annotation at the class or method level, I can define the URLs and HTTP methods that the controller methods will handle.

### `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, `@PatchMapping`
Specialized versions of `@RequestMapping` for specific HTTP methods.

### `@Transactional`
Used to demarcate transactional boundaries at the class or method level. By applying this annotation, I can ensure that a sequence of operations is executed within a database transaction, providing consistency and rollback capabilities.
