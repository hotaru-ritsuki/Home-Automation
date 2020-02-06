INSERT INTO `users`(`id`,`email`,`first_name`,`last_name`,`password`,`secret`) VALUES (1,'lol@gmail.com','Lol','OMG','$2a$10$SNvAyaJ76EdR.xxgRVTUMe8y.3jsNvymdwizUaIYbn2vbg5G6bgam','LV460JAVA'),
                                                        (2,'kek@gmail.com','Kek','SDL','$2a$10$SNvAyaJ76EdR.xxgRVTUMe8y.3jsNvymdwizUaIYbn2vbg5G6bgam','LV460JAVA');

INSERT INTO `home` (`id`,`addressa`, `city`, `country`) VALUES (1,'Rynok sqr 5', 'Lviv', 'Ukraine'), (2,'Valova str 11', 'Lviv', 'Ukraine');

INSERT INTO `location` (`id`,`name`, `home_id`) VALUES (1,'hall', 1),
                                                       (2,'living room',1),
                                                       (3,'bedroom', 1),
                                                       (4,'hall', 2),
                                                       (5,'kitchen', 2),
                                                       (6,'bedroom', 2);

INSERT INTO `user_home` (`user_id`, `home_id`) VALUES (1, 1),
                                                      (2, 1);

INSERT INTO device_template (id, brand, model, power_supply, release_year, type, image)
    VALUES (1, 'Xiaomi', 'NUN4013CN', 'linear regulated', 2018, 'Humidity Meter', 'https://hotline.ua/img/tx/218/2189018225.jpg'),
           (2, 'Xiaomi', 'Mijia 2 Digital Hygrometer', 'unregulated', 2019, 'Temperature Meter', 'https://hotline.ua/img/tx/216/2166920015.jpg'),
           (3, 'Aqara', 'WSDCGQ11LM', 'unregulated', 2017, 'Temperature Meter', 'https://hotline.ua/img/tx/157/1576827185.jpg'),
           (4, 'Life', 'TP-3', 'regulated', 2013, 'Temperature Meter', 'http://stecker.com.ua/files/goods_images/577bd487c0fa8.jpeg'),
           (5, 'ORVIBO', 'ZigBee (ST20-O)', 'regulated', 2018, 'Humidity Meter', 'https://hotline.ua/img/tx/185/1850757045.jpg'),
           (6, 'Aqara', 'AS008CNW01', 'regulated', 2017, 'Temperature Meter', 'https://shonada.com/upload/resize_cache/iblock/946/573_573_1/946ba1295694f87f2b1ff6dc88b09ea5.jpg'),
           (7, 'Ajax', '000005637', 'unregulated', 2016, 'Temperature Meter', 'https://hotline.ua/img/tx/154/1545674635.jpg'),
           (8, 'Ajax', '000005636', 'regulated', 2016, 'Humidity Meter', 'https://hotline.ua/img/tx/154/1545674705.jpg'),
           (9, 'Fibaro', 'FGMS-001', 'regulated', 2015, 'Temperature Meter', 'https://hotline.ua/img/tx/158/1587240975.jpg'),
           (10, 'Arton', 'TPT-2', 'regulated', 2014, 'Temperature Meter', 'https://hotline.ua/img/tx/210/2107941665.jpg '),
           (11, 'SATEL', 'ATD-100', 'regulated', 2013, 'Temperature Meter', 'https://hotline.ua/img/tx/103/10324845.jpg');

INSERT INTO `local_device` (`uuid`, `location_id`, `supported_device_id`)
    VALUES ('ab6296cb-631a-48ae-b429-5c3c3aca', 1, 1),
           ('ab6296cb-632a-48ae-b429-5c3c3aca', 2, 2),
           ('ab6296cb-623a-48ae-b429-5c3c3aca', 3, 1);

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

INSERT INTO `actions` (`id`,`description`, `type`)
  VALUES (1,'send mail',2), (2,'send telegram',1);

INSERT INTO `rules` (`id`,`name`, `conditions`, `local_device_uuid`)
  VALUES (1,'Temperature check', '{field_name: "temperature", value: 23, when: HIGHER}', 'ab6296cb-631a-48ae-b429-5c3c3aca');

INSERT INTO `actions_rule` (`rule_id`, `action_id`, `action_specification`)
  VALUES (1, 2,'{"username": "AlexHack14", "text":"Hello"}'),(1, 1,'{"email": "alexhack14@gmai.com", "text":"Hello"}');
