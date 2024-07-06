CREATE TABLE IF NOT EXISTS topic(
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    message TEXT,
    creation_date TIMESTAMP,
    status VARCHAR(50),

    PRIMARY KEY(id)
);