CREATE TABLE Project (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    creator VARCHAR(100) NOT NULL
);

CREATE TABLE Document (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    creator VARCHAR(100) NOT NULL,
    topic VARCHAR(50),
    content TEXT,
    project_id INTEGER REFERENCES Project(id) ON DELETE CASCADE
);

CREATE TABLE Role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE UserProjectRelation (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES Users(id),
    project_id INTEGER REFERENCES Project(id),
    role_id INTEGER REFERENCES Role(id)
);
