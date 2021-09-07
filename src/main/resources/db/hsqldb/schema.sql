DROP TABLE users IF EXISTS;
DROP Table bank IF EXISTS;

CREATE TABLE users (
  id INTEGER NOT NULL,
  card_num VARCHAR(10),
  pin VARCHAR(4),
  amount VARCHAR(10),
  PRIMARY KEY (id),
);

CREATE TABLE bank (
  denom_type VARCHAR(20) NOT NULL ,
  denom_value INTEGER NOT NULL ,
  denom_count INTEGER,
  PRIMARY KEY (denom_value)
);
