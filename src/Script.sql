CREATE TABLE media (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    cost DECIMAL(10, 2) NOT NULL
);

CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    cost DECIMAL(10, 2) NOT NULL,
    authors TEXT NOT NULL,
    media_id INT REFERENCES media(id)
);

CREATE TABLE compact_disks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    cost DECIMAL(10, 2) NOT NULL,
    artist VARCHAR(255) NOT NULL,
    media_id INT REFERENCES media(id)
);

CREATE TABLE digital_video_disks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    cost DECIMAL(10, 2) NOT NULL,
    director VARCHAR(255) NOT NULL,
    length INT NOT NULL,
    media_id INT REFERENCES media(id)
);

CREATE TABLE tracks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    length INT NOT NULL,
    compact_disk_id INT REFERENCES compact_disks(id)
);
