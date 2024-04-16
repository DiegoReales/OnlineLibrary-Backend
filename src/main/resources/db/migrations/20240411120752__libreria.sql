-- DESCRIPTION:    libreria 
-- CREATED:        jue. 11/04/2024 12:07:57 
-- AUTHOR:         diego.reales 

CREATE TABLE "authors"(
    "id" SERIAL NOT NULL,
    "name" VARCHAR(100) NOT NULL,
    "lastname" VARCHAR(100) NOT NULL,
    "active" BOOLEAN NOT NULL,
    "created_by" INTEGER,
    "created_at" TIMESTAMP WITHOUT TIME ZONE,
    "updated_by" INTEGER,
    "updated_at" TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE "borrow_status"(
    "id" SMALLINT NOT NULL,
    "description" VARCHAR(20) NOT NULL
);

CREATE TABLE "books"(
    "id" SERIAL NOT NULL,
    "author_id" INTEGER NOT NULL,
    "isbn" VARCHAR(50) NOT NULL,
    "title" VARCHAR(100) NOT NULL,
    "description" VARCHAR(2000) NOT NULL,
    "stock" INTEGER NOT NULL,
    "available" INTEGER NOT NULL,
    "status" SMALLINT NOT NULL,
    "active" BOOLEAN NOT NULL,
    "created_by" INTEGER,
    "created_at" TIMESTAMP WITHOUT TIME ZONE,
    "updated_by" INTEGER,
    "updated_at" TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE "books_borrowed"(
    "id" SERIAL NOT NULL,
    "book_id" INTEGER NOT NULL,
    "user_id" INTEGER NOT NULL,
    "checkout" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    "checkin" TIMESTAMP(0) WITHOUT TIME ZONE,
    "status" SMALLINT NOT NULL,
    "created_by" INTEGER,
    "created_at" TIMESTAMP WITHOUT TIME ZONE,
    "updated_by" INTEGER,
    "updated_at" TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE "book_status"(
    "id" SMALLINT NOT NULL,
    "description" VARCHAR(20) NOT NULL
);

ALTER TABLE "authors" ADD PRIMARY KEY("id");
ALTER TABLE "borrow_status" ADD PRIMARY KEY("id");
ALTER TABLE "books" ADD PRIMARY KEY("id");
ALTER TABLE "books_borrowed" ADD PRIMARY KEY("id");
ALTER TABLE "book_status" ADD PRIMARY KEY("id");

ALTER TABLE "books_borrowed" ADD CONSTRAINT "books_borrowed_book_id_foreign" FOREIGN KEY("book_id") REFERENCES "books"("id");
ALTER TABLE "books" ADD CONSTRAINT "books_status_foreign" FOREIGN KEY("status") REFERENCES "book_status"("id");
ALTER TABLE "books" ADD CONSTRAINT "books_author_id_foreign" FOREIGN KEY("author_id") REFERENCES "authors"("id");
ALTER TABLE "books_borrowed" ADD CONSTRAINT "books_borrowed_status_foreign" FOREIGN KEY("status") REFERENCES "borrow_status"("id");
ALTER TABLE "books_borrowed" ADD CONSTRAINT "books_borrowed_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "users"("user_id");

INSERT INTO book_status (id, description) VALUES(1, 'DISPONIBLE');
INSERT INTO book_status (id, description) VALUES(2, 'OCUPADO');

INSERT INTO borrow_status (id, description) VALUES(1, 'EN PROGRESO');
INSERT INTO borrow_status (id, description) VALUES(2, 'TERMINADO');