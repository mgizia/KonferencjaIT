CREATE TABLE IF NOT EXISTS Plan(id IDENTITY PRIMARY KEY, lecture VARCHAR(45), user VARCHAR(45));
DELETE FROM Plan;
INSERT INTO Plan(id,lecture, user) VALUES (1, '121', 'login1');
INSERT INTO Plan(id,lecture, user) VALUES (2, '221', 'login2');
INSERT INTO Plan(id,lecture, user) VALUES (3, '122', 'login3');
