-- CRIA DATABASE
CREATE DATABASE `hotel`;

-- ENTRA NA DATABASE
USE `hotel`;

-- CREATE TABLES
-- Cria a tabela Hóspedes
CREATE TABLE `users` (
	`cpf` VARCHAR(14) UNIQUE NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	`email` VARCHAR(255) UNIQUE NOT NULL,
	`password` VARCHAR(255) NOT NULL,
	`contact_number` VARCHAR(20),
	`address` VARCHAR(255),
	`level` ENUM('USER', 'EMPLOYEE', 'ADMIN'),
	PRIMARY KEY (cpf)
);

-- Cria a tabela Quarto
CREATE TABLE `rooms` (
	`room` INT(2) NOT NULL,
	`image` LONGTEXT,
	`type` ENUM('NORMAL', 'LUXO', 'PRESIDENCIAL', 'SUITE') NOT NULL,
	`beds` INT(2) NOT NULL,
	`extension_phone` INT(2) NOT NULL,
	`status` ENUM('DISPONÍVEL', 'RESERVADO', 'MANUTENCAO'),
	`daily_rate` FLOAT(5) NOT NULL,
	PRIMARY KEY (room)
);

-- Cria a tabela Accomodations
CREATE TABLE `accomodations` (
	`id` INT(2) NOT NULL AUTO_INCREMENT,
	`room` INT(2) NOT NULL,
	`cpf` VARCHAR(14) NOT NULL,
	`type` ENUM('RESERVA', 'ALUGUEL') NOT NULL,
	`entry_date` DATE NOT NULL,
	`departure_date` DATE NOT NULL,
	`status` ENUM('PENDENTE', 'EM ANDAMENTO', 'CANCELADA'),
	`value` FLOAT(10) NOT NULL,
	PRIMARY KEY(id)
) ;

-- Cria a tabela de Serviços Consumidos
CREATE TABLE `consumed_services` (
	`id` INT(2) NOT NULL AUTO_INCREMENT,
	`accomodation_id` INT(3) NOT NULL,
	`service_id` INT(2) NOT NULL,
	`name` VARCHAR(100),
	`value` FLOAT(5) NOT NULL,
	PRIMARY KEY(id)
);

-- Cria tabela Serviços
CREATE TABLE `services` (
	`id` INT(2) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
	`description` VARCHAR(255) NOT NULL,
	`value` FLOAT(5) NOT NULL,
	PRIMARY KEY(id)
);

-- Cria tabela pagamentos
CREATE TABLE `payment` (
	`id` INT(2) NOT NULL AUTO_INCREMENT,
	`accomodation_id` INT(2) NOT NULL,
	`tax` FLOAT(5),
	`services_value` FLOAT(5),
	`total_value` FLOAT(10) NOT NULL,
	`paid` FLOAT(10),
	`payment_type` ENUM('DINHEIRO', 'CARTÃO', 'BOLETO'),
	`status` ENUM('PAGO', 'EM PAGTO', 'CANCELADO', 'PROBLEMA', 'PENDENTE'),
	PRIMARY KEY(id)
);

-- Insere os usuários na tabela users.
INSERT INTO `users` (`cpf`, `name`, `email`, `password`, `contact_number`, `address`, `level`) VALUES 
('000.000.000-00', 'Tatiana Vitorello', 'vitorellotts@gmail.com', 'teste123', '00000-0000', 'Rua Cedral', 'ADMIN'),
('111.111.111-11', 'Fulano da Silva', 'fulanosilva@gmail.com', 'teste1', '11111-1111', 'Rua Central', 'EMPLOYEE'),
('222.222.222-22', 'Ciclano Castro', 'ciclanocastro@gmail.com', 'teste2', '22222-2222', 'Vila Luzita', 'USER'),
('333.333.333-33', 'Beltrano Souza', 'beltranosouza@gmail.com', 'teste3', '33333-3333', 'Vila Rica', 'USER'),
('444.444.444-44', 'Moquidesia Antunes', 'moquidesiantunes@gmail.com', 'teste3', '44444-4444', 'Vila Mestre', 'USER');

-- Insert into Rooms
INSERT INTO `rooms` (`room`, `type`, `beds`, `extension_phone`, `status`, `daily_rate`) VALUES
('01', 'NORMAL', '2', '01', 'DISPONÍVEL', '150.00'),
('02', 'NORMAL', '1', '02', 'DISPONÍVEL', '150.00'),
('03', 'NORMAL', '1', '03', 'RESERVADO', '150.00'),
('04', 'LUXO', '2', '04', 'RESERVADO', '250.00'),
('05', 'LUXO', '2', '05', 'DISPONÍVEL', '250.00'),
('06', 'LUXO', '3', '06', 'MANUTENCAO', '250.00'),
('07', 'PRESIDENCIAL', '1', '07', 'RESERVADO', '500.00'),
('08', 'SUITE', '4', '08', 'DISPONÍVEL', '200.00');

-- Insert into Services
INSERT INTO `services` (`name`, `description`, `value`) VALUES
('Restaurante', 'Restaurante que atende a todos os gostos.', '75.00'),
('Lavanderia', 'Lava todas as suas roupas.', '50.00'),
('Academia', 'Venha ser fitness com a gente!', '40.00'),
('Balada', 'Que tal se divertir?', '60.00'),
('Sauna', 'Venha relaxar!', '20.00');

-- Insert into Consumed Services
INSERT INTO `consumed_services` (`accomodation_id`, `service_id`, `value`) VALUES
('1', '1', '75.00'),
('2', '4', '60.00'),
('1', '2', '50.00'),
('3', '3', '40.00'),
('3', '5', '20.00'),
('1', '4', '60.00');

-- Foi separado um campo para realizar a diferenciação entre Alugel e Reserva.
-- Insere os respectivos alugueis na tabela de Hospedagem.
INSERT INTO `accomodations` (`room`, `cpf`, `type`, `entry_date`, `departure_date`, `status`, `value`) VALUES
('5', '333.333.333-33', 'ALUGUEL', '2019-06-07', '2019-06-09', 'EM ANDAMENTO', '750.00'),
('7', '444.444.444-44', 'ALUGUEL', '2019-06-07', '2019-06-09', 'EM ANDAMENTO', '750.00'),
('8', '222.222.222-22', 'ALUGUEL', '2019-05-17', '2019-06-23', 'PENDENTE', '1200.00'); 

-- Insere as respectivas reservas na tabela de hospedagens.
INSERT INTO `accomodations` (`room`, `cpf`, `type`, `entry_date`, `departure_date`, `status`, `value`) VALUES
('1', '222.222.222-22', 'RESERVA', '2019-06-03', '2019-06-07', 'PENDENTE', '750.00'),
('2', '333.333.333-33', 'RESERVA', '2019-07-01', '2019-07-05', 'PENDENTE', '600.00'),
('4', '444.444.444-44', 'RESERVA', '2019-06-20', '2019-06-23', 'PENDENTE', '750.00');

-- Insere os dados na tabela de pagamentos.
INSERT INTO `payment` (`accomodation_id`, `tax`, `services_value`, `total_value`, `payment_type`, `status`) VALUES
('1', '50.00', '185.00', '985.00', 'CARTÃO', 'PENDENTE'),
('2', '00.00', '60.00', '810.00', 'DINHEIRO', 'PAGO'),
('3', '00.00', '60.00', '810.00', 'CARTÃO', 'PENDENTE');

-- CRIAÇÃO DAS VIEWS
-- View que retorna apenas os usuários, excluindo os funcionários e administradores. 
CREATE VIEW `customer` AS
SELECT `u`.`name` AS 'Nome',
		`u`.`cpf` AS 'CPF', 
		`u`.`level` AS 'Tipo de Usuário', 
		`acc`.`type` AS 'Tipo de Hospedagem',
		`acc`.`value` AS 'Valor das Diárias'
FROM `users` AS `u`
INNER JOIN `accomodations` AS `acc`
ON `u`.`cpf` = `acc`.`cpf`
WHERE `u`.`level` = 'USER';

-- Seleciona a view "Customers".
SELECT * FROM `customer`;

-- View que retorna apenas as hospedagens que foram registradas como aluguel
-- e que são melhor que a média dos valores.
CREATE VIEW `alugueis` AS
SELECT `id` AS `Número do aluguel`,
	`room` AS `Quarto`,
	`cpf` AS `Cpf do Hóspede`,
	`type` AS `Tipo de Hospedagem`,
	`entry_date` AS `Data de Entrada`,
	`departure_date` AS `Data de Saída`,
	`status` AS `Estado da Hospedagem`,
	`value` AS `Valor das Diárias`
FROM `accomodations`
WHERE `type` = 'ALUGUEL' AND `value` > (SELECT AVG(`value`) FROM `accomodations`);

-- Seleciona a View "alugueis".
SELECT * FROM `alugueis`;

-- STORED PROCEDURE
-- Stored Procedure que retorna os registros de acordo com o tipo de hospedagem.
CREATE PROCEDURE Busca(in tipo VARCHAR(20))
SELECT `room` AS `Quarto`,
	`cpf` AS `CPF do Hóspede`,
	`type` AS `Tipo de Hospedagem`,
	`entry_date` AS `Data de Entrada`,
	`departure_date` AS `Data de Saída`,
	`status` AS `Estado da Hospedagem`,
	`value` AS `Valor da Hospedagem`
FROM `accomodations`
WHERE `type` = tipo;

-- Executa a Procedure
CALL Busca('RESERVA');

-- CRIAÇÃO DA SUBCONSULTA
-- Subconsulta que retorna apenas as hospedagens 
-- com valores maiores do que a média.
SELECT * FROM `accomodations`
WHERE `value` >
(SELECT AVG(`value`) FROM `accomodations`);

-- CRIAÇÃO DO TRIGGER
-- Trigger que atualiza o valor dos serviços consumidos
-- na tabela "payment".
DELIMITER ##
CREATE TRIGGER AttValorServicos AFTER INSERT
ON `consumed_services` 
FOR EACH ROW
BEGIN
DECLARE newVal DECIMAL;
SET newVal = (SELECT `value` FROM `consumed_services` WHERE `value` = NEW.`value` LIMIT 1);
UPDATE `payment` SET `services_value` = `services_value` + newVal WHERE `accomodation_id` = NEW.`accomodation_id`;
END##;

-- Seleciona a tabela payment para mostrar antes do TRIGGER
SELECT * FROM `payment` WHERE `accomodation_id` = 1;

-- Insere novo serviço consumido
INSERT INTO `consumed_services` (`accomodation_id`, `service_id`, `name`, `value`)
VALUES ('1', '1', 'Restaurante', '75.00');

-- Seleciona a tabela payment para mostrar depois do TRIGGER
SELECT * FROM `payment` WHERE `accomodation_id` = 1;