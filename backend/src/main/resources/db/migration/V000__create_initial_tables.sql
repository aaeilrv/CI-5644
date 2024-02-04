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
    player_position VARCHAR(30) NOT NULL,
    player_number INT NOT NULL,
    country VACHAR(100) NOT NULL,
    photo VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS payment_information(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    expiration_date VARCHAR(8) NOT NULL,
    card_number VARCHAR(16) NOT NULL UNIQUE,
    security_code VARCHAR(3) NOT NULL,
    cardholder_name VARCHAR(20) NOT NULL,
    bank VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS purchase(
    id BIGSERIAL PRIMARY KEY,
    purchase_date DATE NOT NULL,
    user_id BIGINT NOT NULL REFERENCES users(id),
    price NUMERIC NOT NULL CHECK(price > 0),
    payment_id BIGINT NOT NULL REFERENCES payment_information(id)
    --package_id INTEGER NOT NULL REFERENCES package(id)
    --UNIQUE(user_id, package_id)
);

CREATE TABLE IF NOT EXISTS exchange(
    id BIGSERIAL PRIMARY KEY,
    date DATE NOT NULL,
    owner_id BIGINT NOT NULL REFERENCES users(id),
    card_id BIGINT NOT NULL REFERENCES card(id),
    receiver_id BIGINT NOT NULL REFERENCES users(id),
    number_of_cards_traded INTEGER NOT NULL CHECK(number_of_cards_traded > 0)
);

CREATE TABLE IF NOT EXISTS ownership(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    card_id BIGINT NOT NULL REFERENCES card(id),
    number_of_cards_owned INTEGER NOT NULL CHECK(number_of_cards_owned > 0),
    UNIQUE(user_id, card_id)
);