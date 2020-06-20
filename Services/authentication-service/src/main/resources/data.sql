INSERT INTO authority (name)
VALUES ('ROLE_ADMIN');
INSERT INTO authority (name)
VALUES ('ROLE_AGENT');
INSERT INTO authority (name)
VALUES ('ROLE_USER');

INSERT INTO user(id, email, first_name, last_name, password, deleted)
VALUES (1, 'msm@msm.com', 'msm', 'msm', '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS', false);
INSERT INTO user(id, email, first_name, last_name, password, deleted)
VALUES (2, 'miroslav@gmail.com', 'Miroslav', 'Tomic', '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS', false);
INSERT INTO user(id, email, first_name, last_name, password, deleted)
VALUES (3, 'svetlana@gmail.com', 'Svetlana', 'Antesevic', '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS', false);
INSERT INTO user(id, email, first_name, last_name, password, deleted)
VALUES (4, 'magdalena@gmail.com', 'Magdalena', 'Lakic', '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS', false);
INSERT INTO user(id, email, first_name, last_name, password, deleted)
VALUES (5, 'end@end.com', 'Krajnji', 'Korisnik', '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS', false);

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

INSERT INTO agent(id)
VALUES (1);

INSERT INTO end_user(id, ad_limit_num, canceled_cnt, enabled, obliged)
VALUES (5, 3, 0, true, false);
