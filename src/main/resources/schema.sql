CREATE TABLE IF NOT EXISTS box (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    image bytea NOT NULL
);