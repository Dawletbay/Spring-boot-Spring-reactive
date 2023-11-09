-- ---------------------------------
-- CREATE ROLES for "PUBLIC"."roles"
-- ---------------------------------

INSERT INTO PUBLIC.roles(name)
VALUES ('ROLE_SUPER_ADMIN'),
       ('ROLE_ADMIN');

-- --------------------------------
-- CREATE USER for "PUBLIC"."users"
-- --------------------------------

INSERT INTO PUBLIC.users(id, created_date, first_name, last_name, patronymic, username, password)
VALUES (uuid_generate_v4(), now(), 'Dawletbay', 'Tilepbaev', 'Ong`arbaevich', 'dawletbay_tilepbaev', '$2a$04$9CIjSy9SvyBlHnuNSJxiGuP4Pmh/TytExIEObvmZyz5Vb7sqHKig.'); -- qwerty