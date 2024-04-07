INSERT INTO users (id, first_name, last_name, birthday, username, email, auth0_sub) VALUES
    (1, 'ejemplo', 'ejemploso', '2000-01-01', 'ejemplito', 'ejemploejemploso@gmail.com', 'ejemploejemploso');

INSERT INTO credit_card(id, user_id, expiration_date, card_number, cardholder_name, bank, card_type) VALUES
    (1, 1,'1990-01-01',123456789,'Maria Lopez','Banco','VISA');

INSERT INTO ownership (user_id, card_id, number_of_cards_owned) VALUES
    (1, 1, 5), (1, 2, 1), (1, 3, 1), (1, 4, 1), (1, 5, 1), (1, 6, 1), (1, 7, 1),
    (1, 8, 1), (1, 9, 1), (1, 10, 1), (1, 11, 1), (1, 12, 1), (1, 13, 1), (1, 14, 1),
    (1, 15, 1), (1, 16, 1), (1, 17, 1), (1, 18, 1), (1, 19, 1), (1, 20, 1), (1, 21, 1),
    (1, 22, 1), (1, 23, 1), (1, 24, 1), (1, 25, 1), (1, 26, 1), (1, 27, 1), (1, 28, 1),
    (1, 29, 1), (1, 30, 1), (1, 31, 1), (1, 32, 1), (1, 33, 1), (1, 34, 1), (1, 35, 1),
    (1, 36, 1);