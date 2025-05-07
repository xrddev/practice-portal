CREATE TABLE announcement (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              message VARCHAR(1024) NOT NULL,
                              date_posted DATE NOT NULL
);