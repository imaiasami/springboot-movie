CREATE TABLE MEMBER (
	member_id varchar2(20) PRIMARY KEY,
	password varchar2(20) NOT NULL,
	name varchar2(50) NOT null
);

CREATE TABLE movie (
	movie_id NUMBER PRIMARY KEY,
	title varchar2(300)
);

CREATE TABLE review (
	review_id NUMBER PRIMARY KEY,
	contents varchar2(2000) NOT NULL,
	score NUMBER NOT NULL,
	movie_id NUMBER REFERENCES movie(movie_id),
	member_id varchar2(20) REFERENCES member(member_id),
	created_date DATE DEFAULT sysdate
);

CREATE SEQUENCE seq_movie;

INSERT INTO movie VALUES (seq_movie.nextval, '카운트');
INSERT INTO movie VALUES (seq_movie.nextval, '서치2');
INSERT INTO movie VALUES (seq_movie.nextval, '살수');
INSERT INTO movie VALUES (seq_movie.nextval, '마루이 비디오');
INSERT INTO movie VALUES (seq_movie.nextval, 'TAR 타르');
INSERT INTO movie VALUES (seq_movie.nextval, '미녀와 야수: 마법에 걸린 왕자');
INSERT INTO movie VALUES (seq_movie.nextval, '스톰 보이');
INSERT INTO movie VALUES (seq_movie.nextval, '극장판 카케구루이2: 절체절명의 러시안 룰렛');
INSERT INTO movie VALUES (seq_movie.nextval, '앤트맨과 와스프: 퀀텀매니아');