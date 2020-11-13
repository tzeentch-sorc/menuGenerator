--
-- PostgreSQL database dump
--

-- Dumped from database version 13.0
-- Dumped by pg_dump version 13.0

-- Started on 2020-11-13 13:01:13 MSK

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 206 (class 1259 OID 24746)
-- Name: meal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meal (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    recipe text,
    meal_type character varying(50),
    calories integer,
    portions integer,
    proteins numeric(5,2),
    fats numeric(5,2),
    carbohydrates numeric(5,2),
    weight integer,
    picture character varying(255)
);


ALTER TABLE public.meal OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 24744)
-- Name: meal_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.meal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.meal_id_seq OWNER TO postgres;

--
-- TOC entry 3309 (class 0 OID 0)
-- Dependencies: 205
-- Name: meal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.meal_id_seq OWNED BY public.meal.id;


--
-- TOC entry 208 (class 1259 OID 24757)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    calories integer,
    proteins numeric(5,2),
    fats numeric(5,2),
    carbohydrates numeric(5,2)
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 24755)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO postgres;

--
-- TOC entry 3310 (class 0 OID 0)
-- Dependencies: 207
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- TOC entry 209 (class 1259 OID 24763)
-- Name: productinmeal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.productinmeal (
    product_id integer,
    meal_id integer,
    weight integer
);


ALTER TABLE public.productinmeal OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16529)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying(50)
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16527)
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_id_seq OWNER TO postgres;

--
-- TOC entry 3311 (class 0 OID 0)
-- Dependencies: 202
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


--
-- TOC entry 204 (class 1259 OID 16535)
-- Name: user_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_roles (
    user_id integer,
    role_id integer
);


ALTER TABLE public.user_roles OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16521)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    email character varying(50),
    password character varying(200),
    username character varying(30)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16519)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 3312 (class 0 OID 0)
-- Dependencies: 200
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 3146 (class 2604 OID 24749)
-- Name: meal id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meal ALTER COLUMN id SET DEFAULT nextval('public.meal_id_seq'::regclass);


--
-- TOC entry 3147 (class 2604 OID 24760)
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- TOC entry 3145 (class 2604 OID 16532)
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- TOC entry 3144 (class 2604 OID 16524)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3300 (class 0 OID 24746)
-- Dependencies: 206
-- Data for Name: meal; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.meal (id, name, recipe, meal_type, calories, portions, proteins, fats, carbohydrates, weight, picture) FROM stdin;
1	Нежный хек	1. Филе хека посолить, поперчить. 2. Взбить яйцо и поместить туда филе. 3. Обвалять в муке смоченное в яйце филе. 4. Положить на смазанную растительным маслом сковороду и потушить с каждой стороны по 5-7 минут. Подавать с гарниром или овощами. Блюдо очень простое, недорогое, получается вкусным и нежным.	\N	139	2	15.00	7.50	2.90	135	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1000/7ec8bfd87e7906def5f04e14aeec99e0.JPG
2	Крупеник гречневый	1. Крупу всыпать в кипящую воду, добавить молоко, соль, довести до кипения, варить до готовности. 2. Творог протереть через сито или измельчить в блендере. 3. Яйца растереть с сахаром. 4. Добавить в кашу творог и яйца, перемешать, выложить в смазанную маслом и посыпанную панировочными сухарями форму. 5. Поверхность смазать сметаной и запекать до образования румяной корочки в разогретой до 220 градусов духовке, около 15 минут. Подавать с йогуртом или нежирной сметаной.	\N	142	4	10.50	3.10	17.70	272	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1001/587bb462663ae74eb69cad6cc265d971.jpg
3	Запеченный лосось	1. Уложить рыбу в форму кожей вниз. 2. Смазать взбитым яйцом, посолить, поперчить, посыпать сухарями. 3. Поставить в холодильник на 1 час. 4. Разогреть духовку до 200 градусов. 5. На рыбу положить масло и запекать 20 минут.	\N	176	4	18.60	8.80	6.10	155	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1002/e46deea257cc9c055514ed0b6f1185c6.jpg
4	Минтай, тушеный в сметане	1. Нарезать рыбу порционными кусками. 2. Лук нарезать кольцами и обжарить на растительном масле. 3. В сотейник налить масло, уложить рыбу в один или два слоя, сверху положить лук, залить тонким слоем сметаны и тушить на слабом огне 25 - 30 минут. 4. За 5 минут до готовности добавить соль, перец. Подавать с картофельным пюре и зеленью.	\N	72	4	12.20	1.80	1.80	205	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1003/4e4b484a3382f5f3f320dae641802e87.jpg
5	Ячневая каша с яйцом	1. Вскипятить воду, посолить, засыпать крупу и варить кашу на медленном огне 20 минут. 2. Яйцо отварить, очистить, мелко нарезать, смешать с рубленой зеленью. 3. Кашу подавать со сливочным маслом, посыпав яйцом и зеленью.	\N	112	1	3.60	6.30	10.20	333	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1004/80d9612fcc7baa47cef6bd3c44da658d.jpg
6	Перец, фаршированный мясом и гречкой	1. Перебрать гречку, залить кипятком. 2. Подготовить перцы, аккуратно вынув сердцевину. 3. Сделать начинку из перекрученного мяса, гречки, мелко нарезанного лука и специй. 4. Нафаршировать перцы. 5. Смазать казан или кастрюлю с толстым дном маслом, поставить перчики открытым концом вверх. 6. Развести томатную пасту с водой, крахмалом и солью, залить перцы, полностью. 7. Поставить в разогретую о 200 градусов духовку на 1,5 часа. 8. При подаче посыпать зеленью.	\N	126	4	8.70	6.10	8.90	276	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1005/7595c8318a0eed4227f46a2edb60560b.jpg
7	Печень в сметане	1. Печень нарезать ломтиками, посолить, поперчить, посыпать сахаром. 2. Жарить на сухой разогретой сковороде, постоянно переворачивая, 4 минуты. 3. Добавить масло, жарить еще 2 минуты, посыпать мукой, мелко нарезанным луком и жарить еще 2 минуты. 4. Залить сметаной, добавить воду и тушить под крышкой на слабом огне 10 минут.	\N	111	4	8.80	4.60	8.60	295	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1006/cab30800db02960c27d3270ca1938c7c.jpg
8	Шоколадная курица	1. Нарезать чеснок, добавить какао, соль, перец, сладкий сироп из любого варенья. 2. Залить курицу маринадом. 3. Оставить на ночь или на пару часов. 4. Выложить на противень, запекать 40 минут при температуре 200 градусов.	\N	244	1	16.70	16.40	7.90	140	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1007/6dc8f15a7e90260b1827bc17967e9912.JPG
9	Пикантная курица с пшеном	1. Курицу порубить, натереть сахаром, перцем, тертым чесноком. 2. Отварить пшено до полуготовности, добавить перец и рубленый чеснок. 3. Курицу пожарить на раскаленной сковороде (8 минут, переворачивая). 4. Добавить масло, лук и жарить еще 5 минут. 5. Переложить курицу на пшено и тушить все вместе 5 минут.	\N	206	4	10.60	9.90	19.20	242	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1008/763a4a66fabaa4d231511025642f95d0.jpg
10	Салат из фасоли с яблоками	1. Яблоки (без кожуры и сердцевины) и свеклу нарезать кубиками или натереть на крупной терке. 2. Все ингредиенты смешать и заправить соусом из масла и уксуса.	\N	98	4	3.50	3.10	13.60	192	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1009/dda10f8b45d20b7f0fe1ee0cbe62972f.jpg
11	Скумбрия с грибным соусом	1. Рыбу нарезать, посолить, сбрызнуть лимонным соком, запечь в аэрогриле или в духовке 20 минут при температуре 200 градусов. 2. Переложить в горячую посуду и поставить в теплое место. 3. Собрать жир, который выделит рыба, добавить муку, немного воды, помешивая, довести до кипения. 4. Добавить грибы, соль, перец, оставшийся лимонный сок, желток, тушить 10 минут. Полить рыбу соусом при подаче.	\N	128	4	13.10	6.40	4.50	236	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1010/283075ff4ce2774180ee4c977e8dc728.jpg
12	Тыква, запеченная с курицей	1. Помыть и почистить овощи. 2. Смазать маслом дно антипригарной формы. 3. Нарезать овощи и курицу. 4. Выкладывать слоями: тыкву, нарезанный кубиками лук, курицу, морковь. Каждый слой солить и посыпать специями. 5. Накрыть крышкой и запекать 1 час при температуре 200 градусов. 6. Смешать йогурт с порубленной зеленью и солью. 7. Подавать блюдо с йогуртовым соусом.	\N	89	2	5.60	5.30	4.60	420	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1011/72667e12763d017def269c277938ad37.jpg
13	Венгерский гуляш	1. Мясо нарезать кубиками и обжарить на масле. 2. Добавить лук и жарить, пока не подрумянится, посолить, поперчить. 3. Залить воду и томатную пасту, добавить красный перец, тмин. 4. Тушить до готовности мяса. 5. Добавить капусту и тушить еще 10 минут. 6. Добавить сметаны, довести до кипения. 7. При подаче по желанию посыпать зеленью.	\N	82	6	6.20	5.00	2.60	352	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1012/b588983799c4d681d541a0e83221013b.jpg
14	Пшенная каша с сухофруктами	1. С вечера замочить сухофрукты. 2. Пшено промыть, залить горячей водой, посолить, варить до выпаривания воды. 3. Добавить горячее молоко, сухофрукты, немного сахара, варить до готовности на медленном огне. 4. Перед подачей положить масло.	\N	111	2	3.10	2.50	18.80	342	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1013/c6cdb337895f4ff001c548a96ff30c7f.jpg
15	Омлет по-болгарски	1. Яйца взбить, добавить нарезанные кубиками перец и брынзу, посолить. 2. Жарить на сливочном масле.	\N	166	1	11.20	12.60	2.10	240	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1014/b6873b972989b274fc1365a14e4f1ca6.jpg
16	Овощи-гриль с сыром	1. Нарезать баклажан пластинами толщиной 1 см, посыпать солью и оставить на 5-10 мин., чтобы убрать горечь 2. Сладкий перец разрезать на 4 части, очистить от семян. 3. Помидоры нарезать кружочками, сыр - тонкими пластинами. 4. Отжать сок из баклажан, положить на решетку гриля или сетку, сверху - кружочки помидор, перец. 5. Поперчить, посолить.и запекать в аэрогриле или духовке 15 минут при температуре 180 градусов. 6. Достать и положить сверху овощей сыр. Запечь до расплавления сыра и появления легкой румяной корочки. При подаче посыпать зеленью.	\N	48	2	2.90	2.00	4.40	210	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1015/0b9849583d84c29185ea05036f20f788.jpg
17	Рагу из лосося	1. Овощи нарезать соломкой и обжарить на растительном масле. 2. Добавить немного воды, чеснок, томатную пасту, тимьян и тушить 10 минут. 3. Нарезать филе на порционные куски и добавить к овощам, тушить 15 минут. 4. Добавить нарезанные оливки и зелень.	\N	122	4	11.00	6.40	5.20	250	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1016/3b4603ba9c4e3d38a2bcc89fdd3b0ff5.jpg
18	Микс - салат из овощей	1. Капусту нарезать соломкой, помять руками. 2. Перец и помидоры нарезать, добавить к капусте. 3. Мелко нарезать петрушку. 4. Приправить салат специями и маслом.	\N	38	2	1.40	1.50	4.80	365	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1017/d7e673cd7ef3cd165e6f4a6a030d6453.JPG
19	Помидоры, фаршированные треской	1. Включить духовку на 200 градусов. 2. Вырезать мякоть из помидоров, посолить и поперчить изнутри. 3. Филе нарезать мелкими кусочками, добавить базилик, сахар, лимонный сок и масло. 4. Нафаршировать помидоры. 5. Запекать 15 минут.	\N	55	4	8.80	1.00	2.70	205	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1018/ccf287fa6013b3455835741494017c06.jpg
20	Куриная грудка в яблочном соусе	1. Филе куриной грудки, нарезанное кусочками, и нарезанное кубиками яблоко, потушить в стакане воды 5-7 минут. 2. Добавить муку, размешать, довести до кипения. 3. Залить молоко, еще раз хорошо перемешать и варить на медленном огне 10 минут.	\N	141	1	8.40	7.70	9.20	260	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1019/834b9e01155c7a1975b4d52dd58fa34c.jpg
21	Гренки с помидорами	1. Помидоры черри разрезать на половинки, обжарить на сухой сковороде. 2. Добавить масло, перевернуть помидоры и дожарить до мягкого состояния. 3. Взбить яйца с молоком и солью, обмакнуть багет и обжарить с 2 сторон. 4. Подавать с помидорами.	\N	153	1	5.20	6.40	18.80	310	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1020/4318d071241161e37ef622ece76d8ae6.jpg
22	Майонез домашний с лимонным соком	Для приготовления майонеза необходимо взять погружной блендер с насадкой венчик и узкий высокий стакан такой формы, чтобы венчик помещался в нем с небольшим запасом по диаметру посуды. Куриные яйца должны быть самого высшего качества, пользуйтесь проверенными фирмами-производителями в выборе этого ингредиента для майонеза. 1. В стакане смешать яичные желтки, сахар, щепотку соли и взбивать до увеличения массы в объеме и изменения цвета до более светлого. 2. Добавить 1 коф.л. сухой горчицы и черный молотый перец на кончике ножа, перемешать. 3. Не переставая взбивать блендером добавлять в смесь по 10-50 мл растительного масла, дожидаясь полного эмульгирования порции в смеси. 4. Продолжить взбивать до момента, когда масло кончится, смесь должна загустеть и приобрести глянцевый оттенок. 5. Добавить в майонез свежевыжатый лимонный сок, смесь станет более жидкой. При необходимости, если соус получился слишком густой, добавить 1-2 ст.л. воды, но только после добавления лимонного сока. 6. Попробовать майонез на вкус, при необходимости добавить соль-перец. 7. Хранить соус в холодильнике не более 5 суток, в случае добавления воды - не более 3 суток. Домашний майонез - отличная основа для кулинарного творчества. В готовую смесь можно добавить чеснок, различные пряные травы, маринованные огурчики, каперсы, йогурт, и каждый раз Вы будете получать новый свежий вкус, идеально подходящий под конкретное блюдо или под вашу систему питания.	\N	681	10	3.40	73.00	2.60	27	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1021/cfec318fd939a716b6d5b9ff27fb3182.jpg
23	Рассольник с курицей	1. Перловку и курицу залить водой и поставить на плиту. 2. Подготовить овощи. 3. Как только крупа разварится добавить огурцы, картофель и морковь. 4. В конце варки добавить мелко нарезанный лук, соль и специи.	\N	67	6	4.20	3.70	4.10	342	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1022/d579bea7fa1b035733f51f66e5a5daf3.jpg
24	Овощной суп с курицей	1. Нарезать овощи. 2. Куриную грудку порезать небольшими кусочками. 3. Положить овощи и курицу в кастрюлю, залить водой. 4. Поставить суп на плиту, довечти до кипения и варить на среднем огне около 15 минут. 5. Добавить куркуму и специи.	\N	29	4	3.60	0.20	2.80	426	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1023/6cca3bc847869781fc7790e287921564.png
25	Борщ вегетарианский	1. Замоченную фасоль залить 1л. воды и поставить на плиту. 2. Нарезать овощи. 3. Добавить свеклу, капусту, морковь и варить суп до готовности фасоли. 4. Добавить остальные овощи, довести до кипения и варить еще 5 минут. 5. Приправить суп специями, выключить плиту и дать борщу настояться. 6. При подаче посыпать зеленью и добавить сметану.	\N	37	4	1.80	0.60	5.80	480	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1024/facfa26fec888f35b8bd8044686247bc.jpg
26	Грибной суп с кабачками	1. Поставить воду на плиту. 2. Картофель, кабачки, морковь нарезать кубиками. 3. Лук нарезать полукольцами. 4. Помидор и грибы нарезать ломтиками. 5. Зелень порубить. 6. Грибы опустить в кипящую воду. 5. Кабачки, лук и морковь обжарить на растительном масле. 6. Добавить помидор, немного воды и тушить несколько минут. 7. В грибной бульон добавить картофель, тушеные овощи и специи. 8. Варить 5 минут, затем оставить кастрюлю на плите на 20 минут. 9. Подавать со сметаной.	\N	35	4	1.70	1.20	4.20	425	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1025/3bc443d2c3df9183dce9772527dc1bc7.jpg
27	Куриный суп-лапша	1. Поставить варить курицу, нарезанную порционными кусками. 2. Почистить и нарезать овощи. 3. Как только бульон начнет закипать, снять пену. 4. Положить в бульон овощи. 5. Добавить вермишель. 6. Посолить, добавить специи по вкусу. 7. Посыпать зеленью при подаче.	\N	93	4	6.10	5.40	5.00	348	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1026/f142ae50003ae1bb5fa35947ed954d78.jpg
28	Пастрома из курицы	1. Растворить соль в воде и замочить мясо в полученном рассоле на ночь. 2. Чеснок и зелень измельчить, смешать с растительным маслом. Обмазать филе полученной смесью. 3. Поместить в разогретую до 250 градусов духовку на 15 минут. 4. Выключить духовку и оставить пастрому в печи до полного остывания (около 2х часов). Приготовление простое, но требует времени, поэтому пастрому удобно заготавливать сразу в достаточном количестве и использовать не только как самостоятельное блюдо, но и в качестве ингредиента для салатов и бутербродов.	\N	120	1	19.30	2.90	1.80	245	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1027/6a721ed44ee25b7a09b2b8517af85932.jpg
29	Рататуй	1. Для приготовления соуса нарезать перец, лук, 350г. помидоров. 2. На масле обжарить лук, добавить перец и помидоры, мелко нарезанный чеснок. 3. Пока овощи тушатся, нарезать колечками оставшиеся помидоры, кабачки и баклажаны. 4. Соус немного остудить и измельчить в блендере, добавить соль. 5. Вылить соус в форму. 6. Выложить овощи по кругу, чередуя помидор, баклажан и кабачок. 7. Посыпать сверху специями, немного посолить, сбрызнуть оливковым маслом. 8. Накрыть форму пергаментом или фольгой, запекать в духовке 1,5 часа при температуре 180 градусов. Конечно, время приготовления блюда значительно сократится, если вы нарежете овощи произвольно и просто уложите в форму. Рататуй получится не менее вкусным, просто более домашним, не таким изысканным внешне, как в фильме.	\N	32	6	0.90	1.00	5.00	405	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1028/dbe9193071a6002d2332f43058d723b9.JPG
30	Борщ	1. Разделать курицу, поставить варить бульон. 2. Почистить овощи. 3. Свеклу, морковь, капусту нарезать соломкой, картофель, лук - кубиками. 4. Как только бульон закипит, добавить свеклу. 5. Последовательно заложить в суп капусту, морковь, картофель, лук. 6. Мелко нарезать помидоры(можно заменить их томатной пастой), добавить в суп. 7. Посолить, добавить специи, чеснок. 8. Выключить плиту, оставить борщ настаиваться не менее 15 минут. 9. Подавать с зеленью и сметаной.	\N	51	4	6.80	0.30	4.20	380	https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1029/ac41f052de46100c88692017dc10041a.JPG
\.


--
-- TOC entry 3302 (class 0 OID 24757)
-- Dependencies: 208
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (id, name, calories, proteins, fats, carbohydrates) FROM stdin;
1	Хек	86	16.50	2.00	0.00
2	Яйцо куриное	158	12.00	12.00	0.00
3	Мука пшеничная	340	10.00	0.00	80.00
4	Масло подсолнечное	900	0.00	100.00	0.00
5	Гречневая крупа ядрица	308	12.50	3.50	57.00
6	Молоко 2,5%	54	3.00	2.50	5.00
7	Вода	0	0.00	0.00	0.00
8	Творог, 2 %	114	20.00	1.88	3.13
9	Сахар-песок	380	0.00	0.00	100.00
10	Сухари пшеничные	335	15.00	0.00	70.00
11	Соль поваренная пищевая	0	0.00	0.00	0.00
12	Йогурт 1,5%	58	3.75	1.25	6.25
13	Лосось атлантический (семга)	153	20.00	8.20	0.00
14	Масло сливочное 72%	660	0.00	70.00	0.00
15	Перец черный молотый	267	0.00	0.00	66.67
16	Минтай	70	15.83	0.67	0.00
17	Лук репчатый	41	1.00	0.00	8.00
18	Укроп и другие пряные травы	40	0.00	0.00	10.00
19	Сметана 10,0% жирности	119	3.00	10.00	4.00
20	Крупа ячневая	314	10.00	2.00	66.00
21	Петрушка	40	0.00	0.00	0.00
22	Перец сладкий	26	1.25	0.00	5.00
23	Говядина вырезка	219	18.50	16.00	0.00
24	Томатная паста	102	5.00	0.00	19.00
25	Крахмал картофельный	320	0.00	0.00	80.00
26	Печень говяжья	127	18.00	3.80	5.40
27	Курица филе, без кожи	241	18.33	18.33	0.83
28	Какао-порошок	380	20.00	20.00	40.00
29	Варенье из малины	270	0.00	0.00	70.00
30	Чеснок	140	0.00	0.00	40.00
31	Пшено	348	11.50	3.50	69.50
32	Перец красный жгучий	33	0.00	0.00	0.00
33	Фасоль зерно	298	21.00	2.00	47.00
34	Свекла	42	1.67	0.00	8.67
35	Яблоки	47	0.33	0.33	9.67
36	Масло оливковое	900	0.00	100.00	0.00
37	Винный уксус 3%	10	0.00	0.00	0.00
38	Скумбрия	153	18.00	9.00	0.00
39	Сок виноградный	70	0.00	0.00	17.50
40	Сливки, 10,0% жирности	120	2.50	10.00	5.00
41	Сок лимонный	33	0.00	0.00	5.00
42	Яичный желток куриный	64	16.00	0.00	0.00
43	Шампиньоны	27	4.67	1.33	0.00
44	Тыква	22	1.00	0.00	4.33
45	Морковь	35	1.00	0.00	7.00
46	Перец красный молотый	300	0.00	0.00	100.00
47	Капуста белокочанная, квашеная	23	1.83	0.17	3.00
48	Тмин	333	33.33	0.00	66.67
49	Крупа пшено	342	11.82	3.64	66.36
50	Яблоки сушеные	255	0.00	0.00	60.00
51	Чернослив	255	5.00	0.00	60.00
52	Перец красный сладкий	27	1.43	0.00	5.71
53	Брынза	262	21.67	20.00	0.00
54	Помидоры	20	0.83	0.00	4.17
55	Баклажаны	24	1.33	0.00	4.67
56	Сыр полутвердый (голландский)	360	26.67	26.67	0.00
57	Оливки	296	2.00	24.00	20.00
58	Тимьян	100	10.00	0.00	10.00
59	Капуста белокочанная	28	1.67	0.00	4.67
60	Треска	75	17.50	0.50	0.00
61	Гречневая мука	367	13.33	0.00	73.33
62	Молоко 1,5%	46	4.00	2.00	4.00
63	Батон нарезной	262	8.00	3.00	51.00
64	Горчичный порошок	367	33.33	0.00	33.33
65	Курица тушка	238	18.25	18.50	0.00
66	Огурец соленый	13	1.00	0.00	1.50
67	Картофель	77	2.00	0.50	16.50
68	Крупа перловая	316	10.00	2.00	68.00
69	Куриная грудка, без кожи	113	23.00	1.00	0.00
70	Капуста цветная	30	3.00	0.00	4.00
71	Фасоль стручковая	23	3.00	0.00	3.00
72	Горошек зеленый	73	5.00	0.00	14.00
73	Куркума	333	0.00	0.00	66.67
74	Кабачок	24	0.67	0.33	4.67
75	Лук зеленый	20	0.00	0.00	3.33
76	Макароны из муки в/с	338	12.00	2.00	70.00
\.


--
-- TOC entry 3303 (class 0 OID 24763)
-- Dependencies: 209
-- Data for Name: productinmeal; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.productinmeal (product_id, meal_id, weight) FROM stdin;
1	1	200
2	1	50
3	1	10
4	1	10
5	2	200
6	2	200
7	2	120
8	2	320
9	2	40
2	2	100
10	2	20
4	2	3
11	2	5
12	2	80
13	3	500
2	3	50
10	3	50
14	3	10
11	3	5
15	3	3
16	4	600
17	4	100
15	4	3
11	4	5
18	4	10
19	4	100
20	5	50
7	5	200
2	5	50
14	5	20
11	5	3
18	5	5
21	5	5
22	6	400
23	6	400
5	6	80
17	6	100
24	6	100
25	6	5
18	6	20
26	7	500
17	7	300
3	7	40
14	7	20
19	7	200
7	7	100
9	7	10
11	7	5
15	7	3
27	8	120
28	8	5
29	8	10
30	8	5
27	9	400
31	9	200
17	9	300
30	9	30
14	9	20
9	9	10
32	9	3
11	9	5
33	10	100
34	10	300
35	10	300
36	10	20
37	10	40
11	10	5
15	10	2
38	11	600
3	11	40
39	11	40
40	11	40
41	11	40
42	11	25
43	11	150
15	11	3
11	11	5
44	12	300
27	12	200
17	12	100
45	12	100
12	12	100
18	12	30
36	12	5
11	12	3
46	12	1
15	12	1
23	13	600
7	13	500
17	13	300
14	13	10
24	13	40
47	13	600
32	13	3
11	13	3
48	13	3
12	13	50
49	14	110
7	14	250
14	14	10
9	14	20
11	14	3
50	14	20
51	14	20
6	14	250
2	15	100
14	15	10
52	15	70
53	15	60
52	16	120
54	16	120
55	16	150
56	16	30
13	17	500
17	17	150
45	17	200
4	17	10
30	17	10
24	17	50
57	17	50
15	17	3
11	17	5
18	17	10
58	17	10
59	18	300
54	18	200
52	18	200
21	18	20
36	18	10
54	19	400
60	19	400
41	19	10
9	19	5
36	19	5
35	20	100
6	20	45
61	20	15
27	20	100
54	21	100
2	21	50
62	21	50
63	21	100
4	21	10
42	22	0
4	22	198
9	22	5
41	22	0
11	22	5
64	22	3
15	22	1
65	23	400
66	23	200
67	23	200
45	23	100
17	23	100
7	23	1000
68	23	50
69	24	200
70	24	100
45	24	100
52	24	100
71	24	100
72	24	100
7	24	800
17	24	100
73	24	3
54	24	100
33	25	100
34	25	100
45	25	100
59	25	100
67	25	100
54	25	100
17	25	100
22	25	100
30	25	20
18	25	20
7	25	1000
19	25	80
43	26	400
74	26	300
67	26	200
45	26	100
17	26	100
54	26	100
75	26	30
21	26	20
7	26	400
19	26	40
4	26	10
65	27	400
67	27	100
45	27	100
17	27	100
21	27	20
18	27	20
7	27	600
76	27	50
69	28	200
11	28	10
30	28	10
18	28	10
21	28	10
4	28	5
52	29	350
54	29	850
17	29	200
74	29	500
55	29	500
30	29	10
36	29	20
34	30	200
45	30	100
59	30	100
67	30	100
17	30	100
54	30	100
30	30	20
7	30	400
69	30	400
\.


--
-- TOC entry 3297 (class 0 OID 16529)
-- Dependencies: 203
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id, name) FROM stdin;
1	ROLE_USER
2	ROLE_MODERATOR
\.


--
-- TOC entry 3298 (class 0 OID 16535)
-- Dependencies: 204
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_roles (user_id, role_id) FROM stdin;
1	2
1	1
2	1
\.


--
-- TOC entry 3295 (class 0 OID 16521)
-- Dependencies: 201
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, password, username) FROM stdin;
1	asdfxvxc@mail.ru	$2a$10$cQD57K.U/pKeI1b10SzNMucYsR.3coIVk5yecZg.AumtzwWXrY0Qq	mod
2	artemslepov01@gmail.com	$2a$10$F2RQyLeeBKGQNH1BWRqAVeeiWwDoiXmrX70kY.R5OkWVOoM7J9s3u	artemslepov01
\.


--
-- TOC entry 3313 (class 0 OID 0)
-- Dependencies: 205
-- Name: meal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.meal_id_seq', 1, false);


--
-- TOC entry 3314 (class 0 OID 0)
-- Dependencies: 207
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 1, false);


--
-- TOC entry 3315 (class 0 OID 0)
-- Dependencies: 202
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_id_seq', 2, true);


--
-- TOC entry 3316 (class 0 OID 0)
-- Dependencies: 200
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 2, true);


--
-- TOC entry 3157 (class 2606 OID 24754)
-- Name: meal meal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meal
    ADD CONSTRAINT meal_pkey PRIMARY KEY (id);


--
-- TOC entry 3159 (class 2606 OID 24762)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 3155 (class 2606 OID 16534)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 3149 (class 2606 OID 16551)
-- Name: users uk6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- TOC entry 3151 (class 2606 OID 16549)
-- Name: users ukr43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- TOC entry 3153 (class 2606 OID 16526)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3163 (class 2606 OID 24771)
-- Name: productinmeal productinmeal_meal_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productinmeal
    ADD CONSTRAINT productinmeal_meal_id_fkey FOREIGN KEY (meal_id) REFERENCES public.meal(id);


--
-- TOC entry 3162 (class 2606 OID 24766)
-- Name: productinmeal productinmeal_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productinmeal
    ADD CONSTRAINT productinmeal_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3161 (class 2606 OID 16543)
-- Name: user_roles user_roles_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- TOC entry 3160 (class 2606 OID 16538)
-- Name: user_roles user_roles_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2020-11-13 13:01:14 MSK

--
-- PostgreSQL database dump complete
--

