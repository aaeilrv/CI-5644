CREATE TYPE EXCHANGE_REQUEST_STATUS AS ENUM ('pending', 'accepted', 'rejected');
CREATE TYPE CREDIT_CARD_TYPE AS ENUM ('visa', 'mastercard');
CREATE TYPE FIELD_POSITION AS ENUM ('goalkeeper',
                                    'midfield',
                                    'defense',
                                    'forward');


CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    birthday DATE NOT NULL,
    username VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS card(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    player_position FIELD_POSITION NOT NULL,
    player_number SMALLINT NOT NULL,
    photo VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS credit_card(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    expiration_date DATE NOT NULL,
    card_number VARCHAR(16) NOT NULL UNIQUE,
    cardholder_name VARCHAR(20) NOT NULL,
    bank VARCHAR(20) NOT NULL
    card_type CREDIT_CARD_TYPE NOT NULL
);

CREATE TABLE IF NOT EXISTS purchase(
    id BIGSERIAL PRIMARY KEY,
    purchase_timestamp TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL REFERENCES users(id),
    packets_purchased INT NOT NULL CHECK(packets_purchased > 0),
    base_amount NUMERIC GENERATED ALWAYS AS (packets_purchased*5) NOT NULL, --Precio hipotetico de $5 por paquete
    tax_amount NUMERIC GENERATED ALWAYS AS (base_amount*1.12) NOT NULL, --considerando solo IVA por poner ejemplo
    total_amount NUMERIC GENERATED ALWAYS AS (base_amount + tax_amount) NOT NULL,
    credit_card_id BIGINT NOT NULL REFERENCES credit_card(id)
);

CREATE TABLE IF NOT EXISTS exchange_request(
    id BIGSERIAL PRIMARY KEY,
    requester_id BIGINT NOT NULL REFERENCES users(id),
    recipient_id BIGINT NOT NULL REFERENCES users(id),
    offered_card_id BIGINT NOT NULL REFERENCES card(id),
    offered_card_amount INT NOT NULL CHECK(number_of_offered_card > 0),
    requested_card_id BIGINT NOT NULL REFERENCES card(id),
    requested_card_amount INT NOT NULL CHECK(number_of_requested_card > 0),
    request_status EXCHANGE_REQUEST_STATUS NOT NULL
);

CREATE TABLE IF NOT EXISTS ownership(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    card_id BIGINT NOT NULL REFERENCES card(id),
    number_of_cards_owned INTEGER NOT NULL CHECK(number_of_cards_owned > 0),
    UNIQUE(user_id, card_id)
);