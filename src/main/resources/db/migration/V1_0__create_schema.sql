CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    username VARCHAR(30) NOT NULL UNIQUE,
    about VARCHAR(200),
    avatar VARCHAR(1024),
    email VARCHAR(256) NOT NULL,
    password VARCHAR(256) NOT NULL,
    role VARCHAR(32) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT users_username_unique UNIQUE(username),
    CONSTRAINT users_email_unique UNIQUE(email)
);

CREATE TABLE IF NOT EXISTS wishes (
    id BIGSERIAL,
    owner_id BIGINT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    name VARCHAR(256) NOT NULL,
    link VARCHAR(1024),
    image VARCHAR(1024),
    price DOUBLE PRECISION,
    raised DOUBLE PRECISION,
    description VARCHAR(1024),
    copied INTEGER,
    CONSTRAINT pk_wishes PRIMARY KEY (id),
    CONSTRAINT wishes_to_users_fk
    FOREIGN KEY(owner_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS offers (
    id BIGSERIAL,
    user_id BIGINT NOT NULL,
    wish_id BIGINT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    hidden BOOLEAN,
    CONSTRAINT pk_offers PRIMARY KEY (id),
    CONSTRAINT offers_to_users_fk
    FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT offers_to_wishes_fk
    FOREIGN KEY(wish_id) REFERENCES wishes(id)
);

CREATE TABLE IF NOT EXISTS wishlists (
    id BIGSERIAL,
    owner_id BIGINT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    name VARCHAR(250) NOT NULL,
    description VARCHAR(1500),
    image VARCHAR(1024),
    CONSTRAINT pk_wishlists PRIMARY KEY (id),
    CONSTRAINT wishlists_to_users_fk
    FOREIGN KEY(owner_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS wishlists_wishes (
    wishlist_id BIGINT NOT NULL,
    wish_id BIGINT NOT NULL,
    CONSTRAINT pk_wishlists_wishes
    PRIMARY KEY(wishlist_id, wish_id),
    CONSTRAINT wishlists_wishes_to_wishlists_fk
    FOREIGN KEY(wishlist_id) REFERENCES wishlists(id),
    CONSTRAINT wishlists_wishes_to_wishes_fk
    FOREIGN KEY(wish_id) REFERENCES wishes(id)
);
