use adlister_db;

INSERT INTO ads (title, description, user_id, category_id)
VALUES ('Super Nintendo', 'Get your game on with this old-school classic!', '2', '1'),
('PlayStation 1', 'This is a slightly used playstation', '2', '1'),
('Junior Java Developer Position', 'Minimum 7 years of experience required. You will be working in the scripting language for Java, JavaScript', '2', '2'),
('Javascript Developer Needed', 'Must have strong Java skills', '2', '2');

INSERT INTO categories (title, description)
VALUES
    ('Game System', 'test'),
    ('Job Posting', 'test');