
INSERT INTO role_security(id, role) VALUES ('cfbbecba-069d-4b9f-9d6d-07119f02ae8a','ROLE_INVILLIA');

INSERT INTO user_security(id, password, email, username, enabled, last_password_reset_date) VALUES ('953b9ead-012b-43d0-a0f5-af12da0236b4','$2a$10$CzT5U6u3GHIPrxDj2VaDzeHEW91PC.XaNU2dayiCRfffYU/SsG/R6','invillia@invillia.com.br','invillia',1,now());

INSERT INTO users_roles(user_security_id, role_security_id)VALUES ('953b9ead-012b-43d0-a0f5-af12da0236b4','cfbbecba-069d-4b9f-9d6d-07119f02ae8a');