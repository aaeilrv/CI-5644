INSERT INTO users (first_name, last_name, birthday, username, email, auth0_sub) VALUES
('Juan', 'Pérez', '1990-01-01', 'juanperez', 'ejemplo1@usb.ve', 'auth0|660970347e87f7a8c0af1f01'),
('María', 'López', '1985-02-15', 'marialopez', 'ejemplo2@usb.ve', 'auth0|6609705f9bdf9cea36fd8078'),
('Carlos', 'González', '1992-03-22', 'carlosgonzalez', 'ejemplo3@usb.ve', 'auth0|660977ba9bdf9cea36fd84ee'),
('Ana', 'Martínez', '1988-04-30', 'anamartinez', 'ejemplo4@usb.ve', 'auth0|660977cf5a313d868b92e2b0'),
('Luis', 'Rodríguez', '1993-05-05', 'luisrodriguez', 'ejemplo5@usb.ve', 'auth0|660977e6027024e8fdf52fbc'),
('Carmen', 'García', '1991-06-17', 'carmengarcia', 'ejemplo6@usb.ve', 'auth0|660978f4154c2ff59d88af48'),
('Fernando', 'Hernández', '2009-07-24', 'fernandohernandez', 'ejemplo7@usb.ve', 'auth0|6609790e154c2ff59d88af54'),
('Laura', 'Sánchez', '1995-08-09', 'laurasanchez', 'ejemplo8@usb.ve', 'auth0|660979317e87f7a8c0af241d'),
('Sergio', 'Ramírez', '2005-09-15', 'sergioramirez', 'ejemplo9@usb.ve', 'auth0|6609794d027024e8fdf5309e'),
('Santoigo', 'fififufi', '2000-05-16', 'santoigo', 'ejemplo10@usb.ve', 'auth0|6609799a5a313d868b92e3ac'),
('Andrea', 'Torres', '1994-10-21', 'andreatorres', 'ejemplo11@usb.ve', 'auth0|660979b7027024e8fdf530e3'),
('Jonathan', 'Melian', '1991-03-26', 'jmelian', 'jonathanmelian@gmail.com', 'google-oauth2|110893135691706290122');

INSERT INTO credit_card(user_id,expiration_date,card_number,cardholder_name,bank,card_type) VALUES
    (2,'1990-01-01',123456789,'Maria Lopez','Banco','VISA');

INSERT INTO purchase(user_id,packets_purchased,base_amount,credit_card_id) VALUES
    (2,2,10,1);