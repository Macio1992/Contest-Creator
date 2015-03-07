# --- !Ups

INSERT INTO account(
            id, email, first_name, last_name, password, account_type, account_status)
    VALUES (1, 'user@test.pl', 'testName', 'testLastName', '05a671c66aefea124cc08b76ea6d30bb', 'CLIENT', 'A');

INSERT INTO account(
            id, email, first_name, last_name, password, account_type, account_status)
    VALUES (2, 'admin@test.pl', 'admintestName', 'admintestLastName', '05a671c66aefea124cc08b76ea6d30bb', 'ADMIN', 'A');


INSERT INTO admin(id, account_id) VALUES (1, 2);
INSERT INTO client(id, account_id) VALUES (1, 1);


# --- !Downs


DELETE FROM admin WHERE id=1;
DELETE FROM client WHERE id=1;

DELETE FROM account WHERE id=1;
DELETE FROM account WHERE id=2;