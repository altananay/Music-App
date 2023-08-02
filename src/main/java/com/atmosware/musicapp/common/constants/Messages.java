package com.atmosware.musicapp.common.constants;

public class Messages {
    public static class Admin
    {
        public static final String NotExists = "Admin bulunamadı";
    }

    public static class Album
    {
        public static final String NotExists = "Album bulunamadı";
    }

    public static class ArtistAlbum
    {
        public static final String NotExists = "Sanatçının albumu bulunamadı";
    }

    public static class Artist
    {
        public static final String NotExists = "Sanatçı bulunamadı";
    }

    public static class ArtistSong
    {
        public static final String NotExists = "Sanatçının şarkıları bulunamadı";
        public static final String NotExistsBySongId = "Muzigin sanatçı bilgisi yok";
    }

    public static class PopularSong
    {
        public static final String NotExists = "Popular muzik bulunamadı";
    }

    public static class Song
    {
        public static final String NotExists = "Muzik bulunamadı";
    }

    public static class SongStyle
    {
        public static final String NotExists = "Muzik tarzı bulunamadı";
    }

    public static class Style
    {
        public static final String NotExists = "Müzik tarzı bulunamadı";
    }

    public static class User
    {
        public static final String NotExists = "Kullanıcı bulunamadı";
    }

    public static class UserFavoriteSong
    {
        public static final String NotExists = "Kullanıcının favori muzikleri bulunamadı";
    }

    public static class UserFollower
    {
        public static final String NotExists = "Kullanıcının takipçileri bulunamadı";
        public static final String UsersNotFollowingEachOther = "Kullanıcılar birbirlerini takip etmiyor.";
    }

    public static class AlbumSong
    {
        public static final String NotExists = "Albume ait şarkılar bulunamadı";
    }

    public static class JwtPayload
    {
        public static final String Roles = "roles";
        public static final String Email = "email";
    }

    public static class Authentication
    {
        public static final String RegisterSuccessful = "Başarıyla kayıt oldunuz.";
        public static final String AuthSuccessful = "Giriş başarılı";
    }

    public static class JwtRequest
    {
        public static final String RequestHeader = "Authorization";
        public static final String TokenPrefix = "Bearer ";
        public static final String RolePrefix = "ROLE_";
    }
}