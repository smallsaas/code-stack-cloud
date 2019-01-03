DELETE FROM meta_status_machine WHERE id = 111111;
DELETE FROM meta_status_machine WHERE id = 222222;

INSERT INTO meta_status_machine (id, entity, entity_table_name, from_status, to_status)
VALUES (111111, 'Invoice', 'invoice', 'OPEN', 'SUBMITTED');

INSERT INTO meta_status_machine (id, entity, entity_table_name, from_status, to_status)
VALUES (222222, 'Invoice', 'invoice', 'SUBMITTED', 'APPROVED');