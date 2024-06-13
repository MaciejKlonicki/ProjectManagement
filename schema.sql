CREATE TABLE Project
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    creator     VARCHAR(100) NOT NULL
);

CREATE TABLE Document
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    creator     VARCHAR(100) NOT NULL,
    topic       VARCHAR(50),
    content     TEXT,
    project_id  INTEGER REFERENCES Project (id) ON DELETE CASCADE
);

CREATE TABLE Role
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE Users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    email    VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE UserProjectRelation
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER REFERENCES Users (id),
    project_id INTEGER REFERENCES Project (id),
    role_id    INTEGER REFERENCES Role (id)
);


INSERT INTO Role (name)
VALUES ('MANAGER'),
       ('ENGINEER'),
       ('HR'),
       ('ADMINISTRATOR');

INSERT INTO Users (username, email, password)
VALUES ('admin', 'admine@admin.pl', 'admin');

INSERT INTO Project (name, description, creator)
VALUES ('alpha', 'Description for alpha', 'admin'),
       ('beta', 'Description for beta', 'admin');

INSERT INTO Document (title, description, creator, topic, content, project_id)
VALUES ('Document Title', 'Document Description', 'admin', 'Topic', 'Content',
        (SELECT id FROM Project WHERE name = 'alpha'));

INSERT INTO UserProjectRelation (user_id, project_id, role_id)
VALUES ((SELECT id FROM Users WHERE username = 'admin'),
        (SELECT id FROM Project WHERE name = 'alpha'),
        (SELECT id FROM Role WHERE name = 'ADMINISTRATOR'));
