CREATE TABLE empresas (
  id  int(11) DEFAULT NULL auto_increment PRIMARY KEY,
  fantasia VARCHAR(128),
  cnpj VARCHAR(20),
  criacao DATETIME,
  normal BOOLEAN,
  status int,
  created_at DATETIME,
  updated_at DATETIME
)