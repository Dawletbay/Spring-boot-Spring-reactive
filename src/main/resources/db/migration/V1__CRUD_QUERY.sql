CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- -------------------------------------
-- Table structure for "public"."region"
-- -------------------------------------

CREATE TABLE IF NOT EXISTS PUBLIC.region (
                               id            UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
                               deleted       BOOLEAN DEFAULT FALSE,
                               code          BIGINT UNIQUE,
                               name_uz       CHARACTER VARYING(255),
                               name_en       CHARACTER VARYING(255),
                               name_ru       CHARACTER VARYING(255)
);

-- ---------------------------------------
-- Table structure for "public"."district"
-- ---------------------------------------

CREATE TABLE IF NOT EXISTS PUBLIC.district (
                                 id            UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
                                 deleted       BOOLEAN DEFAULT FALSE,
                                 code          BIGINT,
                                 district_id   CHARACTER VARYING(255) UNIQUE,
                                 name_uz       CHARACTER VARYING(255),
                                 name_en       CHARACTER VARYING(255),
                                 name_ru       CHARACTER VARYING(255),
                                 region_code   BIGINT,
                                 FOREIGN KEY (region_code) REFERENCES region(code)
);

-- ---------------------------------------
-- Table structure for "public"."address"
-- ---------------------------------------

CREATE TABLE IF NOT EXISTS PUBLIC.address (
                                id        UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
                                street    TEXT,
                                house     CHARACTER VARYING(255),
                                apartment CHARACTER VARYING(255),
                                longitude CHARACTER VARYING(255),
                                latitude  CHARACTER VARYING(255),
                                region_id UUID,
                                district_id UUID,
                                FOREIGN KEY (region_id) REFERENCES region(id),
                                FOREIGN KEY (district_id) REFERENCES district(id)
);

-- --------------------------------------------
-- Table structure for "public"."business_type"
-- --------------------------------------------

CREATE TABLE IF NOT EXISTS PUBLIC.business_type (
                                id            UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
                                deleted       BOOLEAN DEFAULT FALSE,
                                code          CHARACTER VARYING(255),
                                name_uz       CHARACTER VARYING(255),
                                name_en       CHARACTER VARYING(255),
                                name_ru       CHARACTER VARYING(255)
);

-- ------------------------------------
-- Table structure for "public"."bank"
-- ------------------------------------

CREATE TABLE IF NOT EXISTS PUBLIC.bank (
                                id            UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
                                deleted       BOOLEAN DEFAULT FALSE,
                                code          CHARACTER VARYING(255),
                                name_uz       CHARACTER VARYING(255),
                                name_en       CHARACTER VARYING(255),
                                name_ru       CHARACTER VARYING(255),
                                mfo           CHARACTER VARYING(255) UNIQUE,
                                tin           CHARACTER VARYING(255),
                                parent_id     UUID,
                                FOREIGN KEY (parent_id) REFERENCES bank(id)
);

-- --------------------------------------------
-- Table structure for "public"."activity_type"
-- --------------------------------------------

CREATE TABLE IF NOT EXISTS PUBLIC.activity_type (
                                id            UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
                                deleted       BOOLEAN DEFAULT FALSE,
                                code          BIGINT,
                                name_uz       CHARACTER VARYING(255),
                                name_en       CHARACTER VARYING(255),
                                name_ru       CHARACTER VARYING(255),
                                parent_id     UUID,
                                FOREIGN KEY (parent_id) REFERENCES activity_type(id)
);

-- ------------------------------------
-- Table structure for "public"."roles"
-- ------------------------------------

CREATE TABLE IF NOT EXISTS PUBLIC.roles (
                                name CHARACTER VARYING(255) NOT NULL PRIMARY KEY
);

-- ----------------------------------------
-- Table structure for "public"."user_role"
-- ----------------------------------------

CREATE TABLE IF NOT EXISTS PUBLIC.user_role (
                                id      UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
                                user_id UUID,
                                role_id CHARACTER VARYING(255)
);

-- -------------------------------------
-- Table structure for "public"."users"
-- -------------------------------------

CREATE TABLE IF NOT EXISTS PUBLIC.users (
                                id               UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
                                deleted          BOOLEAN DEFAULT FALSE,
                                created_date     TIMESTAMP WITHOUT TIME ZONE,
                                updated_date     TIMESTAMP WITHOUT TIME ZONE,
                                first_name       CHARACTER VARYING(255),
                                last_name        CHARACTER VARYING(255),
                                patronymic       CHARACTER VARYING(255),
                                username         CHARACTER VARYING(255),
                                password         CHARACTER VARYING(255),
                                phone            CHARACTER VARYING(255),
                                language         CHARACTER VARYING(255) DEFAULT 'ru',
                                status           CHARACTER VARYING(255) DEFAULT 'PENDING',
                                certificate_name CHARACTER VARYING(255),
                                activation_key   CHARACTER VARYING(255),
                                secret_key       CHARACTER VARYING(255),
                                company_id       UUID,
                                address_id       UUID,
                                FOREIGN KEY (address_id) REFERENCES address(id)
);

-- -------------------------------------
-- Table structure for "public"."branch"
-- -------------------------------------

CREATE TABLE IF NOT EXISTS PUBLIC.branch (
                                id                UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
                                deleted           BOOLEAN DEFAULT FALSE,
                                created_date      TIMESTAMP WITHOUT TIME ZONE,
                                updated_date      TIMESTAMP WITHOUT TIME ZONE,
                                name              CHARACTER VARYING(255),
                                status            CHARACTER VARYING(255),
                                company_id        UUID,
                                address_id        UUID,
                                FOREIGN KEY (address_id) REFERENCES address(id)
);

-- ------------------------------------------
-- Table structure for "public"."user_branch"
-- ------------------------------------------

CREATE TABLE IF NOT EXISTS PUBLIC.user_branch (
                                id              UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
                                user_id         UUID,
                                branch_id       UUID,
                                FOREIGN KEY (user_id) REFERENCES users(id),
                                FOREIGN KEY (branch_id) REFERENCES branch(id)
);

-- --------------------------------------
-- Table structure for "public"."company"
-- --------------------------------------

CREATE TABLE IF NOT EXISTS PUBLIC.company (
                                id             UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
                                deleted        BOOLEAN DEFAULT FALSE,
                                created_date   TIMESTAMP WITHOUT TIME ZONE,
                                updated_date   TIMESTAMP WITHOUT TIME ZONE,
                                tin            CHARACTER VARYING(255) UNIQUE,
                                name           CHARACTER VARYING(255),
                                brand          CHARACTER VARYING(255),
                                phone          CHARACTER VARYING(255),
                                director       CHARACTER VARYING(255),
                                accountant     CHARACTER VARYING(255),
                                address_id         	   UUID,
                                business_type_id       UUID,
                                activity_type_id       UUID,
                                FOREIGN KEY (address_id)       REFERENCES address(id),
                                FOREIGN KEY (business_type_id) REFERENCES business_type(id),
                                FOREIGN KEY (activity_type_id) REFERENCES activity_type(id)
);

-- -------------------------------------------
-- Table structure for "public"."company_bank"
-- -------------------------------------------

CREATE TABLE IF NOT EXISTS PUBLIC.company_bank (
                                id              UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
                                deleted         BOOLEAN DEFAULT FALSE,
                                account_number  CHARACTER VARYING(255),
                                oked            CHARACTER VARYING(255),
                                is_main         BOOLEAN DEFAULT FALSE,
                                company_id      UUID,
                                bank_id         UUID,
                                FOREIGN KEY (company_id) REFERENCES company(id),
                                FOREIGN KEY (bank_id) REFERENCES bank(id)
);

-- -------------------------------------
-- Table structure for "public"."unit"
-- -------------------------------------

CREATE TABLE IF NOT EXISTS PUBLIC.unit (
                                id            UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
                                deleted       BOOLEAN DEFAULT FALSE,
                                measure_id    BIGINT UNIQUE,
                                name_uz       CHARACTER VARYING(255),
                                name_en CHARACTER VARYING(255),
                                name_ru       CHARACTER VARYING(255)
);

-- ------------------------------------------------------------------
-- FOREIGN KEY structure for table "public"."users", field company_id
-- ------------------------------------------------------------------

ALTER TABLE PUBLIC.users  ADD FOREIGN KEY (company_id) REFERENCES company(id);

-- -------------------------------------------------------------------
-- FOREIGN KEY structure for table "public"."branch", field company_id
-- -------------------------------------------------------------------

ALTER TABLE PUBLIC.branch  ADD FOREIGN KEY (company_id) REFERENCES company(id);








