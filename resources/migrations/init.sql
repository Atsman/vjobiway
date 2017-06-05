CREATE SCHEMA IF NOT EXISTS vjobiway;

CREATE TABLE IF NOT EXISTS vjobiway.countries(
  country_id SERIAL PRIMARY KEY,
  country_code TEXT NOT NULL,
  title TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS vjobiway.cities(
  city_id SERIAL PRIMARY KEY,
  title TEXT NOT NULL,
  country_id SERIAL REFERENCES vjobiway.countries(country_id)
);

CREATE TABLE IF NOT EXISTS vjobiway.companies(
  company_id SERIAL PRIMARY KEY,
  title TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS vjobiway.vacancies(
  vacancy_id SERIAL PRIMARY KEY,
  title TEXT NOT NULL,
  description TEXT, 
  company SERIAL REFERENCES vjobiway.companies(company_id),
  city_id SERIAL REFERENCES vjobiway.cities(city_id),
  "type" TEXT CHECK ("type" IN ('full_time', 'part_time')),
  "level" TEXT
);
