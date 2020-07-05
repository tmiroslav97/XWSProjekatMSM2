INSERT INTO auth_db.authority (name)
VALUES ('ROLE_ADMIN');
INSERT INTO auth_db.authority (name)
VALUES ('ROLE_AGENT');
INSERT INTO auth_db.authority (name)
VALUES ('ROLE_USER');

INSERT INTO auth_db.user(id, email, first_name, last_name, password, local, deleted)
VALUES (1, 'msm@msm.com', 'msm', 'msm', '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS', true, false);
INSERT INTO auth_db.user(id, email, first_name, last_name, password, local, deleted)
VALUES (3, 'svetlana@gmail.com', 'Svetlana', 'Antesevic', '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS', true, false);
INSERT INTO auth_db.user(id, email, first_name, last_name, password, local, deleted)
VALUES (4, 'magdalena@gmail.com', 'Magdalena', 'Lakic', '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS', true, false);
INSERT INTO auth_db.user(id, email, first_name, last_name, password, local, deleted)
VALUES (5, 'end@end.com', 'Krajnji', 'Korisnik', '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS', true, false);

INSERT INTO auth_db.user_authority(user_id, authority_id)
VALUES (1,1);
INSERT INTO auth_db.user_authority(user_id, authority_id)
VALUES (3,2);
INSERT INTO auth_db.user_authority(user_id, authority_id)
VALUES (4,2);
INSERT INTO auth_db.user_authority(user_id, authority_id)
VALUES (5,3);

INSERT INTO auth_db.agent(id)
VALUES (3);
INSERT INTO auth_db.agent(id)
VALUES (4);

INSERT INTO auth_db.end_user(id, ad_limit_num, canceled_cnt, enabled, obliged)
VALUES (5, 3, 0, true, false);
