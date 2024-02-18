CREATE TYPE EXCHANGE_REQUEST_STATUS AS ENUM ('PENDING', 'ACCEPTED', 'REJECTED');
CREATE TYPE CREDIT_CARD_TYPE AS ENUM ('VISA', 'MASTERCARD');
CREATE TYPE FIELD_POSITION AS ENUM ('GOALKEEPER',
                                    'MIDFIELDER',
                                    'DEFENDER',
                                    'FORWARD');
CREATE CAST (VARCHAR AS FIELD_POSITION) WITH INOUT AS IMPLICIT;
CREATE CAST (VARCHAR AS EXCHANGE_REQUEST_STATUS) WITH INOUT AS IMPLICIT;
CREATE CAST (VARCHAR AS CREDIT_CARD_TYPE) WITH INOUT AS IMPLICIT;

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100),
    birthday DATE NOT NULL,
    username VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS card(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    player_position FIELD_POSITION NOT NULL,
    player_number SMALLINT NOT NULL,
    country VARCHAR(100) NOT NULL,
    photo_url VARCHAR(255)
);

--Consultar con Jonathan, aparentemente almacenar tarjetas de crédito no es buena práctica
CREATE TABLE IF NOT EXISTS credit_card(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    expiration_date DATE NOT NULL,
    card_number VARCHAR(16) NOT NULL UNIQUE,
    cardholder_name VARCHAR(20) NOT NULL,
    bank VARCHAR(20) NOT NULL,
    card_type CREDIT_CARD_TYPE NOT NULL
);

CREATE TABLE IF NOT EXISTS purchase(
    id BIGSERIAL PRIMARY KEY,
    purchase_timestamp TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL REFERENCES users(id),
    packets_purchased INT NOT NULL CHECK(packets_purchased > 0),
    base_amount NUMERIC GENERATED ALWAYS AS (packets_purchased*5) STORED, --Precio hipotetico de $5 por paquete
    credit_card_id BIGINT NOT NULL REFERENCES credit_card(id)
);

CREATE TABLE IF NOT EXISTS exchange_request(
    id BIGSERIAL PRIMARY KEY,
    requester_id BIGINT NOT NULL REFERENCES users(id),
    recipient_id BIGINT NOT NULL REFERENCES users(id),
    offered_card_id BIGINT NOT NULL REFERENCES card(id),
    offered_card_amount INT NOT NULL CHECK(offered_card_amount > 0),
    requested_card_id BIGINT NOT NULL REFERENCES card(id),
    requested_card_amount INT NOT NULL CHECK(requested_card_amount > 0),
    request_status EXCHANGE_REQUEST_STATUS NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS ownership(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    card_id BIGINT NOT NULL REFERENCES card(id),
    number_of_cards_owned INT NOT NULL,
    UNIQUE(user_id, card_id)
);

CREATE INDEX IF NOT EXISTS idx_card_country ON card(country);