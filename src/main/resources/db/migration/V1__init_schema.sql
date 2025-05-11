CREATE TABLE announcement (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              message VARCHAR(1024) NOT NULL,
                              date_posted DATE NOT NULL
);

CREATE TABLE application_period (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    status_mode VARCHAR(20) NOT NULL,
                                    start_time DATETIME NOT NULL,
                                    end_time DATETIME
);

CREATE TABLE evaluation_period (
                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                   status_mode VARCHAR(20) NOT NULL,
                                   start_time DATETIME NOT NULL,
                                   end_time DATETIME
);