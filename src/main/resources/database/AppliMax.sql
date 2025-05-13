-- DROP AND CREATE DATABASE
DROP DATABASE IF EXISTS AppliMax;
CREATE DATABASE AppliMax;
USE AppliMax;

-- APP_USER
CREATE TABLE app_user (
                          user_id VARCHAR(10) PRIMARY KEY,
                          username VARCHAR(50) NOT NULL,
                          password VARCHAR(50) NOT NULL,
                          address VARCHAR(100),
                          email VARCHAR(50) UNIQUE,
                          role VARCHAR(50) NOT NULL
);

INSERT INTO app_user VALUES
                         ('U001', 'Admin', 'admin1234', 'Colombo', 'admin@gmail.com', 'admin'),
                         ('U002', 'Akila', 'akila1234', 'Colombo', 'akila24@gmail.com', 'manager'),
                         ('U003', 'Kavindu', 'kavindu4325', 'Kandy', 'kavindu23@gmail.com', 'accountant');

-- EMPLOYEE
CREATE TABLE employee (
                          employee_id VARCHAR(10) PRIMARY KEY,
                          nic VARCHAR(12) UNIQUE NOT NULL,
                          email VARCHAR(50) UNIQUE,
                          phone_no VARCHAR(10) UNIQUE,
                          address VARCHAR(100),
                          status VARCHAR(20)
);

-- EMPLOYEE MANAGEMENT
CREATE TABLE employee_management (
                                     employee_id VARCHAR(10) NOT NULL,
                                     user_id VARCHAR(10) NOT NULL,
                                     assigned_date DATE,
                                     FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
                                     FOREIGN KEY (user_id) REFERENCES app_user(user_id)
);

-- CUSTOMER
CREATE TABLE customer (
                          customer_id VARCHAR(10) PRIMARY KEY,
                          name VARCHAR(50) NOT NULL,
                          email VARCHAR(50) UNIQUE,
                          phone_no VARCHAR(10) UNIQUE,
                          address VARCHAR(100)
);

-- CUSTOMER MANAGEMENT
CREATE TABLE customer_management (
                                     user_id VARCHAR(10),
                                     customer_id VARCHAR(10),
                                     assigned_date DATE,
                                     relationship_type VARCHAR(50),
                                     status VARCHAR(20),
                                     note TEXT,
                                     PRIMARY KEY (user_id, customer_id),
                                     FOREIGN KEY (user_id) REFERENCES app_user(user_id),
                                     FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- ITEM
CREATE TABLE item (
                      item_id VARCHAR(10) PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      category VARCHAR(50),
                      brand VARCHAR(50),
                      price DECIMAL(10, 2) NOT NULL
);

INSERT INTO item VALUES
                     ('I001', 'Table Fan 70W', 'Fans', 'USHA', 12000.00),
                     ('I002', 'Table Fan 100W', 'Fans', 'USHA', 14000.00),
                     ('I003', 'TV 21 inches', 'TV', 'LG', 45000.00);

-- ORDER
CREATE TABLE order_table (
                             order_id VARCHAR(10) PRIMARY KEY,
                             customer_id VARCHAR(10) NOT NULL,
                             order_date DATE NOT NULL,
                             total_amount DECIMAL(10, 2),
                             FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- ORDER DETAIL
CREATE TABLE order_detail (
                              order_id VARCHAR(10),
                              item_id VARCHAR(10),
                              qty INT NOT NULL CHECK (qty >= 1),
                              unit_price DECIMAL(10, 2) NOT NULL,
                              PRIMARY KEY (order_id, item_id),
                              FOREIGN KEY (order_id) REFERENCES order_table(order_id),
                              FOREIGN KEY (item_id) REFERENCES item(item_id)
);

-- SUPPLIER
CREATE TABLE supplier (
                          supplier_id VARCHAR(10) PRIMARY KEY,
                          name VARCHAR(50) NOT NULL,
                          nic VARCHAR(12) UNIQUE,
                          phone_no VARCHAR(10),
                          email VARCHAR(50) UNIQUE,
                          address VARCHAR(100)
);

-- SUPPLIER PAYMENT (fixed)
CREATE TABLE supplier_payment (
                                  payment_id VARCHAR(10) PRIMARY KEY,
                                  supplier_id VARCHAR(10) NOT NULL,
                                  invoice_reference VARCHAR(20),
                                  amount DECIMAL(10, 2) NOT NULL,
                                  commission DECIMAL(10, 2) DEFAULT 0.00,
                                  final_payment DECIMAL(10,2) NOT NULL,
                                  payment_date DATE NOT NULL,
                                  payment_method VARCHAR(50),
                                  FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id)
);

-- INVENTORY
CREATE TABLE inventory (
                           inventory_id VARCHAR(10) PRIMARY KEY,
                           item_id VARCHAR(10) NOT NULL,
                           supplier_id VARCHAR(10) NOT NULL,
                           qty INT NOT NULL CHECK (qty >= 0),
                           inventory_date DATE,
                           FOREIGN KEY (item_id) REFERENCES item(item_id),
                           FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id)
);

-- SALE
CREATE TABLE sale (
                      sale_id VARCHAR(10) PRIMARY KEY,
                      order_id VARCHAR(10),
                      item_id VARCHAR(10),
                      sale_date DATE NOT NULL,
                      unit_price DECIMAL(10, 2) NOT NULL,
                      qty INT NOT NULL CHECK (qty >= 1),
                      total_amount DECIMAL(10, 2) NOT NULL,
                      FOREIGN KEY (order_id) REFERENCES order_table(order_id),
                      FOREIGN KEY (item_id) REFERENCES item(item_id)
);

-- SALES REPORT
CREATE TABLE sales_report (
                              sales_report_id VARCHAR(10) PRIMARY KEY,
                              item_id VARCHAR(10) NOT NULL,
                              report_date DATE,
                              unit_price DECIMAL(10, 2) NOT NULL,
                              FOREIGN KEY (item_id) REFERENCES item(item_id)
);

-- ATTENDANCE
CREATE TABLE attendance (
                            attendance_id VARCHAR(10) PRIMARY KEY,
                            employee_id VARCHAR(10) NOT NULL,
                            attendance_date DATE,
                            attendance_time TIME NOT NULL,
                            wrk_hours INT CHECK (wrk_hours >= 0),
                            FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

-- SALARY
CREATE TABLE salary (
                        salary_id VARCHAR(10) PRIMARY KEY,
                        employee_id VARCHAR(10) NOT NULL,
                        salary_date DATE NOT NULL,
                        amount DECIMAL(10, 2) NOT NULL,
                        FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

-- ALLOWANCE
CREATE TABLE allowance (
                           allowance_id VARCHAR(10) PRIMARY KEY,
                           salary_id VARCHAR(10),
                           allowance_date DATE NOT NULL,
                           amount DECIMAL(10, 2),
                           FOREIGN KEY (salary_id) REFERENCES salary(salary_id)
);

-- EMAIL
CREATE TABLE email (
                       email_id VARCHAR(10) PRIMARY KEY,
                       email_address VARCHAR(50) NOT NULL,
                       email_time TIME,
                       email_date DATE
);

-- DISCOUNT
CREATE TABLE discount (
                          discount_id VARCHAR(10) PRIMARY KEY,
                          amount DECIMAL(10, 2) NOT NULL,
                          item_id VARCHAR(10),
                          end_date DATE NOT NULL,
                          FOREIGN KEY (item_id) REFERENCES item(item_id)
);

-- INVOICE
CREATE TABLE invoice (
                         invoice_id VARCHAR(10) PRIMARY KEY,
                         recipient_name VARCHAR(50),
                         invoice_date DATE NOT NULL,
                         email_id VARCHAR(10),
                         discount_id VARCHAR(10),
                         FOREIGN KEY (email_id) REFERENCES email(email_id),
                         FOREIGN KEY (discount_id) REFERENCES discount(discount_id)
);

-- PAYMENT
CREATE TABLE payment (
                         payment_id VARCHAR(10) PRIMARY KEY,
                         discount_id VARCHAR(10),
                         invoice_id VARCHAR(10),
                         total_amount DECIMAL(10, 2) NOT NULL,
                         payment_date DATE NOT NULL,
                         FOREIGN KEY (discount_id) REFERENCES discount(discount_id),
                         FOREIGN KEY (invoice_id) REFERENCES invoice(invoice_id)
);

-- Show all tables
SHOW TABLES;