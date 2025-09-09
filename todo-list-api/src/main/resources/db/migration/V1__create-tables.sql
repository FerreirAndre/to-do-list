CREATE TABLE tasks (
        id BIGINT PRIMARY KEY UNIQUE NOT NULL,
        title VARCHAR(255) unique not null,
        description TEXT,
        completed BOOLEAN NOT NULL
);

CREATE TABLE users (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    login TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    role TEXT NOT NULL
);