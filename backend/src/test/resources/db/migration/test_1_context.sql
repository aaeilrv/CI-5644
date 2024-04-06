INSERT INTO users (id, first_name, last_name, birthday, username, email, auth0_sub) VALUES
    (1, 'ejemplo', 'ejemploso', '2000-01-01', 'ejemplito', 'ejemploejemploso@gmail.com', 'ejemploejemploso');

INSERT INTO credit_card(id, user_id, expiration_date, card_number, cardholder_name, bank, card_type) VALUES
    (1, 1,'1990-01-01',123456789,'Maria Lopez','Banco','VISA');