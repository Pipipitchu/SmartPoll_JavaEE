CREATE TABLE users(
   id serial not null,
   name varchar(500) not null,
   login varchar(500) not null,
   password varchar(500) not null,
   email varchar(150) not null,
   constraint user_pk primary key (id)
);

CREATE TABLE question(
   id serial not null,
   title varchar(500) not null,
   question varchar(500) not null,
   user_id integer not null,
   date_question timestamp not null default now(),
   expiration_date timestamp not null,
   constraint question_pk primary key(id),
   constraint user_fk foreign key (user_id)
      references users(id)
);

CREATE TABLE answer(
   id serial not null,
   question_id integer not null,
   answer_id varchar(500) not null,
   date_vote timestamp not null default now(),
   constraint answer_pk primary key(id),
   constraint question_answer_fk foreign key (question_id)
      references question(id)
);

CREATE TABLE result(
   id serial not null,
   question_id integer not null,
   answer_id integer not null,
   constraint result_pk primary key (id),
   constraint question_result_fk foreign key (question_id)
      references question(id),
   constraint answer_fk foreign key(answer_id)
      references answer(id)
);

INSERT INTO users (id, name, login, password, email) VALUES
(1, 'administrator', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@admin');