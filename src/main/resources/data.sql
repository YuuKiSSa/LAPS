-- 创建 Department 表
CREATE TABLE IF NOT EXISTS department (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) UNIQUE,
    annual_leave INT
);

-- 插入测试数据到 Department 表
INSERT INTO department (name, annual_leave) VALUES
('Department A', 14),
('Department B', 14),
('Admin Department', 20);

-- 创建 Staff 表
CREATE TABLE IF NOT EXISTS staff (
    user_id VARCHAR(20) PRIMARY KEY,
    password VARCHAR(20),
    name VARCHAR(15),
    email VARCHAR(25),
    status BOOLEAN,
    hierarchy INT,
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES department(id)
);

-- 插入测试数据到 Staff 表
INSERT INTO staff (user_id, password, name, email, status, hierarchy, department_id) VALUES
('user1', 'password1', 'Employee One', 'employee1@example.com', true, 0, 1),
('user2', 'password2', 'Employee Two', 'employee2@example.com', true, 0, 2),
('user3', 'password3', 'Employee Three', 'employee3@example.com', true, 0, 1),
('user4', 'password4', 'Employee Four', 'employee4@example.com', true, 0, 2),
('manager1', 'password1', 'Manager One', 'manager1@example.com', true, 1, 1),
('manager2', 'password2', 'Manager Two', 'manager2@example.com', true, 1, 2),
('manager3', 'password3', 'Manager Three', 'manager3@example.com', true, 2, 1),
('manager4', 'password4', 'Manager Four', 'manager4@example.com', true, 2, 2),
('admin1', 'password1', 'Admin One', 'admin1@example.com', true, 3, 3);

-- 创建 Application 表
CREATE TABLE IF NOT EXISTS application (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(20),
    start_time DATE,
    end_time DATE,
    status VARCHAR(10),
    description VARCHAR(1000),
    user_id VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES staff(user_id)
);

-- 插入测试数据到 Application 表
INSERT INTO application (type, start_time, end_time, status, description, user_id) VALUES
('Annual Leave', '2024-06-01', '2024-06-05', 'Applied', 'Annual leave for vacation', 'user1'),
('Medical Leave', '2024-06-10', '2024-06-12', 'Applied', 'Medical leave for health issues', 'user1'),
('Annual Leave', '2024-06-15', '2024-06-20', 'Applied', 'Annual leave for family event', 'user2'),
('Medical Leave', '2024-07-01', '2024-07-03', 'Applied', 'Medical leave for surgery', 'user2'),
('Annual Leave', '2024-06-21', '2024-06-25', 'Applied', 'Annual leave for personal reasons', 'user3'),
('Medical Leave', '2024-07-04', '2024-07-06', 'Applied', 'Medical leave for rest', 'user3'),
('Annual Leave', '2024-07-10', '2024-07-15', 'Applied', 'Annual leave for trip', 'user4'),
('Medical Leave', '2024-07-16', '2024-07-18', 'Applied', 'Medical leave for recovery', 'user4'),
('Annual Leave', '2024-06-01', '2024-06-05', 'Approved', 'Annual leave for vacation', 'manager1'),
('Medical Leave', '2024-06-10', '2024-06-12', 'Approved', 'Medical leave for health issues', 'manager1'),
('Annual Leave', '2024-06-15', '2024-06-20', 'Approved', 'Annual leave for family event', 'manager2'),
('Medical Leave', '2024-07-01', '2024-07-03', 'Approved', 'Medical leave for surgery', 'manager2'),
('Annual Leave', '2024-06-21', '2024-06-25', 'Applied', 'Annual leave for personal reasons', 'manager3'),
('Medical Leave', '2024-07-04', '2024-07-06', 'Applied', 'Medical leave for rest', 'manager3'),
('Annual Leave', '2024-07-10', '2024-07-15', 'Applied', 'Annual leave for trip', 'manager4'),
('Medical Leave', '2024-07-16', '2024-07-18', 'Applied', 'Medical leave for recovery', 'manager4');
