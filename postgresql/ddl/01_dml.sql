--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

-- Started on 2024-04-23 14:07:04 +03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3598 (class 0 OID 87498)
-- Dependencies: 218
-- Data for Name: sensor_type; Type: TABLE DATA; Schema: sensors; Owner: postgres
--

COPY sensors.sensor_type (id, type, unit) FROM stdin;
1	Pressure	bar
2	Voltage	voltage
3	Temperature	°C
4	Humidity	%
\.


--
-- TOC entry 3599 (class 0 OID 87541)
-- Dependencies: 219
-- Data for Name: sensor; Type: TABLE DATA; Schema: sensors; Owner: postgres
--

COPY sensors.sensor (uuid, name, model, range_from, range_to, type_id, location, description, dt_update, dt_create) FROM stdin;
9d43e9d2-287a-4fc9-9f6e-20375545ea84	First	First	0	100	4	myRoom	For smth	2024-04-23 11:05:50.889308	2024-04-23 11:05:50.889308
2b5f66ed-9463-4aec-b7bf-972c3c584f7e	Second	s2	0	100	2	kitchen	For smth	2024-04-23 11:15:00.149352	2024-04-23 11:15:00.149352
18f7ccf4-02c9-4ad6-badb-cb1bca153bac	third	t2sff	0	100	2	kitchen	For smth	2024-04-23 11:15:32.45827	2024-04-23 11:15:32.45827
84e9964b-e0d2-41cc-93e2-4eb9bae7732c	third	t2sff	0	100	2	kitchen	For smth	2024-04-23 11:15:34.408115	2024-04-23 11:15:34.408115
548b5eff-2820-4358-8186-d740c25f9115	fdskfs	sdgnelg	0	100	3	kitchen	For smth	2024-04-23 11:16:55.355329	2024-04-23 11:16:55.355329
\.


--
-- TOC entry 3605 (class 0 OID 0)
-- Dependencies: 217
-- Name: sensor_type_id_seq; Type: SEQUENCE SET; Schema: sensors; Owner: postgres
--

SELECT pg_catalog.setval('sensors.sensor_type_id_seq', 4, true);


-- Completed on 2024-04-23 14:07:05 +03

--
-- PostgreSQL database dump complete
--

