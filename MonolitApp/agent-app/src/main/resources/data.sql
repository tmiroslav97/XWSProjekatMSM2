INSERT INTO authority (name)
VALUES ('ROLE_ADMIN');
INSERT INTO authority (name)
VALUES ('ROLE_AGENT');
INSERT INTO authority (name)
VALUES ('ROLE_USER');

INSERT INTO user(id, email, first_name, last_name, password)
VALUES (1, 'msm@msm.com', 'msm', 'msm', '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS');
INSERT INTO user(id, email, first_name, last_name, password)
VALUES (2, 'tomic.miroslav97@gmail.com', 'Miroslav', 'Tomic', '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS');
INSERT INTO user(id, email, first_name, last_name, password)
VALUES (3, 'svetlana@gmail.com', 'Svetlana', 'Antesevic', '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS');
INSERT INTO user(id, email, first_name, last_name, password)
VALUES (4, 'magdalena@gmail.com', 'Magdalena', 'Lakic', '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS');
INSERT INTO user(id, email, first_name, last_name, password)
VALUES (5, 'end@end.com', 'Krajnji', 'Korisnik', '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS');

INSERT INTO user_authority(user_id, authority_id)
VALUES (1,1);
INSERT INTO user_authority(user_id, authority_id)
VALUES (2,2);
INSERT INTO user_authority(user_id, authority_id)
VALUES (3,2);
INSERT INTO user_authority(user_id, authority_id)
VALUES (4,2);
INSERT INTO user_authority(user_id, authority_id)
VALUES (5,3);

INSERT INTO publisher_user(id, deleted)
VALUES (2, false);

INSERT INTO agent(id)
VALUES (2);

INSERT INTO publisher_user(id, deleted)
VALUES (3, false);

INSERT INTO agent(id)
VALUES (3);

INSERT INTO publisher_user(id, deleted)
VALUES (4, false);

INSERT INTO agent(id)
VALUES (4);

INSERT INTO publisher_user(id, deleted)
VALUES (5, false);

INSERT INTO end_user(id, ad_limit_num, canceled_cnt, enabled, obliged)
VALUES (5, 3, 0, true, false);

INSERT INTO price_list(id, creation_date, price_per_day, price_per_km, price_per_cwd, publisher_user_id)
VALUES (1, STR_TO_DATE('20-04-2020', '%d-%m-%Y'), 20, 100, 0, 3);
INSERT INTO price_list(id, creation_date, price_per_day, price_per_km, price_per_cwd, publisher_user_id)
VALUES (2, STR_TO_DATE('25-05-2020', '%d-%m-%Y'), 4000, 200, 300, 2);
INSERT INTO price_list(id, creation_date, price_per_day, price_per_km, price_per_cwd, publisher_user_id)
VALUES (3, STR_TO_DATE('24-04-2020', '%d-%m-%Y'), 20, 100, 220, 4);

INSERT INTO discount_list(id, discount, day_num, agent_id)
VALUES (1, 22, 30, 2);

INSERT INTO car (id, android_flag, car_manufacturer, car_model, car_type, distance_limit, distance_limit_flag, cdw, children_seat_num, fuel_type, gearbox_type, mileage, token, year)
VALUES (1, false, 'Audi', 'A8', 'Limuzina',  1000, 'LIMITED', false, 2, 'Dizel', 'Automatik', 100000, null, STR_TO_DATE('20-05-2020', '%d-%m-%Y'));
INSERT INTO car (id, android_flag, car_manufacturer, car_model, car_type, distance_limit, distance_limit_flag, cdw, children_seat_num, fuel_type, gearbox_type, mileage, token, year)
VALUES (2, false, 'BMW', '320i', 'Limuzina', null, 'UNLIMITED', true, 3, 'Benzin', 'Manuelni', 150000, null, STR_TO_DATE('20-05-2020', '%d-%m-%Y'));
INSERT INTO car (id, android_flag, car_manufacturer, car_model, car_type, distance_limit, distance_limit_flag, cdw, children_seat_num, fuel_type, gearbox_type, mileage, token, year)
VALUES (3, true, 'Mercedes', 'ML', 'Limuzina', null, 'UNLIMITED', true, 3, 'Dizel', 'Automatik', 120000, 'asdasda34fsdfsd', STR_TO_DATE('20-05-2015', '%d-%m-%Y'));

INSERT INTO ad(id, cover_photo, deleted, enabled, location, name, publish_date, rating_count, rating_num, rent_cnt, price_list_id, publisher_user_id)
VALUES (1, 'slika7.jpg', false, true, 'Zrenjanin', 'Oglas za automobil audi (A8)', STR_TO_DATE('20-04-2020', '%d-%m-%Y'), 6, 31, 8, 2, 2);
INSERT INTO ad(id, cover_photo, deleted, enabled, location, name, publish_date, rating_count, rating_num, rent_cnt, price_list_id, publisher_user_id)
VALUES (2, 'slika1.jpg', false, true, 'Novi Sad', 'Oglas za automobil BMW', STR_TO_DATE('20-04-2020', '%d-%m-%Y'), 2, 17, 6, 2, 2);
INSERT INTO ad(id, cover_photo, deleted, enabled, location, name, publish_date, rating_count, rating_num, rent_cnt, price_list_id, publisher_user_id)
VALUES (3, 'slika9.jpg', false, true, 'Zrenjanin', 'Oglas za automobil Mercedes!', STR_TO_DATE('20-04-2020', '%d-%m-%Y'), 3, 18, 3, 2, 2);

INSERT INTO ad_car(ad_id, car_id) VALUES (1,1);
INSERT INTO ad_car(ad_id, car_id) VALUES (2,2);
INSERT INTO ad_car(ad_id, car_id) VALUES (3,3);

INSERT INTO car_calendar_term(start_date, end_date, ad_id)
VALUES (STR_TO_DATE('09:00 06-02-2020', '%H:%i %d-%m-%Y'), STR_TO_DATE('09:00 09-02-2020', '%H:%i %d-%m-%Y'), 1);
INSERT INTO car_calendar_term(start_date, end_date, ad_id)
VALUES (STR_TO_DATE('09:00 06-03-2020', '%H:%i %d-%m-%Y'), STR_TO_DATE('09:00 06-08-2020', '%H:%i %d-%m-%Y'), 2);
INSERT INTO car_calendar_term(start_date, end_date, ad_id)
VALUES (STR_TO_DATE('09:00 07-02-2020', '%H:%i %d-%m-%Y'), STR_TO_DATE('09:00 29-07-2020', '%H:%i %d-%m-%Y'), 3);

INSERT INTO image(id, name, ad_id)
VALUES (1, 'slika5.jpg', 1);
INSERT INTO image(id, name, ad_id)
VALUES (2, 'slika6.jfif', 1);
INSERT INTO image(id, name, ad_id)
VALUES (3, 'slika7.jpg', 1);
INSERT INTO image(id, name, ad_id)
VALUES (4, 'slika8.jpg', 1);

INSERT INTO image(id, name, ad_id)
VALUES (5, 'slika1.jpg', 2);
INSERT INTO image(id, name, ad_id)
VALUES (6, 'slika2.jfif', 2);
INSERT INTO image(id, name, ad_id)
VALUES (7, 'slika3.jpg', 2);
INSERT INTO image(id, name, ad_id)
VALUES (8, 'slika4.jfif', 2);

INSERT INTO image(id, name, ad_id)
VALUES (9, 'slika9.jpg', 3);
INSERT INTO image(id, name, ad_id)
VALUES (10, 'slika10.jpg', 3);
INSERT INTO image(id, name, ad_id)
VALUES (11, 'slika11.jpg', 3);
INSERT INTO image(id, name, ad_id)
VALUES (12, 'slika12.jpg', 3);

--car manufacturers
INSERT INTO car_manufacturer(id, name)
VALUES (1, 'BMW');
INSERT INTO car_manufacturer(id, name)
VALUES (2, 'Mercedes');
INSERT INTO car_manufacturer(id, name)
VALUES (3, 'Audi');
INSERT INTO car_manufacturer(id, name)
VALUES (4, 'Alfa Romeo');
INSERT INTO car_manufacturer(id, name)
VALUES (5, 'Fiat');
INSERT INTO car_manufacturer(id, name)
VALUES (6, 'Mini');
INSERT INTO car_manufacturer(id, name)
VALUES (7, 'Renault');
INSERT INTO car_manufacturer(id, name)
VALUES (8, 'Peu Geot');
INSERT INTO car_manufacturer(id, name)
VALUES (9, 'Volvo');
INSERT INTO car_manufacturer(id, name)
VALUES (10, 'Range Rover');
INSERT INTO car_manufacturer(id, name)
VALUES (11, 'Land Rover');
INSERT INTO car_manufacturer(id, name)
VALUES (12, 'Citroen');
INSERT INTO car_manufacturer(id, name)
VALUES (13, 'Volkswagen');
INSERT INTO car_manufacturer(id, name)
VALUES (14, 'Honda');
INSERT INTO car_manufacturer(id, name)
VALUES (15, 'Hyundai');
INSERT INTO car_manufacturer(id, name)
VALUES (16, 'Dodge');
INSERT INTO car_manufacturer(id, name)
VALUES (17, 'Ford');
INSERT INTO car_manufacturer(id, name)
VALUES (18, 'Toyota');
INSERT INTO car_manufacturer(id, name)
VALUES (19, 'Seat');
INSERT INTO car_manufacturer(id, name)
VALUES (20, 'Ferari');
INSERT INTO car_manufacturer(id, name)
VALUES (21, 'Porsche');

--car models
INSERT INTO car_model(id, name, car_manufacturer_id)
VALUES (1, 'A4', 3);
INSERT INTO car_model(id, name, car_manufacturer_id)
VALUES (2, 'A6', 3);
INSERT INTO car_model(id, name, car_manufacturer_id)
VALUES (3, 'A8', 3);
INSERT INTO car_model(id, name, car_manufacturer_id)
VALUES (4, 'A3', 3);
INSERT INTO car_model(id, name, car_manufacturer_id)
VALUES (5, '320d', 1);
INSERT INTO car_model(id, name, car_manufacturer_id)
VALUES (6, '320i', 1);
INSERT INTO car_model(id, name, car_manufacturer_id)
VALUES (7, '318d', 1);
INSERT INTO car_model(id, name, car_manufacturer_id)
VALUES (8, '318i', 1);
INSERT INTO car_model(id, name, car_manufacturer_id)
VALUES (9, 'ML', 2);
INSERT INTO car_model(id, name, car_manufacturer_id)
VALUES (10, 'Moris', 6);
INSERT INTO car_model(id, name, car_manufacturer_id)
VALUES (11, 'xc90', 9);

--car types
INSERT INTO car_type(id, name)
VALUES (1, 'Limuzina');
INSERT INTO car_type(id, name)
VALUES (2, 'Karavan');
INSERT INTO car_type(id, name)
VALUES (3, 'Coupe');
INSERT INTO car_type(id, name)
VALUES (4, 'SUV');

--fuel types
INSERT INTO fuel_type(id, name)
VALUES (1, 'Benzin');
INSERT INTO fuel_type(id, name)
VALUES (2, 'Dizel');
INSERT INTO fuel_type(id, name)
VALUES (3, 'Elektro');
INSERT INTO fuel_type(id, name)
VALUES (4, 'Hibrid');

--gearbox types
INSERT INTO gearbox_type(id, name)
VALUES (1, 'Manuelni');
INSERT INTO gearbox_type(id, name)
VALUES (2, 'Automatik');
INSERT INTO gearbox_type(id, name)
VALUES (3, 'Poluautomatik');
INSERT INTO gearbox_type(id, name)
VALUES (4, 'DSG');
INSERT INTO gearbox_type(id, name)
VALUES (5, 'Tiptronik');