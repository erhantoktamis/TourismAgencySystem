PGDMP                         |            tourismagency    15.6    15.6 .    B           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            C           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            D           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            E           1262    16396    tourismagency    DATABASE     o   CREATE DATABASE tourismagency WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
    DROP DATABASE tourismagency;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false            F           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            �            1259    16404    hotel    TABLE     �   CREATE TABLE public.hotel (
    hotel_id bigint NOT NULL,
    hotel_name text,
    hotel_address text,
    hotel_email text,
    hotel_phone text,
    hotel_star smallint,
    hotel_facility_resources bigint[],
    hotel_hostel_pension_types bigint[]
);
    DROP TABLE public.hotel;
       public         heap    postgres    false    4            �            1259    16409    hotelFacilityResource    TABLE     t   CREATE TABLE public."hotelFacilityResource" (
    facility_id smallint NOT NULL,
    facility_resource_name text
);
 +   DROP TABLE public."hotelFacilityResource";
       public         heap    postgres    false    4            �            1259    16414 %   hotelFacilityResource_facility_id_seq    SEQUENCE     �   ALTER TABLE public."hotelFacilityResource" ALTER COLUMN facility_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."hotelFacilityResource_facility_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    4    215            �            1259    16415    hotelPensionType    TABLE     d   CREATE TABLE public."hotelPensionType" (
    pension_id smallint NOT NULL,
    pension_name text
);
 &   DROP TABLE public."hotelPensionType";
       public         heap    postgres    false    4            �            1259    16420    hotelPensionType_pension_id_seq    SEQUENCE     �   ALTER TABLE public."hotelPensionType" ALTER COLUMN pension_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."hotelPensionType_pension_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    4    217            �            1259    16421 	   hotelRoom    TABLE     �  CREATE TABLE public."hotelRoom" (
    room_id bigint NOT NULL,
    room_stock bigint,
    room_metersquare text,
    room_tv boolean,
    room_minibar boolean,
    room_ps boolean,
    room_vault boolean,
    room_projection boolean,
    room_bed_number smallint,
    room_type_id smallint,
    room_adult_price text,
    room_child_price text,
    hotel_id bigint,
    season_id bigint,
    pension_type_id smallint,
    room_door_number text
);
    DROP TABLE public."hotelRoom";
       public         heap    postgres    false    4            �            1259    16426    hotelRoomTypes    TABLE     a   CREATE TABLE public."hotelRoomTypes" (
    room_type_id smallint NOT NULL,
    room_name text
);
 $   DROP TABLE public."hotelRoomTypes";
       public         heap    postgres    false    4            �            1259    16431    hotelRoomTypes_room_type_id_seq    SEQUENCE     �   ALTER TABLE public."hotelRoomTypes" ALTER COLUMN room_type_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."hotelRoomTypes_room_type_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    4    220            �            1259    16432    hotelRoom_room_id_seq    SEQUENCE     �   ALTER TABLE public."hotelRoom" ALTER COLUMN room_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."hotelRoom_room_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    219    4            �            1259    16433    hotel_otel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN hotel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_otel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    214    4            �            1259    16460    reservations    TABLE     ~  CREATE TABLE public.reservations (
    "reservation_Id" bigint NOT NULL,
    reservation_full_name text,
    reservation_identification_number text,
    reservation_phone text,
    reservation_email text,
    reservation_adult bigint,
    reservation_child bigint,
    reservation_check_in_date date,
    reservation_check_out_date date,
    reservation_hotel_id bigint NOT NULL
);
     DROP TABLE public.reservations;
       public         heap    postgres    false    4            �            1259    16463    reservations_reservation_Id_seq    SEQUENCE     �   ALTER TABLE public.reservations ALTER COLUMN "reservation_Id" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."reservations_reservation_Id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    4    228            �            1259    16434    season    TABLE     �   CREATE TABLE public.season (
    season_id bigint NOT NULL,
    season_name text,
    season_start_date date,
    season_end_date date,
    hotel_id bigint NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false    4            �            1259    16439    season_season_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN season_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    4    224            �            1259    16440    user    TABLE     �   CREATE TABLE public."user" (
    user_id bigint NOT NULL,
    user_name text NOT NULL,
    user_password text NOT NULL,
    user_title text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false    4            �            1259    16445    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    226    4            0          0    16404    hotel 
   TABLE DATA           �   COPY public.hotel (hotel_id, hotel_name, hotel_address, hotel_email, hotel_phone, hotel_star, hotel_facility_resources, hotel_hostel_pension_types) FROM stdin;
    public          postgres    false    214   n7       1          0    16409    hotelFacilityResource 
   TABLE DATA           V   COPY public."hotelFacilityResource" (facility_id, facility_resource_name) FROM stdin;
    public          postgres    false    215   y8       3          0    16415    hotelPensionType 
   TABLE DATA           F   COPY public."hotelPensionType" (pension_id, pension_name) FROM stdin;
    public          postgres    false    217   �8       5          0    16421 	   hotelRoom 
   TABLE DATA             COPY public."hotelRoom" (room_id, room_stock, room_metersquare, room_tv, room_minibar, room_ps, room_vault, room_projection, room_bed_number, room_type_id, room_adult_price, room_child_price, hotel_id, season_id, pension_type_id, room_door_number) FROM stdin;
    public          postgres    false    219   �9       6          0    16426    hotelRoomTypes 
   TABLE DATA           C   COPY public."hotelRoomTypes" (room_type_id, room_name) FROM stdin;
    public          postgres    false    220   �:       >          0    16460    reservations 
   TABLE DATA             COPY public.reservations ("reservation_Id", reservation_full_name, reservation_identification_number, reservation_phone, reservation_email, reservation_adult, reservation_child, reservation_check_in_date, reservation_check_out_date, reservation_hotel_id) FROM stdin;
    public          postgres    false    228   �:       :          0    16434    season 
   TABLE DATA           f   COPY public.season (season_id, season_name, season_start_date, season_end_date, hotel_id) FROM stdin;
    public          postgres    false    224   u;       <          0    16440    user 
   TABLE DATA           O   COPY public."user" (user_id, user_name, user_password, user_title) FROM stdin;
    public          postgres    false    226   1<       G           0    0 %   hotelFacilityResource_facility_id_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public."hotelFacilityResource_facility_id_seq"', 7, true);
          public          postgres    false    216            H           0    0    hotelPensionType_pension_id_seq    SEQUENCE SET     P   SELECT pg_catalog.setval('public."hotelPensionType_pension_id_seq"', 14, true);
          public          postgres    false    218            I           0    0    hotelRoomTypes_room_type_id_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('public."hotelRoomTypes_room_type_id_seq"', 4, true);
          public          postgres    false    221            J           0    0    hotelRoom_room_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public."hotelRoom_room_id_seq"', 16, true);
          public          postgres    false    222            K           0    0    hotel_otel_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.hotel_otel_id_seq', 15, true);
          public          postgres    false    223            L           0    0    reservations_reservation_Id_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('public."reservations_reservation_Id_seq"', 6, true);
          public          postgres    false    229            M           0    0    season_season_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.season_season_id_seq', 18, true);
          public          postgres    false    225            N           0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 24, true);
          public          postgres    false    227            �           2606    16447 1   hotelFacilityResource hotelFacilityResources_pkey 
   CONSTRAINT     |   ALTER TABLE ONLY public."hotelFacilityResource"
    ADD CONSTRAINT "hotelFacilityResources_pkey" PRIMARY KEY (facility_id);
 _   ALTER TABLE ONLY public."hotelFacilityResource" DROP CONSTRAINT "hotelFacilityResources_pkey";
       public            postgres    false    215            �           2606    16449 &   hotelPensionType hotelPensionType_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public."hotelPensionType"
    ADD CONSTRAINT "hotelPensionType_pkey" PRIMARY KEY (pension_id);
 T   ALTER TABLE ONLY public."hotelPensionType" DROP CONSTRAINT "hotelPensionType_pkey";
       public            postgres    false    217            �           2606    16451 "   hotelRoomTypes hotelRoomTypes_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public."hotelRoomTypes"
    ADD CONSTRAINT "hotelRoomTypes_pkey" PRIMARY KEY (room_type_id);
 P   ALTER TABLE ONLY public."hotelRoomTypes" DROP CONSTRAINT "hotelRoomTypes_pkey";
       public            postgres    false    220            �           2606    16453    hotelRoom hotelRoom_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public."hotelRoom"
    ADD CONSTRAINT "hotelRoom_pkey" PRIMARY KEY (room_id);
 F   ALTER TABLE ONLY public."hotelRoom" DROP CONSTRAINT "hotelRoom_pkey";
       public            postgres    false    219            �           2606    16455    hotel otel_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT otel_pkey PRIMARY KEY (hotel_id);
 9   ALTER TABLE ONLY public.hotel DROP CONSTRAINT otel_pkey;
       public            postgres    false    214            �           2606    16470    reservations reservations_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.reservations
    ADD CONSTRAINT reservations_pkey PRIMARY KEY ("reservation_Id");
 H   ALTER TABLE ONLY public.reservations DROP CONSTRAINT reservations_pkey;
       public            postgres    false    228            �           2606    16457    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    224            �           2606    16459    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    226            0   �   x�e��j�0Eף��ة�j�t��
m�%��"�d�ѿW��>(�Aw���A>�Eۓ��5�ns_/ful��9/�`#)h�`x�����xgzx��WNcpsZ��{{0}�TUUNbf��������<���vwx�~S�4�glSf��,,+a����w^⧶S&�WC#-�S�RT���*����QL)��	Y�^G4K�P�G��m�H���s��$��dj|��B<�H�|7_�}�B_&�~      1   u   x�3�<<'�(��8�J��$� �(��I,<�-�˘3���T�ĲҪR.N�̒���b�Լ��".SN���������Ԣ�T.3�� G.sNs}#��D��Ԣ���L�=... � &�      3   z   x�3��))JT�H-::?�R�%1#3�ˈ�o�韒����Q��Srd#�)gdbё��
�yř��y\f���)�ɩ
��%��\朎9��9
�E���+����($��d�p�p�$"i����� j�-�      5   �   x�U�Q��0D��]�1�e��{��n�̚�X�M7��TΔߪ7�Q��]�M��n��k��ND�euB�t�ݲ%�����u�N�Wu^V��Ey���O}(��X^�� �w..�es���m<�k�p]�w0�̵EsAz��jl����f���m.I'���lL��� -�k�La�)�Т�|�̅D�O]�ϑˎ�)׮\�=k�F%��\G~��b�K�\�����Wk��`�      6   >   x�3�,.�,IU�OI�2��*���/R@s^��V���yt~fNf6XЄS�$5U,F��� �-      >   �   x�m���0E��]��q
���bU	TIP�Of`�zA~����Gp�_y�d-��rt�ev������Mϗ����@��@�N��S-_�'+|Z�^��N�X 8��K�� �xΐ�l�}�[L�����AP�Ai�zD|806�      :   �   x�e�1� Eg|*�@���ԡK�vȐU�m��B����1�����jB9�t�FR��iYv �re1
�zi���E~Ļ2�@�G8M�A�B<�hN �\Ӗ������MVHGK`�?t'���`�N9���b l���lS�n����&Yz���8��� _�F\�      <   A   x�34�L-�H��442�tL����22�LB�܂����T.#C��Ҝ�ļ#��l+����qqq �L�     