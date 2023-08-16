CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO public.person (age, createdat, createdperson, email, name,
                           password, phonenumber, removedat, removedperson, role)
VALUES (42, '2014-04-22 00:00:00.000000', '2014-04-22 00:00:00', 'admin@mail.ru', 'Anton', crypt ('2222', gen_salt('bf', 12) ) , '+7895434534', '2014-04-22 00:00:00.000000', '2014-04-22 00:00:00', 'admin');

