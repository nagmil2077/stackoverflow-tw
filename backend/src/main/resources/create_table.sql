CREATE TABLE IF NOT EXISTS question
(
    id SERIAL PRIMARY KEY,
    title TEXT,
    description TEXT,
    date_created TIMESTAMP
);

CREATE TABLE IF NOT EXISTS answer
(
    answer_id SERIAL PRIMARY KEY,
    answer TEXT,
    id INT,
    FOREIGN KEY (id) REFERENCES question (id)
);