ALTER TABLE supplier_products DROP CONSTRAINT supplier_products_pkey;
ALTER TABLE supplier_products ADD COLUMN id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY;
ALTER TABLE supplier_products ALTER COLUMN supplier_id SET NOT NULL;
ALTER TABLE supplier_products ALTER COLUMN product_id SET NOT NULL;