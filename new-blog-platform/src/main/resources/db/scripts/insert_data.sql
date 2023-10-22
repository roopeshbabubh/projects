-- Insert roles
INSERT INTO roles (role_id, role_name)VALUES (1, 'ROLE_ADMIN')
    ON DUPLICATE KEY UPDATE role_name = VALUES(role_name);
INSERT INTO roles (role_id, role_name) VALUES (2, 'ROLE_USER')
    ON DUPLICATE KEY UPDATE role_name = VALUES(role_name);


-- Insert tags
INSERT INTO tags (tag_id, category) VALUES (1, 'Technology')
    ON DUPLICATE KEY UPDATE category = VALUES(category);

INSERT INTO tags (tag_id, category) VALUES (2, 'Travel')
    ON DUPLICATE KEY UPDATE category = VALUES(category);

INSERT INTO tags (tag_id, category) VALUES (3, 'Food')
    ON DUPLICATE KEY UPDATE category = VALUES(category);

INSERT INTO tags (tag_id, category) VALUES (4, 'Fashion')
    ON DUPLICATE KEY UPDATE category = VALUES(category);

INSERT INTO tags (tag_id, category) VALUES (5, 'Sports')
    ON DUPLICATE KEY UPDATE category = VALUES(category);

INSERT INTO tags (tag_id, category) VALUES (6, 'Science')
    ON DUPLICATE KEY UPDATE category = VALUES(category);

INSERT INTO tags (tag_id, category) VALUES (7, 'Music')
    ON DUPLICATE KEY UPDATE category = VALUES(category);

INSERT INTO tags (tag_id, category) VALUES (8, 'Health')
    ON DUPLICATE KEY UPDATE category = VALUES(category);

INSERT INTO tags (tag_id, category) VALUES (9, 'Books')
    ON DUPLICATE KEY UPDATE category = VALUES(category);

INSERT INTO tags (tag_id, category) VALUES (10, 'Movies')
    ON DUPLICATE KEY UPDATE category = VALUES(category);

INSERT INTO tags (tag_id, category) VALUES (11, 'Fitness')
    ON DUPLICATE KEY UPDATE category = VALUES(category);

INSERT INTO tags (tag_id, category) VALUES (12, 'Gaming')
    ON DUPLICATE KEY UPDATE category = VALUES(category);

INSERT INTO tags (tag_id, category) VALUES (13, 'Art')
    ON DUPLICATE KEY UPDATE category = VALUES(category);

INSERT INTO tags (tag_id, category) VALUES (14, 'History')
    ON DUPLICATE KEY UPDATE category = VALUES(category);

INSERT INTO tags (tag_id, category) VALUES (15, 'Nature')
    ON DUPLICATE KEY UPDATE category = VALUES(category);
