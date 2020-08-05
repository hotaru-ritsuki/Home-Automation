create table actions_rule
(
    action_id            int8         not null,
    rule_id              int8         not null,
    action_specification varchar(255) not null,
    primary key (action_id, rule_id)
);

alter table actions_rule
    add constraint FKdrnem74m95bpp2s23adqb7u0a foreign key (rule_id) references rules;
alter table actions_rule
    add constraint FKkbdxmu5bsutxkvor7stne0wbb foreign key (action_id) references actions;
