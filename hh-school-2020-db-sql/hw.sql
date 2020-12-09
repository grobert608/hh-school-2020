-- Task1
CREATE TABLE IF NOT EXISTS cv (
    cv_id serial PRIMARY KEY,
    cv_name varchar NOT NULL,
    salary integer
);

CREATE TABLE IF NOT EXISTS hirer (
    hirer_id serial PRIMARY KEY,
    hirer_name varchar NOT NULL,
    area_id integer NOT NULL
);

CREATE TABLE IF NOT EXISTS vacancy (
    vacancy_id serial PRIMARY KEY,
    hirer_id integer NOT NULL references hirer (hirer_id),
    position varchar NOT NULL,
    compensation_from integer,
    compensation_to integer,
    compensation_gross boolean,
    time_apply timestamp NOT NULL default now()
);

CREATE TABLE IF NOT EXISTS respond (
    respond_id serial PRIMARY KEY,
    vacancy_id integer NOT NULL references vacancy (vacancy_id),
    cv_id integer NOT NULL references cv (cv_id),
    time_apply timestamp NOT NULL default now()
);


-- Task2
INSERT INTO cv (cv_name, salary)
VALUES  ('Cv Name 1', 1),
        ('Cv Name 2', 2),
        ('Cv Name 3', 3),
        ('Cv Name 4', 4),
        ('Cv Name 5', 5),
        ('Cv Name 6', 6),
        ('Cv Name 7', 7),
        ('Cv Name 8', 8),
        ('Cv Name 9', 9),
        ('Cv Name 10', 10);

INSERT INTO hirer (hirer_name, area_id)
VALUES  ('Hirer Name 1', 1),
        ('Hirer Name 2', 2),
        ('Hirer Name 3', 3),
        ('Hirer Name 4', 4),
        ('Hirer Name 5', 5),
        ('Hirer Name 6', 6),
        ('Hirer Name 7', 7),
        ('Hirer Name 8', 8),
        ('Hirer Name 9', 9),
        ('Hirer Name 10', 10);

INSERT INTO vacancy (hirer_id, position, compensation_from, compensation_to, compensation_gross)
VALUES  (1, 'Position 1', 0, 100, false),
        (2, 'Position 2', 0, 200, true),
        (3, 'Position 3', 100, 200, false),
        (4, 'Position 4', NULL, 300, true),
        (5, 'Position 5', 300, 600, false),
        (1, 'Position 6', NULL, NULL, NULL),
        (2, 'Position 7', NULL, NULL, NULL),
        (3, 'Position 8', 100, NULL, false),
        (4, 'Position 9', 200, 300, false),
        (5, 'Position 10', 200, 300, false);

INSERT INTO respond (vacancy_id, cv_id)
VALUES  (1, 1),
        (2, 2),
        (3, 3),
        (4, 4),
        (5, 5),
        (1, 6),
        (2, 7),
        (3, 8),
        (4, 9),
        (5, 10);

-- Task3
SELECT position,
       area_id,
       hirer_name
FROM vacancy
INNER JOIN hirer ON hirer.hirer_id = vacancy.hirer_id
WHERE
    compensation_from IS NULL
    AND compensation_to IS NULL
ORDER BY time_apply DESC
LIMIT 10;

-- Task4
SELECT
    AVG(CASE WHEN compensation_gross IS TRUE
             THEN compensation_to
             ELSE compensation_to / 0.87
        END) AS avg_of_max_salary,
    AVG(CASE WHEN compensation_gross IS TRUE
             THEN compensation_from
             ELSE compensation_from / 0.87
        END) AS avg_of_min_salary,
    AVG(CASE WHEN compensation_gross IS TRUE
             THEN compensation_to + compensation_from
             ELSE ((compensation_to + compensation_from) / 2) / 0.87
        END) AS avg_of_mean_salary
FROM vacancy;

-- Task5
WITH max_count_of_each_respond AS (
    SELECT DISTINCT hirer_name,
                    max(count(respond.vacancy_id <> 0)) OVER (PARTITION BY hirer_name) as count
    FROM hirer
    RIGHT JOIN vacancy ON vacancy.hirer_id = hirer.hirer_id
    RIGHT JOIN respond ON respond.vacancy_id = vacancy.vacancy_id
    GROUP BY hirer_name, respond.vacancy_id
)
SELECT hirer_name
FROM max_count_of_each_respond
ORDER BY max_count_of_each_respond.count DESC, hirer_name
LIMIT 5;

-- Task6
WITH count_of_vacancies AS (
    SELECT  hirer.hirer_id,
            count(vacancy.vacancy_id <> 0) AS count
    FROM hirer
    LEFT JOIN vacancy ON vacancy.hirer_id = hirer.hirer_id
    GROUP BY hirer.hirer_id
)
SELECT
    percentile_cont(0.5) WITHIN GROUP (ORDER BY count)
FROM count_of_vacancies;

-- Task7
SELECT hirer.area_id,
       max(respond.time_apply - vacancy.time_apply) AS max_time,
       min(respond.time_apply - vacancy.time_apply) AS min_time
FROM vacancy
INNER JOIN respond ON respond.vacancy_id = vacancy.vacancy_id
INNER JOIN hirer ON hirer.hirer_id = vacancy.hirer_id
GROUP BY hirer.area_id;