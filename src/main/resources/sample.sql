INSERT INTO sample.category
(id, created_datetime, modified_datetime, category_code, category_name, parent_category_id)
VALUES(1, '2022-11-08 23:51:50', '2022-11-08 23:51:50', 'koreanfood', '한식', NULL);
INSERT INTO sample.category
(id, created_datetime, modified_datetime, category_code, category_name, parent_category_id)
VALUES(2, '2022-11-09 00:00:12', '2022-11-09 00:00:12', 'soup_stews', '찜_탕_찌개', 1);
INSERT INTO sample.category
(id, created_datetime, modified_datetime, category_code, category_name, parent_category_id)
VALUES(3, '2022-11-09 00:02:14', '2022-11-09 00:02:14', 'stew', '찌개', 2);
INSERT INTO sample.category
(id, created_datetime, modified_datetime, category_code, category_name, parent_category_id)
VALUES(4, '2022-11-09 00:28:32', '2022-11-09 00:28:32', 'steamed', '찜', 2);
INSERT INTO sample.category
(id, created_datetime, modified_datetime, category_code, category_name, parent_category_id)
VALUES(5, '2022-11-09 00:31:38', '2022-11-09 00:31:38', 'noodle', '국수', 1);





INSERT INTO sample.menu
(id, created_datetime, modified_datetime, category_code, description, menu_code, menu_name, category_id)
VALUES(1, '2022-11-09 00:32:19', '2022-11-09 00:32:19', 'stew', '김치찌개입니다.', 111, '김치찌개', 3);
INSERT INTO sample.menu
(id, created_datetime, modified_datetime, category_code, description, menu_code, menu_name, category_id)
VALUES(2, '2022-11-09 00:32:41', '2022-11-09 00:32:41', 'stew', '된장찌개입니다.', 112, '된장찌개', 3);
INSERT INTO sample.menu
(id, created_datetime, modified_datetime, category_code, description, menu_code, menu_name, category_id)
VALUES(3, '2022-11-09 00:33:01', '2022-11-09 00:33:01', 'noodle', '잔치국수입니다.', 113, '잔치국수', 5);
INSERT INTO sample.menu
(id, created_datetime, modified_datetime, category_code, description, menu_code, menu_name, category_id)
VALUES(4, '2022-11-09 00:33:10', '2022-11-09 00:33:10', 'noodle', '칼국수입니다.', 114, '칼국수', 5);
INSERT INTO sample.menu
(id, created_datetime, modified_datetime, category_code, description, menu_code, menu_name, category_id)
VALUES(5, '2022-11-09 00:34:02', '2022-11-09 00:34:02', 'steamed', '아구찜입니다.', 115, '아구찜', 4);
INSERT INTO sample.menu
(id, created_datetime, modified_datetime, category_code, description, menu_code, menu_name, category_id)
VALUES(6, '2022-11-09 00:34:12', '2022-11-09 00:34:12', 'steamed', '찜닭입니다.', 116, '찜닭', 4);
