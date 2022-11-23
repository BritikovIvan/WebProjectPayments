INSERT INTO `user_role`(`role`) VALUES
    ('ROLE_CLIENT'),
    ('ROLE_ADMIN');

INSERT INTO `user`(`full_name`, `email`, `phone_number`, `role_id`) VALUES
    ('Гордеев Арсений Дмитрьевич', '22fadb5d14@4e1ca1e3.com', '+375445985852', 1),
    ('Гусева Ульяна Матвеевна', '5b2d11gd55@ed1d3ggg.com', '+375294613715', 2);

INSERT INTO `login`(`login`, `password`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`, `user_id`) VALUES
    ('test_user', '$2a$12$ibWL0GtULZNN2JjcY2bT8.ubSydIkV9ZvhKx46mMUOo7aUHzr.mRi', 1, 1, 1, 1, 1),
    ('test_admin', '$2a$12$ibWL0GtULZNN2JjcY2bT8.ubSydIkV9ZvhKx46mMUOo7aUHzr.mRi', 1, 1, 1, 1, 2);

INSERT INTO `bank_account`(`iban`, `status`, `balance`, `user_id`) VALUES
    ('NL05RABO6044978370', 'A', 120.5, 1),
    ('NL11ABNA1430833394', 'A', 23.56, 1);

INSERT INTO `credit_card`(`number`, `name`, `type`, `status`, `validity`, `bank_account_id`) VALUES
    ('4024007173571042', 'Test', 'D', 'A', '2023-04-01', 1),
    ('5139620505836286', 'Test2', 'C', 'A', '2023-10-01', 2),
    ('4005693571794725', 'TestExpired', 'D', 'E', '2022-01-01', 1);