-- Insert users
INSERT INTO users (id, username, email) VALUES ('c5d8eb12-5735-4b3e-b45b-2a22d167b697', 'user1', 'user1@example.com');
INSERT INTO users (id, username, email) VALUES ('e8f9a93e-2fd0-4641-b49b-1f087ceddf50', 'user2', 'user2@example.com');

-- Insert tasks
INSERT INTO tasks (id, title, description, status, create_time, update_time, user_id)
VALUES ('b9f1e1cd-dbd9-4b71-a5d4-e19b0c9f72d7', 'Task 1', 'Description of task 1', 'PENDING', '2025-03-16T10:00:00Z', '2025-03-16T10:00:00Z', 'c5d8eb12-5735-4b3e-b45b-2a22d167b697');
INSERT INTO tasks (id, title, description, status, create_time, update_time, user_id)
VALUES ('c8b1e370-8304-4882-bb98-7c5da07cf102', 'Task 2', 'Description of task 2', 'PENDING', '2025-03-16T10:10:00Z', '2025-03-16T10:10:00Z', 'e8f9a93e-2fd0-4641-b49b-1f087ceddf50');
