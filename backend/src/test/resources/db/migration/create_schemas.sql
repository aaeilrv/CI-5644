CREATE TYPE EXCHANGE_REQUEST_STATUS AS ENUM ('PENDING', 'ACCEPTED', 'REJECTED', 'CANCELLED');
CREATE TYPE CREDIT_CARD_TYPE AS ENUM ('VISA', 'MASTERCARD');
CREATE TYPE FIELD_POSITION AS ENUM ('GOALKEEPER',
    'MIDFIELDER',
    'DEFENDER',
    'FORWARD');
CREATE TYPE EXCHANGE_OFFER_STATUS AS ENUM ('PENDING', 'ACCEPTED', 'REJECTED', 'COUNTEROFFER', 'CANCELLED');
CREATE CAST (VARCHAR AS FIELD_POSITION) WITH INOUT AS IMPLICIT;
CREATE CAST (VARCHAR AS EXCHANGE_REQUEST_STATUS) WITH INOUT AS IMPLICIT;
CREATE CAST (VARCHAR AS EXCHANGE_OFFER_STATUS) WITH INOUT AS IMPLICIT;
CREATE CAST (VARCHAR AS CREDIT_CARD_TYPE) WITH INOUT AS IMPLICIT;

CREATE TABLE IF NOT EXISTS users (
                                     id BIGSERIAL PRIMARY KEY,
                                     first_name VARCHAR(100) NOT NULL,
                                     last_name VARCHAR(100) NOT NULL,
                                     email VARCHAR(100) NOT NULL,
                                     birthday DATE NOT NULL,
                                     username VARCHAR(20) NOT NULL UNIQUE,
                                     auth0_sub VARCHAR(100) UNIQUE --Identificador de usuario de Auth0
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
                                       purchase_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       user_id BIGINT NOT NULL REFERENCES users(id),
                                       packets_purchased INT NOT NULL CHECK(packets_purchased > 0),
                                       base_amount INT NOT NULL, --Precio hipotetico de $5 por paquete
                                       credit_card_id BIGINT NOT NULL REFERENCES credit_card(id)
);

CREATE TABLE IF NOT EXISTS exchange_request(
                                               id BIGSERIAL PRIMARY KEY,
                                               user_id BIGINT NOT NULL REFERENCES users(id),
                                               requested_card_id BIGINT NOT NULL REFERENCES card(id),
                                               status EXCHANGE_REQUEST_STATUS NOT NULL,
                                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS exchange_offer(
                                             id BIGSERIAL PRIMARY KEY,
                                             bidder_id BIGINT NOT NULL REFERENCES users(id),
                                             exchange_request_id BIGINT NOT NULL REFERENCES exchange_request(id),
                                             offered_card_id BIGINT NOT NULL REFERENCES card(id),
                                             status EXCHANGE_OFFER_STATUS NOT NULL,
                                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS exchange_counteroffer (
                                                     id BIGSERIAL PRIMARY KEY,
                                                     offered_card_id BIGINT NOT NULL REFERENCES card(id),
                                                     status EXCHANGE_REQUEST_STATUS NOT NULL,
                                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                     exchange_request_id BIGINT NOT NULL REFERENCES exchange_request(id),
                                                     exchange_offer_id BIGINT NOT NULL REFERENCES exchange_offer(id),
                                                     UNIQUE(exchange_request_id, exchange_offer_id)
);

CREATE TABLE IF NOT EXISTS ownership(
                                        id BIGSERIAL PRIMARY KEY,
                                        user_id BIGINT NOT NULL REFERENCES users(id),
                                        card_id BIGINT NOT NULL REFERENCES card(id),
                                        number_of_cards_owned INT NOT NULL,
                                        UNIQUE(user_id, card_id)
);

INSERT INTO card (id, name, player_position, player_number, country, photo_url) VALUES
        (1, 'Lionel Messi', 'FORWARD', 10, 'Argentina', 'https://i.pinimg.com/736x/f3/5b/fd/f35bfdfea8114351f2a262edc25561f3.jpg'),
        (2, 'Cristiano Ronaldo', 'FORWARD', 7, 'Portugal', '/cards/Cristiano Ronaldo.jpeg'),
        (3, 'Neymar Jr', 'FORWARD', 10, 'Brazil', '/cards/Neymar Jr.jpeg'),
        (4, 'Kylian Mbappé', 'FORWARD', 10, 'France', '/cards/Kylian Mbappé.jpeg'),
        (5, 'Kevin De Bruyne', 'MIDFIELDER', 7, 'Belgium', '/cards/Kevin De Bruyne.jpeg'),
        (6, 'Virgil van Dijk', 'DEFENDER', 4, 'Netherlands', '/cards/Virgil Van Dijk.jpeg'),
        (7, 'Sadio Mané', 'FORWARD', 10, 'Senegal', '/cards/Sadio Mané.jpeg'),
        (8, 'Harry Kane', 'FORWARD', 9, 'England', '/cards/Harry Kane.jpeg'),
        (9, 'Luka Modrić', 'MIDFIELDER', 10, 'Croatia', '/cards/Luka Modrić.jpeg'),
        (10, 'Robert Lewandowski', 'FORWARD', 9, 'Poland', '/cards/Robert Lewandowski.jpeg'),
        (11, 'Eden Hazard', 'FORWARD', 7, 'Belgium', '/cards/Eden Hazard.jpeg'),
        (12, 'Paul Pogba', 'MIDFIELDER', 6, 'France', '/cards/Paul Pogba.jpeg'),
        (13, 'Alisson Becker', 'GOALKEEPER', 1, 'Brazil', '/cards/Alisson.jpeg'),
        (14, 'Raheem Sterling', 'FORWARD', 10, 'England', '/cards/Raheem Sterling.jpeg'),
        (15, 'Antoine Griezmann', 'FORWARD', 7, 'France', '/cards/Antoine Griezmann.jpeg'),
        (16, 'Frenkie de Jong', 'MIDFIELDER', 21, 'Netherlands', '/cards/Frenkie De Jong.jpeg'),
        (17, 'Romelu Lukaku', 'FORWARD', 9, 'Belgium', '/cards/Romelu Lukaku.jpeg'),
        (18, 'Thiago Silva', 'DEFENDER', 2, 'Brazil', '/cards/Thiago Silva.jpeg'),
        (19, 'Luis Suárez', 'FORWARD', 9, 'Uruguay', '/cards/Luis Suárez.jpeg'),
        (20, 'Angel Di Maria', 'FORWARD', 11, 'Argentina', '/cards/Angel Di María.jpeg'),
        (21, 'Manuel Neuer', 'GOALKEEPER', 1, 'Germany', '/cards/Manuel Neuer.jpeg'),
        (22, 'Gareth Bale', 'FORWARD', 11, 'Wales', '/cards/Gareth Bale.jpeg'),
        (23, 'Sergio Busquets', 'MIDFIELDER', 5, 'Spain', '/cards/Sergio Busquets.jpeg'),
        (24, 'Memphis Depay', 'FORWARD', 10, 'Netherlands', '/cards/Memphis Depay.jpeg'),
        (25, 'Milan Borjan', 'GOALKEEPER', 18, 'Canada', '/cards/Milan Borjan.jpeg'),
        (26, 'Joshua Kimmich', 'MIDFIELDER', 6, 'Germany', '/cards/Joshua Kimmich.jpeg'),
        (27, 'Hugo Lloris', 'GOALKEEPER', 1, 'France', '/cards/Hugo Lloris.jpeg'),
        (28, 'Marquinhos', 'DEFENDER', 5, 'Brazil', '/cards/Marquinhos.jpeg'),
        (29, 'Heung-min Son', 'FORWARD', 7, 'South Korea', '/cards/Heung-Min Son.jpeg'),
        (30, 'Rúben Dias', 'DEFENDER', 3, 'Portugal', '/cards/Rúben Dias.jpeg'),
        (31, 'Ederson', 'GOALKEEPER', 23, 'Brazil', '/cards/Ederson.jpeg'),
        (32, 'Alex Sandro', 'DEFENDER', 12, 'Brazil', '/cards/Alex Sandro.jpeg'),
        (33, 'Éder Militão', 'DEFENDER', 14, 'Brazil', '/cards/Éder Militão.jpeg'),
        (34, 'Casemiro', 'MIDFIELDER', 5, 'Brazil', '/cards/Casemiro.jpeg'),
        (35, 'Vinícius Jr', 'FORWARD', 20, 'Brazil', '/cards/Vinícius Jr.jpeg'),
        (36, 'Gabriel Jesus', 'FORWARD', 9, 'Brazil', '/cards/Gabriel Jesus.jpeg');