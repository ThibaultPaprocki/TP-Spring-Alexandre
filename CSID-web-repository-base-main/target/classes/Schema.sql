CREATE TABLE public.repository
(
    name character varying(32) NOT NULL,
    owner character varying(32),
    forks int,
    open_issues int,
    CONSTRAINT repository_pkey PRIMARY KEY (name)
);