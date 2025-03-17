CREATE TABLE IF NOT EXISTS public.mytable (
	id int4 NULL,
	"name" varchar(50) NULL
);

-- Add sample data
INSERT INTO public.mytable
(id, "name")
VALUES(1, 'Jay');

INSERT INTO public.mytable
(id, "name")
VALUES(2, 'John');

INSERT INTO public.mytable
(id, "name")
VALUES(3, 'Jack');

INSERT INTO public.mytable
(id, "name")
VALUES(4, 'Charles');