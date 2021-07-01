TRUNCATE users, accounts, transfers, transfer_types, transfer_statuses CASCADE;

--insert a user first
INSERT INTO users (user_id, username, password_hash)
VALUES (1, "fluffycat", "catlover"), (2, "bigdog", "doglover");

--insert an account
INSERT INTO accounts (account_id, user_id, balance)
VALUES (1, 1, 1000), (2, 2, 1000);

--insert into transfer_types
INSERT INTO transfer_types(transfer_type_id, transfer_type_desc)
VALUES (1, "request"), (2, "sent");

--insert into transfer_statuses
INSERT INTO transfer_statuses(transfer_statuses_id, transfer_statuses_desc)
VALUES (1, "pending"), (2,"accepted");

--insert into transfers
INSERT INTO transfers (transfer_id, transfer_type_id, transfer_statuses_id, account_from, account_to, amount)
VALUES (1, 1, 1, 1, 2, 10), (2, 2, 1, 2, 10);

