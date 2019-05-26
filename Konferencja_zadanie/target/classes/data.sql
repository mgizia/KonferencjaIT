CREATE TABLE IF NOT EXISTS Plan(id IDENTITY PRIMARY KEY, user VARCHAR(45), lecture VARCHAR(45), deleted BOOLEAN);
DELETE FROM Plan;
INSERT INTO Plan(id, user, lecture,deleted) VALUES (1,  'login1','121', false );
INSERT INTO Plan(id, user,lecture,deleted) VALUES (2,  'login2','221', false );
INSERT INTO Plan(id, user,lecture,deleted) VALUES (3, 'login3','122',  false );
