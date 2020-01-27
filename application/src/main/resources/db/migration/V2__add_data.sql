insert into users (email, password, firstName, lastName,secret)
  values ('olga7325@gmail.com', '12345678','olga','lol','lolipop');

insert into users (email, password, firstName, lastName,secret)
  values ('vasya@gmail.com', '11111111','vasyl','petrashchuk','lolipop');

insert into home (addressa, city, country)
  values ('Rynok sqr 5', 'Lviv', 'Ukraine');

insert into home (addressa, city, country)
  values ('Valova str 11', 'Lviv', 'Ukraine');

insert into location (name, home_id)
  values ('hall', 1);

insert into location (name, home_id)
  values ('living room', 1);

insert into location (name, home_id)
  values ('bedroom', 1);

insert into location (name, home_id)
  values ('hall', 2);

insert into location (name, home_id)
  values ('kitchen', 2);

insert into location (name, home_id)
  values ('bedroom', 2);

insert into user_home (user_id, home_id)
  values (1, 2);

insert into user_home (user_id, home_id)
  values (2, 1);

insert into device_template (id, brand, model, power_supply, release_year, type)
  values (1, 'Xiaomi', 'NUN4013CN', 'linear regulated', 2018, 'Humidity Meter');

insert into device_template (id, brand, model, power_supply, release_year, type)
  values (2, 'Xiaomi', 'Mijia 2 Digital Hygrometer', 'unregulated', 2019, 'Temperature Meter');

insert into local_device (uuid, location_id, supported_device_id)
  values ('1ec3cf2a-2a3b-11ea', 1, 1);

insert into local_device (uuid, location_id, supported_device_id)
  values ('51225a98-00b0-4076', 2, 2);

insert into local_device (uuid, location_id, supported_device_id)
  values ('bf85b676-2a3b-11ea', 3, 1);

insert into features (id, description, name)
  values (1, 'Temperature', 'getTemperature');

insert into features (id, description, name)
  values (2, 'Humidity', 'getHumidity');

insert into device_features (device_id, features_id, specification)
  values (1, 2, '{"columnName": "temperature", "type": "numeric", "restriction": {"min": 15, "max": 25}}');

insert into actions (description, type)
  values ('"send mail',1);

insert into actions (description, type)
  values ('send telegram',1);

insert into rules (name, conditions, local_device_uuid)
  values ('Temperature check', '{field_name: "temperature", value: 23, when: HIGHER}', 'bf85b676-2a3b-11ea');

insert into actions_rule (rule_id, action_id, action_specification)
  values (1,1,'{email: alexaza636@gmail.com}');
