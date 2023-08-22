CREATE TABLE IF NOT EXISTS question
(
    id serial PRIMARY KEY,
    title TEXT,
    description TEXT,
    date_created TIMESTAMP
);