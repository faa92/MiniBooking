CREATE TABLE landlord
(
    id            BIGSERIAL PRIMARY KEY,
    name          TEXT NOT NULL,
    email         TEXT NOT NULL,
    password_hash TEXT NOT NULL
);

CREATE TABLE tenant
(
    id            BIGSERIAL PRIMARY KEY,
    name          TEXT NOT NULL,
    email         TEXT NOT NULL,
    password_hash TEXT NOT NULL
);

CREATE TABLE rentalAd
(
    id          BIGSERIAL PRIMARY KEY,
    title       TEXT      NOT NULL,
    description TEXT      NOT NULL,
    price       DECIMAL   NOT NULL,
    created_at  TIMESTAMP NOT NULL,
    active      BOOLEAN   NOT NULL,
    landlord_id BIGINT REFERENCES landlord (id)
);

CREATE TABLE responseToAd
(
    id          BIGSERIAL PRIMARY KEY,
    message     TEXT      NOT NULL,
    created_at  TIMESTAMP NOT NULL,
    tenant_id   BIGINT REFERENCES tenant (id),
    rentalAd_id BIGINT REFERENCES rentalAd (id)
);