INSERT INTO carreq_db.ad(id, ad_name)
VALUES (10, 'Audi A8');

INSERT INTO carreq_db.ad(id, ad_name)
VALUES (20, 'Audi A6');

INSERT INTO carreq_db.ad(id, ad_name)
VALUES (30, 'Audi A8');

INSERT INTO carreq_db.ad(id, ad_name)
VALUES (40, 'Audi A6');

INSERT INTO carreq_db.request(id, status, submit_date, end_user, publisher_user, start_date, end_date, bundle)
VALUES (1, 'PENDING', STR_TO_DATE('16.06.2020 21:49:00','%d.%m.%Y %H:%i:%S'), 5, 3, STR_TO_DATE('22.06.2020 10:00:00','%d.%m.%Y %H:%i:%S'), STR_TO_DATE('26.06.2020 10:00:00','%d.%m.%Y %H:%i:%S'), false);

INSERT INTO carreq_db.request_ad(request_id, ad_id)
VALUES (1, 10);

INSERT INTO carreq_db.request(id, status, submit_date, end_user, publisher_user, start_date, end_date, bundle)
VALUES (2, 'PENDING', STR_TO_DATE('16.06.2020 22:49:00','%d.%m.%Y %H:%i:%S'), 5, 3, STR_TO_DATE('22.06.2020 10:00:00','%d.%m.%Y %H:%i:%S'), STR_TO_DATE('26.06.2020 10:00:00','%d.%m.%Y %H:%i:%S'), false);

INSERT INTO carreq_db.request_ad(request_id, ad_id)
VALUES (2, 20);

INSERT INTO carreq_db.request(id, status, submit_date, end_user, publisher_user, start_date, end_date, bundle)
VALUES (3, 'PAID', STR_TO_DATE('16.06.2020 21:49:00','%d.%m.%Y %H:%i:%S'), 5, 3, STR_TO_DATE('22.06.2020 10:00:00','%d.%m.%Y %H:%i:%S'), STR_TO_DATE('26.06.2020 10:00:00','%d.%m.%Y %H:%i:%S'), false);

INSERT INTO carreq_db.request_ad(request_id, ad_id)
VALUES (3, 30);

INSERT INTO carreq_db.request(id, status, submit_date, end_user, publisher_user, start_date, end_date, bundle)
VALUES (4, 'PAID', STR_TO_DATE('16.06.2020 22:49:00','%d.%m.%Y %H:%i:%S'), 5, 3, STR_TO_DATE('22.06.2020 10:00:00','%d.%m.%Y %H:%i:%S'), STR_TO_DATE('26.06.2020 10:00:00','%d.%m.%Y %H:%i:%S'), false);

INSERT INTO carreq_db.request_ad(request_id, ad_id)
VALUES (4, 40);