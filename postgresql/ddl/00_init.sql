--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

-- Started on 2024-04-23 14:08:41 +03

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
-- TOC entry 7 (class 2615 OID 87478)
-- Name: sensors; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA sensors;


ALTER SCHEMA sensors OWNER TO dmitry;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 87541)
-- Name: sensor; Type: TABLE; Schema: sensors; Owner: postgres
--

CREATE TABLE sensors.sensor (
    uuid uuid NOT NULL,
    name character varying(30) NOT NULL,
    model character varying(15) NOT NULL,
    range_from integer,
    range_to integer,
    type_id integer,
    location character varying(40),
    description character varying(200),
    dt_update timestamp without time zone,
    dt_create timestamp without time zone
);


ALTER TABLE sensors.sensor OWNER TO dmitry;

--
-- TOC entry 218 (class 1259 OID 87498)
-- Name: sensor_type; Type: TABLE; Schema: sensors; Owner: postgres
--

CREATE TABLE sensors.sensor_type (
    id integer NOT NULL,
    type character varying(32) NOT NULL,
    unit character varying(16)
);


ALTER TABLE sensors.sensor_type OWNER TO dmitry;

--
-- TOC entry 217 (class 1259 OID 87497)
-- Name: sensor_type_id_seq; Type: SEQUENCE; Schema: sensors; Owner: postgres
--

CREATE SEQUENCE sensors.sensor_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sensors.sensor_type_id_seq OWNER TO dmitry;

--
-- TOC entry 3603 (class 0 OID 0)
-- Dependencies: 217
-- Name: sensor_type_id_seq; Type: SEQUENCE OWNED BY; Schema: sensors; Owner: postgres
--

ALTER SEQUENCE sensors.sensor_type_id_seq OWNED BY sensors.sensor_type.id;


--
-- TOC entry 3448 (class 2604 OID 87501)
-- Name: sensor_type id; Type: DEFAULT; Schema: sensors; Owner: postgres
--

ALTER TABLE ONLY sensors.sensor_type ALTER COLUMN id SET DEFAULT nextval('sensors.sensor_type_id_seq'::regclass);


--
-- TOC entry 3454 (class 2606 OID 87545)
-- Name: sensor sensor_pkey; Type: CONSTRAINT; Schema: sensors; Owner: postgres
--

ALTER TABLE ONLY sensors.sensor
    ADD CONSTRAINT sensor_pkey PRIMARY KEY (uuid);


--
-- TOC entry 3450 (class 2606 OID 87505)
-- Name: sensor_type sensor_type_name_unit_key; Type: CONSTRAINT; Schema: sensors; Owner: postgres
--

ALTER TABLE ONLY sensors.sensor_type
    ADD CONSTRAINT sensor_type_name_unit_key UNIQUE (type, unit);


--
-- TOC entry 3452 (class 2606 OID 87503)
-- Name: sensor_type sensor_type_pkey; Type: CONSTRAINT; Schema: sensors; Owner: postgres
--

ALTER TABLE ONLY sensors.sensor_type
    ADD CONSTRAINT sensor_type_pkey PRIMARY KEY (id);


--
-- TOC entry 3455 (class 2606 OID 87546)
-- Name: sensor sensor_type_id_fkey; Type: FK CONSTRAINT; Schema: sensors; Owner: postgres
--

ALTER TABLE ONLY sensors.sensor
    ADD CONSTRAINT sensor_type_id_fkey FOREIGN KEY (type_id) REFERENCES sensors.sensor_type(id);


-- Completed on 2024-04-23 14:08:42 +03

--
-- PostgreSQL database dump complete
--

