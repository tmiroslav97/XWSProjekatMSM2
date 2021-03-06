INSERT INTO car (id, car_manufacturer, car_model, car_type, distance_limit, distance_limit_flag, cdw, children_seat_num, fuel_type, gearbox_type, mileage, year)
VALUES (1, 'Fiat', '500', 'Limuzina', 400, 'LIMITED', false, 2, 'Dizel', 'Automatik', 120000, STR_TO_DATE('20-05-2020', '%d-%m-%Y'));
INSERT INTO car (id, car_manufacturer, car_model, car_type, distance_limit, distance_limit_flag, cdw, children_seat_num, fuel_type, gearbox_type, mileage, year)
VALUES (2, 'Volvo', 'xc90', 'Limuzina', 500, 'LIMITED', false, 3, 'Dizel', 'Automatik', 180000, STR_TO_DATE('20-05-2019', '%d-%m-%Y'));
INSERT INTO car (id, car_manufacturer, car_model, car_type, distance_limit, distance_limit_flag, cdw, children_seat_num, fuel_type, gearbox_type, mileage, year)
VALUES (3, 'Audi', 'A3', 'Limuzina', null, 'UNLIMITED', false, 3, 'Dizel', 'Automatik', 125000, STR_TO_DATE('20-05-2017', '%d-%m-%Y'));
INSERT INTO car (id, car_manufacturer, car_model, car_type, distance_limit, distance_limit_flag, cdw, children_seat_num, fuel_type, gearbox_type, mileage, year)
VALUES (4, 'BMW', '320d', 'Limuzina', null, 'UNLIMITED', false, 3, 'Benzin', 'Manuelni', 250000, STR_TO_DATE('20-05-2019', '%d-%m-%Y'));
INSERT INTO car (id, car_manufacturer, car_model, car_type, distance_limit, distance_limit_flag, cdw, children_seat_num, fuel_type, gearbox_type, mileage, year)
VALUES (5, 'Mini', 'Moris', 'Limuzina', null, 'UNLIMITED', false, 3, 'Dizel', 'Automatik', 200000, STR_TO_DATE('20-05-2019', '%d-%m-%Y'));

INSERT INTO ad_search_db.ad(id, cover_photo, deleted, enabled, location, name, publish_date, rating_count, rating_num, rent_cnt, price_per_day, publisher_user)
VALUES (1, 'slika1.jpg', false, true, 'Beograd', 'Oglas za autic fiat', STR_TO_DATE('20-04-2020', '%d-%m-%Y'), 0, 0, 5, 2500.0, 3);
INSERT INTO ad_search_db.ad(id, cover_photo, deleted, enabled, location, name, publish_date, rating_count, rating_num, rent_cnt, price_per_day, publisher_user)
VALUES (2, 'slika17.jfif', false, true, 'Zrenjanin', 'Oglas za automobil Volvo !', STR_TO_DATE('29-03-2020', '%d-%m-%Y'), 2, 19, 2, 2000.0, 4);
INSERT INTO ad_search_db.ad(id, cover_photo, deleted, enabled, location, name, publish_date, rating_count, rating_num, rent_cnt, price_per_day, publisher_user)
VALUES (3, 'slika5.jpg', false, true, 'Beograd', 'Oglas za audi', STR_TO_DATE('20-05-2020', '%d-%m-%Y'), 8, 40, 10, 2550.0, 3);
INSERT INTO ad_search_db.ad(id, cover_photo, deleted, enabled, location, name, publish_date, rating_count, rating_num, rent_cnt, price_per_day, publisher_user)
VALUES (4, 'slika9.jpg', false, true, 'Novi Sad', 'Oglas za automobil BMW', STR_TO_DATE('29-03-2020', '%d-%m-%Y'), 5, 21, 5, 3500.0, 4);
INSERT INTO ad_search_db.ad(id, cover_photo, deleted, enabled, location, name, publish_date, rating_count, rating_num, rent_cnt, price_per_day, publisher_user)
VALUES (5, 'slika14.jpg', false, true, 'Novi Sad', 'Oglas za automobil Mini Moris !', STR_TO_DATE('29-03-2020', '%d-%m-%Y'), 2, 19, 2, 1800.0, 4);

INSERT INTO ad_car(ad_id, car_id)
VALUES (1, 1);
INSERT INTO ad_car(ad_id, car_id)
VALUES (2, 2);
INSERT INTO ad_car(ad_id, car_id)
VALUES (3, 3);
INSERT INTO ad_car(ad_id, car_id)
VALUES (4, 4);
INSERT INTO ad_car(ad_id, car_id)
VALUES (5, 5);

INSERT INTO car_calendar_term(id, start_date, end_date, ad_id)
VALUES (1, STR_TO_DATE('09:00 06-02-2020', '%H:%i %d-%m-%Y'), STR_TO_DATE('09:00 02-09-2020', '%H:%i %d-%m-%Y'), 1);
INSERT INTO car_calendar_term(id, start_date, end_date, ad_id)
VALUES (2, STR_TO_DATE('10:00 01-07-2020', '%H:%i %d-%m-%Y'), STR_TO_DATE('09:00 28-08-2020', '%H:%i %d-%m-%Y'), 2);
INSERT INTO car_calendar_term(id, start_date, end_date, ad_id)
VALUES (3, STR_TO_DATE('09:00 02-07-2020', '%H:%i %d-%m-%Y'), STR_TO_DATE('09:00 29-07-2020', '%H:%i %d-%m-%Y'), 3);
INSERT INTO car_calendar_term(id, start_date, end_date, ad_id)
VALUES (4, STR_TO_DATE('09:00 06-08-2020', '%H:%i %d-%m-%Y'), STR_TO_DATE('09:00 08-08-2020', '%H:%i %d-%m-%Y'), 4);
INSERT INTO car_calendar_term(id, start_date, end_date, ad_id)
VALUES (5, STR_TO_DATE('09:00 06-07-2020', '%H:%i %d-%m-%Y'), STR_TO_DATE('09:00 18-08-2020', '%H:%i %d-%m-%Y'), 5);

INSERT INTO image(id, name, ad_id)
VALUES (1, 'slika1.jpg', 1);
INSERT INTO image(id, name, ad_id)
VALUES (2, 'slika2.jpg', 1);
INSERT INTO image(id, name, ad_id)
VALUES (3, 'slika3.png', 1);
INSERT INTO image(id, name, ad_id)
VALUES (4, 'slika4.jpg', 1);

INSERT INTO image(id, name, ad_id)
VALUES (5, 'slika17.jfif', 2);
INSERT INTO image(id, name, ad_id)
VALUES (6, 'slika18.jpg', 2);
INSERT INTO image(id, name, ad_id)
VALUES (7, 'slika19.png', 2);
INSERT INTO image(id, name, ad_id)
VALUES (8, 'slika20.jpg', 2);

INSERT INTO image(id, name, ad_id)
VALUES (9, 'slika5.jpg', 3);
INSERT INTO image(id, name, ad_id)
VALUES (10, 'slika6.jpg', 3);
INSERT INTO image(id, name, ad_id)
VALUES (11, 'slika7.jpg', 3);
INSERT INTO image(id, name, ad_id)
VALUES (12, 'slika8.jpg', 3);

INSERT INTO image(id, name, ad_id)
VALUES (13, 'slika9.jpg', 4);
INSERT INTO image(id, name, ad_id)
VALUES (14, 'slika10.jpg', 4);
INSERT INTO image(id, name, ad_id)
VALUES (15, 'slika11.jpg', 4);
INSERT INTO image(id, name, ad_id)
VALUES (16, 'slika12.jpg', 4);

INSERT INTO image(id, name, ad_id)
VALUES (17, 'slika13.jpg', 5);
INSERT INTO image(id, name, ad_id)
VALUES (18, 'slika14.jpg', 5);
INSERT INTO image(id, name, ad_id)
VALUES (19, 'slika15.jpg', 5);
INSERT INTO image(id, name, ad_id)
VALUES (20, 'slika16.jfif', 5);