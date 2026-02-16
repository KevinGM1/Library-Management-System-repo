The goal of this project is to build a Java library management system with:
    - OOP 
    - layered architecture
    - a database
    - REST API (Spring Boot)
    - unit tests
    - validation and error handling
    - clean documentation

Architecture
Here is a high-level overview of the planned Layered architecture:
client -> controller -> service layer -> repository layer -> database

supporting layers are DTOs, mappers, and exceptions

Database Schema
the database will have three tables: Books, Loans, and Accounts
one-to-many relationship between Books and Loans (one book can appear in multiple loans)
one-to-many relationship between Accounts and Loans  (an account can have multiple loans)

Features the API should be able to process
1) register new accounts
2) remove account
3) list all outstanding loans from a particular account
4) add book
5) remove book
6) search book by title/author/isbn
7) get all books
8) loan a book
9) return a book
10) renew a loan
11) get all outstanding loans