@startuml WarehouseHub_ER
hide circle
skinparam linetype ortho
skinparam class {
  BackgroundColor LightBlue
  BorderColor DarkBlue
  ArrowColor DarkBlue
}
skinparam note {
  BackgroundColor LightYellow
  BorderColor Orange
}

' ================= ENTITIES =================

entity "users" as users {
  * id : BIGSERIAL <<PK>>
  --
  * email : VARCHAR(255) <<UNIQUE>>
  * full_name : VARCHAR(255)
  * role : VARCHAR(32)  <<CUSTOMER|COURIER|ADMIN>>
  * password_hash : VARCHAR(255)
  * created_at : TIMESTAMP
}

entity "warehouses" as warehouses {
  * id : BIGSERIAL <<PK>>
  --
  * name : VARCHAR(255)
  * address : VARCHAR(512)
}

entity "suppliers" as suppliers {
  * id : BIGSERIAL <<PK>>
  --
  * name : VARCHAR(255)
  * contact_email : VARCHAR(255)
}

entity "products" as products {
  * id : BIGSERIAL <<PK>>
  --
  * sku : VARCHAR(64) <<UNIQUE>>
  * name : VARCHAR(255)
  * price : NUMERIC(12,2)
  * created_at : TIMESTAMP
}

entity "supplier_products" as supplier_products {
  * supplier_id : BIGINT <<FK>>
  * product_id : BIGINT <<FK>>
  --
  * supply_price : NUMERIC(12,2)
  * lead_time_days : INT
}

entity "stock_items" as stock_items {
  * id : BIGSERIAL <<PK>>
  --
  * product_id : BIGINT <<FK>>
  * warehouse_id : BIGINT <<FK>>
  * quantity : INT
  * reserved : INT
}

entity "orders" as orders {
  * id : BIGSERIAL <<PK>>
  --
  * customer_id : BIGINT <<FK>>
  * status : VARCHAR(32) <<CREATED|PAID|SHIPPED|DELIVERED|CANCELLED>>
  * total_amount : NUMERIC(12,2)
  * created_at : TIMESTAMP
}

entity "order_items" as order_items {
  * id : BIGSERIAL <<PK>>
  --
  * order_id : BIGINT <<FK>>
  * product_id : BIGINT <<FK>>
  * quantity : INT
  * price_at_order : NUMERIC(12,2)
}

entity "shipments" as shipments {
  * id : BIGSERIAL <<PK>>
  --
  * order_id : BIGINT <<FK, UNIQUE>>
  * courier_id : BIGINT <<FK>>
  * status : VARCHAR(32) <<PENDING|IN_TRANSIT|DELIVERED|FAILED>>
  * dispatched_at : TIMESTAMP
  * delivered_at : TIMESTAMP
}

entity "stock_movements" as stock_movements {
  * id : BIGSERIAL <<PK>>
  --
  * stock_item_id : BIGINT <<FK>>
  * delta : INT
  * reason : VARCHAR(64) <<ORDER_RESERVE|ORDER_SHIP|SUPPLY|CANCEL|RETURN>>
  * created_at : TIMESTAMP
}

' ================= RELATIONSHIPS =================

users ||--o{ orders : "customer_id"
users ||--o{ shipments : "courier_id"

orders ||--|{ order_items : "order_id"
orders ||--o| shipments : "order_id (1:1)"

products ||--o{ order_items : "product_id"
products ||--o{ stock_items : "product_id"

warehouses ||--o{ stock_items : "warehouse_id"

suppliers ||--o{ supplier_products : "supplier_id"
products  ||--o{ supplier_products : "product_id"

stock_items ||--o{ stock_movements : "stock_item_id"

' ================= NOTES =================

note bottom of supplier_products
  **Many-to-Many с доп. полями**
  suppliers ↔ products
  доп: supply_price, lead_time_days
  PK: (supplier_id, product_id)
end note

note bottom of stock_items
  **Many-to-Many с доп. полями**
  products ↔ warehouses
  доп: quantity, reserved
  UNIQUE: (product_id, warehouse_id)
end note

note right of orders
  Транзакция №1:
  создание заказа + резерв остатков
end note

note right of shipments
  Транзакция №2:
  списание остатков + история + статусы
end note

note bottom of stock_movements
  Транзакция №3:
  приход товара (SUPPLY)
end note

@enduml