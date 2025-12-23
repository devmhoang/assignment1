# README
proof of concept submission - Tran Minh Hoang

[FRONTEND - React/Node.js 22](https://localhost:5173/)
[BACKEND - Springboot 3.5.8 Java 21 Maven 3.9.9 ](https://localhost:8011)

# Business Logic Design
- User must sign up and log in to create order.
- User can view their own order history.
- Upon a new order creation(saved into database), Backend valuates quantity per item of that order and deduct stocks in the Product inventory accordingly.
- Admin (default password: abc123) can create new product, delete existing product.
- Admin can view all orders.

- JWT is stored in HttpOnly Cookies, transfer via HTTPS as required by common browsers
- current JWT default lifespan is 5 minutes, upon accessing authenticated resources with still-valid JWT, a new JWT is provided thus extends login session 6 minutes mores.


# API Documentation
[SwaggerAPI](https://localhost:8011/swagger-ui/index.html#/)

## USER
`/api/user/signup` - for User to signup to create a new account

`/api/user/login` - for User to login to create any order

`/api/user/validate` - client calls upon accessing authenticated resources

`/api/user/logout` - upon logging out will clear user and cart states in Client

## PRODUCT
`/api/product/create` - for Admin to create products

`/api/product/view` - for everyone to view shop/inventory

`/api/product/delete/{id}` - for Admin to delete a product

## ORDER
`/api/order/create` - for User to create order

`/api/order/view` -  for User to view their order history

`/api/order/viewall` -  for Admin to view all orders

# SETUP DEMO
## Postgres 17.6  - main database
- execute schema.sql first, then init.sql
[schema.sql](./database/schema.sql)
[init.sql](./database/init.sql)
__Database relationship modeling__
OneToMany: User can have/view multiple Orders.

## Redis 8.0.2 - to blacklist JWT upon user/admin logging out
- In current environment which Redis runs on, no password is set. Please check if your Redis has password and update at application.properties
[application.properties](./backend/src/main/resources/application.properties)

# Backend & Frontend
__Quick Start__
Backend: `cd ./backend && mvn spring-boot:run`
Frontend: `cd ./frontend && npm i && npm run dev`

- For JWT Authentication, Backend uses localhost.p12 for local SSL certification, Frontend uses Mkcert plugin to both facilitate HTTPS as required by Chrome/Firefox. This enables HttpOnly Cookie JWT, to limit access from client and only handled between browsers and Springboot.

# Default Credentials
**Admin Account:**
- Password: `abc123`
**User Accounts:**
sarah_jones (password: abc123)
david_brown (password: abc123)


## Known Limitations
- JWT lifespan: 5 minutes (sliding window).
- Self-signed certificates for local HTTPS development.
- No implemented limit on quantity per Product which can exceed avaivable stock.
