DROP TABLE IF EXISTS movies;
CREATE TABLE movies(
    id INTEGER PRIMARY KEY,
    title TEXT,
    release_date TEXT,
    duration INTEGER,
    score INTEGER
);

DROP TABLE IF EXISTS genres;
CREATE TABLE genres(
    id INTEGER PRIMARY KEY,
    name TEXT
);

DROP TABLE IF EXISTS movieGenre;
CREATE TABLE movieGenre(
    movieID INTEGER NOT NULL,
    genreID INTEGER NOT NULL,
    CONSTRAINT PK_MovieGenre PRIMARY KEY(
        movieID,
        genreID
    ),
    FOREIGN KEY (movieID) REFERENCES movies (id),
    FOREIGN KEY (genreID) REFERENCES genres (id)
);

DROP TABLE IF EXISTS actors;
CREATE TABLE actors(
    id INTEGER PRIMARY KEY,
    name TEXT
);

DROP TABLE IF EXISTS directors;
CREATE TABLE directors(
    id INTEGER PRIMARY KEY,
    name TEXT
);




