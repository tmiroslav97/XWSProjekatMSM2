
INSERT INTO car (id, android_flag, car_manufacturer, car_model, car_type, cdw, children_seat_num, fuel_type, gearbox_type, mileage, token, year)
VALUES (1, false, 'Fiat', '500', 'Limuzina', false, 2, 'Dizel', 'Automatski', 120, null, '2020-05-20');
INSERT INTO car (id, android_flag, car_manufacturer, car_model, car_type, cdw, children_seat_num, fuel_type, gearbox_type, mileage, token, year)
VALUES (2, false, 'Mercedes-Benz', 'C-klasa', 'Limuzina', false, 2, 'Dizel', 'Automatski', 200, null, '2020-05-20');
INSERT INTO car (id, android_flag, car_manufacturer, car_model, car_type, cdw, children_seat_num, fuel_type, gearbox_type, mileage, token, year)
VALUES (3, false, 'Audi', 'A3', 'Limuzina', false, 3, 'Dizel', 'Automatski', 125, null, '2017-05-20');
INSERT INTO car (id, android_flag, car_manufacturer, car_model, car_type, cdw, children_seat_num, fuel_type, gearbox_type, mileage, token, year)
VALUES (4, false, 'BMW', '320d', 'Limuzina', true, 3, 'Benzin', 'Manuelni', 200, null, '2019-02-20');

INSERT INTO ad(id, cover_photo, deleted, distance_limit, distance_limit_flag, enabled, location, name, publish_date, rating_count, rating_num, rent_cnt, price_list, publisher_user)
VALUES (1, 'slika1', false, 20, 'LIMITED', true, 'Beograd', 'Oglas za autic fiat', '2020.04.20.', 0, 0, 5, 3, 3);
INSERT INTO ad(id, cover_photo, deleted, distance_limit, distance_limit_flag, enabled, location, name, publish_date, rating_count, rating_num, rent_cnt, price_list, publisher_user)
VALUES (2, 'slika6', false, 25, 'LIMITED', true, 'Zrenjanin', 'Oglas za automobil mercedes', '2020.04.20.', 0, 0, 1100, 1, 2);
INSERT INTO ad(id, cover_photo, deleted, distance_limit, distance_limit_flag, enabled, location, name, publish_date, rating_count, rating_num, rent_cnt, price_list, publisher_user)
VALUES (3, 'slika9', false, 200, 'LIMITED', true, 'Beograd', 'Oglas za audi', '2020.05.22.', 3, 2, 10, 2, 4);
INSERT INTO ad(id, cover_photo, deleted, distance_limit, distance_limit_flag, enabled, location, name, publish_date, rating_count, rating_num, rent_cnt, price_list, publisher_user)
VALUES (4, 'slika16', false, 100, 'LIMITED', true, 'Novi Sad', 'Oglas za automobil BMW', '2020.03.29', 0, 0, 1100, 2, 4);

INSERT INTO ad_car(ad_id, car_id) VALUES (1,1);
INSERT INTO ad_car(ad_id, car_id) VALUES (2,2);
INSERT INTO ad_car(ad_id, car_id) VALUES (3,3);
INSERT INTO ad_car(ad_id, car_id) VALUES (4,4);

INSERT INTO car_calendar_term(start_date, end_date, ad_id)
VALUES ('2020.06.02.', '2020.09.02', 1);
INSERT INTO car_calendar_term(start_date, end_date, ad_id)
VALUES ('2020.06.03.', '2020.06.08.', 2);
INSERT INTO car_calendar_term(start_date, end_date, ad_id)
VALUES ('2020.07.02.', '2020.07.29', 3);
INSERT INTO car_calendar_term(start_date, end_date, ad_id)
VALUES ('2020.06.08.', '2020.08.08.', 4);

-- INSERT INTO image(id, name, ad_id)
-- VALUES (1, 'auto1', 1);
-- INSERT INTO image(id, name, ad_id)
-- VALUES (2, 'auto2', 1);
-- INSERT INTO image(id, name, ad_id)
-- VALUES (3, 'auto3', 1);
-- INSERT INTO image(id, name, ad_id)
-- VALUES (4, 'auto4', 1);
INSERT INTO image(id, name, ad_id)
VALUES (1, 'slika0', 1);
INSERT INTO image(id, name, ad_id)
VALUES (2, 'slika1', 1);
INSERT INTO image(id, name, ad_id)
VALUES (3, 'slika2', 1);
INSERT INTO image(id, name, ad_id)
VALUES (4, 'slika3', 1);
INSERT INTO image(id, name, ad_id)
VALUES (5, 'slika4', 2);
INSERT INTO image(id, name, ad_id)
VALUES (6, 'slika5', 2);
INSERT INTO image(id, name, ad_id)
VALUES (7, 'slika6', 2);
INSERT INTO image(id, name, ad_id)
VALUES (8, 'slika7', 2);
INSERT INTO image(id, name, ad_id)
VALUES (9, 'slika8', 2);
INSERT INTO image(id, name, ad_id)
VALUES (10, 'slika9', 3);
INSERT INTO image(id, name, ad_id)
VALUES (11, 'slika10', 3);
INSERT INTO image(id, name, ad_id)
VALUES (12, 'slika11', 3);
INSERT INTO image(id, name, ad_id)
VALUES (13, 'slika12', 3);
INSERT INTO image(id, name, ad_id)
VALUES (14, 'slika13', 3);
INSERT INTO image(id, name, ad_id)
VALUES (15, 'slika14', 3);
INSERT INTO image(id, name, ad_id)
VALUES (16, 'slika15', 4);
INSERT INTO image(id, name, ad_id)
VALUES (17, 'slika16', 4);
INSERT INTO image(id, name, ad_id)
VALUES (18, 'slika17', 4);
INSERT INTO image(id, name, ad_id)
VALUES (19, 'slika18', 4);
INSERT INTO image(id, name, ad_id)
VALUES (20, 'slika19', 4);