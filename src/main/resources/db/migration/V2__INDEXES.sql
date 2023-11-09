-- ----------------------------------
-- INDEX for "public"."activity_type"
-- ----------------------------------

CREATE INDEX IF NOT EXISTS activity_type_name_ru_idx ON public.activity_type(name_ru);
CREATE INDEX IF NOT EXISTS activity_type_name_uz_idx ON public.activity_type(name_uz);
CREATE INDEX IF NOT EXISTS activity_type_name_en_idx ON public.activity_type(name_en);
CREATE INDEX IF NOT EXISTS activity_type_code_idx ON public.activity_type(code);
CREATE INDEX IF NOT EXISTS activity_type_search_keys_index ON public.activity_type(code, name_ru, name_en, name_uz);

-- -------------------------
-- INDEX for "public"."bank"
-- -------------------------

CREATE INDEX IF NOT EXISTS bank_name_ru_idx ON public.bank(name_ru);
CREATE INDEX IF NOT EXISTS bank_name_uz_idx ON public.bank(name_uz);
CREATE INDEX IF NOT EXISTS bank_name_en_idx ON public.bank(name_en);
CREATE INDEX IF NOT EXISTS bank_code_idx ON public.bank(code);
CREATE INDEX IF NOT EXISTS bank_search_keys_index ON public.bank(code, name_ru, name_en, name_uz);

-- ---------------------------
-- INDEX for "public"."branch"
-- ---------------------------

CREATE INDEX IF NOT EXISTS branch_name_idx ON public.branch(name);
CREATE INDEX IF NOT EXISTS branch_company_id_idx ON public.branch(company_id);
CREATE INDEX IF NOT EXISTS branch_id_idx ON public.branch(id);

-- -------------------------
-- INDEX for "public"."unit"
-- -------------------------

CREATE INDEX IF NOT EXISTS measure_id_idx ON public.unit(measure_id);

-- -----------------------------
-- INDEX for "public"."district"
-- -----------------------------

CREATE INDEX IF NOT EXISTS district_name_ru_idx ON public.district(name_ru);
CREATE INDEX IF NOT EXISTS district_name_uz_idx ON public.district(name_uz);
CREATE INDEX IF NOT EXISTS district_name_en_idx ON public.district(name_en);
CREATE INDEX IF NOT EXISTS district_code_idx ON public.district(code);
CREATE INDEX IF NOT EXISTS district_region_idx ON public.district(region_code);
CREATE INDEX IF NOT EXISTS district_search_keys_index ON public.district(code, name_ru, name_en, name_uz);

-- ---------------------------
-- INDEX for "public"."region"
-- ---------------------------

CREATE INDEX IF NOT EXISTS region_name_ru_idx ON public.region(name_ru);
CREATE INDEX IF NOT EXISTS region_name_uz_idx ON public.region(name_uz);
CREATE INDEX IF NOT EXISTS region_name_en_idx ON public.region(name_en);
CREATE INDEX IF NOT EXISTS region_code_idx ON public.region(code);
CREATE INDEX IF NOT EXISTS region_search_keys_index ON public.region(code, name_ru, name_en, name_uz);

-- ----------------------------
-- INDEX for "public"."company"
-- ----------------------------

CREATE INDEX IF NOT EXISTS company_id_idx ON public.company(id);
CREATE INDEX IF NOT EXISTS company_tin_idx ON public.company(tin);
CREATE INDEX IF NOT EXISTS company_name_idx ON public.company(name);
CREATE INDEX IF NOT EXISTS company_search_keys_index ON public.company(tin, name);

-- ---------------------------------
-- INDEX for "public"."company_bank"
-- ---------------------------------

CREATE INDEX IF NOT EXISTS company_bank_company_id_idx ON public.company_bank(company_id);
CREATE INDEX IF NOT EXISTS company_bank_bank_id_idx ON public.company_bank(bank_id);
