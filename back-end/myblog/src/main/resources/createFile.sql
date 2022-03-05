CREATE TABLE user (
    user_id INT NOT NULL AUTO_INCREMENT,
    username varchar(64) NOT NULL,
    password varchar(64) NOT NULL,
    email varchar(255) DEFAULT NULL,
    create_time datetime NOT NULL,
    last_login datetime DEFAULT NULL,
    avatar varchar(255) DEFAULT NULL,
    PRIMARY KEY (user_id)
)ENGINE=InnoDB,CHARSET=utf8;


CREATE TABLE blog (
    blog_id INT NOT NULL AUTO_INCREMENT,
    user_name varchar(20) NOT NULL,
    title varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    content mediumtext,
    create_time DATETIME NOT NULL,
    last_edit DATETIME NOT NULL,
    PRIMARY KEY (blog_id)
)ENGINE=InnoDB,CHARSET=utf8;