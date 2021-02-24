CREATE TABLE IF NOT EXISTS patient (
    id serial PRIMARY KEY,
    firstname VARCHAR (50) NOT NULL,
    lastname VARCHAR (50)NOT NULL,
    email VARCHAR (50) UNIQUE NOT NULL,
    phone VARCHAR (25) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS doctor (
    id serial PRIMARY KEY,
    firstname VARCHAR (50),
    lastname VARCHAR (50)
);

CREATE TABLE IF NOT EXISTS appointment (
    id serial PRIMARY KEY,
    patient_id bigint references patient(id),
    doctor_id bigint references doctor(id),
    date DATE NOT NULL,
    time TIME NOT NULL
);

CREATE TABLE IF NOT EXISTS department (
    id serial PRIMARY KEY,
    doctor_id bigint references doctor(id),
    name VARCHAR (50)
);

CREATE TABLE IF NOT EXISTS hospital (
    id serial PRIMARY KEY,
    department_id bigint references department(id),
    name VARCHAR (50),
    address VARCHAR (250)
);
