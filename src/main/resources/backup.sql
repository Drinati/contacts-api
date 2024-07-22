BEGIN TRANSACTION;
CREATE TABLE Contact (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    number TEXT NOT NULL,
    email TEXT NOT NULL
);
INSERT INTO "Contact" VALUES('de561161-dd11-4f78-ac06-c6b80308f4c8','John Doe','123456789','john.doe@example.com');
INSERT INTO "Contact" VALUES('d9790aca-3120-4d96-a643-c262d15e6f3f','Jane Smith','987654321','jane.smith@example.com');
INSERT INTO "Contact" VALUES('1c2593d8-46e1-43f3-b11a-3053261fbfbc','Alice Johnson','555555555','alice.johnson@example.com');
COMMIT;
