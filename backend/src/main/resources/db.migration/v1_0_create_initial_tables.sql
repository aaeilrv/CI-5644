CREATE TABLE member (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    birthday DATE NOT NULL,
    username VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE card(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    player_position VARCHAR(30) NOT NULL,
    player_number VARCHAR(2) NOT NULL,
    photo VARCHAR(100) NOT NULL
);

CREATE TABLE purchase(
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    user_id INTEGER NOT NULL REFERENCES member(id),
    price INTEGER NOT NULL CHECK(price > 0)
    --package_id INTEGER NOT NULL REFERENCES package(id)
    --UNIQUE(user_id, package_id)
);

CREATE TABLE payment_information(
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES member(id),
    expiration_date DATE NOT NULL,
    card_number VARCHAR(16) NOT NULL UNIQUE,
    security_code VARCHAR(3) NOT NULL,
    cardholder_name VARCHAR(20) NOT NULL,
    bank VARCHAR(20) NOT NULL
);

CREATE TABLE exchange(
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    owner_id INTEGER NOT NULL REFERENCES member(id),
    card_id INTEGER NOT NULL REFERENCES card(id),
    receiver_id INTEGER NOT NULL REFERENCES member(id),
    number_of_cards_traded INTEGER NOT NULL CHECK(number_of_cards_traded > 0)
    --UNIQUE(owner_id, card_id, receiver_id)
);

CREATE TABLE ownership(
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES member(id),
    card_id INTEGER NOT NULL REFERENCES card(id),
    number_of_cards_owned INTEGER NOT NULL CHECK(number_of_cards_owned > 0),
    UNIQUE(user_id, card_id)
);