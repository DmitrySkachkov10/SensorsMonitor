--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

-- Started on 2024-04-23 15:00:26 +03

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
-- TOC entry 3591 (class 0 OID 87473)
-- Dependencies: 216
-- Data for Name: user; Type: TABLE DATA; Schema: users; Owner: postgres
--

COPY users."user" (uuid, login, password, role) FROM stdin;
82f123e4-7bd9-46ff-854d-ce43d5587f54	admin	$2a$10$l/dMRQOxG72Fffvb.6xaZeTLyrckvPQWYB6CVXxgDfcvJIhf2a8Bi	ADMINISTRATOR
ddc29879-102d-4a41-90f6-02ea7df23de6	viewer	$2a$10$sMfPngDrPQ4EkWz56Zbu0etja63Loh8G9EZ1kW3BvLVGXiwaIAVSG	VIEWER
\.


-- Completed on 2024-04-23 15:00:26 +03

--
-- PostgreSQL database dump complete
--

