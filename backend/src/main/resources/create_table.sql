CREATE TABLE IF NOT EXISTS question
(
    question_id SERIAL PRIMARY KEY,
    title TEXT,
    description TEXT,
    date_created TIMESTAMP
);

CREATE TABLE IF NOT EXISTS answer
(
    answer_id SERIAL PRIMARY KEY,
    answer TEXT,
    question_id INT,
    date_created TIMESTAMP,
    FOREIGN KEY (question_id) REFERENCES question (question_id) ON DELETE CASCADE
    );