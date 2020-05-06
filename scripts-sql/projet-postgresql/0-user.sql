

-- Supprime le schéma dreamdev

DROP SCHEMA IF EXISTS dreamdev CASCADE;


-- Crée l'utilisateur projet
-- (après l'avoir supprimé au préalable s'il existait déjà)

DO $code$
BEGIN
	IF EXISTS (SELECT  FROM pg_catalog.pg_roles WHERE rolname  = 'dreamdev')
	THEN
		REVOKE CREATE ON DATABASE postgres FROM dreamdev;
		DROP USER dreamdev;
	END IF;
END
$code$;

CREATE USER dreamdev WITH PASSWORD 'dreamdev';
GRANT CREATE ON DATABASE postgres TO dreamdev;

