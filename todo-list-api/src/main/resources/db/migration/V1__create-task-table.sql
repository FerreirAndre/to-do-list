CREATE TABLE tasks (
        id BIGINT PRIMARY KEY UNIQUE NOT NULL,
        title VARCHAR(255) unique not null,
        description TEXT,
        completed BOOLEAN NOT NULL
);
