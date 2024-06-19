INSERT INTO nft_test.meta_status_machine (id, entity, entity_table_name, from_status, to_status, permission) VALUES (1, 'status', 'nft_player', 'START', 'OPEN', '');
INSERT INTO nft_test.meta_status_machine (id, entity, entity_table_name, from_status, to_status, permission) VALUES (2, 'status', 'nft_player', 'OPEN', 'SUBMITTED', null);
INSERT INTO nft_test.meta_status_machine (id, entity, entity_table_name, from_status, to_status, permission) VALUES (3, 'status', 'nft_player', 'SUBMITTED', 'APPROVED', null);
INSERT INTO nft_test.meta_status_machine (id, entity, entity_table_name, from_status, to_status, permission) VALUES (4, 'status', 'nft_player', 'APPROVED', 'END', null);
