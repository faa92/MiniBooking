INSERT INTO landlord (name, email, password_hash)
VALUES ('Eminem', 'eminem@example.com', 'password123'),
       ('Anthony Kiedis', 'anthony@example.com', 'passwordRHСP'),
       ('Serj Tankian', 'serj@example.com', 'passwordSOAD');



INSERT INTO tenant (name, email, password_hash)
VALUES ('Chester Bennington;', 'сhester@example.com', 'passwordLP'),
       ('Kurt Cobain', 'kurt@example.com', 'passwordNV'),
       ('Boris Brejcha', 'boris@example.com', 'passwordBB');



INSERT INTO rentalAd (title, description, price, created_at, active, landlord_id)
VALUES ('Квартира в центре города', 'Просторная квартира в центре города', 1500.0, NOW(), true, 1),
       ('Дом с видом на озеро', 'Уютный дом с прекрасным видом на озеро', 2500.0, NOW(), true, 2),
       ('Дачный участок', 'Отличное место для отдыха на даче', 1000.0, NOW(), false, 3);



INSERT INTO responseToAd (message, date_from, date_to, created_at, tenant_id, rentalAd_id)
VALUES ('Ваш дом выглядит потрясающе! Я хотел бы его снять.', '2023-09-10', '2023-10-10', NOW(), 1, 1),
       ('Подскажите, какие дополнительные услуги включены в стоимость аренды?', '2023-09-05', '2023-09-20', NOW(), 2,
        2),
       ('Какая сумма залога требуется для аренды вашего дома?', '2023-09-08', '2023-09-30', NOW(), 3, 3);
