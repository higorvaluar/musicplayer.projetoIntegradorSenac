create database musicPlayer
default char set utf8
default collate utf8_general_ci;

use musicPlayer;

create table artista(
	id int not null auto_increment primary key,
    nome varchar(60) not null);
    
create table genero(
	id int not null auto_increment primary key,
    nome varchar(30) not null);
    
create table albuns(
	id int not null auto_increment primary key,
    nome varchar(50),
    ano year not null,
    artista_id int,
    genero_id int,	
    foreign key (artista_id) references artista(id),
    foreign key (genero_id) references genero(id),
    imagem varchar(255));
    
create table musica(
	id int not null auto_increment primary key,
    nome varchar(40) not null,
    album_id int,
    musica varchar (255),
    foreign key (album_id) references albuns(id));
    
insert into artista(nome)
values ('Linkin Park'),
	   ('Iron Maiden'),
       ('Kiss'),
       ('Britney Spears'),
       ('Rihanna'),
       ('Tim Maia'),
       ('Tom Jobim'),
       ('Dani Wilde'),
       ('Sad Hours'),
       ('Xitãozinho e Xororó'),
       ('Henrique e Juliano'),
       ('Luiz Gonzaga'),
       ('Calcinha Preta'),
       ('Luciano Pavarotti'),
       ('Yunk Vino'),
       ('Travis Scott'),
       ('Alok'),
       ('David Guetta'),
       ('Anitta'),
       ('MC marcinho'),
       ('Péricles'),
       ('Bob Marley'),
       ('BTS'),
       ('Coldplay e BTS'),
       ('Jessé Aguiar');
       
       

insert into genero(nome)
values ('Rock'),
		('Pop'),
		('Hip-Hop'),
		('Clássica'),
		('Jazz/Blues'),
		('Eletrônica'),
		('MPB'),
		('Sertanejo'),
		('Kpop'),
		('Funk'),
		('Forró'),
		('Pagode'),
		('Regaee'),
		('Gospel');



insert into albuns(nome, ano, artista_id, genero_id, imagem)
values('Hybrid Theory', 2000, 1, 1, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Linkin Park.jpg'),
	  ('Fear of the dark', 1992, 2, 1,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Iron Maiden.jpg'),
      ('Dynasty', 1979, 3, 1,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Kiss.jpg'),
      ('In the Zone', 2003, 4, 2,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Britney Spears.jpg'),
      ('Unapologetic', 2012, 5, 2,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Rihanna.jpg'),
      ('237', 2020, 15, 3,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Leans'),
      ('Astroworld', 2018, 16, 3,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Travis Scott.jpg'),
      ('Pavarotti & Friends 2', 1995, 14, 4, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Pavarotti.jpg'),
      ('Live at Brighton Road', 2017, 8, 5,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Dani Wilde.jpg'),
	  ('Boss Blues Harmonica', 1972, 9, 5, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Little Walter.jpg'),
	  ('Fuego', 2017, 17, 6,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Alok.jpg'),
      ('Nothing but the Beat', 2011, 18, 6,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/David Guetta.jpg'),
      ('Garota de Impanema', 1962, 13, 7,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Tom Jobim.jpg'),
      ('O Descobridor dos Sete Mares', 1983, 6, 7,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Tim Maia.png'),
      ('Cowboy do Asfalto', 1990, 10, 8,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Evidências.jpg'),
      ('O Céu Explica Tudo', 2017, 11, 8,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Henrique e Juliano.jpg'),
      ('Be', 2020, 23, 9,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Dynamite.jpg'),
      ('Music of the Spheres', 2021, 24, 9,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Coldplay, BTS.jpg'),
      ('Anitta', 2013, 19, 10,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Anitta.jpg'),
      ('Tudo é Festa',2021, 20, 10,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/MC Marcinho.jpg'), 
	  ('Asa Branca',1947,12, 11, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Luiz Gonzaga.jpg'),
	  ('Sou Seu Amor, Vol. 6',1999,13, 11,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Calcinha Preta.jpg'),
      ('Em Sua Direção, Vol. 6',1918,21, 12,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Péricles - Até que durou.jpg'),
      ('Feito pra durar',2015,21, 12,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Péricles - Feito para durar.jpg'),
      ('Natty Dread', 1974, 22, 13,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Bob Marley - No Women, No Cry.jpg'),
      ('The Wailing Wailers', 1965, 22, 13,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Bob Marley - One Love.jpg'),
      ('Alívio', 2021, 25, 14,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/imagens/Jessé.jpg');
      


insert into musica (nome, album_id, musica)
values  ('Given Up', 1, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Linkin Park - Given Up.mp3'),
	    ('Fear of the dark', 2, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Iron Maiden - Fear Of The Dark.mp3'),
        ('I was made for loving you',3, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Kiss - I Was Made For Lovin\' You.mp3'),
        ('PT 2- Yunk vino',6, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Yunk Vino - Leans Pt. 2.mp3'),
        ('Sicko mode',7, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Travis Scott - SICKO MODE.mp3'),
        ('Toxic',4, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Britney Spears - Toxic.mp3'),
        ('Diamonds',5, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Rihanna - Diamonds.mp3'),
        ('O sole mio',8, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Luciano Pavarotti - O Sole Mio.mp3'),
        ('Bumble bee', 9,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Dani Wilde - Bumble Bee.mp3'),
        ('Little walter', 10,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Little Walter - Sad Hours.mp3'),
        ('Fuego',11, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Alok & Bhaskar - FUEGO.mp3'),
        ('Titanium',12, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/David Guetta - Titanium ft. Sia.mp3'),
        ('Garota de Ipanema',13, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Tom Jobim - Garota De Ipanema.mp3'),
        ('Descobridor dos sete mares',14, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Tim Maia - O Descobridor Dos Sete Mares.mp3'),
        ('Evidências', 15,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Chitãozinho & Xororó - Evidências.mp3'),
        ('Aquela pessoa',16, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Henrique e Juliano - AQUELA PESSOA.mp3'),
        ('Dynamite', 17,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/BTS - Dynamite.mp3'),
        ('My universe',18, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Coldplay X BTS - My Universe.mp3'),
        ('Show das poderosas',19, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Anitta - Show das Poderosas.mp3'),
        ('Glamurosa', 20,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Mc Marcinho - Glamurosa.mp3'),
        ('Asa branca', 21,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Luiz Gonzaga - Asa Branca.mp3'),
        ('Cobertor',22, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Calcinha Preta - Cobertor.mp3'),
        ('Até que durou',23, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Péricles - Até Que Durou.mp3'),
        ('Melhor eu ir', 24,'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Péricles - Melhor Eu Ir.mp3'),
        ('No woman no cry',25, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Bob Marley - No Woman, No Cry.mp3'),
        ('One love',26, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Bob Marley - One Love.mp3'),
        ('Alívio',27, 'C:/Users/higor/Desktop/Estudos/SENAC/Programador de Sistemas - (200 Horas)/musicplayer/musicPlayer/musica/Jessé Aguiar - Alívio.mp3');


select * from albuns;
select * from genero;
select * from artista;       
select * from musica;

select musica.nome, albuns.nome, albuns.imagem from artista join albuns join musica on albuns.artista_id = artista.id and musica.album_id = albuns.id where artista.nome = 'Linkin Park' order by musica.nome;