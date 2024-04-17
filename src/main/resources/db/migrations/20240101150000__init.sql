
CREATE TABLE public.action_log (
    action_log_id integer NOT NULL,
    username character varying(20) NOT NULL,
    module character varying(20) NOT NULL,
    action character varying(20) NOT NULL,
    message text,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    response_status character varying(3) NOT NULL,
    method character varying(8) NOT NULL,
    endpoint character varying(255) NOT NULL,
    ip_address character varying(50) NOT NULL,
    ip_local character varying(50) NOT NULL,
    before_value text,
    after_value text
);


--
-- TOC entry 220 (class 1259 OID 33339)
-- Name: menus; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.menus (
    menu_id integer NOT NULL,
    parent_id integer,
    permission_id integer NOT NULL,
    route character varying(50) NOT NULL,
    title character varying(255) NOT NULL,
    icon character varying(50),
    order_item integer NOT NULL,
    active boolean DEFAULT true NOT NULL
);


--
-- TOC entry 217 (class 1259 OID 33320)
-- Name: parameters; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.parameters (
    parameter_id integer NOT NULL,
    parameter_key character varying(20) NOT NULL,
    value character varying(100) NOT NULL,
    description character varying(100) NOT NULL,
    editable boolean DEFAULT true NOT NULL,
    updated_by integer NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


--
-- TOC entry 223 (class 1259 OID 33389)
-- Name: password_recovery; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.password_recovery (
    password_recovery_id bigint NOT NULL,
    user_id integer NOT NULL,
    password character varying(100) NOT NULL,
    recovered boolean DEFAULT false NOT NULL,
    expiration timestamp without time zone NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


--
-- TOC entry 218 (class 1259 OID 33329)
-- Name: permissions; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.permissions (
    permission_id integer NOT NULL,
    name character varying(25) NOT NULL,
    description character varying(50) NOT NULL,
    created_by integer,
    created_at timestamp without time zone,
    updated_by integer,
    updated_at timestamp without time zone
);


--
-- TOC entry 221 (class 1259 OID 33355)
-- Name: role_permissions; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.role_permissions (
    role_permission_id integer NOT NULL,
    role_id smallint NOT NULL,
    permission_id integer NOT NULL,
    created_by integer,
    created_at timestamp without time zone,
    updated_by integer,
    updated_at timestamp without time zone
);


--
-- TOC entry 219 (class 1259 OID 33334)
-- Name: roles; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.roles (
    role_id smallint NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(20) NOT NULL,
    description character varying(40) NOT NULL,
    created_by integer,
    created_at timestamp without time zone,
    updated_by integer,
    updated_at timestamp without time zone
);


--
-- TOC entry 224 (class 1259 OID 33408)
-- Name: seq_action_log_id; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.seq_action_log_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


--
-- TOC entry 225 (class 1259 OID 33409)
-- Name: seq_menus_id; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.seq_menus_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


--
-- TOC entry 226 (class 1259 OID 33410)
-- Name: seq_password_recovery_id; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.seq_password_recovery_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 227 (class 1259 OID 33411)
-- Name: seq_permissions_id; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.seq_permissions_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


--
-- TOC entry 228 (class 1259 OID 33412)
-- Name: seq_role_permissions_id; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.seq_role_permissions_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


--
-- TOC entry 229 (class 1259 OID 33413)
-- Name: seq_roles_id; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.seq_roles_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 32767
    CACHE 1;


--
-- TOC entry 230 (class 1259 OID 33415)
-- Name: seq_users_id; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.seq_users_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


--
-- TOC entry 222 (class 1259 OID 33372)
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    role_id smallint NOT NULL,
    username character varying(20) NOT NULL,
    password character varying(100) NOT NULL,
    change_password boolean DEFAULT false NOT NULL,
    single_session boolean DEFAULT true NOT NULL,
    current_token character varying(255),
    active boolean DEFAULT true NOT NULL,
    name character varying(100),
    lastname character varying(100),
    dni character varying(12),
    created_by integer,
    created_at timestamp without time zone,
    updated_by integer,
    updated_at timestamp without time zone
);


--
-- TOC entry 4860 (class 0 OID 33339)
-- Dependencies: 220
-- Data for Name: menus; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.menus VALUES (1, NULL, 1, 'home', 'Home2', 'icon', 1, true);


--
-- TOC entry 4857 (class 0 OID 33320)
-- Dependencies: 217
-- Data for Name: parameters; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.parameters VALUES (1, 'AUTH_VGR', 'S', 'Validación de Google Recaptcha', true, 1, '2024-01-06 11:02:39.461');
INSERT INTO public.parameters VALUES (2, 'AUTH_IDLE_TIME', '300', 'Tiempo límite de inactividad en segundos', true, 1, '2024-01-06 11:02:39.461');
INSERT INTO public.parameters VALUES (3, 'AUTH_PASSWORD_LENGTH', '12', 'Tamaño de contraseña por defecto', true, 1, '2024-01-06 11:02:39.461');
INSERT INTO public.parameters VALUES (4, 'AUTH_PASSWORD_EXPMIN', '300', 'Tiempo de expiración de contaseña en minutos', true, 1, '2024-01-06 11:02:39.461');


--
-- TOC entry 4858 (class 0 OID 33329)
-- Dependencies: 218
-- Data for Name: permissions; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.permissions VALUES (1, 'auth:basic', 'Permisos Basicos', NULL, NULL, 1, '2024-01-29 16:11:07.366');
INSERT INTO public.permissions VALUES (11, 'permission:index', 'Listar todos los permisos', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (12, 'permission:show', 'Consultar un permiso', NULL, NULL, 1, '2024-01-29 16:07:44.437');
INSERT INTO public.permissions VALUES (13, 'permission:store', 'Crear un permiso', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (14, 'permission:update', 'Modificar un permiso', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (15, 'permission:delete', 'Eliminar un permiso', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (16, 'role:index', 'Listar todos los roles', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (17, 'role:show', 'Consultar un rol', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (18, 'role:store', 'Crear un rol', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (19, 'role:update', 'Modificar un role', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (20, 'role:delete', 'Eliminar un rol', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (21, 'role:permission-by-role', 'Listar permisos por id de role', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (22, 'menu:index', 'Listar todos los menu', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (23, 'menu:show', 'Consultar un menu', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (24, 'menu:store', 'Crear un menu', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (25, 'menu:update', 'Modificar un menu', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (26, 'menu:delete', 'Eliminar un menu', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (27, 'user:index', 'Listar todos los usuarios', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (28, 'user:show', 'Consultar un usuario', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (29, 'user:store', 'Crear un usuario', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (30, 'user:update', 'Modificar un usuario', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (31, 'user:delete', 'Eliminar un usuario', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (32, 'parameter:index', 'Listar todos los parametros', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (33, 'parameter:show', 'Consultar parametro', NULL, NULL, NULL, NULL);
INSERT INTO public.permissions VALUES (34, 'parameter:update', 'Modificar parametro', NULL, NULL, NULL, NULL);


--
-- TOC entry 4861 (class 0 OID 33355)
-- Dependencies: 221
-- Data for Name: role_permissions; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.role_permissions VALUES (264, 1, 1, 2, '2024-01-31 18:43:55.410467', 2, '2024-01-31 18:43:55.410467');
INSERT INTO public.role_permissions VALUES (265, 1, 11, 2, '2024-01-31 18:43:55.411669', 2, '2024-01-31 18:43:55.411669');
INSERT INTO public.role_permissions VALUES (266, 1, 12, 2, '2024-01-31 18:43:55.41267', 2, '2024-01-31 18:43:55.41267');
INSERT INTO public.role_permissions VALUES (267, 1, 13, 2, '2024-01-31 18:43:55.41267', 2, '2024-01-31 18:43:55.41267');
INSERT INTO public.role_permissions VALUES (268, 1, 14, 2, '2024-01-31 18:43:55.413671', 2, '2024-01-31 18:43:55.413671');
INSERT INTO public.role_permissions VALUES (269, 1, 15, 2, '2024-01-31 18:43:55.413671', 2, '2024-01-31 18:43:55.413671');
INSERT INTO public.role_permissions VALUES (270, 1, 16, 2, '2024-01-31 18:43:55.414671', 2, '2024-01-31 18:43:55.414671');
INSERT INTO public.role_permissions VALUES (271, 1, 17, 2, '2024-01-31 18:43:55.414671', 2, '2024-01-31 18:43:55.414671');
INSERT INTO public.role_permissions VALUES (272, 1, 18, 2, '2024-01-31 18:43:55.414671', 2, '2024-01-31 18:43:55.414671');
INSERT INTO public.role_permissions VALUES (273, 1, 19, 2, '2024-01-31 18:43:55.415714', 2, '2024-01-31 18:43:55.415714');
INSERT INTO public.role_permissions VALUES (274, 1, 20, 2, '2024-01-31 18:43:55.415714', 2, '2024-01-31 18:43:55.415714');
INSERT INTO public.role_permissions VALUES (275, 1, 21, 2, '2024-01-31 18:43:55.416751', 2, '2024-01-31 18:43:55.416751');
INSERT INTO public.role_permissions VALUES (276, 1, 22, 2, '2024-01-31 18:43:55.416751', 2, '2024-01-31 18:43:55.416751');
INSERT INTO public.role_permissions VALUES (277, 1, 23, 2, '2024-01-31 18:43:55.417692', 2, '2024-01-31 18:43:55.417692');
INSERT INTO public.role_permissions VALUES (278, 1, 24, 2, '2024-01-31 18:43:55.417692', 2, '2024-01-31 18:43:55.417692');
INSERT INTO public.role_permissions VALUES (279, 1, 25, 2, '2024-01-31 18:43:55.418673', 2, '2024-01-31 18:43:55.418673');
INSERT INTO public.role_permissions VALUES (280, 1, 26, 2, '2024-01-31 18:43:55.418673', 2, '2024-01-31 18:43:55.418673');
INSERT INTO public.role_permissions VALUES (281, 1, 27, 2, '2024-01-31 18:43:55.419757', 2, '2024-01-31 18:43:55.419757');
INSERT INTO public.role_permissions VALUES (282, 1, 28, 2, '2024-01-31 18:43:55.419757', 2, '2024-01-31 18:43:55.419757');
INSERT INTO public.role_permissions VALUES (283, 1, 29, 2, '2024-01-31 18:43:55.420671', 2, '2024-01-31 18:43:55.420671');
INSERT INTO public.role_permissions VALUES (284, 1, 30, 2, '2024-01-31 18:43:55.420671', 2, '2024-01-31 18:43:55.420671');
INSERT INTO public.role_permissions VALUES (285, 1, 31, 2, '2024-01-31 18:43:55.421249', 2, '2024-01-31 18:43:55.421249');
INSERT INTO public.role_permissions VALUES (286, 1, 32, 2, '2024-01-31 18:43:55.421249', 2, '2024-01-31 18:43:55.421249');
INSERT INTO public.role_permissions VALUES (287, 1, 33, 2, '2024-01-31 18:43:55.422327', 2, '2024-01-31 18:43:55.422327');
INSERT INTO public.role_permissions VALUES (288, 1, 34, 2, '2024-01-31 18:43:55.422327', 2, '2024-01-31 18:43:55.422327');

--
-- TOC entry 4859 (class 0 OID 33334)
-- Dependencies: 219
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.roles VALUES (1, 'sa', 'Superadmin', 'Usuario Superadmin', NULL, NULL, NULL, NULL);
INSERT INTO public.roles VALUES (2, 'user', 'Solicitante', 'Usuario Solicitante', NULL, NULL, NULL, NULL);



--
-- TOC entry 4862 (class 0 OID 33372)
-- Dependencies: 222
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.users VALUES (1, 1, 'dreales4@cuc.edu.co', '$2a$10$tlRuxRGQ2DHkXJWV/6F3kuUurNB1NoiADnz2RwM.sJiGQLEXkOgam', true, true, NULL, true, 'Super', 'Admin', '10482899512', NULL, NULL, NULL, NULL);


--
-- TOC entry 4878 (class 0 OID 0)
-- Dependencies: 224
-- Name: seq_action_log_id; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.seq_action_log_id', 60, true);


--
-- TOC entry 4879 (class 0 OID 0)
-- Dependencies: 225
-- Name: seq_menus_id; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.seq_menus_id', 1, true);


--
-- TOC entry 4880 (class 0 OID 0)
-- Dependencies: 226
-- Name: seq_password_recovery_id; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.seq_password_recovery_id', 1, true);


--
-- TOC entry 4881 (class 0 OID 0)
-- Dependencies: 227
-- Name: seq_permissions_id; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.seq_permissions_id', 36, true);


--
-- TOC entry 4882 (class 0 OID 0)
-- Dependencies: 228
-- Name: seq_role_permissions_id; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.seq_role_permissions_id', 289, true);


--
-- TOC entry 4883 (class 0 OID 0)
-- Dependencies: 229
-- Name: seq_roles_id; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.seq_roles_id', 2, false);


--
-- TOC entry 4884 (class 0 OID 0)
-- Dependencies: 230
-- Name: seq_users_id; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.seq_users_id', 4, true);





--
-- TOC entry 4683 (class 2606 OID 33311)
-- Name: action_log auth_action_log_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.action_log
    ADD CONSTRAINT auth_action_log_pk PRIMARY KEY (action_log_id);


--
-- TOC entry 4695 (class 2606 OID 33344)
-- Name: menus auth_menus_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.menus
    ADD CONSTRAINT auth_menus_pk PRIMARY KEY (menu_id);


--
-- TOC entry 4705 (class 2606 OID 33395)
-- Name: password_recovery auth_password_recovery_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.password_recovery
    ADD CONSTRAINT auth_password_recovery_pk PRIMARY KEY (password_recovery_id);


--
-- TOC entry 4691 (class 2606 OID 33333)
-- Name: permissions auth_permissions_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.permissions
    ADD CONSTRAINT auth_permissions_pk PRIMARY KEY (permission_id);


--
-- TOC entry 4697 (class 2606 OID 33359)
-- Name: role_permissions auth_role_permissions_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role_permissions
    ADD CONSTRAINT auth_role_permissions_pk PRIMARY KEY (role_permission_id);


--
-- TOC entry 4699 (class 2606 OID 33361)
-- Name: role_permissions auth_role_permissions_un; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role_permissions
    ADD CONSTRAINT auth_role_permissions_un UNIQUE (role_id, permission_id);


--
-- TOC entry 4693 (class 2606 OID 33338)
-- Name: roles auth_roles_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT auth_roles_pk PRIMARY KEY (role_id);


--
-- TOC entry 4701 (class 2606 OID 33383)
-- Name: users auth_users_un; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT auth_users_un UNIQUE (username);


--
-- TOC entry 4687 (class 2606 OID 33326)
-- Name: parameters parameters_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.parameters
    ADD CONSTRAINT parameters_pk PRIMARY KEY (parameter_id);


--
-- TOC entry 4689 (class 2606 OID 33328)
-- Name: parameters parameters_un; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.parameters
    ADD CONSTRAINT parameters_un UNIQUE (parameter_key);


--
-- TOC entry 4703 (class 2606 OID 33381)
-- Name: users users_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pk PRIMARY KEY (user_id);


--
-- TOC entry 4706 (class 2606 OID 33345)
-- Name: menus auth_menus_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.menus
    ADD CONSTRAINT auth_menus_fk FOREIGN KEY (menu_id) REFERENCES public.menus(menu_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 4707 (class 2606 OID 33350)
-- Name: menus auth_menus_permission_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.menus
    ADD CONSTRAINT auth_menus_permission_fk FOREIGN KEY (permission_id) REFERENCES public.permissions(permission_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 4711 (class 2606 OID 33396)
-- Name: password_recovery auth_password_recovery_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.password_recovery
    ADD CONSTRAINT auth_password_recovery_fk FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4708 (class 2606 OID 33362)
-- Name: role_permissions auth_role_permissions_p_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role_permissions
    ADD CONSTRAINT auth_role_permissions_p_fk FOREIGN KEY (permission_id) REFERENCES public.permissions(permission_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4709 (class 2606 OID 33367)
-- Name: role_permissions auth_role_permissions_r_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role_permissions
    ADD CONSTRAINT auth_role_permissions_r_fk FOREIGN KEY (role_id) REFERENCES public.roles(role_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4710 (class 2606 OID 33384)
-- Name: users auth_users_role_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT auth_users_role_fk FOREIGN KEY (role_id) REFERENCES public.roles(role_id) ON UPDATE CASCADE ON DELETE RESTRICT;


-- Completed on 2024-03-12 10:06:49