CREATE TABLE IF NOT EXISTS question
(
    id SERIAL PRIMARY KEY,
    title TEXT,
    description TEXT,
    date_created TIMESTAMP
);