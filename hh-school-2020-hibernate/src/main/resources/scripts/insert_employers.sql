-- employers
INSERT INTO employer(employer_id, company_name, employees_count, creation_time)
  VALUES (1, 'Blues Bar', 10, '2018-01-01');
INSERT INTO employer(employer_id, company_name, employees_count, creation_time)
  VALUES (2, 'Jazz Club', 15, '2018-02-02');
INSERT INTO employer(employer_id, company_name, employees_count, creation_time)
  VALUES (3, 'Rock Cafe', 20, '2018-03-03');
INSERT INTO employer(employer_id, company_name, employees_count, creation_time)
  VALUES (4, 'Metal Basement', 5, '2018-04-04');

-- vacancies
INSERT INTO vacancy(vacancy_id, title, description, employer_id)
  VALUES (1, 'Sad man with guitar', 'Oh yeah. The blues has got me.', 1);
INSERT INTO vacancy(vacancy_id, title, description, employer_id)
  VALUES (2, 'Another sad guy', null, 1);
INSERT INTO vacancy(vacancy_id, title, description, employer_id)
  VALUES (3, 'And one more', null, 1);

INSERT INTO vacancy(vacancy_id, title, description, employer_id)
  VALUES (4, 'Saxophonist', null, 2);

INSERT INTO vacancy(vacancy_id, title, description, employer_id)
  VALUES (5, 'Lennon clone', null, 3);

INSERT INTO vacancy(vacancy_id, title, description, employer_id)
  VALUES (6, 'Guy with long hair', null, 4);
