
-- Create users table
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    role VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create books table
CREATE TABLE IF NOT EXISTS books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    publication_year INT NOT NULL,
    available BOOLEAN DEFAULT TRUE
);

-- Create transactions table
CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    book_id INT NOT NULL REFERENCES books(id) ON DELETE CASCADE,
    issued_at DATE NOT NULL,
    due_date DATE NOT NULL,
    returned_at DATE DEFAULT NULL,
    fine FLOAT DEFAULT NULL
);

-- Insert random users
INSERT INTO users (name, email, role) VALUES
('Alice Smith', 'alice.smith@example.com', 'MEMBER'),
('Bob Johnson', 'bob.johnson@example.com', 'MEMBER'),
('Charlie Brown', 'charlie.brown@example.com', 'MEMBER'),
('Diana Prince', 'diana.prince@example.com', 'ADMIN'),
('Ethan Hunt', 'ethan.hunt@example.com', 'MEMBER');


-- Insert random books
INSERT INTO books (title, author, genre, publication_year, available) VALUES
('Spring Boot in Action', 'Craig Walls', 'Programming', 2016, TRUE),
('Effective Java', 'Joshua Bloch', 'Programming', 2018, TRUE),
('Clean Code', 'Robert C. Martin', 'Programming', 2008, TRUE),
('The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction', 1925, TRUE),
('To Kill a Mockingbird', 'Harper Lee', 'Fiction', 1960, TRUE),
('1984', 'George Orwell', 'Dystopian', 1949, TRUE),
('The Catcher in the Rye', 'J.D. Salinger', 'Fiction', 1951, TRUE),
('The Lord of the Rings', 'J.R.R. Tolkien', 'Fantasy', 1954, TRUE),
('The Lean Startup', 'Eric Ries', 'Business', 2011, TRUE);


-- Insert random transactions (issued books)
INSERT INTO transactions (user_id, book_id, issued_at, due_date, returned_at, fine) VALUES
(1, 1, '2024-11-01', '2024-11-15', '2024-11-10', NULL),
(2, 2, '2024-11-05', '2024-11-20', NULL, NULL),
(3, 3, '2024-11-10', '2024-11-25', NULL, NULL),
(1, 4, '2024-11-15', '2024-11-30', NULL, NULL),
(4, 5, '2024-11-20', '2024-12-05', NULL, NULL);

-- Change owner of users table
ALTER TABLE users OWNER TO library_user;

-- Change owner of books table
ALTER TABLE books OWNER TO library_user;

-- Change owner of transactions table
ALTER TABLE transactions OWNER TO library_user;

