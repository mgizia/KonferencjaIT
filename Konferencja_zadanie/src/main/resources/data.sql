CREATE TABLE IF NOT EXISTS Plan(id IDENTITY PRIMARY KEY, user VARCHAR(45), lecture VARCHAR(45), deleted BOOLEAN);
DELETE FROM Plan;
INSERT INTO Plan(id, user, lecture,deleted) VALUES (1,  'name','2,1.06.2019r., 12:00-13:45', false );
