INSERT INTO `users`(`email`,`first_name`,`last_name`,`password`,`secret`) VALUES ('lol@gmail.com','Lol','OMG','$2a$10$SNvAyaJ76EdR.xxgRVTUMe8y.3jsNvymdwizUaIYbn2vbg5G6bgam','LV460JAVA'),
                                                        ('kek@gmail.com','Kek','SDL','$2a$10$SNvAyaJ76EdR.xxgRVTUMe8y.3jsNvymdwizUaIYbn2vbg5G6bgam','LV460JAVA');

SELECT @usero := `users`.`id` FROM `users` WHERE `users`.`email` = 'lol@gmail.com';
SELECT @userv := `users`.`id` FROM `users` WHERE `users`.`email` = 'kek@gmail.com';

INSERT INTO `home` (`addressa`, `city`, `country`) VALUES ('Rynok sqr 5', 'Lviv', 'Ukraine'), ('Valova str 11', 'Lviv', 'Ukraine');

SELECT @rynok := `home`.`id` FROM `home` WHERE `home`.`addressa` = 'Rynok sqr 5';
SELECT @valova := `home`.`id` FROM `home` WHERE `home`.`addressa` = 'Valova str 11';

INSERT INTO `location` (`name`, `home_id`) VALUES ('hall', (SELECT @rynok)),
                                                   ('living room', (SELECT @rynok)),
                                                   ('bedroom', (SELECT @rynok)),
                                                   ('hall', (SELECT @valova)),
                                                   ('kitchen', (SELECT @valova)),
                                                   ('bedroom', (SELECT @valova));

SELECT @locationHall := `location`.`id` FROM `location` WHERE `location`.`name` = 'hall';
SELECT @locationLR := `location`.`id` FROM `location` WHERE `location`.`name` = 'living room';
SELECT @locationBR := `location`.`id` FROM `location` WHERE `location`.`name` = 'bedroom';
SELECT @locationHvalova := `location`.`id` FROM `location` WHERE `location`.`name` = 'hall';
SELECT @locationKT := `location`.`id` FROM `location` WHERE `location`.`name` = 'kitchen';
SELECT @locationBRvalova := `location`.`id` FROM `location` WHERE `location`.`name` = 'bedroom';

INSERT INTO `user_home` (`user_id`, `home_id`) VALUES ((SELECT @usero), (SELECT @rynok)),
                                                ((SELECT @userv), (SELECT @rynok));

INSERT INTO `device_template` (`id`, `brand`, `model`, `power_supply`, `release_year`, `type`)
    VALUES (1, 'Xiaomi', 'NUN4013CN', 'linear regulated', 2018, 'Humidity Meter'),
           (2, 'Xiaomi', 'Mijia 2 Digital Hygrometer', 'unregulated', 2019, 'Temperature Meter'),
           (3, 'Aqara', 'WSDCGQ11LM', 'unregulated', 2017, 'Temperature Meter'),
           (4, 'Life', 'TP-3', 'regulated', 2013, 'Temperature Meter'),
           (5, 'ORVIBO', 'ZigBee (ST20-O)', 'regulated', 2018, 'Humidity Meter'),
           (6, 'Aqara', 'AS008CNW01', 'regulated', 2017, 'Temperature Meter'),
           (7, 'Ajax', '000005637', 'unregulated', 2016, 'Temperature Meter'),
           (8, 'Ajax', '000005636', 'regulated', 2016, 'Humidity Meter'),
           (9, 'Fibaro', 'FGMS-001', 'regulated', 2015, 'Temperature Meter'),
           (10, 'Arton', 'TPT-2', 'regulated', 2014, 'Temperature Meter'),
           (11, 'SATEL', 'ATD-100', 'regulated', 2013, 'Temperature Meter');

INSERT INTO `local_device` (`uuid`, `location_id`, `supported_device_id`)
    VALUES ('1ec3cf2a-2a3b-11ea', (SELECT @locationHall), 1),
            ('51225a98-00b0-4076', (SELECT @locationLR), 2),
            ('bf85b676-2a3b-11ea', (SELECT @locationBR), 1);

INSERT INTO `features` (`id`, `description`, `name`)
  VALUES (1, 'Temperature', 'getTemperature'),
            (2, 'Humidity', 'getHumidity');

INSERT INTO `device_features` (`device_id`, `features_id`, `specification`)
  VALUES (1, 2, '{"columnName": "temperature", "type": "numeric", "restriction": {"min": 15, "max": 25}}'),
   (2, 1, '{"columnName": "temperature", "type": "numeric", "restriction": {"min": 15, "max": 25}}'),
   (3, 1, '{"columnName": "temperature", "type": "numeric", "restriction": {"min": 15, "max": 25}}'),
   (3, 2, '{"columnName": "humidity", "type": "numeric", "restriction": {"min": 15, "max": 25}}'),
   (4, 1, '{"columnName": "temperature", "type": "numeric", "restriction": {"min": 15, "max": 25}}'),
   (5, 2, '{"columnName": "humidity", "type": "numeric", "restriction": {"min": 15, "max": 25}}'),
   (6, 1, '{"columnName": "temperature", "type": "numeric", "restriction": {"min": 15, "max": 25}}'),
   (6, 2, '{"columnName": "humidity", "type": "numeric", "restriction": {"min": 15, "max": 25}}'),
   (7, 1, '{"columnName": "temperature", "type": "numeric", "restriction": {"min": 15, "max": 25}}'),
   (8, 2, '{"columnName": "humidity", "type": "numeric", "restriction": {"min": 15, "max": 25}}'),
   (9, 1, '{"columnName": "temperature", "type": "numeric", "restriction": {"min": 15, "max": 25}}'),
   (10, 1, '{"columnName": "temperature", "type": "numeric", "restriction": {"min": 15, "max": 25}}'),
   (10, 2, '{"columnName": "humidity", "type": "numeric", "restriction": {"min": 15, "max": 25}}'),
   (11, 1, '{"columnName": "temperature", "type": "numeric", "restriction": {"min": 15, "max": 25}}');

INSERT INTO `actions` (`description`, `type`)
  VALUES ('send mail',1), ('send telegram',1);

SELECT @action1 := `actions`.`id` FROM `actions` WHERE `description` = 'send mail';

INSERT INTO `rules` (`name`, `conditions`, `local_device_uuid`)
  VALUES ('Temperature check', '{field_name: "temperature", value: 23, when: HIGHER}', 'bf85b676-2a3b-11ea');

SELECT @rule1 := `rules`.`id` FROM `rules` WHERE `name` = 'Temperature check';

INSERT INTO `actions_rule` (`rule_id`, `action_id`, `action_specification`)
  VALUES ((SELECT @rule1), (SELECT @action1),'{email: alexaza636@gmail.com}');
