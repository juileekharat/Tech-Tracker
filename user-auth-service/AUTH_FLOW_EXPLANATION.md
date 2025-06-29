
<hr></hr> Purpose:
This code implements a basic user authentication system using Spring Boot, JWT (JSON Web Token) for stateless authentication, and BCrypt for password hashing. It allows users to register and log in, returning a JWT token on successful login.

<hr></hr> Sequence & Flow:


User Registration (/api/auth/register):


The client sends a POST request with a username and password.
AuthController.register() receives the request and calls AuthService.registerUser().
AuthServiceImpl.registerUser() hashes the password and saves the user to the database via UserRepository.
User Login (/api/auth/login):


The client sends a POST request with username and password.
AuthController.login() calls AuthService.authenticate() to verify credentials.
If valid, a JWT token is generated using JwtUtil.generateToken() and returned in an AuthResponse.
JWT Token Validation (for protected endpoints):


For each request, JwtAuthFilter checks for a JWT in the Authorization header.
If present, it validates the token using JwtUtil.validateToken() and extracts the username.
If valid, it sets the authentication in the Spring Security context.
Security Configuration:


SecurityConfig disables sessions (stateless), allows /api/auth/** endpoints without authentication, and protects all other endpoints.
It registers the JwtAuthFilter to process JWTs before the standard authentication filter.
<hr></hr> Key Components:


Model:


User: Entity for user data.
AuthRequest/AuthResponse: DTOs for authentication requests and responses.
Repository:


UserRepository: JPA repository for user persistence.
Service:


AuthService/AuthServiceImpl: Business logic for registration and authentication.
Controller:


AuthController: REST endpoints for registration and login.
Security:


JwtUtil: Utility for generating and validating JWT tokens.
JwtAuthFilter: Filter to authenticate requests using JWT.
SecurityConfig: Configures Spring Security for stateless JWT authentication.
<hr></hr> Typical Sequence:


User registers → password is hashed and saved.
User logs in → credentials are checked, JWT is returned.
User accesses protected endpoint → JWT is validated by filter, access is granted if valid.