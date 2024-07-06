CREATE TABLE IF NOT EXISTS user_profile(
    user_id BIGINT NOT NULL,
    profile_id BIGINT NOT NULL,

    PRIMARY KEY(user_id, profile_id),
    FOREIGN KEY(user_id) REFERENCES user(id),
    FOREIGN KEY(profile_id) REFERENCES profile(id)
);