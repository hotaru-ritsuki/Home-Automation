INSERT INTO `users`(`id`,`enabled`,`email`,`first_name`,`last_name`,`password`,`secret`) VALUES (1,1,'lol@gmail.com','Lol','OMG','$2a$10$SNvAyaJ76EdR.xxgRVTUMe8y.3jsNvymdwizUaIYbn2vbg5G6bgam','LV460JAVA'),
                                                        (2,1,'kek@gmail.com','Kek','SDL','$2a$10$SNvAyaJ76EdR.xxgRVTUMe8y.3jsNvymdwizUaIYbn2vbg5G6bgam','LV460JAVA');

INSERT INTO `home` (`id`,`addressa`, `city`, `country`) VALUES (1,'Rynok sqr 5', 'Lviv', 'Ukraine'), (2,'Valova str 11', 'Lviv', 'Ukraine');
INSERT INTO `location` (`id`,`name`, `home_id`) VALUES (1,'hall', 1),
                                                       (2,'living room',1),
                                                       (3,'bedroom', 1),
                                                       (4,'hall', 2),
                                                       (5,'kitchen', 2),
                                                       (6,'bedroom', 2);
INSERT INTO `user_home` (`user_id`, `home_id`) VALUES (1, 1),
                                                      (2, 1);
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
INSERT INTO `local_device` (`uuid`, `description`,`location_id`, `supported_device_id`)
    VALUES ('ab6296cb-631a-48ae-b429-5c3c3aca', 'Xiaomi motion detector', 1, 1),
           ('ab6296cb-632a-48ae-b429-5c3c3aca', 'Xiaomi temperature detector', 2, 2),
           ('ab6296cb-623a-48ae-b429-5c3c3aca', 'Xiaomi motion detector', 3, 1);
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
