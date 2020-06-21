INSERT INTO authority (name)
VALUES ('ROLE_ADMIN');
INSERT INTO authority (name)
VALUES ('ROLE_AGENT');
INSERT INTO authority (name)
VALUES ('ROLE_USER');

INSERT INTO user(id, email, first_name, last_name, password)
VALUES (1, 'msm@msm.com', 'msm', 'msm', '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS');
INSERT INTO user(id, email, first_name, last_name, password)
VALUES (2, 'miroslav@gmail.com', 'Miroslav', 'Tomic', '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS');
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
VALUES (21, 'Proche');

INSERT INTO price_list(id, creation_date, price_per_day, price_per_km, price_per_cwd, publisher_user_id)
VALUES (1, '20.04.2020', 20, 10, 0, 3);
INSERT INTO price_list(id, creation_date, price_per_day, price_per_km, price_per_cwd, publisher_user_id)
VALUES (2, '25.05.2020', 20, 10, 0, 2);
INSERT INTO price_list(id, creation_date, price_per_day, price_per_km, price_per_cwd, publisher_user_id)
VALUES (3, '24.04.2020', 20, 10, 0, 4);

INSERT INTO car (id, android_flag, car_manufacturer, car_model, car_type, cdw, children_seat_num, fuel_type, gearbox_type, mileage, token, year)
VALUES (1, false, 'Fiat', '500', 'Limuzina', false, 2, 'Dizel', 'Automatski', 120, null, '2020-05-20');
INSERT INTO car (id, android_flag, car_manufacturer, car_model, car_type, cdw, children_seat_num, fuel_type, gearbox_type, mileage, token, year)
VALUES (2, false, 'Mercedes-Benz', 'C-klasa', 'Limuzina', false, 2, 'Dizel', 'Automatski', 200, null, '2020-05-20');
INSERT INTO car (id, android_flag, car_manufacturer, car_model, car_type, cdw, children_seat_num, fuel_type, gearbox_type, mileage, token, year)
VALUES (3, false, 'Audi', 'A3', 'Limuzina', false, 3, 'Dizel', 'Automatski', 125, null, '2017-05-20');
INSERT INTO car (id, android_flag, car_manufacturer, car_model, car_type, cdw, children_seat_num, fuel_type, gearbox_type, mileage, token, year)
VALUES (4, false, 'BMW', '320d', 'Limuzina', true, 3, 'Benzin', 'Manuelni', 250, null, '2019-02-20');

INSERT INTO ad(id, cover_photo, deleted, distance_limit, distance_limit_flag, enabled, location, name, publish_date, rating_count, rating_num, rent_cnt, price_list_id, publisher_user_id)
VALUES (1, 'slika1', false, 20, 'LIMITED', true, 'Beograd', 'Oglas za autic fiat', '20.04.2020.', 1, 8, 1, 1, 3);
INSERT INTO ad(id, cover_photo, deleted, distance_limit, distance_limit_flag, enabled, location, name, publish_date, rating_count, rating_num, rent_cnt, price_list_id, publisher_user_id)
VALUES (2, 'slika6', false, 25, 'LIMITED', true, 'Zrenjanin', 'Oglas za automobil mercedes', '20.04.2020.', 3, 30, 3, 1, 2);
INSERT INTO ad(id, cover_photo, deleted, distance_limit, distance_limit_flag, enabled, location, name, publish_date, rating_count, rating_num, rent_cnt, price_list_id, publisher_user_id)
VALUES (3, 'slika9', false, 200, 'LIMITED', true, 'Beograd', 'Oglas za audi', '20.05.2020.', 3, 15, 3, 1, 4);
INSERT INTO ad(id, cover_photo, deleted, distance_limit, distance_limit_flag, enabled, location, name, publish_date, rating_count, rating_num, rent_cnt, price_list_id, publisher_user_id)
VALUES (4, 'slika16', false, 100, 'LIMITED', true, 'Novi Sad', 'Oglas za automobil BMW', '29.03.2020.', 2, 17, 2, 1, 4);

INSERT INTO ad_car(ad_id, car_id) VALUES (1,1);
INSERT INTO ad_car(ad_id, car_id) VALUES (2,2);
INSERT INTO ad_car(ad_id, car_id) VALUES (3,3);
INSERT INTO ad_car(ad_id, car_id) VALUES (4,4);

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

INSERT INTO car_calendar_term(start_date, end_date, ad_id)
VALUES ('2020.06.02.', '2020.09.02', 1);
INSERT INTO car_calendar_term(start_date, end_date, ad_id)
VALUES ('2020.06.03.', '2020.06.08.', 2);
INSERT INTO car_calendar_term(start_date, end_date, ad_id)
VALUES ('2020.07.02.', '2020.07.29', 3);
INSERT INTO car_calendar_term(start_date, end_date, ad_id)
VALUES ('2020.06.08.', '2020.08.08.', 4);

INSERT INTO image(id, name, ad_id)
VALUES (1, 'slika1', 1);
INSERT INTO image(id, name, ad_id)
VALUES (2, 'slika2', 1);
INSERT INTO image(id, name, ad_id)
VALUES (3, 'slika3', 1);
INSERT INTO image(id, name, ad_id)
VALUES (4, 'slika4', 1);
INSERT INTO image(id, name, ad_id)
VALUES (5, 'slika5', 2);
INSERT INTO image(id, name, ad_id)
VALUES (6, 'slika6', 2);
INSERT INTO image(id, name, ad_id)
VALUES (7, 'slika7', 2);
INSERT INTO image(id, name, ad_id)
VALUES (8, 'slika8', 2);
INSERT INTO image(id, name, ad_id)
VALUES (9, 'slika9', 3);
INSERT INTO image(id, name, ad_id)
VALUES (10, 'slika10', 3);
INSERT INTO image(id, name, ad_id)
VALUES (11, 'slika11', 3);
INSERT INTO image(id, name, ad_id)
VALUES (12, 'slika12', 3);
INSERT INTO image(id, name, ad_id)
VALUES (13, 'slika13', 4);
INSERT INTO image(id, name, ad_id)
VALUES (14, 'slika14', 4);
INSERT INTO image(id, name, ad_id)
VALUES (15, 'slika15', 4);
INSERT INTO image(id, name, ad_id)
VALUES (16, 'slika16', 4);
