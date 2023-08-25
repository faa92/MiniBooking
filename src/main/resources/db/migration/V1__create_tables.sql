CREATE TABLE landlord
(
    id            BIGSERIAL PRIMARY KEY,
    name          TEXT NOT NULL,
    email         TEXT NOT NULL,
    password_hash TEXT NOT NULL
);

CREATE UNIQUE INDEX ON landlord (lower(email));

CREATE TABLE tenant
(
    id            BIGSERIAL PRIMARY KEY,
    name          TEXT NOT NULL,
    email         TEXT NOT NULL,
    password_hash TEXT NOT NULL
);

CREATE UNIQUE INDEX ON tenant (lower(email));

CREATE TABLE rentalAd
(
    id          BIGSERIAL PRIMARY KEY,
    title       TEXT      NOT NULL,
    description TEXT      NOT NULL,
    price       DECIMAL   NOT NULL,
    created_at  TIMESTAMP NOT NULL,
    active      BOOLEAN   NOT NULL,
    landlord_id BIGINT    NOT NULL REFERENCES landlord (id)
);

CREATE TABLE responseToAd
(
    id          BIGSERIAL PRIMARY KEY,
    message     TEXT      NOT NULL,
    date_from   DATE      NOT NULL,
    date_to     DATE      NOT NULL,
    created_at  TIMESTAMP NOT NULL,
    tenant_id   BIGINT    NOT NULL REFERENCES tenant (id),
    rentalAd_id BIGINT    NOT NULL REFERENCES rentalAd (id),
    UNIQUE (tenant_id, rentalAd_id)
);
