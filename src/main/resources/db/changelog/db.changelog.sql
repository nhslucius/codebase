-- changeset lucius:001
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(150) NOT NULL UNIQUE,
                       dob DATE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
