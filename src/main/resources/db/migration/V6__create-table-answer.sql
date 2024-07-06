CREATE TABLE IF NOT EXISTS answer(
    id BIGINT NOT NULL AUTO_INCREMENT,
    message TEXT,
    creation_date TIMESTAMP,
    solution TEXT,

    PRIMARY KEY(id)
);