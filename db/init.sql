USE appdb;

-- password is 123
INSERT INTO users (first_name, last_name, email, password) VALUES
    ('admin', 'admin', 'test@admin.gr', '$2a$10$JPxfrcHHh9xRTfq.DKLvgObfp9vpoxDzgeJeegChh9ohP7VR0CBp.');

INSERT INTO users_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u
         JOIN roles r ON r.name = 'ROLE_ADMIN'
WHERE u.email = 'test@admin.gr';

-- Insert the user into the admins table
INSERT INTO admins (id, agency)
SELECT id, 'test'
FROM users
WHERE email = 'test@admin.gr';
